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
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.jsontype.TypeSerializer;
import tools.jackson.databind.ser.std.StdSerializer;

abstract class HListSerializer<T> extends StdSerializer<T> {

    private final JavaType type;

    HListSerializer(JavaType type) {
        super(type);
        this.type = type;
    }

    void write(Object val, int containedTypeIndex, JsonGenerator gen, SerializationContext context) {
        if (val != null) {
            if (type.containedTypeCount() > containedTypeIndex) {
                ValueSerializer<Object> ser;
                JavaType containedType = type.containedType(containedTypeIndex);
                if (containedType != null && containedType.hasGenericTypes()) {
                    JavaType st = context.constructSpecializedType(containedType, val.getClass());
                    ser = context.findTypedValueSerializer(st, true);
                } else {
                    ser = context.findTypedValueSerializer(val.getClass(), true);
                }
                ser.serialize(val, gen, context);
            } else {
                gen.writePOJO(val);
            }
        } else {
            gen.writeNull();
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
