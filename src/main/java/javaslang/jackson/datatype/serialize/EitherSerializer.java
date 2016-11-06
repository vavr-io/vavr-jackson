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
package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javaslang.control.Either;

import java.io.IOException;

class EitherSerializer extends StdSerializer<Either<?, ?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType type;

    EitherSerializer(JavaType type) {
        super(type);
        this.type = type;
    }

    @Override
    public void serialize(Either<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        if (value.isLeft()) {
            gen.writeString("left");
            write(value.left().get(), 0, gen, provider);
        } else {
            gen.writeString("right");
            write(value.right().get(), 1, gen, provider);
        }
        gen.writeEndArray();
    }

    private void write(Object val, int containedTypeIndex, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (val != null) {
            if (type.containedTypeCount() > containedTypeIndex) {
                JsonSerializer<Object> ser;
                JavaType containedType = type.containedType(containedTypeIndex);
                if (containedType.getRawClass() != Object.class) {
                    ser = provider.findTypedValueSerializer(type.containedType(containedTypeIndex), true, null);
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
    public boolean isEmpty(SerializerProvider provider, Either<?, ?> value) {
        return value.isEmpty();
    }
}
