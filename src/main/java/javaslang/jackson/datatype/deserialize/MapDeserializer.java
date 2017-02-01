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
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.*;

import java.io.IOException;

class MapDeserializer extends MaplikeDeserializer<Map<?, ?>> {

    private static final long serialVersionUID = 1L;

    MapDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public Map<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final java.util.List<Tuple2<Object, Object>> result = new java.util.ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.getCurrentName();
            Object key = keyDeserializer.deserializeKey(name, ctxt);
            p.nextToken();
            result.add(Tuple.of(key, valueDeserializer.deserialize(p, ctxt)));
        }
        if (SortedMap.class.isAssignableFrom(handledType())) {
            return TreeMap.ofEntries(keyComparator, result);
        }
        if (LinkedHashMap.class.isAssignableFrom(handledType())) {
            return LinkedHashMap.ofEntries(result);
        }
        // default deserialization [...] -> Map
        return HashMap.ofEntries(result);
    }
}
