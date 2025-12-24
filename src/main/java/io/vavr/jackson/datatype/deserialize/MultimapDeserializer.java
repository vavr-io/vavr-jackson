/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2025 Vavr, http://vavr.io
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

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.KeyDeserializer;
import tools.jackson.databind.jsontype.TypeDeserializer;
import tools.jackson.databind.type.MapLikeType;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.collection.TreeMultimap;

import java.util.ArrayList;

class MultimapDeserializer extends MaplikeDeserializer<Multimap<?, ?>> {

    private ValueDeserializer<?> containerDeserializer;

    MultimapDeserializer(MapLikeType mapType, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer,
                         ValueDeserializer<?> elementDeserializer) {
        super(mapType, keyDeserializer, elementTypeDeserializer, elementDeserializer);
    }

    MultimapDeserializer(MultimapDeserializer origin, KeyDeserializer keyDeserializer,
                         TypeDeserializer elementTypeDeserializer, ValueDeserializer<?> elementDeserializer) {
        super(origin.mapType, keyDeserializer, elementTypeDeserializer, elementDeserializer);
        containerDeserializer = origin.containerDeserializer;
    }

    @Override
    MultimapDeserializer createDeserializer(KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer,
                                            ValueDeserializer<?> elementDeserializer) {
        return new MultimapDeserializer(this, keyDeserializer, elementTypeDeserializer, elementDeserializer);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws DatabindException {
        JavaType containerType = ctxt.getTypeFactory()
            .constructCollectionType(ArrayList.class, mapType.getContentType());
        containerDeserializer = ctxt.findContextualValueDeserializer(containerType, null);
    }

    @Override
    public Multimap<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) {
        final java.util.List<Tuple2<Object, Object>> result = new java.util.ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.currentName();
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
