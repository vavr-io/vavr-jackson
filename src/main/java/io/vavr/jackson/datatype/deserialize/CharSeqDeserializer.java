/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.CharSeq;

import java.io.IOException;

class CharSeqDeserializer extends StdDeserializer<CharSeq> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    private JsonDeserializer<?> deserializer;

    CharSeqDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public CharSeq deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object obj = deserializer.deserialize(p, ctxt);
        if (obj instanceof String) {
            return CharSeq.of((String) obj);
        } else {
            throw ctxt.wrongTokenException(p, JsonToken.VALUE_STRING, "CharSeq can only be deserialized from String");
        }
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        deserializer = ctxt.findContextualValueDeserializer(TypeFactory.unknownType(), null);
    }
}
