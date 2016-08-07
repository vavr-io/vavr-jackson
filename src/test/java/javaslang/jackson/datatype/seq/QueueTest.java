package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;

import javaslang.collection.Queue;
import javaslang.collection.Seq;
import javaslang.control.Option;

public class QueueTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Queue.class;
    }

    @Override
    protected TypeReference<Queue<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Queue<Option<String>>>() {};
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Queue.ofAll(Arrays.asList(objects));
    }
}
