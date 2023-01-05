/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2023 Vavr, http://vavr.io
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

import com.fasterxml.jackson.databind.JavaType;
import io.vavr.Tuple5;

import java.util.Arrays;
import java.util.List;

class Tuple5Serializer extends TupleSerializer<Tuple5<?, ?, ?, ?, ?>> {

    Tuple5Serializer(JavaType type) {
        super(type);
    }

    @Override
    List<?> toList(Tuple5<?, ?, ?, ?, ?> value) {
        return Arrays.asList(value._1, value._2, value._3, value._4, value._5);
    }
}
