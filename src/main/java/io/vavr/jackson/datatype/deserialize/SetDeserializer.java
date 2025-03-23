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

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

class SetDeserializer extends ArrayDeserializer<Set<?>> {

    private static final long serialVersionUID = 1L;

    SetDeserializer(JavaType collectionType, JavaType elementType, TypeDeserializer elementTypeDeserializer,
                    JsonDeserializer<?> elementDeserializer, boolean deserializeNullAsEmptyCollection) {
        super(collectionType, 1, elementType, elementTypeDeserializer, elementDeserializer, deserializeNullAsEmptyCollection);
    }

    /**
     * Creates a new deserializer from the original one.
     *
     * @param origin                  the original deserializer
     * @param elementTypeDeserializer the new deserializer for the element type
     * @param elementDeserializer     the new deserializer for the element itself
     */
    private SetDeserializer(SetDeserializer origin, TypeDeserializer elementTypeDeserializer,
                            JsonDeserializer<?> elementDeserializer) {
        this(origin.collectionType, origin.elementType, elementTypeDeserializer, elementDeserializer,
            origin.deserializeNullAsEmptyCollection);
    }

    @SuppressWarnings("unchecked")
    @Override
    Set<?> create(List<Object> result, DeserializationContext ctx) throws JsonMappingException {
        if (io.vavr.collection.SortedSet.class.isAssignableFrom(collectionType.getRawClass())) {
            checkContainedTypeIsComparable(ctx, collectionType.containedTypeOrUnknown(0));
            return io.vavr.collection.TreeSet.ofAll((Comparator<Object> & Serializable) (o1, o2) -> ((Comparable) o1).compareTo(o2), result);
        }
        if (io.vavr.collection.LinkedHashSet.class.isAssignableFrom(collectionType.getRawClass())) {
            return io.vavr.collection.LinkedHashSet.ofAll(result);
        }
        // default deserialization [...] -> Set
        return HashSet.ofAll(result);
    }

    @Override
    SetDeserializer createDeserializer(TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) {
        return new SetDeserializer(this, elementTypeDeserializer, elementDeserializer);
    }
}
