package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Map;

import java.io.IOException;

class MapDeserializer extends BaseDeserializer<Map<?,?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    MapDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    @Override
    public Map<?,?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return (Map<?, ?>) _deserialize(p, javaType, ctxt);
    }
}
