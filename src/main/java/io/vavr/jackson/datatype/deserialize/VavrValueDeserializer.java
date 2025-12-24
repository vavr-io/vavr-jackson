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

import java.util.ArrayList;
import java.util.List;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;

abstract class VavrValueDeserializer<T> extends StdDeserializer<T> {

    private final JavaType javaType;
    private final int typeCount;
    private final List<ValueDeserializer<?>> deserializers;

    VavrValueDeserializer(JavaType valueType, int typeCount) {
        super(valueType);
        this.javaType = valueType;
        this.typeCount = typeCount;
        this.deserializers = new ArrayList<>(typeCount);
    }

    int deserializersCount() {
        return deserializers.size();
    }

    ValueDeserializer<Object> deserializer(int index) {
        return (ValueDeserializer<Object>) deserializers.get(index);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws DatabindException {
        // TODO rewrite this
        if (javaType.isCollectionLikeType() || javaType.isReferenceType()) {
            deserializers.add(ctxt.findRootValueDeserializer(javaType.getContentType()));
            return;
        }
        for (int i = 0; i < typeCount; i++) {
            JavaType containedType = javaType.containedTypeOrUnknown(i);
            deserializers.add(ctxt.findRootValueDeserializer(containedType));
        }
    }

    // DEV-NOTE: original method is deprecated since 2.8
    static DatabindException mappingException(DeserializationContext ctxt, Class<?> targetClass, JsonToken token) {
        String tokenDesc = (token == null) ? "<end of input>" : String.format("%s token", token);
        return DatabindException.from(ctxt.getParser(),
            String.format("Can not deserialize instance of %s out of %s",
                _calcName(targetClass), tokenDesc));
    }

    private static String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return _calcName(cls.getComponentType()) + "[]";
        }
        return cls.getName();
    }
}
