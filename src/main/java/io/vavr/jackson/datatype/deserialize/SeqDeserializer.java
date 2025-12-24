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

import io.vavr.collection.Array;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.collection.Vector;
import java.util.List;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.jsontype.TypeDeserializer;

class SeqDeserializer extends ArrayDeserializer<Seq<?>> {

    SeqDeserializer(JavaType collectionType, JavaType elementType, TypeDeserializer elementTypeDeserializer,
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
    private SeqDeserializer(SeqDeserializer origin, TypeDeserializer elementTypeDeserializer,
                            ValueDeserializer<?> elementDeserializer) {
        this(origin.collectionType, origin.elementType, elementTypeDeserializer, elementDeserializer,
            origin.deserializeNullAsEmptyCollection);
    }

    @Override
    Seq<?> create(List<Object> result, DeserializationContext ctxt) throws DatabindException {
        if (Array.class.isAssignableFrom(collectionType.getRawClass())) {
            return Array.ofAll(result);
        }
        if (Queue.class.isAssignableFrom(collectionType.getRawClass())) {
            return Queue.ofAll(result);
        }
        if (Stream.class.isAssignableFrom(collectionType.getRawClass())) {
            return Stream.ofAll(result);
        }
        if (Vector.class.isAssignableFrom(collectionType.getRawClass())) {
            return Vector.ofAll(result);
        }
        if (IndexedSeq.class.isAssignableFrom(collectionType.getRawClass())) {
            return Array.ofAll(result);
        }
        // default deserialization [...] -> Seq
        return io.vavr.collection.List.ofAll(result);
    }

    @Override
    SeqDeserializer createDeserializer(TypeDeserializer elementTypeDeserializer, ValueDeserializer<?> elementDeserializer) {
        return new SeqDeserializer(this, elementTypeDeserializer, elementDeserializer);
    }

}
