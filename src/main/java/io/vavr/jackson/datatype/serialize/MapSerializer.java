/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.Map;

import java.io.IOException;
import java.util.LinkedHashMap;

class MapSerializer extends ValueSerializer<Map<?, ?>> {

    private static final long serialVersionUID = 1L;

    MapSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Map<?, ?> value) throws IOException {
        return value.toJavaMap();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        MapLikeType mapLikeType = (MapLikeType) type;
        return typeFactory.constructMapType(LinkedHashMap.class, mapLikeType.getKeyType(), mapLikeType.getContentType());
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Map<?, ?> value) {
        return value.isEmpty();
    }
}
