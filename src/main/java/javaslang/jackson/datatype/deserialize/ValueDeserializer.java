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

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

abstract class ValueDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;
    private final int typeCount;
    private final List<JsonDeserializer<?>> deserializers;

    ValueDeserializer(JavaType valueType, int typeCount) {
        super(valueType);
        this.javaType = valueType;
        this.typeCount = typeCount;
        this.deserializers = new ArrayList<>(typeCount);
    }

    int deserializersCount() {
        return deserializers.size();
    }

    JsonDeserializer<?> deserializer(int index) {
        return deserializers.get(index);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        for (int i = 0; i < typeCount; i++) {
            JavaType containedType = javaType.isCollectionLikeType() ? javaType.getContentType() : javaType.containedTypeOrUnknown(i);
            deserializers.add(ctxt.findRootValueDeserializer(containedType));
        }
    }

}
