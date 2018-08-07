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
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import io.vavr.Tuple;
import io.vavr.Tuple7;

import java.util.List;

class Tuple7Deserializer extends TupleDeserializer<Tuple7<?, ?, ?, ?, ?, ?, ?>> {

    Tuple7Deserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    Tuple7<?, ?, ?, ?, ?, ?, ?> create(List<Object> list, DeserializationContext ctxt) {
        return Tuple.of(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
    }
}
