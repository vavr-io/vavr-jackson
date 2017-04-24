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
import io.vavr.collection.PriorityQueue;

import java.util.List;

class PriorityQueueDeserializer extends ArrayDeserializer<PriorityQueue<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    PriorityQueueDeserializer(JavaType valueType, boolean deserializeNullAsEmptyCollection) {
        super(valueType, 1, deserializeNullAsEmptyCollection);
        javaType = valueType;
    }

    @SuppressWarnings("unchecked")
    @Override
    PriorityQueue<?> create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException {
        checkContainedTypeIsComparable(ctxt, javaType.containedTypeOrUnknown(0));
        return PriorityQueue.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), list);
    }
}
