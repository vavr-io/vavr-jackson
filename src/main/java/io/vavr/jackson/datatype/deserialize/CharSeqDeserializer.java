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

import io.vavr.collection.CharSeq;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;
import tools.jackson.databind.type.TypeFactory;

class CharSeqDeserializer extends StdDeserializer<CharSeq> {

    private ValueDeserializer<?> deserializer;

    CharSeqDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public CharSeq deserialize(JsonParser p, DeserializationContext ctxt) {
        Object obj = deserializer.deserialize(p, ctxt);
        if (obj instanceof String) {
            return CharSeq.of((String) obj);
        } else {
            throw DatabindException.from(p, String.format("Unexpected token (%s), expected %s: CharSeq can only be deserialized from String", p.currentToken(), JsonToken.VALUE_STRING));
        }
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws DatabindException {
        deserializer = ctxt.findContextualValueDeserializer(TypeFactory.unknownType(), null);
    }
}
