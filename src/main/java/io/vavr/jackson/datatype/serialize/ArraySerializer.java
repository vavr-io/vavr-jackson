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

import io.vavr.Value;
import java.util.ArrayList;
import java.util.List;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.jsontype.TypeSerializer;
import tools.jackson.databind.type.CollectionLikeType;
import tools.jackson.databind.type.TypeFactory;

class ArraySerializer<T extends Value<?>> extends VavrValueSerializer<T> {

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
    public void serializeWithType(T value, JsonGenerator gen, SerializationContext context,
                                  TypeSerializer typeSer) {
        typeSer.writeTypePrefix(gen, context, typeSer.typeId(value, JsonToken.START_ARRAY));
        List<?> list = value.toJavaList();
        JavaType contentType = collectionType.getContentType();
        for (Object item : list) {
            if (item == null) {
                gen.writeNull();
            } else {
                ValueSerializer<Object> ser;
                if (contentType != null && !contentType.isJavaLangObject()) {
                    ser = context.findPrimaryPropertySerializer(contentType, beanProperty);
                } else {
                    ser = context.findPrimaryPropertySerializer(item.getClass(), beanProperty);
                }
                ser.serialize(item, gen, context);
            }
        }
        typeSer.writeTypeSuffix(gen, context, typeSer.typeId(value, JsonToken.START_ARRAY));
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
