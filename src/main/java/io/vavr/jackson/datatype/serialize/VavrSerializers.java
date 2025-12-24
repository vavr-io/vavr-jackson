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

import com.fasterxml.jackson.annotation.JsonFormat;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.CheckedFunction3;
import io.vavr.CheckedFunction4;
import io.vavr.CheckedFunction5;
import io.vavr.CheckedFunction6;
import io.vavr.CheckedFunction7;
import io.vavr.CheckedFunction8;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.Function5;
import io.vavr.Function6;
import io.vavr.Function7;
import io.vavr.Function8;
import io.vavr.Lazy;
import io.vavr.Tuple0;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.Tuple5;
import io.vavr.Tuple6;
import io.vavr.Tuple7;
import io.vavr.Tuple8;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.jsontype.TypeSerializer;
import tools.jackson.databind.ser.Serializers;
import tools.jackson.databind.type.CollectionLikeType;
import tools.jackson.databind.type.MapLikeType;
import tools.jackson.databind.type.ReferenceType;

public class VavrSerializers extends Serializers.Base {

    private final VavrModule.Settings settings;

    public VavrSerializers(VavrModule.Settings settings) {
        this.settings = settings;
    }

    @Override
    public ValueSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription.Supplier beanDesc, JsonFormat.Value formatOverrides) {

        Class<?> raw = type.getRawClass();
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherSerializer(type);
        }

        if (Tuple0.class.isAssignableFrom(raw)) {
            return new Tuple0Serializer(type);
        }
        if (Tuple1.class.isAssignableFrom(raw)) {
            return new Tuple1Serializer(type);
        }
        if (Tuple2.class.isAssignableFrom(raw)) {
            return new Tuple2Serializer(type);
        }
        if (Tuple3.class.isAssignableFrom(raw)) {
            return new Tuple3Serializer(type);
        }
        if (Tuple4.class.isAssignableFrom(raw)) {
            return new Tuple4Serializer(type);
        }
        if (Tuple5.class.isAssignableFrom(raw)) {
            return new Tuple5Serializer(type);
        }
        if (Tuple6.class.isAssignableFrom(raw)) {
            return new Tuple6Serializer(type);
        }
        if (Tuple7.class.isAssignableFrom(raw)) {
            return new Tuple7Serializer(type);
        }
        if (Tuple8.class.isAssignableFrom(raw)) {
            return new Tuple8Serializer(type);
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

        return super.findSerializer(config, type, beanDesc, formatOverrides);
    }

    @Override
    public ValueSerializer<?> findReferenceSerializer(SerializationConfig config,
                                                     ReferenceType type, BeanDescription.Supplier beanDesc,
                                                                             JsonFormat.Value formatOverrides, TypeSerializer contentTypeSerializer, ValueSerializer<Object> contentValueSerializer) {
        Class<?> raw = type.getRawClass();
        if (Lazy.class.isAssignableFrom(raw)) {
            return new LazySerializer(type, type.getContentType(), contentTypeSerializer, contentValueSerializer);
        }
        if (Option.class.isAssignableFrom(raw)) {
            return new OptionSerializer(type, type.getContentType(), contentTypeSerializer, contentValueSerializer, settings.useOptionInPlainFormat());
        }
        return super.findReferenceSerializer(config, type, beanDesc, formatOverrides, contentTypeSerializer, contentValueSerializer);
    }

    @Override
    public ValueSerializer<?> findCollectionLikeSerializer(SerializationConfig config,
                                                          CollectionLikeType collectionType, BeanDescription.Supplier beanDesc,
                                                                                  JsonFormat.Value formatOverrides, TypeSerializer elementTypeSerializer, ValueSerializer<Object> elementValueSerializer) {
        Class<?> raw = collectionType.getRawClass();
        if (raw == CharSeq.class) {
            return new CharSeqSerializer(collectionType);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new ArraySerializer<>(collectionType);
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new ArraySerializer<>(collectionType);
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return new ArraySerializer<>(collectionType);
        }
        return super.findCollectionLikeSerializer(config, collectionType, beanDesc, formatOverrides, elementTypeSerializer, elementValueSerializer);
    }

    @Override
    public ValueSerializer<?> findMapLikeSerializer(SerializationConfig config, MapLikeType type, BeanDescription.Supplier beanDesc, JsonFormat.Value formatOverrides, ValueSerializer<Object> keySerializer, TypeSerializer elementTypeSerializer, ValueSerializer<Object> elementValueSerializer) {
            Class<?> raw = type.getRawClass();
            if (Map.class.isAssignableFrom(raw)) {
                return new MapSerializer(type);
            }
            if (Multimap.class.isAssignableFrom(raw)) {
                return new MultimapSerializer(type);
            }
            return super.findMapLikeSerializer(config, type, beanDesc, formatOverrides, keySerializer, elementTypeSerializer, elementValueSerializer);
    }
}
