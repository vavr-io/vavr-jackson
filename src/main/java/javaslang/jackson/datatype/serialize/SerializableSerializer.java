package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SerializableSerializer<T> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    SerializableSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(buf);
        stream.writeObject(value);
        return buf.toByteArray();
    }

}
