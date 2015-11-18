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
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import javaslang.Tuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TupleDeserializer extends BaseDeserializer<Tuple> implements ContextualDeserializer {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;
    private final JavaType[] subTypes;

    TupleDeserializer(JavaType javaType) {
        this(javaType, new JavaType[0]);
    }

    TupleDeserializer(JavaType valueType, JavaType[] subTypes) {
        super(valueType);
        this.javaType = valueType;
        this.subTypes = subTypes;
    }

    @Override
    public Tuple deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Object> list = new ArrayList<>();
        for(int index = 0; p.nextToken() != JsonToken.END_ARRAY; index++) {
            list.add(_deserialize(p, getSubType(index), ctxt));
        }
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
        if(!result.getClass().isAssignableFrom(javaType.getRawClass())) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
        return result;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        int genericsNum = javaType.containedTypeCount();
        JavaType[] subTypes = new JavaType[genericsNum];
        for (int i = 0; i < genericsNum; i++) {
            subTypes[i] = javaType.containedType(i);
        }
        return new TupleDeserializer(javaType, subTypes);
    }

    private JavaType getSubType(int index) {
        return subTypes.length <= index ? null : subTypes[index];
    }
}
