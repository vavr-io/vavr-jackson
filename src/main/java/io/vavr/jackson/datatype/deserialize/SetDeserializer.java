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
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import java.util.List;

class SetDeserializer extends ArrayDeserializer<Set<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SetDeserializer(JavaType valueType, boolean deserializeNullAsEmptyCollection) {
        super(valueType, 1, deserializeNullAsEmptyCollection);
        javaType = valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    Set<?> create(List<Object> result, DeserializationContext ctx) throws JsonMappingException {
        if (io.vavr.collection.SortedSet.class.isAssignableFrom(javaType.getRawClass())) {
            checkContainedTypeIsComparable(ctx, javaType.containedTypeOrUnknown(0));
            return io.vavr.collection.TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), result);
        }
        if (io.vavr.collection.LinkedHashSet.class.isAssignableFrom(javaType.getRawClass())) {
            return io.vavr.collection.LinkedHashSet.ofAll(result);
        }
        // default deserialization [...] -> Set
        return HashSet.ofAll(result);
    }
}
