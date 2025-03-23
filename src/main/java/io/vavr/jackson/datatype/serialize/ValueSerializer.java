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
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

abstract class ValueSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    final JavaType type;
    final BeanProperty beanProperty;

    ValueSerializer(JavaType type) {
        this(type, null);
    }

    ValueSerializer(JavaType type, BeanProperty property) {
        super(type);
        this.type = type;
        this.beanProperty = property;
    }

    abstract Object toJavaObj(T value) throws IOException;

    abstract JavaType emulatedJavaType(TypeFactory typeFactory);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Object obj = toJavaObj(value);
        if (obj == null) {
            provider.getDefaultNullValueSerializer().serialize(null, gen, provider);
        } else {
            JsonSerializer<Object> ser;
            try {
                JavaType emulated = emulatedJavaType(provider.getTypeFactory());
                if (emulated.getRawClass() != Object.class) {
                    ser = provider.findTypedValueSerializer(emulated, true, beanProperty);
                } else {
                    ser = provider.findTypedValueSerializer(obj.getClass(), true, beanProperty);
                }
            } catch (Exception ignore) {
                ser = provider.findTypedValueSerializer(obj.getClass(), true, beanProperty);
            }
            ser.serialize(obj, gen, provider);
        }
    }

    @Override
    public void serializeWithType(T value, JsonGenerator gen, SerializerProvider serializers,
                                  TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefix(gen, typeSer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, gen, serializers);
        typeSer.writeTypeSuffix(gen, typeSer.typeId(value, JsonToken.VALUE_STRING));
    }
}
