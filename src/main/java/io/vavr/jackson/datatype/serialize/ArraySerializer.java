/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.Value;

import java.io.IOException;
import java.util.ArrayList;

class ArraySerializer<T extends Value<?>> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    ArraySerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        return value.toJavaList();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        CollectionLikeType collectionLikeType = (CollectionLikeType) type;
        return typeFactory.constructCollectionType(ArrayList.class, collectionLikeType.getContentType());
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, T value) {
        return value.isEmpty();
    }
}
