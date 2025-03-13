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

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;

import java.io.Serializable;
import java.util.Comparator;

abstract class MaplikeDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer {

    private static final long serialVersionUID = 1L;

    final MapLikeType mapType;

    final Comparator<Object> keyComparator;
    final KeyDeserializer keyDeserializer;
    final TypeDeserializer elementTypeDeserializer;
    final JsonDeserializer<?> elementDeserializer;

    MaplikeDeserializer(MapLikeType mapType, KeyDeserializer keyDeserializer,
                        TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) {
        super(mapType);
        this.mapType = mapType;
        this.keyComparator = createKeyComparator(mapType.getKeyType());
        this.keyDeserializer = keyDeserializer;
        this.elementTypeDeserializer = elementTypeDeserializer;
        this.elementDeserializer = elementDeserializer;
    }

    private Comparator<Object> createKeyComparator(JavaType keyType) {
        if (Comparable.class.isAssignableFrom(keyType.getRawClass())) {
            @SuppressWarnings("unchecked")
            Comparator<Object> comparator = (Comparator<Object> & Serializable) (o1, o2) -> ((Comparable<Object>) o1).compareTo(o2);
            return comparator;
        } else {
            return (Comparator<Object> & Serializable) (o1, o2) -> o1.toString().compareTo(o2.toString());
        }
    }

    /**
     * Creates a new deserializer from the original one (this).
     *
     * @param keyDeserializer         the new deserializer for key
     * @param elementTypeDeserializer the new deserializer for element type
     * @param elementDeserializer     the new deserializer for element
     *
     * @return a new deserializer
     */
    abstract MaplikeDeserializer<T> createDeserializer(KeyDeserializer keyDeserializer,
                                                       TypeDeserializer elementTypeDeserializer,
                                                       JsonDeserializer<?> elementDeserializer);

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) throws JsonMappingException {
        KeyDeserializer keyDeser = keyDeserializer;
        if (keyDeser == null) {
            keyDeser = context.findKeyDeserializer(mapType.getKeyType(), property);
        } else if (keyDeser instanceof ContextualKeyDeserializer) {
            keyDeser = ((ContextualKeyDeserializer) keyDeser).createContextual(context, property);
        }

        TypeDeserializer elementTypeDeser = elementTypeDeserializer;
        if (elementTypeDeser != null) {
            elementTypeDeser = elementTypeDeser.forProperty(property);
        }
        JsonDeserializer<?> elementDeser = elementDeserializer;
        if (elementDeser == null) {
            elementDeser = context.findContextualValueDeserializer(mapType.getContentType(), property);
        } else {
            elementDeser = context.handleSecondaryContextualization(elementDeser, property, mapType.getContentType());
        }
        return createDeserializer(keyDeser, elementTypeDeser, elementDeser);
    }
}
