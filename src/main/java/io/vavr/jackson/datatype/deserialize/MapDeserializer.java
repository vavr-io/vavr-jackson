/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2017 Vavr, http://vavr.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.*;

import java.io.IOException;

class MapDeserializer extends MaplikeDeserializer<Map<?, ?>> {

    private static final long serialVersionUID = 1L;

    MapDeserializer(MapLikeType mapType, KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        super(mapType, keyDeserializer, valueDeserializer);
    }

    MapDeserializer(MapDeserializer origin, KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        super(origin.mapType, origin.keyComparator, keyDeserializer, valueDeserializer);
    }

    @Override
    MaplikeDeserializer<Map<?, ?>> createDeserializer(KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        return new MapDeserializer(this, keyDeserializer, valueDeserializer);
    }

    @Override
    public Map<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final java.util.List<Tuple2<Object, Object>> result = new java.util.ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.getCurrentName();
            Object key = keyDeserializer.deserializeKey(name, ctxt);
            JsonToken t = p.nextToken();
            // Note: must handle null explicitly here; value deserializers won't
            Object value;
            if (t == JsonToken.VALUE_NULL) {
                value = valueDeserializer.getNullValue(ctxt);
            } else {
                value = valueDeserializer.deserialize(p, ctxt);
            }
            result.add(Tuple.of(key, value));
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
