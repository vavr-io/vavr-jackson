package io.vavr.jackson.datatype.seq;

import tools.jackson.core.type.TypeReference;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.control.Option;

import java.util.Arrays;

public class QueueTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Queue.class;
    }

    @Override
    protected TypeReference<Queue<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Queue<Option<String>>>() {
        };
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Queue.ofAll(Arrays.asList(objects));
    }
}
