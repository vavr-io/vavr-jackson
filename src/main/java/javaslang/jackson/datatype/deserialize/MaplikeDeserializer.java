/**
 * Copyright 2015 The Javaslang Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.Comparator;

abstract class MaplikeDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    MapLikeType mapLikeType;
    Comparator<Object> keyComparator;
    KeyDeserializer keyDeserializer;
    JsonDeserializer<?> valueDeserializer;

    MaplikeDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        mapLikeType = mapLike(javaType, ctxt);
        JavaType keyType = mapLikeType.getKeyType();
        if (keyType.getRawClass().isAssignableFrom(Comparable.class)) {
            keyComparator = (o1, o2) -> ((Comparable) o1).compareTo(o2);
        } else {
            keyComparator = (o1, o2) -> o1.toString().compareTo(o2.toString());
        }
        keyDeserializer = ctxt.findKeyDeserializer(keyType, null);
        valueDeserializer = ctxt.findContextualValueDeserializer(mapLikeType.getContentType(), null);
    }

    private static MapLikeType mapLike(JavaType type, DeserializationContext ctxt) {
        JavaType keyType = type.containedTypeCount() > 0 ? type.containedType(0) : TypeFactory.unknownType();
        JavaType valueType = type.containedTypeCount() > 1 ? type.containedType(1) : TypeFactory.unknownType();
        return ctxt.getTypeFactory().constructMapLikeType(type.getRawClass(), keyType, valueType);
    }
}
