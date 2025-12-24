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

import io.vavr.collection.PriorityQueue;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.jsontype.TypeDeserializer;

class PriorityQueueDeserializer extends ArrayDeserializer<PriorityQueue<?>> {

    PriorityQueueDeserializer(JavaType collectionType, JavaType elementType, TypeDeserializer elementTypeDeserializer,
                              ValueDeserializer<?> elementDeserializer, boolean deserializeNullAsEmptyCollection) {
        super(collectionType, 1, elementType, elementTypeDeserializer, elementDeserializer, deserializeNullAsEmptyCollection);
    }

    /**
     * Creates a new deserializer from the original one.
     *
     * @param origin                  the original deserializer
     * @param elementTypeDeserializer the new deserializer for the element type
     * @param elementDeserializer     the new deserializer for the element itself
     */
    PriorityQueueDeserializer(PriorityQueueDeserializer origin, TypeDeserializer elementTypeDeserializer,
                              ValueDeserializer<?> elementDeserializer) {
        this(origin.collectionType, origin.elementType, elementTypeDeserializer, elementDeserializer,
            origin.deserializeNullAsEmptyCollection);
    }

    @SuppressWarnings("unchecked")
    @Override
    PriorityQueue<?> create(List<Object> list, DeserializationContext ctxt) throws DatabindException {
        checkContainedTypeIsComparable(ctxt, collectionType.containedTypeOrUnknown(0));
        return PriorityQueue.ofAll((Comparator<Object> & Serializable) (o1, o2) -> ((Comparable) o1).compareTo(o2), list);
    }

    @Override
    ArrayDeserializer<PriorityQueue<?>> createDeserializer(TypeDeserializer elementTypeDeserializer, ValueDeserializer<?> elementDeserializer) {
        return new PriorityQueueDeserializer(this, elementTypeDeserializer, elementDeserializer);
    }


}
