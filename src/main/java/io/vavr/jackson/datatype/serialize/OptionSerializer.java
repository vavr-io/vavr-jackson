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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import io.vavr.control.Option;

import java.io.IOException;

class OptionSerializer extends HListSerializer<Option<?>> implements ContextualSerializer {

    private static final long serialVersionUID = 1L;

    private final boolean plainMode;

    private final TypeSerializer valueTypeSerializer;

    private final JsonSerializer<Object> valueSerializer;

    private final JavaType fullType;

    private final JavaType valueType;

    @SuppressWarnings("unchecked")
    OptionSerializer(JavaType fullType, JavaType valueType, TypeSerializer valueTypeSerializer, JsonSerializer<?> valueSerializer, boolean plainMode) {
        super(fullType);
        this.fullType = fullType;
        this.valueType = valueType;
        this.plainMode = plainMode;
        this.valueTypeSerializer = valueTypeSerializer;
        this.valueSerializer = (JsonSerializer<Object>)  valueSerializer;
    }

    @Override
    public void serialize(Option<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (plainMode) {
            if (value.isDefined()) {
                if (valueSerializer != null) {
                    valueSerializer.serialize(value.get(), gen, provider);
                } else {
                    write(value.get(), 0, gen, provider);
                }
            } else {
                gen.writeNull();
            }
        } else {
            gen.writeStartArray();
            if (value.isDefined()) {
                gen.writeString("defined");
                write(value.get(), 0, gen, provider);
            } else {
                gen.writeString("undefined");
            }
            gen.writeEndArray();
        }
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Option<?> value) {
        return value.isEmpty();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        TypeSerializer vts = valueTypeSerializer;
        if (vts != null) {
            vts = vts.forProperty(property);
        }
        JsonSerializer<?> ser = valueSerializer;
        if (ser == null) {
            // A few conditions needed to be able to fetch serializer here:
            if (useStatic(provider, property, valueType)) {
                ser = provider.findTypedValueSerializer(valueType, true, property);
            }
        } else {
            ser = provider.handlePrimaryContextualization(ser, property);
        }
        return withResolved(fullType, vts, ser);
    }

    private boolean useStatic(SerializerProvider provider, BeanProperty property, JavaType referredType) {
        // First: no serializer for `Object.class`, must be dynamic
        if (referredType.isJavaLangObject()) {
            return false;
        }
        // but if type is final, might as well fetch
        if (referredType.isFinal()) { // or should we allow annotation override? (only if requested...)
            return true;
        }
        // also: if indicated by typing, should be considered static
        if (referredType.useStaticType()) {
            return true;
        }
        // if neither, maybe explicit annotation?
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        if ((intr != null) && (property != null)) {
            Annotated ann = property.getMember();
            if (ann != null) {
                JsonSerialize.Typing t = intr.findSerializationTyping(property.getMember());
                if (t == JsonSerialize.Typing.STATIC) {
                    return true;
                }
                if (t == JsonSerialize.Typing.DYNAMIC) {
                    return false;
                }
            }
        }
        // and finally, may be forced by global static typing (unlikely...)
        return provider.isEnabled(MapperFeature.USE_STATIC_TYPING);
    }

    private OptionSerializer withResolved(JavaType refType, TypeSerializer typeSer, JsonSerializer<?> valueSer) {
        if (refType == valueType && typeSer == valueTypeSerializer && valueSer == valueSerializer) {
            return this;
        }
        return new OptionSerializer(refType, valueType, typeSer, valueSer, plainMode);
    }

}
