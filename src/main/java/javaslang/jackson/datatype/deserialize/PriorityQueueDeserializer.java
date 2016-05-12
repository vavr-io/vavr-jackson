/**
 * Copyright 2016 The Javaslang Authors
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
import javaslang.collection.PriorityQueue;

import java.util.List;

class PriorityQueueDeserializer extends ArrayDeserializer<PriorityQueue<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    PriorityQueueDeserializer(JavaType valueType) {
        super(valueType, 1);
        javaType = valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    PriorityQueue<?> create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException {
        if(javaType.containedTypeCount() == 0) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
        JavaType generic = javaType.containedType(0);
        if(generic.getRawClass() == Object.class || !Comparable.class.isAssignableFrom(generic.getRawClass())) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
        return PriorityQueue.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), list);
    }
}
