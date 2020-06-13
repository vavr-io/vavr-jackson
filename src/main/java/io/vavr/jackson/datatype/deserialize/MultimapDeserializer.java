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
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.MapLikeType;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.collection.TreeMultimap;

import java.io.IOException;
import java.util.ArrayList;

class MultimapDeserializer extends MaplikeDeserializer<Multimap<?, ?>> {

    private static final long serialVersionUID = 1L;

    private JsonDeserializer<?> containerDeserializer;

    MultimapDeserializer(MapLikeType mapType, KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        super(mapType, keyDeserializer, valueDeserializer);
    }

    MultimapDeserializer(MultimapDeserializer origin, KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        super(origin.mapType, origin.keyComparator, keyDeserializer, valueDeserializer);
        containerDeserializer = origin.containerDeserializer;
    }

    @Override
    MultimapDeserializer createDeserializer(KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        return new MultimapDeserializer(this, keyDeserializer, valueDeserializer);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        super.resolve(ctxt);
        JavaType containerType = ctxt.getTypeFactory().constructCollectionType(ArrayList.class, mapType.getContentType());
        containerDeserializer = ctxt.findContextualValueDeserializer(containerType, null);
    }

    @Override
    public Multimap<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final java.util.List<Tuple2<Object, Object>> result = new java.util.ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.getCurrentName();
            Object key = keyDeserializer.deserializeKey(name, ctxt);
            p.nextToken();
            ArrayList<?> list = (ArrayList<?>) containerDeserializer.deserialize(p, ctxt);
            for (Object elem : list) {
                result.add(Tuple.of(key, elem));
            }
        }
        if (TreeMultimap.class.isAssignableFrom(handledType())) {
            return TreeMultimap.withSeq().ofEntries(keyComparator, result);
        }
        if (LinkedHashMultimap.class.isAssignableFrom(handledType())) {
            return LinkedHashMultimap.withSeq().ofEntries(result);
        }
        // default deserialization [...] -> Map
        return HashMultimap.withSeq().ofEntries(result);
    }
}
