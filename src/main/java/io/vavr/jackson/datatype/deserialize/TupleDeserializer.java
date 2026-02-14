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

import io.vavr.Tuple;
import io.vavr.Tuple0;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.Tuple5;
import io.vavr.Tuple6;
import io.vavr.Tuple7;
import java.util.ArrayList;
import java.util.List;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;

import static tools.jackson.core.JsonToken.END_ARRAY;
import static tools.jackson.core.JsonToken.VALUE_NULL;

class TupleDeserializer extends VavrValueDeserializer<Tuple> {

    private final JavaType javaType;

    TupleDeserializer(JavaType valueType) {
        super(valueType, arity(valueType));
        this.javaType = valueType;
    }

    @Override
    public Tuple deserialize(JsonParser p, DeserializationContext ctxt) {
        List<Object> list = new ArrayList<>();
        int ptr = 0;

        for (JsonToken jsonToken = p.nextToken(); jsonToken != END_ARRAY; jsonToken = p.nextToken()) {
            if (ptr >= deserializersCount()) {
                throw mappingException(ctxt, javaType.getRawClass(), jsonToken);
            }
            ValueDeserializer<?> deserializer = deserializer(ptr++);
            Object value = (jsonToken != VALUE_NULL) ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
            list.add(value);
        }
        if (list.size() == deserializersCount()) {
            return create(list);
        } else {
            throw mappingException(ctxt, javaType.getRawClass(), null);
        }
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

    private Tuple create(List<Object> list) {
        return switch (list.size()) {
            case 0 -> Tuple.empty();
            case 1 -> Tuple.of(list.get(0));
            case 2 -> Tuple.of(list.get(0), list.get(1));
            case 3 -> Tuple.of(list.get(0), list.get(1), list.get(2));
            case 4 -> Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3));
            case 5 -> Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
            case 6 -> Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
            case 7 -> Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
            case 8 -> Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7));
            default -> throw new IllegalArgumentException("Unsupported tuple arity: " + list.size());
        };
    }
}
