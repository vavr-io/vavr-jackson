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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class ArrayDeserializer<T> extends ValueDeserializer<T> {

    private static final long serialVersionUID = 1L;

    ArrayDeserializer(JavaType valueType, int typeCount) {
        super(valueType, typeCount);
    }

    abstract T create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<?> deserializer = deserializer(0);
        List<Object> list = new ArrayList<>();
        while (p.nextToken() != JsonToken.END_ARRAY) {
            list.add(deserializer.deserialize(p, ctxt));
        }
        return create(list, ctxt);
    }
}
