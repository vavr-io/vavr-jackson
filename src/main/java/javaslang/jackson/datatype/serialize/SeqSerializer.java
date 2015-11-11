package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Seq;

class SeqSerializer extends ValueSerializer<Seq<?>> {

    private static final long serialVersionUID = 1L;

    SeqSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Seq<?> value) {
        return value.toJavaList();
    }

}
