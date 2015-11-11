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
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.Tuple;

import java.io.IOException;
import java.util.List;

class TupleDeserializer extends BaseDeserializer<Tuple> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    TupleDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @Override
    public Tuple deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<?> list = (List<?>) _deserialize(p, ctxt.constructType(List.class), ctxt);
        switch (list.size()) {
            case 0:
                return Tuple.empty();
            case 1:
                return Tuple.of(list.get(0));
            case 2:
                return Tuple.of(list.get(0), list.get(1));
            case 3:
                return Tuple.of(list.get(0), list.get(1), list.get(2));
            case 4:
                return Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3));
            case 5:
                return Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
            case 6:
                return Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
            case 7:
                return Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
            case 8:
                return Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7));
            default:
                throw ctxt.mappingException(javaType.getRawClass());
        }
    }
}
