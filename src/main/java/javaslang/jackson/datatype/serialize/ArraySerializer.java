/**
 * Copyright 2016 The Javaslang Authors
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
import com.fasterxml.jackson.databind.type.CollectionType;
import javaslang.Value;

import java.io.IOException;
import java.util.ArrayList;

class ArraySerializer<T extends Value<?>> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    ArraySerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        return value.toJavaList();
    }

    @Override
    JavaType emulatedJavaType(JavaType type) {
        return CollectionType.construct(ArrayList.class, type.containedType(0));
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, T value) {
        return value.isEmpty();
    }
}
