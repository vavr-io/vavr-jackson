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

import com.fasterxml.jackson.databind.JavaType;
import javaslang.*;

import java.util.Arrays;
import java.util.Collections;

class TupleSerializer extends ValueSerializer<Tuple> {

    private static final long serialVersionUID = 1L;

    TupleSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Tuple tuple) {
        switch (tuple.arity()) {
            case 1: {
                final Tuple1<?> t = (Tuple1<?>) tuple;
                return Collections.singletonList(t._1);
            }
            case 2: {
                final Tuple2<?, ?> t = (Tuple2<?, ?>) tuple;
                return Arrays.asList(t._1, t._2);
            }
            case 3: {
                final Tuple3<?, ?, ?> t = (Tuple3<?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3);
            }
            case 4: {
                final Tuple4<?, ?, ?, ?> t = (Tuple4<?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4);
            }
            case 5: {
                final Tuple5<?, ?, ?, ?, ?> t = (Tuple5<?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5);
            }
            case 6: {
                final Tuple6<?, ?, ?, ?, ?, ?> t = (Tuple6<?, ?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6);
            }
            case 7: {
                final Tuple7<?, ?, ?, ?, ?, ?, ?> t = (Tuple7<?, ?, ?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6, t._7);
            }
            case 8: {
                final Tuple8<?, ?, ?, ?, ?, ?, ?, ?> t = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
            }
            default: {
                return Collections.emptyList();
            }
        }
    }

}
