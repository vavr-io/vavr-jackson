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

import tools.jackson.core.JsonToken;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.jsontype.TypeDeserializer;
import io.vavr.collection.Traversable;
import tools.jackson.core.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tools.jackson.core.JsonToken.END_ARRAY;
import static tools.jackson.core.JsonToken.VALUE_NULL;

abstract class ArrayDeserializer<T> extends VavrValueDeserializer<T> {

    private static final long serialVersionUID = 1L;

    protected final JavaType collectionType;
    protected final JavaType elementType;
    protected final TypeDeserializer elementTypeDeserializer;
    protected final ValueDeserializer<?> elementDeserializer;
    protected final boolean deserializeNullAsEmptyCollection;

    ArrayDeserializer(JavaType collectionType, int typeCount, JavaType elementType,
                      TypeDeserializer elementTypeDeserializer, ValueDeserializer<?> elementDeserializer,
                      boolean deserializeNullAsEmptyCollection) {
        super(collectionType, typeCount);
        this.collectionType = collectionType;
        this.elementType = elementType;
        this.elementTypeDeserializer = elementTypeDeserializer;
        this.elementDeserializer = elementDeserializer;
        this.deserializeNullAsEmptyCollection = deserializeNullAsEmptyCollection;
    }

    abstract T create(List<Object> list, DeserializationContext ctxt) throws DatabindException;

    /**
     * Creates a new deserializer from the original one (this).
     *
     * @param elementTypeDeserializer the new deserializer for the element type
     * @param elementDeserializer     the new deserializer for the element itself
     *
     * @return a new deserializer
     */
    abstract ArrayDeserializer<T> createDeserializer(TypeDeserializer elementTypeDeserializer,
                                                     ValueDeserializer<?> elementDeserializer);

    @Override
    public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws DatabindException {
        ValueDeserializer<?> elementDeser = elementDeserializer;
        TypeDeserializer elementTypeDeser = elementTypeDeserializer;

        if (elementDeser == null) {
            elementDeser = ctxt.findContextualValueDeserializer(elementType, property);
        } else {
            elementDeser = ctxt.handleSecondaryContextualization(elementDeser, property, elementType);
        }
        if (elementTypeDeser != null) {
            elementTypeDeser = elementTypeDeser.forProperty(property);
        }
        return createDeserializer(elementTypeDeser, elementDeser);
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context, T intoValue) {
        if (!parser.isExpectedStartArrayToken()) {
            throw mappingException(context, collectionType.getRawClass(), parser.currentToken());
        }

        List<Object> elements = new ArrayList<>();

        if (intoValue instanceof Traversable) {
            for (Object o : ((Traversable) intoValue)) {
                elements.add(o);
            }
        }

        for (JsonToken jsonToken = parser.nextToken(); jsonToken != END_ARRAY; jsonToken = parser.nextToken()) {
            Object element;
            if (jsonToken == VALUE_NULL) {
                element = elementDeserializer.getNullValue(context);
            } else if (elementTypeDeserializer == null) {
                element = elementDeserializer.deserialize(parser, context);
            } else {
                element = elementDeserializer.deserializeWithType(parser, context, elementTypeDeserializer);
            }
            elements.add(element);
        }
        return create(elements, context);
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return deserialize(parser, context, null);
    }

    @Override
    public T getNullValue(DeserializationContext ctxt) throws DatabindException {
        if (deserializeNullAsEmptyCollection) {
            return create(Collections.emptyList(), ctxt);
        }
        return (T) super.getNullValue(ctxt);
    }

    static void checkContainedTypeIsComparable(DeserializationContext ctxt, JavaType type) throws DatabindException {
        Class<?> clz = type.getRawClass();
        if (clz == Object.class || !Comparable.class.isAssignableFrom(clz)) {
            throw mappingException(ctxt, clz, null);
        }
    }
}
