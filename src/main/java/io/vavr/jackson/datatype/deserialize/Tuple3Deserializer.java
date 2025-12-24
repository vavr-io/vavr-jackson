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

import io.vavr.Tuple;
import io.vavr.Tuple3;
import java.util.List;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;

class Tuple3Deserializer extends TupleDeserializer<Tuple3<?, ?, ?>> {

    Tuple3Deserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    Tuple3<?, ?, ?> create(List<Object> list, DeserializationContext ctxt) {
        return Tuple.of(list.get(0), list.get(1), list.get(2));
    }
}
