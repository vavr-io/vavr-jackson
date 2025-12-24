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
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;

import java.util.List;

abstract class TupleSerializer<T> extends HListSerializer<T> {

    TupleSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializationContext context) {
        gen.writeStartArray();
        List<?> list = toList(value);
        for (int i = 0; i < list.size(); i++) {
            write(list.get(i), i, gen, context);
        }
        gen.writeEndArray();
    }

    abstract List<?> toList(T value);
}
