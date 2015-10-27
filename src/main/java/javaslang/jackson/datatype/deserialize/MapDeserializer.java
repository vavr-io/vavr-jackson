package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.HashMap;
import javaslang.collection.Map;
import javaslang.collection.TreeMap;

import java.io.IOException;

abstract class MapDeserializer extends StdDeserializer<Map<?,?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    MapDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    abstract Map<?, ?> it(java.util.Map<?, ?> elements);

    @Override
    public Map<?,?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            final BaseDeserializer deserializer = new BaseDeserializer(ctxt);
            return it((java.util.Map<?,?>) deserializer.deserialize(p, javaType));
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(this.getClass());
        }
    }

    static class AsHashMap extends MapDeserializer {

        private static final long serialVersionUID = 1L;

        AsHashMap(JavaType valueType) {
            super(valueType);
        }

        @Override
        Map<?, ?> it(java.util.Map<?, ?> elements) {
            Map<Object, Object> result = HashMap.empty();
            for (java.util.Map.Entry<?, ?> e : elements.entrySet()) {
                result = result.put(e.getKey(), e.getValue());
            }
            return result;
        }
    }

    static class AsTreeMap extends MapDeserializer {

        private static final long serialVersionUID = 1L;

        AsTreeMap(JavaType valueType) {
            super(valueType);
        }

        @Override
        Map<?, ?> it(java.util.Map<?, ?> elements) {
            Map<Object, Object> result = TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString()));
            for (java.util.Map.Entry<?, ?> e : elements.entrySet()) {
                result = result.put(e.getKey(), e.getValue());
            }
            return result;
        }
    }
}
