package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Option;

import java.util.Arrays;

public class StreamTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Stream.class;
    }

    @Override
    protected TypeReference<Stream<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Stream<Option<String>>>() {
        };
    }

    @Override
    protected String implClzName() {
        return "io.vavr.collection.StreamModule$ConsImpl";
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Stream.ofAll(Arrays.asList(objects));
    }
}
