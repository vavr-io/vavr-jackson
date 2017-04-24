/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.control.Option;

import java.io.IOException;

class OptionSerializer extends ValueSerializer<Option<?>> {

    private static final long serialVersionUID = 1L;

    private final boolean plainMode;

    OptionSerializer(JavaType type, boolean plainMode) {
        super(type);
        this.plainMode = plainMode;
    }


    @Override
    public void serialize(Option<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (plainMode) {
            super.serialize(value, gen, provider);
        } else {
            gen.writeStartArray();
            if (value.isDefined()) {
                gen.writeString("defined");
                super.serialize(value, gen, provider);
            } else {
                gen.writeString("undefined");
            }
            gen.writeEndArray();
        }
    }

    @Override
    Object toJavaObj(Option<?> value) throws IOException {
        // plain mode only
        if (value.isDefined()) {
            return value.get();
        } else {
            return null;
        }
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        // plain mode only
        return type.containedType(0);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Option<?> value) {
        return value.isEmpty();
    }
}
