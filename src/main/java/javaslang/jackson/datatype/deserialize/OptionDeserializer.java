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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.control.Option;

import java.io.IOException;

class OptionDeserializer extends BaseDeserializer<Option<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    OptionDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @Override
    public Option<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object obj = _deserialize(p, containedType(javaType, 0), ctxt);
        return Option.of(obj);
    }

    @Override
    public Option<?> getNullValue(DeserializationContext ctxt) {
        return Option.of(null);
    }
}
