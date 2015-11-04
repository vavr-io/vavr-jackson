package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Set;

import java.io.IOException;

class SetSerializer<T extends Set<?>> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    SetSerializer(JavaType type, Class<?> clz) {
        super(type, clz);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        return value.toJavaList();
    }

}
