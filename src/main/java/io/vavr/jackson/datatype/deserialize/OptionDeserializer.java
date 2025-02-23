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

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import io.vavr.control.Option;

class OptionDeserializer extends ReferenceTypeDeserializer<Option<?>> {

    private static final long serialVersionUID = 1L;

    public OptionDeserializer(JavaType fullType, ValueInstantiator vi, TypeDeserializer typeDeser, JsonDeserializer<?> deser) {
        super(fullType, vi, typeDeser, deser);
    }

    @Override
    protected OptionDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
        return new OptionDeserializer(_fullType, _valueInstantiator, typeDeser, valueDeser);
    }

    @Override
    public Option<?> getNullValue(DeserializationContext ctxt) {
        return Option.none();
    }

    @Override
    public Option<?> referenceValue(Object contents) {
        return Option.of(contents);
    }

    @Override
    public Option<?> updateReference(Option<?> reference, Object contents) {
        return referenceValue(contents);
    }

    @Override
    public Object getReferenced(Option<?> reference) {
        return reference.get();
    }
}
