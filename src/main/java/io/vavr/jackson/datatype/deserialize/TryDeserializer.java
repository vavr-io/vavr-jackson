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

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.vavr.control.Try;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TryDeserializer extends StdDeserializer<Try<?>> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    private final JavaType                  javaType;
    private       JsonDeserializer<?>       stringDeserializer;
    private final List<JsonDeserializer<?>> deserializers;

    TryDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
        this.deserializers = new ArrayList<>(2);
    }

    JsonDeserializer<?> deserializer(int index) {
        return deserializers.get(index);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        stringDeserializer = ctxt.findContextualValueDeserializer(ctxt.constructType(String.class), null);

        if (javaType.isCollectionLikeType() || javaType.isReferenceType()) {
            deserializers.add(ctxt.findRootValueDeserializer(javaType.getContentType()));
            return;
        }

        JavaType containedType = javaType.containedTypeOrUnknown(0);
        deserializers.add(ctxt.findRootValueDeserializer(containedType));
        deserializers.add(ctxt.findRootValueDeserializer(ctxt.constructType(Throwable.class)));
    }

    @Override
    public Try<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        boolean success = false;
        Object value = null;
        int cnt = 0;

        for (JsonToken jsonToken = p.nextToken(); jsonToken != END_ARRAY; jsonToken = p.nextToken()) {
            cnt++;
            switch (cnt) {
                case 1:
                    String def = (String) stringDeserializer.deserialize(p, ctxt);
                    if (isSuccess(def)) {
                        success = true;
                    } else if (isFailure(def)) {
                        success = false;
                    } else {
                        throw mappingException(ctxt, javaType.getRawClass(), jsonToken);
                    }
                    break;
                case 2:
                    JsonDeserializer<?> deserializer = success ? deserializer(0) : deserializer(1);
                    value = (jsonToken != VALUE_NULL) ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
                    break;
            }
        }
        if (cnt != 2) {
            throw mappingException(ctxt, javaType.getRawClass(), null);
        }
        return success ? Try.success(value) : Try.failure((Throwable) value);
    }

    private static boolean isSuccess(final String fieldName) {
        return "success".equals(fieldName) || "s".equals(fieldName);
    }

    private static boolean isFailure(final String fieldName) {
        return "failure".equals(fieldName) || "f".equals(fieldName);
    }

    static JsonMappingException mappingException(DeserializationContext ctxt, Class<?> targetClass, JsonToken token) {
        String tokenDesc = (token == null) ? "<end of input>" : String.format("%s token", token);
        return JsonMappingException.from(ctxt.getParser(),
                                         String.format("Can not deserialize instance of %s out of %s",
                                                       _calcName(targetClass), tokenDesc));
    }

    private static String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return _calcName(cls.getComponentType())+"[]";
        }
        return cls.getName();
    }

}
