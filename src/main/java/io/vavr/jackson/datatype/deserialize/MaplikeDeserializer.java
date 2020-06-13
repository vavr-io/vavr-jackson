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

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;

import java.io.Serializable;
import java.util.Comparator;

abstract class MaplikeDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer, ContextualDeserializer {

    private static final long serialVersionUID = 1L;

    final MapLikeType mapType;

    Comparator<Object> keyComparator;
    final KeyDeserializer keyDeserializer;
    final JsonDeserializer<?> valueDeserializer;

    MaplikeDeserializer(MapLikeType mapType, KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer) {
        super(mapType);
        this.mapType = mapType;
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
    }

    MaplikeDeserializer(MapLikeType mapType, Comparator<Object> keyComparator, KeyDeserializer keyDeserializer,
                        JsonDeserializer<?> valueDeserializer) {
        super(mapType);
        this.mapType = mapType;
        this.keyComparator = keyComparator;
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
    }

    /**
     * Creates a new deserializer from the original one (this).
     *
     * @param keyDeserializer   the new deserializer for key
     * @param valueDeserializer the new deserializer for value
     * @return a new deserializer
     */
    abstract MaplikeDeserializer<T> createDeserializer(KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer);

    @SuppressWarnings("unchecked")
    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        JavaType keyType = mapType.getKeyType();
        if (Comparable.class.isAssignableFrom(keyType.getRawClass())) {
            keyComparator = (Comparator<Object> & Serializable) (o1, o2) -> ((Comparable<Object>) o1).compareTo(o2);
        } else {
            keyComparator = (Comparator<Object> & Serializable) (o1, o2) -> o1.toString().compareTo(o2.toString());
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) throws JsonMappingException {
        KeyDeserializer keyDeser = keyDeserializer;
        if (keyDeser == null) {
            keyDeser = context.findKeyDeserializer(mapType.getKeyType(), property);
        }

        JsonDeserializer<?> valueDeser = valueDeserializer;
        if (valueDeser == null) {
            valueDeser = context.findContextualValueDeserializer(mapType.getContentType(), property);
        } else {
            valueDeser = context.handleSecondaryContextualization(valueDeser, property, mapType.getContentType());
        }
        return createDeserializer(keyDeser, valueDeser);
    }
}
