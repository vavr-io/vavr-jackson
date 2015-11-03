package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Traversable;

import java.io.IOException;

class TraversableDeserializer extends BaseDeserializer<Traversable<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    TraversableDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    @Override
    public Traversable<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return (Traversable<?>) _deserialize(p, javaType, ctxt);
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
    }
}
