/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.CharSeq;

import java.io.IOException;

class CharSeqSerializer extends ValueSerializer<CharSeq> {

    private static final long serialVersionUID = 1L;

    CharSeqSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(CharSeq value) throws IOException {
        return value.toString();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, CharSeq value) {
        return value.isEmpty();
    }
}
