package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Set;

import java.io.IOException;

class SetDeserializer extends BaseDeserializer<Set<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SetDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    @Override
    public Set<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return (Set<?>) _deserialize(p, javaType, ctxt);
    }
}
