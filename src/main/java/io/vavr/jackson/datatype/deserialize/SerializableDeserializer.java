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
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

class SerializableDeserializer<T> extends StdDeserializer<T> {

    SerializableDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) {
        ValueDeserializer<Object> deserializer = ctxt.findRootValueDeserializer(ctxt.constructType(byte[].class));
        byte[] bytes = (byte[]) deserializer.deserialize(p, ctxt);
        return deserialize(bytes);
    }

    @SuppressWarnings("unchecked")
    private static <T> T deserialize(byte[] objectData) {
        try (ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(objectData))) {
            return (T) stream.readObject();
        } catch (Exception x) {
            throw new IllegalStateException("Error deserializing object", x);
        }
    }
}
