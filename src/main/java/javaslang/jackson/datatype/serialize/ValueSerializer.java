package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

abstract class ValueSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    private final boolean compact;
    private final Class<?> clz;

    ValueSerializer(JavaType type, Class<?> clz, boolean compact) {
        super(type);
        this.compact = compact;
        this.clz = clz;
    }

    abstract Object toJavaObj(T value) throws IOException;

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (compact) {
            gen.writeObject(toJavaObj(value));
        } else {
            gen.writeStartObject();
            gen.writeFieldName("@class");
            gen.writeString(clz.getCanonicalName());
            gen.writeFieldName("@data");
            gen.writeObject(toJavaObj(value));
            gen.writeEndObject();
        }
    }
}
