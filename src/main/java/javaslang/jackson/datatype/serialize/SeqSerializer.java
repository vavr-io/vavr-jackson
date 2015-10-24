package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javaslang.collection.Seq;

import java.io.IOException;

class SeqSerializer<T extends Seq<?>> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    private final boolean compact;
    private final Class<?> clz;

    protected SeqSerializer(JavaType type, Class<?> clz, boolean compact) {
        super(type);
        this.compact = compact;
        this.clz = clz;
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (compact) {
            gen.writeObject(value.toJavaList());
        } else {
            gen.writeStartObject();
            gen.writeFieldName("@class");
            gen.writeString(clz.getCanonicalName());
            gen.writeFieldName("@data");
            gen.writeObject(value.toJavaList());
            gen.writeEndObject();
        }
    }
}
