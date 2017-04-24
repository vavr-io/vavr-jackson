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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

abstract class ArrayDeserializer<T> extends ValueDeserializer<T> {

    private static final long serialVersionUID = 1L;

    private final JavaType valueType;
    private final boolean deserializeNullAsEmptyCollection;

    ArrayDeserializer(JavaType valueType, int typeCount, boolean deserializeNullAsEmptyCollection) {
        super(valueType, typeCount);
        this.valueType = valueType;
        this.deserializeNullAsEmptyCollection = deserializeNullAsEmptyCollection;
    }

    abstract T create(List<Object> list, DeserializationContext ctxt) throws JsonMappingException;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<?> deserializer = deserializer(0);
        List<Object> list = new ArrayList<>();
        if (!p.isExpectedStartArrayToken()) {
            throw mappingException(ctxt, valueType.getRawClass(), p.getCurrentToken());
        }
        for (JsonToken jsonToken = p.nextToken(); jsonToken != END_ARRAY; jsonToken = p.nextToken()) {
            Object value = (jsonToken != VALUE_NULL) ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
            list.add(value);
        }
        return create(list, ctxt);
    }

    @Override
    public T getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        if (deserializeNullAsEmptyCollection) {
            return create(Collections.emptyList(), ctxt);
        }
        return super.getNullValue(ctxt);
    }

    static void checkContainedTypeIsComparable(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        Class<?> clz = type.getRawClass();
        if (clz == Object.class || !Comparable.class.isAssignableFrom(clz)) {
            throw mappingException(ctxt, clz, null);
        }
    }
}
