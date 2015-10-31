package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.*;

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
        try {
            return (Seq<?>) deserialize(p, javaType, ctxt);
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
    }
}
