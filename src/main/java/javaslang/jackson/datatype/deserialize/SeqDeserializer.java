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
package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.collection.Seq;

import java.util.List;

class SeqDeserializer extends ArrayDeserializer<Seq<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SeqDeserializer(JavaType valueType) {
        super(valueType, 1);
        javaType = valueType;
    }

    @Override
    Seq<?> create(List<Object> result, DeserializationContext ctxt) throws JsonMappingException {
        if (javaslang.collection.Array.class.isAssignableFrom(javaType.getRawClass())) {
            return javaslang.collection.Array.ofAll(result);
        }
        if (javaslang.collection.Queue.class.isAssignableFrom(javaType.getRawClass())) {
            return javaslang.collection.Queue.ofAll(result);
        }
        if (javaslang.collection.Stack.class.isAssignableFrom(javaType.getRawClass())) {
            return javaslang.collection.Stack.ofAll(result);
        }
        if (javaslang.collection.Stream.class.isAssignableFrom(javaType.getRawClass())) {
            return javaslang.collection.Stream.ofAll(result);
        }
        if (javaslang.collection.Vector.class.isAssignableFrom(javaType.getRawClass())) {
            return javaslang.collection.Vector.ofAll(result);
        }
        // default deserialization [...] -> Seq
        return javaslang.collection.List.ofAll(result);
    }

}
