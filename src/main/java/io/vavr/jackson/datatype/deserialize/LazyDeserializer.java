/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import io.vavr.Lazy;

import java.io.IOException;

class LazyDeserializer extends ValueDeserializer<Lazy<?>> {

    private static final long serialVersionUID = 1L;

    LazyDeserializer(JavaType valueType) {
        super(valueType, 1);
    }

    @Override
    public Lazy<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object obj = deserializer(0).deserialize(p, ctxt);
        return Lazy.of(() -> obj);
    }

    @Override
    public Lazy<?> getNullValue(DeserializationContext ctxt) {
        return Lazy.of(() -> null);
    }
}
