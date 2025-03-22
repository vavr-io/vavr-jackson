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
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.Map;

import java.util.LinkedHashMap;

class MapSerializer extends ValueSerializer<Map<?, ?>> implements ContextualSerializer {

    private static final long serialVersionUID = 1L;
    private final MapLikeType mapType;

    MapSerializer(MapLikeType mapType, BeanProperty beanProperty) {
        super(mapType, beanProperty);
        this.mapType = mapType;
    }

    MapSerializer(MapLikeType mapType) {
        this(mapType, null);
    }

    @Override
    Object toJavaObj(Map<?, ?> value) {
        return value.toJavaMap();
    }

    @Override
    JavaType emulatedJavaType(TypeFactory typeFactory) {
        return typeFactory.constructMapType(LinkedHashMap.class, mapType.getKeyType(), mapType.getContentType());
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Map<?, ?> value) {
        return value.isEmpty();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property == beanProperty) {
            return this;
        }
        return new MapSerializer(mapType, property);
    }
}
