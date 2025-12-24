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

import io.vavr.control.Either;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;

class EitherSerializer extends HListSerializer<Either<?, ?>> {

    EitherSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(Either<?, ?> value, JsonGenerator gen, SerializationContext context) {
        gen.writeStartArray();
        if (value.isLeft()) {
            gen.writeString("left");
            write(value.getLeft(), 0, gen, context);
        } else {
            gen.writeString("right");
            write(value.get(), 1, gen, context);
        }
        gen.writeEndArray();
    }

    @Override
    public boolean isEmpty(SerializationContext context, Either<?, ?> value) {
        return value.isEmpty();
    }
}
