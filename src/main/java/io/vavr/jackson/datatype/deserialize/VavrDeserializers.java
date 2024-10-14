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

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import io.vavr.*;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;

public class VavrDeserializers extends Deserializers.Base {

    private final VavrModule.Settings settings;

    public VavrDeserializers(VavrModule.Settings settings) {
        this.settings = settings;
    }

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type,
                                                    DeserializationConfig config,
                                                    BeanDescription beanDesc) throws JsonMappingException {
        Class<?> raw = type.getRawClass();
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherDeserializer(type);
        }

        if (Tuple0.class.isAssignableFrom(raw)) {
            return new Tuple0Deserializer(type);
        }
        if (Tuple1.class.isAssignableFrom(raw)) {
            return new Tuple1Deserializer(type);
        }
        if (Tuple2.class.isAssignableFrom(raw)) {
            return new Tuple2Deserializer(type);
        }
        if (Tuple3.class.isAssignableFrom(raw)) {
            return new Tuple3Deserializer(type);
        }
        if (Tuple4.class.isAssignableFrom(raw)) {
            return new Tuple4Deserializer(type);
        }
        if (Tuple5.class.isAssignableFrom(raw)) {
            return new Tuple5Deserializer(type);
        }
        if (Tuple6.class.isAssignableFrom(raw)) {
            return new Tuple6Deserializer(type);
        }
        if (Tuple7.class.isAssignableFrom(raw)) {
            return new Tuple7Deserializer(type);
        }
        if (Tuple8.class.isAssignableFrom(raw)) {
            return new Tuple8Deserializer(type);
        }

        if (Function0.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function1.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function2.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function3.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function4.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function5.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function6.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function7.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (Function8.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }

        if (CheckedFunction0.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction1.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction2.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction3.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction4.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction5.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction6.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction7.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }
        if (CheckedFunction8.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }

        return super.findBeanDeserializer(type, config, beanDesc);
    }

    @Override
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType type,
                                                         DeserializationConfig config, BeanDescription beanDesc,
                                                         TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer)
            throws JsonMappingException {
        Class<?> raw = type.getRawClass();
        if (raw == Lazy.class) {
            return new LazyDeserializer(type, type.getContentType(), contentTypeDeserializer, contentDeserializer);
        }
        if (raw == Option.class) {
            return new OptionDeserializer(type, type.getContentType(), contentTypeDeserializer, contentDeserializer, settings.useOptionInPlainFormat());
        }
        return super.findReferenceDeserializer(type, config, beanDesc, contentTypeDeserializer, contentDeserializer);
    }

    @Override
    public JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType collectionType,
                                                              DeserializationConfig config, BeanDescription beanDesc,
                                                              TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer)
            throws JsonMappingException
    {
        Class<?> raw = collectionType.getRawClass();
        if (raw == CharSeq.class) {
            return new CharSeqDeserializer(collectionType);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new SeqDeserializer(collectionType, collectionType.getContentType(), elementTypeDeserializer,
                    elementDeserializer, settings.deserializeNullAsEmptyCollection());
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new SetDeserializer(collectionType, collectionType.getContentType(), elementTypeDeserializer,
                    elementDeserializer, settings.deserializeNullAsEmptyCollection());
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return new PriorityQueueDeserializer(collectionType, collectionType.getContentType(),
                    elementTypeDeserializer, elementDeserializer, settings.deserializeNullAsEmptyCollection());
        }
        return super.findCollectionLikeDeserializer(collectionType, config, beanDesc, elementTypeDeserializer, elementDeserializer);
    }

    @Override
    public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType type,
                                                       DeserializationConfig config, BeanDescription beanDesc,
                                                       KeyDeserializer keyDeserializer,
                                                       TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer)
            throws JsonMappingException
    {
        Class<?> raw = type.getRawClass();
        if (Map.class.isAssignableFrom(raw)) {
            return new MapDeserializer(type, keyDeserializer, elementTypeDeserializer, elementDeserializer);
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return new MultimapDeserializer(type, keyDeserializer, elementTypeDeserializer, elementDeserializer);
        }
        return super.findMapLikeDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
    }
}
