package javaslang.jackson.datatype.seq;

import javaslang.collection.Seq;
import javaslang.collection.Stream;

import java.util.Arrays;

public class StreamTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Stream.class;
    }

    @Override
    protected String implClzName() {
        return "javaslang.collection.StreamModule$ConsImpl";
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Stream.ofAll(Arrays.asList(objects));
    }
}
