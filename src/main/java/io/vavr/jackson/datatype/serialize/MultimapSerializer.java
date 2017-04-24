/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.Multimap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class MultimapSerializer extends ValueSerializer<Multimap<?, ?>> {

    private static final long serialVersionUID = 1L;

    MultimapSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Multimap<?, ?> value) throws IOException {
        final LinkedHashMap<Object, List<Object>> result = new LinkedHashMap<>();
        value.forEach(e -> {
            List<Object> list = result.get(e._1);
            if (list == null) {
                list = new ArrayList<>();
                result.put(e._1, list);
            }
            list.add(e._2);
        });
        return result;
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        JavaType containerType = typeFactory.constructCollectionType(ArrayList.class, type.containedType(1));
        return typeFactory.constructMapType(LinkedHashMap.class, type.containedType(0), containerType);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Multimap<?, ?> value) {
        return value.isEmpty();
    }
}
