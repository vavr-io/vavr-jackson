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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import io.vavr.Lazy;

import java.io.IOException;

class LazySerializer extends HListSerializer<Lazy<?>> implements ContextualSerializer {

    private static final long serialVersionUID = 1L;

    private final JavaType fullType;
    private final JavaType valueType;
    private final TypeSerializer valueTypeSerializer;
    private final JsonSerializer<Object> valueSerializer;

    @SuppressWarnings("unchecked")
    LazySerializer(JavaType fullType, JavaType valueType, TypeSerializer typeSer, JsonSerializer<?> valueSer) {
        super(fullType);
        this.fullType = fullType;
        this.valueType = valueType;
        this.valueTypeSerializer = typeSer;
        this.valueSerializer = (JsonSerializer<Object>) valueSer;
    }

    @Override
    public void serialize(Lazy<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (valueSerializer != null) {
            valueSerializer.serialize(value.get(), gen, provider);
        } else {
            write(value.get(), 0, gen, provider);
        }
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
        return new LazySerializer(fullType, valueType, vts, ser);
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
}
