/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SerializableSerializer<T> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    SerializableSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(buf);
        stream.writeObject(value);
        return buf.toByteArray();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        return typeFactory.constructArrayType(byte.class);
    }

}
