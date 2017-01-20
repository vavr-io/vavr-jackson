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
package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javaslang.collection.Map;

import java.io.IOException;
import java.util.LinkedHashMap;

class MapSerializer extends ValueSerializer<Map<?, ?>> {

    private static final long serialVersionUID = 1L;

    MapSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Map<?, ?> value) throws IOException {
        return value.toJavaMap();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        MapLikeType mapLikeType = (MapLikeType) type;
        return typeFactory.constructMapType(LinkedHashMap.class, mapLikeType.getKeyType(), mapLikeType.getContentType());
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Map<?, ?> value) {
        return value.isEmpty();
    }
}
