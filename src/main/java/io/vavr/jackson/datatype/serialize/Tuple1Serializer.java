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
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import io.vavr.Tuple1;

import java.util.Collections;
import java.util.List;

class Tuple1Serializer extends TupleSerializer<Tuple1<?>> {

    Tuple1Serializer(JavaType type) {
        super(type);
    }

    @Override
    List<?> toList(Tuple1<?> value) {
        return Collections.singletonList(value._1);
    }
}
