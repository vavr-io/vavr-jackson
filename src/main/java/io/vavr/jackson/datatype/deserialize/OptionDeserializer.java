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
import tools.jackson.core.JsonToken;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.jsontype.TypeDeserializer;
import io.vavr.control.Option;

class OptionDeserializer<T> extends VavrValueDeserializer<Option<T>> {

    private final JavaType fullType;
    private final JavaType valueType;
    private final boolean plainMode;
    private final TypeDeserializer valueTypeDeserializer;
    private final ValueDeserializer<T> valueDeserializer;
    private ValueDeserializer<?> stringDeserializer;

    OptionDeserializer(JavaType fullType, JavaType valueType, TypeDeserializer typeDeser, ValueDeserializer<T> valueDeser, boolean plainMode) {
        super(fullType, 1);
        this.fullType = fullType;
        this.valueType = valueType;
        this.valueTypeDeserializer = typeDeser;
        this.valueDeserializer = valueDeser;
        this.plainMode = plainMode;
    }

    private OptionDeserializer(OptionDeserializer<T> origin, TypeDeserializer typeDeser, ValueDeserializer<T> valueDeser) {
        this(origin.fullType, origin.valueType, typeDeser, valueDeser, origin.plainMode);
        this.stringDeserializer = origin.stringDeserializer;
    }

    @Override
    public Option<T> deserialize(JsonParser p, DeserializationContext ctxt, Option<T> intoValue) {
        if (plainMode) {
            if (valueTypeDeserializer == null) {
                return Boolean.TRUE.equals(valueDeserializer.supportsUpdate(ctxt.getConfig())) && intoValue != null
                    ? Option.of(valueDeserializer.deserialize(p, ctxt, intoValue.getOrElse((T) null)))
                    : Option.of(valueDeserializer.deserialize(p, ctxt));
            } else {
                return Boolean.TRUE.equals(valueDeserializer.supportsUpdate(ctxt.getConfig())) && intoValue != null
                    ? Option.of((T) valueDeserializer.deserializeWithType(p, ctxt, valueTypeDeserializer, intoValue.getOrElse((T) null)))
                    : Option.of((T) valueDeserializer.deserializeWithType(p, ctxt, valueTypeDeserializer));
            }
        }
        boolean defined = false;
        T value = null;
        int cnt = 0;
        while (p.nextToken() != JsonToken.END_ARRAY) {
            cnt++;
            switch (cnt) {
                case 1:
                    JsonToken currentToken = p.currentToken();
                    String def = (String) stringDeserializer.deserialize(p, ctxt);
                    if ("defined".equals(def)) {
                        defined = true;
                    } else if ("undefined".equals(def)) {
                        defined = false;
                    } else {
                        throw mappingException(ctxt, fullType.getRawClass(), currentToken);
                    }
                    break;
                case 2:
                    if (valueTypeDeserializer == null) {
                        value = Boolean.TRUE.equals(valueDeserializer.supportsUpdate(ctxt.getConfig())) && intoValue != null
                            ? valueDeserializer.deserialize(p, ctxt, intoValue.getOrElse((T) null))
                            : valueDeserializer.deserialize(p, ctxt);
                    } else {
                        value = Boolean.TRUE.equals(valueDeserializer.supportsUpdate(ctxt.getConfig())) && intoValue != null
                            ? (T) valueDeserializer.deserializeWithType(p, ctxt, valueTypeDeserializer, intoValue.getOrElse((T) null))
                            : (T) valueDeserializer.deserializeWithType(p, ctxt, valueTypeDeserializer);
                    }
                    break;
            }
        }
        if (defined) {
            if (cnt != 2) {
                throw mappingException(ctxt, fullType.getRawClass(), null);
            }
            return Option.some(value);
        } else {
            if (cnt != 1) {
                throw mappingException(ctxt, fullType.getRawClass(), null);
            }
            return Option.none();
        }
    }

    @Override
    public Option<T> deserialize(JsonParser p, DeserializationContext ctxt) {
        return deserialize(p, ctxt, null);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws DatabindException {
        super.resolve(ctxt);
        stringDeserializer = ctxt.findContextualValueDeserializer(ctxt.constructType(String.class), null);
    }

    @Override
    public Option<T> getNullValue(DeserializationContext ctxt) {
        return Option.none();
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
        return withResolved(refType, typeDeser, deser);
    }

    /**
     * Overridable fluent factory method used for creating contextual
     * instances.
     */
    private OptionDeserializer<?> withResolved(JavaType refType, TypeDeserializer typeDeser, ValueDeserializer<?> valueDeser) {
        if (refType == valueType && valueDeser == valueDeserializer && typeDeser == valueTypeDeserializer) {
            return this;
        }
        return new OptionDeserializer(this, typeDeser, valueDeser);
    }
}
