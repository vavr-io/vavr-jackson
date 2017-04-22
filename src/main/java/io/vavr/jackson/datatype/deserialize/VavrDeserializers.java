/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;

public class VavrDeserializers extends Deserializers.Base {

    private final VavrModule.Settings settings;

    public VavrDeserializers(VavrModule.Settings settings) {
        this.settings = settings;
    }

    @SuppressWarnings("deprecation")
    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type,
                                                    DeserializationConfig config,
                                                    BeanDescription beanDesc) throws JsonMappingException {
        Class<?> raw = type.getRawClass();
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherDeserializer(type);
        }
        if (Tuple.class.isAssignableFrom(raw)) {
            return new TupleDeserializer(type);
        }
        if (io.vavr.λ.class.isAssignableFrom(raw)) {
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
            return new LazyDeserializer(type);
        }
        if (raw == Option.class) {
            return new OptionDeserializer(type, settings.useOptionInPlainFormat());
        }
        return super.findReferenceDeserializer(type, config, beanDesc, contentTypeDeserializer, contentDeserializer);
    }

    @Override
    public JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType type,
                                                              DeserializationConfig config, BeanDescription beanDesc,
                                                              TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer)
            throws JsonMappingException
    {
        Class<?> raw = type.getRawClass();
        if (raw == CharSeq.class) {
            return new CharSeqDeserializer(type);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new SeqDeserializer(type, settings.deserializeNullAsEmptyCollection());
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new SetDeserializer(type, settings.deserializeNullAsEmptyCollection());
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return new PriorityQueueDeserializer(type, settings.deserializeNullAsEmptyCollection());
        }
        return super.findCollectionLikeDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
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
            return new MapDeserializer(type);
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return new MultimapDeserializer(type);
        }
        return super.findMapLikeDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
    }
}
