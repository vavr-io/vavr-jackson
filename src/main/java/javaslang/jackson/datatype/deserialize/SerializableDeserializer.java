package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class SerializableDeserializer<T> extends StdDeserializer<T> {

    private static final long serialVersionUID = 1L;

    SerializableDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonDeserializer<Object> deserializer = ctxt.findRootValueDeserializer(ctxt.constructType(byte[].class));
        byte[] bytes = (byte[]) deserializer.deserialize(p, ctxt);
        return deserialize(bytes);
    }

    @SuppressWarnings("unchecked")
    private static <T> T deserialize(byte[] objectData) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(objectData));
            return (T) stream.readObject();
        } catch (Exception x) {
            throw new IllegalStateException("Error deserializing object", x);
        }
    }
}
