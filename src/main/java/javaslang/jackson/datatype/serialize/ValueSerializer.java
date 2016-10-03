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
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

abstract class ValueSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    JavaType type;

    ValueSerializer(JavaType type) {
        super(type);
        this.type = type;
    }

    abstract Object toJavaObj(T value) throws IOException;
    abstract JavaType emulatedJavaType(JavaType type);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Object obj = toJavaObj(value);
        if (obj == null) {
            provider.getDefaultNullValueSerializer().serialize(null, gen, provider);
        } else {
            JsonSerializer<Object> ser;
            try {
                ser = provider.findTypedValueSerializer(emulatedJavaType(type), true, null);
            } catch (Exception ignore) {
                ser = provider.findTypedValueSerializer(obj.getClass(), true, null);
            }
            ser.serialize(obj, gen, provider);
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
