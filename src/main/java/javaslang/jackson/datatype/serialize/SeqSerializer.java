package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Seq;

import java.io.IOException;

class SeqSerializer<T extends Seq<?>> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    SeqSerializer(JavaType type, Class<?> clz, boolean compact) {
        super(type, clz, compact);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        return value.toJavaList();
    }

}
