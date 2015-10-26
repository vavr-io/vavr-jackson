package javaslang.jackson.datatype.seq;

import javaslang.collection.Seq;
import javaslang.collection.Vector;

import java.util.Arrays;

public class VectorTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Vector.class;
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Vector.ofAll(Arrays.asList(objects));
    }
}
