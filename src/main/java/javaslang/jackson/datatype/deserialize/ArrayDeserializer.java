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
import javaslang.collection.Seq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

abstract class ArrayDeserializer<T> extends ValueDeserializer<T> {

    private static final long serialVersionUID = 1L;

    private final boolean deserializeNullAsEmptyCollection;

    ArrayDeserializer(JavaType valueType, int typeCount, boolean deserializeNullAsEmptyCollection) {
        super(valueType, typeCount);
        this.deserializeNullAsEmptyCollection = deserializeNullAsEmptyCollection;
    }

    abstract T create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<?> deserializer = deserializer(0);
        List<Object> list = new ArrayList<>();
        for (JsonToken jsonToken = p.nextToken(); jsonToken != END_ARRAY; jsonToken = p.nextToken()) {
            Object value = (jsonToken != VALUE_NULL) ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
            list.add(value);
        }
        return create(list, ctxt);
    }

    @Override
    public T getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        if (deserializeNullAsEmptyCollection) {
            return create(Collections.emptyList(), ctxt);
        }
        return super.getNullValue(ctxt);
    }
}
