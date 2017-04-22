/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

abstract class ValueDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;
    private final int typeCount;
    private final List<JsonDeserializer<?>> deserializers;

    ValueDeserializer(JavaType valueType, int typeCount) {
        super(valueType);
        this.javaType = valueType;
        this.typeCount = typeCount;
        this.deserializers = new ArrayList<>(typeCount);
    }

    int deserializersCount() {
        return deserializers.size();
    }

    JsonDeserializer<?> deserializer(int index) {
        return deserializers.get(index);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        // TODO rewrite this
        if (javaType.isCollectionLikeType() || javaType.isReferenceType()) {
            deserializers.add(ctxt.findRootValueDeserializer(javaType.getContentType()));
            return;
        }
        for (int i = 0; i < typeCount; i++) {
            JavaType containedType = javaType.containedTypeOrUnknown(i);
            deserializers.add(ctxt.findRootValueDeserializer(containedType));
        }
    }

    // DEV-NOTE: original method is deprecated since 2.8
    static JsonMappingException mappingException(DeserializationContext ctxt, Class<?> targetClass, JsonToken token) {
        String tokenDesc = (token == null) ? "<end of input>" : String.format("%s token", token);
        return JsonMappingException.from(ctxt.getParser(),
                String.format("Can not deserialize instance of %s out of %s",
                        _calcName(targetClass), tokenDesc));
    }

    private static String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return _calcName(cls.getComponentType())+"[]";
        }
        return cls.getName();
    }

}
