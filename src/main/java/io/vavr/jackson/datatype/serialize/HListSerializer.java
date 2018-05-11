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
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

abstract class HListSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    private final JavaType type;

    HListSerializer(JavaType type) {
        super(type);
        this.type = type;
    }

    void write(Object val, int containedTypeIndex, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (val != null) {
            if (type.containedTypeCount() > containedTypeIndex) {
                JsonSerializer<Object> ser;
                JavaType containedType = type.containedType(containedTypeIndex);
                if (containedType != null && containedType.hasGenericTypes()) {
                    JavaType st = provider.constructSpecializedType(containedType, val.getClass());
                    ser = provider.findTypedValueSerializer(st, true, null);
                } else {
                    ser = provider.findTypedValueSerializer(val.getClass(), true, null);
                }
                ser.serialize(val, gen, provider);
            } else {
                gen.writeObject(val);
            }
        } else {
            gen.writeNull();
        }
    }

    @Override
    public void serializeWithType(T value, JsonGenerator gen, SerializerProvider serializers,
                                  TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForScalar(value, gen);
        serialize(value, gen, serializers);
        typeSer.writeTypeSuffixForScalar(value, gen);
    }
}
