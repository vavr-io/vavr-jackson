/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

abstract class ValueSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    JavaType type;

    ValueSerializer(JavaType type) {
        super(type);
        this.type = type;
    }

    abstract Object toJavaObj(T value) throws IOException;
    abstract JavaType emulatedJavaType(JavaType type, TypeFactory typeFactory);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Object obj = toJavaObj(value);
        if (obj == null) {
            provider.getDefaultNullValueSerializer().serialize(null, gen, provider);
        } else {
            JsonSerializer<Object> ser;
            try {
                JavaType emulated = emulatedJavaType(type, provider.getTypeFactory());
                if (emulated.getRawClass() != Object.class) {
                    ser = provider.findTypedValueSerializer(emulated, true, null);
                } else {
                    ser = provider.findTypedValueSerializer(obj.getClass(), true, null);
                }
            } catch (Exception ignore) {
                ser = provider.findTypedValueSerializer(obj.getClass(), true, null);
            }
            ser.serialize(obj, gen, provider);
        }
    }

    @Override
    public void serializeWithType(T value, JsonGenerator gen, SerializerProvider serializers,
                                  TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForScalar(value, gen);
        serialize(value, gen, serializers);
        typeSer.writeTypeSuffixForScalar(value, gen);
    }

}
