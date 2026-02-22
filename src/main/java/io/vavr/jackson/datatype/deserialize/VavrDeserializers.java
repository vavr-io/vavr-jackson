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

import io.vavr.Lazy;
import io.vavr.Tuple;
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
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.KeyDeserializer;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.Deserializers;
import tools.jackson.databind.jsontype.TypeDeserializer;
import tools.jackson.databind.type.CollectionLikeType;
import tools.jackson.databind.type.MapLikeType;
import tools.jackson.databind.type.ReferenceType;

public class VavrDeserializers extends Deserializers.Base {

    private final VavrModule.Settings settings;

    public VavrDeserializers(VavrModule.Settings settings) {
        this.settings = settings;
    }

    @Override
    public ValueDeserializer<?> findBeanDeserializer(JavaType type,
                                                     DeserializationConfig config,
                                                     BeanDescription.Supplier beanDesc) throws DatabindException {
        Class<?> raw = type.getRawClass();
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherDeserializer(type);
        }

        if (Tuple.class.isAssignableFrom(raw)) {
            return new TupleDeserializer(type);
        }

        if (VavrModule.FUNCTION_TYPES.stream().anyMatch(t -> t.isAssignableFrom(raw))) {
            return new SerializableDeserializer<>(type);
        }

        return super.findBeanDeserializer(type, config, beanDesc);
    }

    @Override
    public ValueDeserializer<?> findReferenceDeserializer(ReferenceType type,
                                                         DeserializationConfig config, BeanDescription.Supplier beanDesc,
                                                         TypeDeserializer contentTypeDeserializer, ValueDeserializer<?> contentDeserializer)
        throws DatabindException {
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
    public ValueDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType collectionType,
                                                              DeserializationConfig config, BeanDescription.Supplier beanDesc,
                                                              TypeDeserializer elementTypeDeserializer, ValueDeserializer<?> elementDeserializer)
        throws DatabindException {
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
    public ValueDeserializer<?> findMapLikeDeserializer(MapLikeType type,
                                                       DeserializationConfig config, BeanDescription.Supplier beanDesc,
                                                       KeyDeserializer keyDeserializer,
                                                       TypeDeserializer elementTypeDeserializer, ValueDeserializer<?> elementDeserializer)
        throws DatabindException {
        Class<?> raw = type.getRawClass();
        if (Map.class.isAssignableFrom(raw)) {
            return new MapDeserializer(type, keyDeserializer, elementTypeDeserializer, elementDeserializer);
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return new MultimapDeserializer(type, keyDeserializer, elementTypeDeserializer, elementDeserializer);
        }
        return super.findMapLikeDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
    }

    @Override
    public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {
        return true;
    }
}
