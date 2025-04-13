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
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.io.IOException;

/**
 * Serializer for {@link Option} that unwraps the value if it is defined and the property is annotated with {@code @JsonUnwrapped}.
 * <p/>
 * Note that {@code Option} values of other Vavr types like {@link io.vavr.control.Try}, {@link Either}, etc are not supported
 * due to {@link com.fasterxml.jackson.annotation.JsonUnwrapped} only supports Java beans.
 * <p/>
 * Delegates the unwrapping to a {@link com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer}
 * since to support unwrapping requires to serialize only the fields instead of the whole object
 * but that is an internal {@link BeanSerializerBase} function and therefore not available here.
 */
class UnwrappingOptionSerializer extends StdSerializer<Option<?>> implements ContextualSerializer {

    private static final long serialVersionUID = 1L;
    private final OptionSerializer optionSerializer;
    private final NameTransformer unwrapper;

    public UnwrappingOptionSerializer(OptionSerializer optionSerializer, NameTransformer unwrapper) {
        super(optionSerializer.getValueType());
        this.optionSerializer = optionSerializer;
        this.unwrapper = unwrapper;
    }

    /**
     * Try to serialize the option value.
     * Writes null if the value is not defined.
     *
     * @param value Value to serialize; can <b>not</b> be null.
     * @param gen Generator used to output resulting Json content
     * @param provider Provider that can be used to get serializers for serializing Objects value contains, if any.
     * @throws JsonGenerationException if the option value is not a bean
     */
    @Override
    public void serialize(Option<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value.isDefined()) {
                JsonSerializer ser;
                Object val = value.get();
                JavaType containedType = optionSerializer.getValueType().containedType(0);
                if (containedType != null && containedType.hasGenericTypes()) {
                    JavaType st = provider.constructSpecializedType(containedType, val.getClass());
                    ser = provider.findTypedValueSerializer(st, true, null);
                } else {
                    ser = provider.findTypedValueSerializer(val.getClass(), true, null);
                }
                // can only unwrap if the inner values is a bean.
                if (ser instanceof BeanSerializer) {
                    JsonSerializer<Object> unwrappingSerializer = ser.unwrappingSerializer(unwrapper);
                    unwrappingSerializer.serialize(val, gen, provider);
                } else {
                    // Cannot unwrap a non-bean object, so throw an exception
                    throw new JsonGenerationException("Cannot unwrap a non-bean object", gen);
                }
            } else {
                gen.writeNull();
            }
    }

    @Override
    public boolean isUnwrappingSerializer() {
        return true; // sure it is
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Option<?> value) {
        return value.isEmpty();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        return optionSerializer.createContextual(provider, property);
    }
}
