/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.*;
import io.vavr.Lazy;
import io.vavr.collection.*;
import io.vavr.control.Option;

import java.lang.reflect.Type;

public class VavrTypeModifier extends TypeModifier {

    @Override
    public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory)
    {
        final Class<?> raw = type.getRawClass();
        if (Seq.class.isAssignableFrom(raw) && CharSeq.class != raw) {
            return CollectionLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (Set.class.isAssignableFrom(raw)) {
            return CollectionLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return CollectionLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (Map.class.isAssignableFrom(raw)) {
            return MapLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0), type.containedTypeOrUnknown(1));
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return MapLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0), type.containedTypeOrUnknown(1));
        }
        if (Lazy.class.isAssignableFrom(raw)) {
            return ReferenceType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (Option.class.isAssignableFrom(raw)) {
            return ReferenceType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        return type;
    }
}
