package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.CharSeq;

import java.io.IOException;

class CharSeqSerializer extends ValueSerializer<CharSeq> {

    private static final long serialVersionUID = 1L;

    CharSeqSerializer(JavaType type, boolean compact) {
        super(type, CharSeq.class, compact);
    }

    @Override
    Object toJavaObj(CharSeq value) throws IOException {
        return value.toString();
    }
}
