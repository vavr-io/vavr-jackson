/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.Lazy;

import java.io.IOException;

class LazySerializer extends ValueSerializer<Lazy<?>> {

    private static final long serialVersionUID = 1L;

    LazySerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Lazy<?> value) throws IOException {
        return value.get();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        return type.containedType(0);
    }
}
