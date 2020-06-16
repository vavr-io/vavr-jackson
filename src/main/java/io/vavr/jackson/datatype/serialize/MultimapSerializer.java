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

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.Multimap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class MultimapSerializer extends ValueSerializer<Multimap<?, ?>> implements ContextualSerializer {

    private static final long serialVersionUID = 1L;
    private final MapLikeType mapType;

    MultimapSerializer(MapLikeType mapType) {
        this(mapType, null);
    }

    MultimapSerializer(MapLikeType mapType, BeanProperty beanProperty) {
        super(mapType, beanProperty);
        this.mapType = mapType;
    }

    @Override
    Object toJavaObj(Multimap<?, ?> value) throws IOException {
        final LinkedHashMap<Object, List<Object>> result = new LinkedHashMap<>();
        value.forEach(e -> {
            List<Object> list = result.get(e._1);
            if (list == null) {
                list = new ArrayList<>();
                result.put(e._1, list);
            }
            list.add(e._2);
        });
        return result;
    }

    @Override
    JavaType emulatedJavaType(TypeFactory typeFactory) {
        JavaType containerType = typeFactory.constructCollectionType(ArrayList.class, mapType.getContentType());
        return typeFactory.constructMapType(LinkedHashMap.class, mapType.getKeyType(), containerType);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Multimap<?, ?> value) {
        return value.isEmpty();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property == beanProperty) {
            return this;
        }
        return new MultimapSerializer(mapType, property);
    }
}
