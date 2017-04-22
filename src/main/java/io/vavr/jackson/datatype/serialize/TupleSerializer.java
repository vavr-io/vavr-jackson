/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.Tuple;

import java.io.IOException;
import java.util.ArrayList;

class TupleSerializer extends ValueSerializer<Tuple> {

    private static final long serialVersionUID = 1L;

    TupleSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Tuple tuple) throws IOException {
        return tuple.toSeq().toJavaList();
    }

    @Override
    JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory) {
        return typeFactory.constructCollectionType(ArrayList.class, Object.class);
    }

}
