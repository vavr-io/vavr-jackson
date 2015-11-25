/**
 * Copyright 2015 The Javaslang Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javaslang.collection.HashMap;
import javaslang.collection.LinkedHashMap;
import javaslang.collection.Map;
import javaslang.collection.TreeMap;

import java.io.IOException;
import java.util.Comparator;

class MapDeserializer extends StdDeserializer<Map<?,?>> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    private JavaType keyType;
    private KeyDeserializer keyDeserializer;
    private JavaType valueType;
    private JsonDeserializer<?> valueDeserializer;

    MapDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        MapLikeType type = mapLike(javaType, ctxt);
        keyType = type.getKeyType();
        keyDeserializer = ctxt.findKeyDeserializer(keyType, null);
        valueType = type.getContentType();
        valueDeserializer = ctxt.findContextualValueDeserializer(valueType, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<?,?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final java.util.Map<Object, Object> result = new java.util.HashMap<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.getCurrentName();
            Object key = keyDeserializer.deserializeKey(name, ctxt);
            p.nextToken();
            result.put(key, valueDeserializer.deserialize(p, ctxt));
        }
        if (TreeMap.class.isAssignableFrom(javaType.getRawClass())) {
            Comparator<Object> c;
            if(keyType.getRawClass().isAssignableFrom(Comparable.class)) {
                c = (o1, o2) -> ((Comparable)o1).compareTo(o2);
            } else {
                c = (o1, o2) -> o1.toString().compareTo(o2.toString());
            }
            return fill(TreeMap.empty(c), result);
        }
        if (LinkedHashMap.class.isAssignableFrom(javaType.getRawClass())) {
            return fill(LinkedHashMap.empty(), result);
        }
        // default deserialization [...] -> Map
        return fill(HashMap.empty(), result);
    }

    private static MapLikeType mapLike(JavaType type, DeserializationContext ctxt) {
        JavaType keyType = type.containedTypeCount() > 0 ? type.containedType(0) : TypeFactory.unknownType();
        JavaType valueType = type.containedTypeCount() > 1 ? type.containedType(1) : TypeFactory.unknownType();
        return ctxt.getTypeFactory().constructMapLikeType(type.getRawClass(), keyType, valueType);
    }

    private static javaslang.collection.Map<Object, Object> fill(javaslang.collection.Map<Object, Object> result, java.util.Map<Object, Object> content) {
        for (java.util.Map.Entry<Object, Object> e : content.entrySet()) {
            result = result.put(e.getKey(), e.getValue());
        }
        return result;
    }
}
