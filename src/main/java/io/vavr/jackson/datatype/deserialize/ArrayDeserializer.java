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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

abstract class ArrayDeserializer<T> extends ValueDeserializer<T> implements ContextualDeserializer {

    private static final long serialVersionUID = 1L;

    protected final JavaType collectionType;
    protected final JavaType elementType;
    protected final TypeDeserializer elementTypeDeserializer;
    protected final JsonDeserializer<?> elementDeserializer;
    protected final boolean deserializeNullAsEmptyCollection;

    ArrayDeserializer(JavaType collectionType, int typeCount, JavaType elementType,
                      TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer,
                      boolean deserializeNullAsEmptyCollection) {
        super(collectionType, typeCount);
        this.collectionType = collectionType;
        this.elementType = elementType;
        this.elementTypeDeserializer = elementTypeDeserializer;
        this.elementDeserializer = elementDeserializer;
        this.deserializeNullAsEmptyCollection = deserializeNullAsEmptyCollection;
    }

    abstract T create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException;

    /**
     * Creates a new deserializer from the original one (this).
     *
     * @param elementTypeDeserializer the new deserializer for the element type
     * @param elementDeserializer     the new deserializer for the element itself
     * @return a new deserializer
     */
    abstract ArrayDeserializer<T> createDeserializer(TypeDeserializer elementTypeDeserializer,
                                                     JsonDeserializer<?> elementDeserializer);

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<?> elementDeser = elementDeserializer;
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
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (!parser.isExpectedStartArrayToken()) {
            throw mappingException(context, collectionType.getRawClass(), parser.getCurrentToken());
        }

        List<Object> elements = new ArrayList<>();
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
    public T getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        if (deserializeNullAsEmptyCollection) {
            return create(Collections.emptyList(), ctxt);
        }
        return super.getNullValue(ctxt);
    }

    static void checkContainedTypeIsComparable(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        Class<?> clz = type.getRawClass();
        if (clz == Object.class || !Comparable.class.isAssignableFrom(clz)) {
            throw mappingException(ctxt, clz, null);
        }
    }
}
