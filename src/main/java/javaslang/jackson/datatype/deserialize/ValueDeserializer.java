package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.Value;

import java.io.IOException;

public class ValueDeserializer extends BaseDeserializer<Value<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    ValueDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @Override
    public Value<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return (Value<?>) _deserialize(p, javaType, ctxt);
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
    }
}
