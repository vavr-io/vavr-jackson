package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.CharSeq;

class CharSeqSerializer extends ValueSerializer<CharSeq> {

    private static final long serialVersionUID = 1L;

    CharSeqSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(CharSeq value) {
        return value.toString();
    }
}
