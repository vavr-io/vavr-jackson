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

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.jsontype.TypeSerializer;
import tools.jackson.databind.ser.std.StdSerializer;
import tools.jackson.databind.type.TypeFactory;

abstract class VavrValueSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    final JavaType type;
    final BeanProperty beanProperty;

    VavrValueSerializer(JavaType type) {
        this(type, null);
    }

    VavrValueSerializer(JavaType type, BeanProperty property) {
        super(type);
        this.type = type;
        this.beanProperty = property;
    }

    abstract Object toJavaObj(T value);

    abstract JavaType emulatedJavaType(TypeFactory typeFactory);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializationContext context) {
        Object obj = toJavaObj(value);
        if (obj == null) {
            context.getDefaultNullValueSerializer().serialize(null, gen, context);
        } else {
            ValueSerializer<Object> ser;
            try {
                JavaType emulated = emulatedJavaType(context.getTypeFactory());
                if (emulated.getRawClass() != Object.class) {
                    ser = context.findPrimaryPropertySerializer(emulated, beanProperty);
                } else {
                    ser = context.findPrimaryPropertySerializer(obj.getClass(), beanProperty);
                }
            } catch (Exception ignore) {
                ser = context.findPrimaryPropertySerializer(obj.getClass(), beanProperty);
            }
            ser.serialize(obj, gen, context);
        }
    }

    @Override
    public void serializeWithType(T value, JsonGenerator gen, SerializationContext context,
                                  TypeSerializer typeSer) {
        typeSer.writeTypePrefix(gen, context, typeSer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, gen, context);
        typeSer.writeTypeSuffix(gen, context, typeSer.typeId(value, JsonToken.VALUE_STRING));
    }
}
