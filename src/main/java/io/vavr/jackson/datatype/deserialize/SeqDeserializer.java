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
import com.fasterxml.jackson.databind.JsonMappingException;
import io.vavr.collection.Array;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.collection.Vector;

import java.util.List;

class SeqDeserializer extends ArrayDeserializer<Seq<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SeqDeserializer(JavaType valueType, boolean deserializeNullAsEmptyCollection) {
        super(valueType, 1, deserializeNullAsEmptyCollection);
        javaType = valueType;
    }

    @Override
    Seq<?> create(List<Object> result, DeserializationContext ctxt) throws JsonMappingException {
        if (Array.class.isAssignableFrom(javaType.getRawClass())) {
            return Array.ofAll(result);
        }
        if (Queue.class.isAssignableFrom(javaType.getRawClass())) {
            return Queue.ofAll(result);
        }
        if (Stream.class.isAssignableFrom(javaType.getRawClass())) {
            return Stream.ofAll(result);
        }
        if (Vector.class.isAssignableFrom(javaType.getRawClass())) {
            return Vector.ofAll(result);
        }
        if (IndexedSeq.class.isAssignableFrom(javaType.getRawClass())) {
            return Array.ofAll(result);
        }
        // default deserialization [...] -> Seq
        return io.vavr.collection.List.ofAll(result);
    }
}
