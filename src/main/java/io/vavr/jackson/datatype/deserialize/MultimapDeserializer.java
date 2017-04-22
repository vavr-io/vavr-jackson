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
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.collection.TreeMultimap;

import java.io.IOException;
import java.util.ArrayList;

class MultimapDeserializer extends MaplikeDeserializer<Multimap<?, ?>> {

    private static final long serialVersionUID = 1L;

    private JsonDeserializer<?> containerDeserializer;

    MultimapDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        super.resolve(ctxt);
        JavaType containerType = ctxt.getTypeFactory().constructCollectionType(ArrayList.class, javaType.getContentType());
        containerDeserializer = ctxt.findContextualValueDeserializer(containerType, null);
    }

    @Override
    public Multimap<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final java.util.List<Tuple2<Object, Object>> result = new java.util.ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String name = p.getCurrentName();
            Object key = keyDeserializer.deserializeKey(name, ctxt);
            p.nextToken();
            ArrayList<?> list = (ArrayList<?>) containerDeserializer.deserialize(p, ctxt);
            for (Object elem : list) {
                result.add(Tuple.of(key, elem));
            }
        }
        if (TreeMultimap.class.isAssignableFrom(handledType())) {
            return TreeMultimap.withSeq().ofEntries(keyComparator, result);
        }
        if (LinkedHashMultimap.class.isAssignableFrom(handledType())) {
            return LinkedHashMultimap.withSeq().ofEntries(result);
        }
        // default deserialization [...] -> Map
        return HashMultimap.withSeq().ofEntries(result);
    }
}
