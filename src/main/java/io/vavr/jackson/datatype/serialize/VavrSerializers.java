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

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import io.vavr.*;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;

public class VavrSerializers extends Serializers.Base {

    private final VavrModule.Settings settings;

    public VavrSerializers(VavrModule.Settings settings) {
        this.settings = settings;
    }

    @SuppressWarnings("deprecation")
    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        Class<?> raw = type.getRawClass();
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherSerializer(type);
        }
        if (Tuple.class.isAssignableFrom(raw)) {
            return new TupleSerializer(type);
        }

        if (Function0.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function1.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function2.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function3.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function4.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function5.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function6.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function7.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (Function8.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }

        if (CheckedFunction0.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction1.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction2.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction3.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction4.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction5.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction6.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction7.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }
        if (CheckedFunction8.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }

        return super.findSerializer(config, type, beanDesc);
    }

    @Override
    public JsonSerializer<?> findReferenceSerializer(SerializationConfig config,
                                                     ReferenceType type, BeanDescription beanDesc,
                                                     TypeSerializer contentTypeSerializer, JsonSerializer<Object> contentValueSerializer) {
        Class<?> raw = type.getRawClass();
        if (Lazy.class.isAssignableFrom(raw)) {
            return new LazySerializer(type);
        }
        if (Option.class.isAssignableFrom(raw)) {
            return new OptionSerializer(type, settings.useOptionInPlainFormat());
        }
        return super.findReferenceSerializer(config, type, beanDesc, contentTypeSerializer, contentValueSerializer);
    }

    @Override
    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig config,
                                                          CollectionLikeType type, BeanDescription beanDesc,
                                                          TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) {
        Class<?> raw = type.getRawClass();
        if (raw == CharSeq.class) {
            return new CharSeqSerializer(type);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new ArraySerializer<>(type);
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new ArraySerializer<>(type);
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return new ArraySerializer<>(type);
        }
        return super.findCollectionLikeSerializer(config, type, beanDesc, elementTypeSerializer, elementValueSerializer);
    }

    @Override
    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig config,
                                                   MapLikeType type, BeanDescription beanDesc,
                                                   JsonSerializer<Object> keySerializer,
                                                   TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) {
        Class<?> raw = type.getRawClass();
        if (Map.class.isAssignableFrom(raw)) {
            return new MapSerializer(type);
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return new MultimapSerializer(type);
        }
        return super.findMapLikeSerializer(config, type, beanDesc, keySerializer, elementTypeSerializer, elementValueSerializer);
    }
}
