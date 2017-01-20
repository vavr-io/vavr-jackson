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
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.CollectionType;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.HashMultimap;
import javaslang.collection.LinkedHashMultimap;
import javaslang.collection.Multimap;
import javaslang.collection.TreeMultimap;

import java.io.IOException;
import java.util.ArrayList;

class MultimapDeserializer extends MaplikeDeserializer<Multimap<?, ?>> {

    private static final long serialVersionUID = 1L;

    private JsonDeserializer<?> containerDeserializer;

    MultimapDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        super.resolve(ctxt);
        JavaType containerType = ctxt.getTypeFactory().constructCollectionType(ArrayList.class, javaType.getContentType());
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
