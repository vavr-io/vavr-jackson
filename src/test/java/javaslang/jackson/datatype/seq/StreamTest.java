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
    protected Class<?> implClz() {
        return Stream.Cons.class;
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Stream.ofAll(Arrays.asList(objects));
    }
}
