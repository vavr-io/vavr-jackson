package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.CharSeq;

import java.io.IOException;

class CharSeqDeserializer extends StdDeserializer<CharSeq> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    protected CharSeqDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @Override
    public CharSeq deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            final BaseDeserializer deserializer = new BaseDeserializer(ctxt);
            return (CharSeq) deserializer.deserialize(p, javaType);
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(this.getClass());
        }
    }
}
