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
import javaslang.collection.Set;

import java.util.List;

class SetDeserializer extends ArrayDeserializer<Set<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SetDeserializer(JavaType valueType) {
        super(valueType, 1);
        javaType = valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    Set<?> create(List<Object> result, DeserializationContext ctx) throws JsonMappingException {
        if(javaslang.collection.TreeSet.class.isAssignableFrom(javaType.getRawClass())) {
            if(javaType.containedTypeCount() == 0) {
                throw ctx.mappingException(javaType.getRawClass());
            }
            JavaType generic = javaType.containedType(0);
            if(generic.getRawClass() == Object.class || !Comparable.class.isAssignableFrom(generic.getRawClass())) {
                throw ctx.mappingException(javaType.getRawClass());
            }
            return javaslang.collection.TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), result);
        }
        if(javaslang.collection.LinkedHashSet.class.isAssignableFrom(javaType.getRawClass())) {
            return javaslang.collection.LinkedHashSet.ofAll(result);
        }
        // default deserialization [...] -> Set
        return javaslang.collection.HashSet.ofAll(result);
    }
}
