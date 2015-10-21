package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javaslang.collection.List;

import java.io.IOException;

public class ListSerializer extends StdSerializer<List<?>> {

    private static final long serialVersionUID = 1L;

    protected ListSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(List<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        serializers.defaultSerializeValue(value.toJavaList(), gen);
    }
}
