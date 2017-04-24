/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.vavr.control.Either;

import java.io.IOException;

class EitherSerializer extends StdSerializer<Either<?, ?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType type;

    EitherSerializer(JavaType type) {
        super(type);
        this.type = type;
    }

    @Override
    public void serialize(Either<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        if (value.isLeft()) {
            gen.writeString("left");
            write(value.left().get(), 0, gen, provider);
        } else {
            gen.writeString("right");
            write(value.right().get(), 1, gen, provider);
        }
        gen.writeEndArray();
    }

    private void write(Object val, int containedTypeIndex, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (val != null) {
            if (type.containedTypeCount() > containedTypeIndex) {
                JsonSerializer<Object> ser;
                JavaType containedType = type.containedType(containedTypeIndex);
                if (containedType.getRawClass() != Object.class) {
                    ser = provider.findTypedValueSerializer(type.containedType(containedTypeIndex), true, null);
                } else {
                    ser = provider.findTypedValueSerializer(val.getClass(), true, null);
                }
                ser.serialize(val, gen, provider);
            } else {
                gen.writeObject(val);
            }
        } else {
            gen.writeNull();
        }
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Either<?, ?> value) {
        return value.isEmpty();
    }
}
