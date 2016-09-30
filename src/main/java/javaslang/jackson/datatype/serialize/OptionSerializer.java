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
import javaslang.control.Option;

import java.io.IOException;

class OptionSerializer extends ValueSerializer<Option<?>> {

    private static final long serialVersionUID = 1L;

    private final boolean plainMode;

    OptionSerializer(JavaType type, boolean plainMode) {
        super(type);
        this.plainMode = plainMode;
    }


    @Override
    public void serialize(Option<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (plainMode) {
            super.serialize(value, gen, provider);
        } else {
            gen.writeStartArray();
            if (value.isDefined()) {
                gen.writeString("defined");
                Object val = value.get();
                if (val != null) {
                    if (type.containedTypeCount() > 0) {
                        JsonSerializer<Object> ser = provider.findTypedValueSerializer(type.containedType(0), true, null);
                        ser.serialize(val, gen, provider);
                    } else {
                        gen.writeObject(val);
                    }
                } else {
                    gen.writeNull();
                }
            } else {
                gen.writeString("undefined");
            }
            gen.writeEndArray();
        }
    }

    @Override
    Object toJavaObj(Option<?> value) throws IOException {
        // plain mode only
        if (value.isDefined()) {
            return value.get();
        } else {
            return null;
        }
    }

    @Override
    JavaType emulatedJavaType(JavaType type) {
        // plain mode only
        return type.containedType(0);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Option<?> value) {
        return value.isEmpty();
    }
}
