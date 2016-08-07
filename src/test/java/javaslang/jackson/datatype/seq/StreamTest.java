package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;

import javaslang.collection.Seq;
import javaslang.collection.Stream;
import javaslang.control.Option;

public class StreamTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Stream.class;
    }

    @Override
    protected TypeReference<Stream<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Stream<Option<String>>>() {};
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
