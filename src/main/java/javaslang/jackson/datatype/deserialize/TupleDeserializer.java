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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TupleDeserializer extends ValueDeserializer<Tuple> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    TupleDeserializer(JavaType valueType) {
        super(valueType, arity(valueType));
        this.javaType = valueType;
    }

    @Override
    public Tuple deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<Object> list = new ArrayList<>();
        int ptr = 0;
        while (p.nextToken() != JsonToken.END_ARRAY) {
            if (ptr >= deserializersCount()) {
                throw ctxt.mappingException(javaType.getRawClass());
            }
            list.add(deserializer(ptr++).deserialize(p, ctxt));
        }
        return create(list, ctxt);
    }

    private Tuple create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException {
        final Tuple result;
        switch (list.size()) {
            case 0:
                result = Tuple.empty();
                break;
            case 1:
                result = Tuple.of(list.get(0));
                break;
            case 2:
                result = Tuple.of(list.get(0), list.get(1));
                break;
            case 3:
                result = Tuple.of(list.get(0), list.get(1), list.get(2));
                break;
            case 4:
                result = Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3));
                break;
            case 5:
                result = Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
                break;
            case 6:
                result = Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
                break;
            case 7:
                result = Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
                break;
            case 8:
                result = Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7));
                break;
            default:
                throw ctxt.mappingException(javaType.getRawClass());
        }
        if(!javaType.getRawClass().isAssignableFrom(result.getClass())) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
        return result;
    }

    private static int arity(JavaType valueType) {
        Class<?> clz = valueType.getRawClass();
        if (clz == Tuple0.class) {
            return 0;
        } else if (clz == Tuple1.class) {
            return 1;
        } else if (clz == Tuple2.class) {
            return 2;
        } else if (clz == Tuple3.class) {
            return 3;
        } else if (clz == Tuple4.class) {
            return 4;
        } else if (clz == Tuple5.class) {
            return 5;
        } else if (clz == Tuple6.class) {
            return 6;
        } else if (clz == Tuple7.class) {
            return 7;
        } else {
            return 8;
        }
    }
}
