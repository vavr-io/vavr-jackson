/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2025 Vavr, http://vavr.io
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

import tools.jackson.core.JsonParser;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.jsontype.TypeDeserializer;
import io.vavr.Lazy;

class LazyDeserializer extends VavrValueDeserializer<Lazy<?>> {

    private final JavaType fullType;
    private final JavaType valueType;
    private final TypeDeserializer valueTypeDeserializer;
    private final ValueDeserializer<?> valueDeserializer;

    LazyDeserializer(JavaType fullType, JavaType valueType, TypeDeserializer typeDeser, ValueDeserializer<?> valueDeser) {
        super(valueType, 1);
        this.fullType = fullType;
        this.valueType = valueType;
        this.valueTypeDeserializer = typeDeser;
        this.valueDeserializer = valueDeser;
    }

    private LazyDeserializer(LazyDeserializer origin, TypeDeserializer typeDeser, ValueDeserializer<?> valueDeser) {
        this(origin.fullType, origin.valueType, typeDeser, valueDeser);
    }

    @Override
    public Lazy<?> deserialize(JsonParser p, DeserializationContext ctxt) {
        Object value;
        if (valueTypeDeserializer == null) {
            value = valueDeserializer.deserialize(p, ctxt);
        } else {
            value = valueDeserializer.deserializeWithType(p, ctxt, valueTypeDeserializer);
        }
        return Lazy.of(() -> value);
    }

    @Override
    public Lazy<?> getNullValue(DeserializationContext ctxt) {
        return Lazy.of(() -> null);
    }

    @Override
    public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws DatabindException {
        ValueDeserializer<?> deser = valueDeserializer;
        TypeDeserializer typeDeser = valueTypeDeserializer;
        JavaType refType = valueType;

        if (deser == null) {
            deser = ctxt.findContextualValueDeserializer(refType, property);
        } else { // otherwise directly assigned, probably not contextual yet:
            deser = ctxt.handleSecondaryContextualization(deser, property, refType);
        }
        if (typeDeser != null) {
            typeDeser = typeDeser.forProperty(property);
        }
        return new LazyDeserializer(this, typeDeser, deser);
    }
}
