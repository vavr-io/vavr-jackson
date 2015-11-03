package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Traversable;

import java.io.IOException;

class TraversableSerializer<T extends Traversable<?>> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    TraversableSerializer(JavaType type, Class<?> clz, boolean compact) {
        super(type, clz, compact);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        return value.toJavaList();
    }

}
