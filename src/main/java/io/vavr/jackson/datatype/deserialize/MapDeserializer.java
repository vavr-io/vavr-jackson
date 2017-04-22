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
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.*;

import java.io.IOException;

class MapDeserializer extends MaplikeDeserializer<Map<?, ?>> {

    private static final long serialVersionUID = 1L;

    MapDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public Map<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final java.util.List<Tuple2<Object, Object>> result = new java.util.ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.getCurrentName();
            Object key = keyDeserializer.deserializeKey(name, ctxt);
            p.nextToken();
            result.add(Tuple.of(key, valueDeserializer.deserialize(p, ctxt)));
        }
        if (SortedMap.class.isAssignableFrom(handledType())) {
            return TreeMap.ofEntries(keyComparator, result);
        }
        if (LinkedHashMap.class.isAssignableFrom(handledType())) {
            return LinkedHashMap.ofEntries(result);
        }
        // default deserialization [...] -> Map
        return HashMap.ofEntries(result);
    }
}
