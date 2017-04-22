/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;

import java.util.Comparator;

abstract class MaplikeDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    final MapLikeType javaType;

    Comparator<Object> keyComparator;
    KeyDeserializer keyDeserializer;
    JsonDeserializer<?> valueDeserializer;

    MaplikeDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = (MapLikeType) valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        JavaType keyType = javaType.getKeyType();
        if (keyType.getRawClass().isAssignableFrom(Comparable.class)) {
            keyComparator = (o1, o2) -> ((Comparable) o1).compareTo(o2);
        } else {
            keyComparator = (o1, o2) -> o1.toString().compareTo(o2.toString());
        }
        keyDeserializer = ctxt.findKeyDeserializer(keyType, null);
        valueDeserializer = ctxt.findRootValueDeserializer(javaType.getContentType());
    }
}
