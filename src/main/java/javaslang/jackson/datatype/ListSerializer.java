package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import javaslang.collection.List;

import java.io.IOException;

public class ListSerializer extends SeqSerializer<List<?>> {

    private static final long serialVersionUID = 1L;

    protected ListSerializer(JavaType type, boolean compact) {
        super(type, compact);
    }

    @Override
    public void serialize(List<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        serialize(value, List.class, gen);
    }
}
