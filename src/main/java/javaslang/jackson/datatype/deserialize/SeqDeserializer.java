package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.*;

import java.io.IOException;

abstract class SeqDeserializer extends BaseDeserializer<Seq<?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    SeqDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    abstract Seq<?> it(java.lang.Iterable<?> elements);

    @Override
    public Seq<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return it((Iterable<?>) deserialize(p, javaType, ctxt));
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
    }

    static class AsArray extends SeqDeserializer {

        private static final long serialVersionUID = 1L;

        AsArray(JavaType valueType) {
            super(valueType);
        }

        @Override
        Seq<?> it(Iterable<?> elements) {
            return Array.ofAll(elements);
        }
    }

    static class AsList extends SeqDeserializer {

        private static final long serialVersionUID = 1L;

        AsList(JavaType valueType) {
            super(valueType);
        }

        @Override
        Seq<?> it(Iterable<?> elements) {
            return List.ofAll(elements);
        }
    }

    static class AsQueue extends SeqDeserializer {

        private static final long serialVersionUID = 1L;

        AsQueue(JavaType valueType) {
            super(valueType);
        }

        @Override
        Seq<?> it(Iterable<?> elements) {
            return Queue.ofAll(elements);
        }
    }

    static class AsStack extends SeqDeserializer {

        private static final long serialVersionUID = 1L;

        AsStack(JavaType valueType) {
            super(valueType);
        }

        @Override
        Seq<?> it(Iterable<?> elements) {
            return Stack.ofAll(elements);
        }
    }

    static class AsStream extends SeqDeserializer {

        private static final long serialVersionUID = 1L;

        AsStream(JavaType valueType) {
            super(valueType);
        }

        @Override
        Seq<?> it(Iterable<?> elements) {
            return Stream.ofAll(elements);
        }
    }

    static class AsVector extends SeqDeserializer {

        private static final long serialVersionUID = 1L;

        AsVector(JavaType valueType) {
            super(valueType);
        }

        @Override
        Seq<?> it(Iterable<?> elements) {
            return Vector.ofAll(elements);
        }
    }
}
