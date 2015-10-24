package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.List;

import java.io.IOException;

class ListDeserializer extends StdDeserializer<List<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    protected ListDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    @Override
    public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            final BaseDeserializer deserializer = new BaseDeserializer(ctxt);
            return List.ofAll((java.util.List<?>) deserializer.deserialize(p, javaType));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
