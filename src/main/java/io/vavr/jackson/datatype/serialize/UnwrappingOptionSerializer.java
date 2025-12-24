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

import io.vavr.control.Either;
import io.vavr.control.Option;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.databind.*;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.ser.bean.BeanSerializerBase;
import tools.jackson.databind.ser.std.StdSerializer;
import tools.jackson.databind.util.NameTransformer;

/**
 * Serializer for {@link Option} that unwraps the value if it is defined and the property is annotated with {@code @JsonUnwrapped}.
 *
 * Note that {@code Option} values of other Vavr types like {@link io.vavr.control.Try}, {@link Either}, etc are not supported
 * due to {@link com.fasterxml.jackson.annotation.JsonUnwrapped} only supports Java beans.
 *
 * Delegates the unwrapping to a {@link tools.jackson.databind.ser.impl.UnwrappingBeanSerializer}
 * since to support unwrapping requires to serialize only the fields instead of the whole object
 * but that is an internal {@link BeanSerializerBase} function and therefore not available here.
 */
class UnwrappingOptionSerializer extends StdSerializer<Option<?>> {

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
     * @param context Provider that can be used to get serializers for serializing Objects value contains, if any.
     * @throws StreamWriteException if the option value is not a bean
     */
    @Override
    public void serialize(Option<?> value, JsonGenerator gen, SerializationContext context) {
            if (value.isDefined()) {
                ValueSerializer<?> ser;
                Object val = value.get();
                JavaType containedType = optionSerializer.getValueType().containedType(0);
                if (containedType != null && containedType.hasGenericTypes()) {
                    JavaType st = context.constructSpecializedType(containedType, val.getClass());
                    ser = context.findTypedValueSerializer(st, true);
                } else {
                    ser = context.findTypedValueSerializer(val.getClass(), true);
                }
                // can only unwrap if the inner values is a bean.
                if (ser instanceof BeanSerializerBase) {
                    ValueSerializer<Object> unwrappingSerializer = (ValueSerializer<Object>) ser.unwrappingSerializer(unwrapper);
                    unwrappingSerializer.serialize(val, gen, context);
                } else {
                    // Cannot unwrap a non-bean object, so throw an exception
                    throw new StreamWriteException(gen, "Cannot unwrap a non-bean object");
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
    public boolean isEmpty(SerializationContext context, Option<?> value) {
        return value.isEmpty();
    }

    @Override
    public ValueSerializer<?> createContextual(SerializationContext context, BeanProperty property) throws DatabindException {
        return optionSerializer.createContextual(context, property);
    }
}
