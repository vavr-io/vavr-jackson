package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Set;

import java.io.IOException;

class SetSerializer extends ValueSerializer<Set<?>> {

    private static final long serialVersionUID = 1L;

    SetSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Set<?> value) throws IOException {
        return value.toJavaList();
    }

}
