package javaslang.jackson.datatype.seq;

import javaslang.collection.Array;
import javaslang.collection.Seq;

import java.util.Arrays;

public class ArrayTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Array.class;
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Array.ofAll(Arrays.asList(objects));
    }
}
