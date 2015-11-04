package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Seq;

import java.io.IOException;

class SeqDeserializer extends BaseDeserializer<Seq<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SeqDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    @Override
    public Seq<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return (Seq<?>) _deserialize(p, javaType, ctxt);
    }
}
