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
package io.vavr.jackson.datatype.serialize;

import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.type.CollectionLikeType;
import tools.jackson.databind.type.TypeFactory;
import io.vavr.Value;

import java.util.ArrayList;

class ArraySerializer<T extends Value<?>> extends VavrValueSerializer<T> {

    private static final long serialVersionUID = 1L;
    private final CollectionLikeType collectionType;

    ArraySerializer(CollectionLikeType collectionType, BeanProperty property) {
        super(collectionType, property);
        this.collectionType = collectionType;
    }

    ArraySerializer(CollectionLikeType collectionType) {
        this(collectionType, null);
    }

    /**
     * Creates a new serializer from the original one.
     *
     * @param origin   the original serializer
     * @param property the new bean property
     */
    ArraySerializer(ArraySerializer<T> origin, BeanProperty property) {
        this(origin.collectionType, property);
    }

    @Override
    Object toJavaObj(T value) {
        return value.toJavaList();
    }

    @Override
    JavaType emulatedJavaType(TypeFactory typeFactory) {
        return typeFactory.constructCollectionType(ArrayList.class, collectionType.getContentType());
    }

    @Override
    public boolean isEmpty(SerializationContext context, T value) {
        return value.isEmpty();
    }

    @Override
    public ValueSerializer<?> createContextual(SerializationContext context, BeanProperty property)
        throws DatabindException {
        if (property == beanProperty) {
            return this;
        }
        return new ArraySerializer<>(this, property);
    }
}
