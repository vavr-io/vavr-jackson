package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.List;

import java.io.IOException;

public class ListDeserializer extends StdDeserializer<List<?>> {

    private static final long serialVersionUID = 1L;

    private final BaseDeserializer deserializer = new BaseDeserializer();

    protected ListDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return List.ofAll((java.util.List<?>) deserializer.deserialize(p, ctxt));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
