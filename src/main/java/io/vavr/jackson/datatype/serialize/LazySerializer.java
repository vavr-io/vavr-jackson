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

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.AnnotationIntrospector;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.introspect.Annotated;
import tools.jackson.databind.jsontype.TypeSerializer;
import io.vavr.Lazy;

class LazySerializer extends HListSerializer<Lazy<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType fullType;
    private final JavaType valueType;
    private final TypeSerializer valueTypeSerializer;
    private final ValueSerializer<Object> valueSerializer;

    @SuppressWarnings("unchecked")
    LazySerializer(JavaType fullType, JavaType valueType, TypeSerializer typeSer, ValueSerializer<?> valueSer) {
        super(fullType);
        this.fullType = fullType;
        this.valueType = valueType;
        this.valueTypeSerializer = typeSer;
        this.valueSerializer = (ValueSerializer<Object>) valueSer;
    }

    @Override
    public void serialize(Lazy<?> value, JsonGenerator gen, SerializationContext context) {
        if (valueSerializer != null) {
            valueSerializer.serialize(value.get(), gen, context);
        } else {
            write(value.get(), 0, gen, context);
        }
    }

    @Override
    public ValueSerializer<?> createContextual(SerializationContext provider, BeanProperty property) throws DatabindException {
        TypeSerializer vts = valueTypeSerializer;
        if (vts != null) {
            vts = vts.forProperty(provider, property);
        }
        ValueSerializer<?> ser = valueSerializer;
        if (ser == null) {
            // A few conditions needed to be able to fetch serializer here:
            if (useStatic(provider, property, valueType)) {
                ser = provider.findPrimaryPropertySerializer(valueType, property);
            }
        } else {
            ser = provider.handlePrimaryContextualization(ser, property);
        }
        return new LazySerializer(fullType, valueType, vts, ser);
    }

    private boolean useStatic(SerializationContext context, BeanProperty property, JavaType referredType) {
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
        AnnotationIntrospector intr = context.getAnnotationIntrospector();
        if ((intr != null) && (property != null)) {
            Annotated ann = property.getMember();
            if (ann != null) {
                JsonSerialize.Typing t = intr.findSerializationTyping(context.getConfig(), property.getMember());
                if (t == JsonSerialize.Typing.STATIC) {
                    return true;
                }
                if (t == JsonSerialize.Typing.DYNAMIC) {
                    return false;
                }
            }
        }
        // and finally, may be forced by global static typing (unlikely...)
        return context.isEnabled(MapperFeature.USE_STATIC_TYPING);
    }
}
