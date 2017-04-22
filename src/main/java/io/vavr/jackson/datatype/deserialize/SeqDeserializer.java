/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.vavr.collection.Array;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.collection.Vector;

import java.util.List;

class SeqDeserializer extends ArrayDeserializer<Seq<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SeqDeserializer(JavaType valueType, boolean deserializeNullAsEmptyCollection) {
        super(valueType, 1, deserializeNullAsEmptyCollection);
        javaType = valueType;
    }

    @Override
    Seq<?> create(List<Object> result, DeserializationContext ctxt) throws JsonMappingException {
        if (Array.class.isAssignableFrom(javaType.getRawClass())) {
            return Array.ofAll(result);
        }
        if (Queue.class.isAssignableFrom(javaType.getRawClass())) {
            return Queue.ofAll(result);
        }
        if (Stream.class.isAssignableFrom(javaType.getRawClass())) {
            return Stream.ofAll(result);
        }
        if (Vector.class.isAssignableFrom(javaType.getRawClass())) {
            return Vector.ofAll(result);
        }
        // default deserialization [...] -> Seq
        return io.vavr.collection.List.ofAll(result);
    }
}
