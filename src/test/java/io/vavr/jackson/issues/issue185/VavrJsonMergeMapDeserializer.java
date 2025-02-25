package io.vavr.jackson.issues.issue185;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;

/**
 * A generic deserializer for VAVR Maps that supports JSON merge.
 * Enhanced to handle both simple maps and nested maps through generic type detection.
 * 
 * @param <T> The type of the parent/container object
 * @param <K> The type of keys in the map
 * @param <V> The type of values in the map
 */
public class VavrJsonMergeMapDeserializer<T, K, V> extends JsonDeserializer<Map<K, V>> {
    
    private final Class<T> parentClass;
    private final Class<K> keyClass;
    private final Class<V> valueClass;
    private final Function<T, Map<K, V>> mapGetter;
    
    /**
     * Creates a new VAVRMapDeserializer.
     * 
     * @param parentClass The class of the parent/container object
     * @param keyClass The class of the keys in the map
     * @param valueClass The class of the values in the map
     * @param mapGetter Function to extract the current map from the parent object
     * 
     */
    public VavrJsonMergeMapDeserializer(
        Class<T> parentClass, Class<K> keyClass, Class<V> valueClass, 
        Function<T, Map<K, V>> mapGetter) {
        this.parentClass = parentClass;
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.mapGetter = mapGetter;
    }
    
    @Override
    public Map<K, V> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        // Get current map from the parent instance
        Map<K, V> currentMap = HashMap.empty();
        Object currentValue = p.currentValue();
        
        if (parentClass.isInstance(currentValue)) {
            @SuppressWarnings("unchecked")
            T parent = (T) currentValue;
            currentMap = mapGetter.apply(parent);
            if (currentMap == null) {
                currentMap = HashMap.empty();
            }
        }
        
        // Create a mutable map to store all entries
        java.util.Map<K, V> mergedMap = new java.util.HashMap<>();
        
        // Add all entries from current map
        currentMap.forEach(mergedMap::put);
        
        // Add or update entries from the incoming JSON
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode valueNode = node.get(fieldName);
                
                // Convert the key from String to K
                K key = convertToType(mapper, fieldName, keyClass);
                
                // Convert the value from JsonNode to V, handling nested maps
                V value = convertNodeToType(mapper, valueNode, valueClass, mergedMap.get(key));
                
                mergedMap.put(key, value);
            }
        }
        
        // Convert back to VAVR Map
        return HashMap.ofAll(mergedMap);
    }
    
    /**
     * Converts a String to the target type
     */
    @SuppressWarnings("unchecked")
    private <X> X convertToType(ObjectMapper mapper, String value, Class<X> targetClass) {
        if (targetClass == String.class) {
            return (X) value;
        } else if (targetClass == Integer.class) {
            return (X) Integer.valueOf(value);
        } else if (targetClass == Long.class) {
            return (X) Long.valueOf(value);
        } else if (targetClass == Double.class) {
            return (X) Double.valueOf(value);
        } else if (targetClass == Boolean.class) {
            return (X) Boolean.valueOf(value);
        } else {
            try {
                return mapper.readValue("\"" + value + "\"", targetClass);
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert " + value + " to " + targetClass.getName(), e);
            }
        }
    }
    
    /**
     * Converts a JsonNode to the target type, with special handling for nested maps
     * 
     * @param mapper ObjectMapper to use for conversion
     * @param node JsonNode to convert
     * @param targetClass Target class
     * @param existingValue The existing value (if any) to merge with
     * @return Converted value
     */
    @SuppressWarnings("unchecked")
    private <X> X convertNodeToType(ObjectMapper mapper, JsonNode node, Class<X> targetClass, Object existingValue) {
        try {
            // Handle primitive types directly
            if (node.isTextual() && targetClass == String.class) {
                return (X) node.asText();
            } else if (node.isNumber()) {
                if (targetClass == Integer.class) {
                    return (X) Integer.valueOf(node.asInt());
                } else if (targetClass == Long.class) {
                    return (X) Long.valueOf(node.asLong());
                } else if (targetClass == Double.class) {
                    return (X) Double.valueOf(node.asDouble());
                }
            } else if (node.isBoolean() && targetClass == Boolean.class) {
                return (X) Boolean.valueOf(node.asBoolean());
            }
            
            // Special handling for nested maps
            if (node.isObject() && Map.class.isAssignableFrom(targetClass)) {
                // Get the existing nested map if available
                Map<String, Object> existingNestedMap = null;
                if (existingValue instanceof Map) {
                    existingNestedMap = (Map<String, Object>) existingValue;
                }
                
                // This is a nested map case - need to convert each entry recursively
                java.util.Map<String, Object> nestedMap = new java.util.HashMap<>();
                
                // First, add all existing entries to preserve them
                if (existingNestedMap != null) {
                    existingNestedMap.forEach(nestedMap::put);
                }
                
                // Process each field in the nested object
                Iterator<String> fieldNames = node.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    JsonNode nestedValueNode = node.get(fieldName);
                    
                    // Get existing nested value if available (for deep nested maps)
                    Object existingNestedValue = null;
                    if (existingNestedMap != null) {
                        existingNestedValue = existingNestedMap.get(fieldName).getOrNull();
                    }
                    
                    // For nested maps, we assume String keys and process values according to their JSON type
                    Object nestedValue;
                    if (nestedValueNode.isObject()) {
                        // Another level of nesting - handle recursively with merging
                        nestedValue = convertToVavrMap(mapper, nestedValueNode, existingNestedValue);
                    } else if (nestedValueNode.isTextual()) {
                        nestedValue = nestedValueNode.asText();
                    } else if (nestedValueNode.isNumber()) {
                        nestedValue = nestedValueNode.isInt() ? nestedValueNode.asInt() : 
                                     nestedValueNode.isLong() ? nestedValueNode.asLong() : nestedValueNode.asDouble();
                    } else if (nestedValueNode.isBoolean()) {
                        nestedValue = nestedValueNode.asBoolean();
                    } else {
                        // For other types, use Jackson's conversion
                        nestedValue = mapper.treeToValue(nestedValueNode, Object.class);
                    }
                    
                    nestedMap.put(fieldName, nestedValue);
                }
                
                // Convert to VAVR Map
                return (X) HashMap.ofAll(nestedMap);
            }
            
            // Default fallback to Jackson's conversion
            return mapper.convertValue(node, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert node to " + targetClass.getName(), e);
        }
    }
    
    /**
     * Default overload for convertNodeToType without existing value
     */
    private <X> X convertNodeToType(ObjectMapper mapper, JsonNode node, Class<X> targetClass) {
        return convertNodeToType(mapper, node, targetClass, null);
    }
    
    /**
     * Helper method to convert a JSON object node to a VAVR Map with merging support
     */
    private Map<String, Object> convertToVavrMap(ObjectMapper mapper, JsonNode node, Object existingMap) throws IOException {
        java.util.Map<String, Object> javaMap = new java.util.HashMap<>();
        
        // First, add all existing entries from the map to merge with
        if (existingMap instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> existingVavrMap = (Map<String, Object>) existingMap;
            existingVavrMap.forEach(javaMap::put);
        }
        
        // Then add/update entries from the incoming JSON
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();
            JsonNode valueNode = node.get(key);
            
            // Get the existing value for this key if it exists
            Object existingValue = null;
            if (existingMap instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> existingVavrMap = (Map<String, Object>) existingMap;
                existingValue = existingVavrMap.get(key).getOrNull();
            }
            
            Object value;
            if (valueNode.isObject()) {
                // Recursively handle nested objects with merging
                value = convertToVavrMap(mapper, valueNode, existingValue);
            } else if (valueNode.isTextual()) {
                value = valueNode.asText();
            } else if (valueNode.isNumber()) {
                value = valueNode.isInt() ? valueNode.asInt() : 
                       valueNode.isLong() ? valueNode.asLong() : valueNode.asDouble();
            } else if (valueNode.isBoolean()) {
                value = valueNode.asBoolean();
            } else {
                // For other types, use Jackson's conversion
                value = mapper.treeToValue(valueNode, Object.class);
            }
            
            javaMap.put(key, value);
        }
        
        return HashMap.ofAll(javaMap);
    }
    
    /**
     * Overload for convertToVavrMap without existing map
     */
    private Map<String, Object> convertToVavrMap(ObjectMapper mapper, JsonNode node) throws IOException {
        return convertToVavrMap(mapper, node, null);
    }
} 
