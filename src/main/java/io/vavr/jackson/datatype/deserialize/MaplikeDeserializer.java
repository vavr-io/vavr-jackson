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
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;

import java.util.Comparator;

abstract class MaplikeDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    final MapLikeType javaType;

    Comparator<Object> keyComparator;
    KeyDeserializer keyDeserializer;
    JsonDeserializer<?> valueDeserializer;

    MaplikeDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = (MapLikeType) valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        JavaType keyType = javaType.getKeyType();
        if (keyType.getRawClass().isAssignableFrom(Comparable.class)) {
            keyComparator = (o1, o2) -> ((Comparable) o1).compareTo(o2);
        } else {
            keyComparator = (o1, o2) -> o1.toString().compareTo(o2.toString());
        }
        keyDeserializer = ctxt.findKeyDeserializer(keyType, null);
        valueDeserializer = ctxt.findRootValueDeserializer(javaType.getContentType());
    }
}
