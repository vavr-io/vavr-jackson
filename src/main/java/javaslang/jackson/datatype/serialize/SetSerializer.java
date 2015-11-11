package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Set;

class SetSerializer extends ValueSerializer<Set<?>> {

    private static final long serialVersionUID = 1L;

    SetSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Set<?> value) {
        return value.toJavaList();
    }

}
