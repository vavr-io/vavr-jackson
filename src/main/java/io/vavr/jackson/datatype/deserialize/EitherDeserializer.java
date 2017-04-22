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

import io.vavr.control.Either;

import static com.fasterxml.jackson.core.JsonToken.*;

class EitherDeserializer extends ValueDeserializer<Either<?, ?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;
    private JsonDeserializer<?> stringDeserializer;

    EitherDeserializer(JavaType valueType) {
        super(valueType, 2);
        this.javaType = valueType;
    }

    @Override
    public Either<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonToken nextToken = p.getCurrentToken();

        if (nextToken == START_ARRAY) {
            boolean right = false;
            Object value = null;
            int cnt = 0;

            for (JsonToken jsonToken = p.nextToken(); jsonToken != END_ARRAY; jsonToken = p.nextToken()) {
                cnt++;
                switch (cnt) {
                    case 1:
                        String def = (String) stringDeserializer.deserialize(p, ctxt);
                        if (isRight(def)) {
                            right = true;
                        } else if (isLeft(def)) {
                            right = false;
                        } else {
                            throw mappingException(ctxt, javaType.getRawClass(), jsonToken);
                        }
                        break;
                    case 2:
                        JsonDeserializer<?> deserializer = right ? deserializer(1) : deserializer(0);
                        value = (jsonToken != VALUE_NULL) ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
                        break;
                }
            }
            if (cnt != 2) {
                throw mappingException(ctxt, javaType.getRawClass(), null);
            }
            return right ? Either.right(value) : Either.left(value);
        } else if (nextToken == START_OBJECT) {
            final JsonToken currentToken = p.getCurrentToken();
            final String type = p.nextFieldName();
            if (isRight(type)) {
                final JsonDeserializer<?> deserializer = deserializer(1);
                final Object value = p.nextToken() != VALUE_NULL ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
                return Either.right(value);
            } else if (isLeft(type)) {
                final JsonDeserializer<?> deserializer = deserializer(0);
                final Object value = p.nextToken() != VALUE_NULL ? deserializer.deserialize(p, ctxt) : deserializer.getNullValue(ctxt);
                return Either.left(value);
            } else {
                throw mappingException(ctxt, javaType.getRawClass(), currentToken);
            }
        } else {
            throw mappingException(ctxt, javaType.getRawClass(), p.getCurrentToken());
        }
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        super.resolve(ctxt);
        stringDeserializer = ctxt.findContextualValueDeserializer(ctxt.constructType(String.class), null);
    }

    private static boolean isRight(final String fieldName) {
        return "right".equals(fieldName) || "r".equals(fieldName);
    }

    private static boolean isLeft(final String fieldName) {
        return "left".equals(fieldName) || "l".equals(fieldName);
    }
}
