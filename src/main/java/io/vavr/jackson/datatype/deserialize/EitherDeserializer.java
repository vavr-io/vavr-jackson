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
import tools.jackson.databind.DatabindException;
import io.vavr.control.Either;
import tools.jackson.databind.ValueDeserializer;

import static tools.jackson.core.JsonToken.END_ARRAY;
import static tools.jackson.core.JsonToken.END_OBJECT;
import static tools.jackson.core.JsonToken.START_ARRAY;
import static tools.jackson.core.JsonToken.START_OBJECT;
import static tools.jackson.core.JsonToken.VALUE_NULL;

class EitherDeserializer extends VavrValueDeserializer<Either<?, ?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;
    private ValueDeserializer<?> stringDeserializer;

    EitherDeserializer(JavaType valueType) {
        super(valueType, 2);
        this.javaType = valueType;
    }

    @Override
    public Either<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) {
        final JsonToken nextToken = p.currentToken();

        if (nextToken == START_ARRAY) {
            boolean right = false;
            Object value = null;
            int cnt = 0;

            for (JsonToken jsonToken = p.nextToken(); jsonToken != END_ARRAY; jsonToken = p.nextToken()) {
                cnt++;
                switch (cnt) {
                    case 1:
                        String def = (String) stringDeserializer.deserialize(p, ctxt);
                        if (isRight(def)) {
                            right = true;
                        } else if (isLeft(def)) {
                            right = false;
                        } else {
                            throw mappingException(ctxt, javaType.getRawClass(), jsonToken);
                        }
                        break;
                    case 2:
                        ValueDeserializer<?> deserializer = right ? deserializer(1) : deserializer(0);
                        value = (jsonToken != VALUE_NULL) ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
                        break;
                }
            }
            if (cnt != 2) {
                throw mappingException(ctxt, javaType.getRawClass(), null);
            }
            return right ? Either.right(value) : Either.left(value);
        } else if (nextToken == START_OBJECT) {
            final JsonToken currentToken = p.currentToken();
            final String type = p.nextName();
            if (isRight(type)) {
                return Either.right(getValue(p, ctxt, 1));
            } else if (isLeft(type)) {
                return Either.left(getValue(p, ctxt, 0));
            } else {
                throw mappingException(ctxt, javaType.getRawClass(), currentToken);
            }
        } else {
            throw mappingException(ctxt, javaType.getRawClass(), p.currentToken());
        }
    }



    @Override
    public void resolve(DeserializationContext ctxt) throws DatabindException {
        super.resolve(ctxt);
        stringDeserializer = ctxt.findContextualValueDeserializer(ctxt.constructType(String.class), null);
    }

    private static boolean isRight(final String fieldName) {
        return "right".equals(fieldName) || "r".equals(fieldName);
    }

    private static boolean isLeft(final String fieldName) {
        return "left".equals(fieldName) || "l".equals(fieldName);
    }

    private Object getValue(JsonParser p, DeserializationContext ctxt, int index) {
        final ValueDeserializer<?> deserializer = deserializer(index);
        final Object value = p.nextToken() != VALUE_NULL ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
        if (p.nextToken() != END_OBJECT) {
            throw mappingException(ctxt, javaType.getRawClass(), p.currentToken());
        }
        return value;
    }
}
