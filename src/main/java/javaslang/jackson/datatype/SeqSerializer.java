package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javaslang.collection.Seq;

import java.io.IOException;

public abstract class SeqSerializer<T> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    private final boolean compact;

    protected SeqSerializer(JavaType type, boolean compact) {
        super(type);
        this.compact = compact;
    }

    public void serialize(Seq<?> value, Class<?> seqClass, JsonGenerator gen) throws IOException {
        if (compact) {
            gen.writeObject(value.toJavaList());
        } else {
            gen.writeStartObject();
            gen.writeFieldName("@class");
            gen.writeString(seqClass.getCanonicalName());
            gen.writeFieldName("@data");
            gen.writeObject(value.toJavaList());
            gen.writeEndObject();
        }
    }
}
