package javaslang.jackson.datatype.seq;

import javaslang.collection.Queue;
import javaslang.collection.Seq;

import java.util.Arrays;

public class QueueTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Queue.class;
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Queue.ofAll(Arrays.asList(objects));
    }
}
