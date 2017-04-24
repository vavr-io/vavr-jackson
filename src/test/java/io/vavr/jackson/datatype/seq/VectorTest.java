package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;

import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Option;

public class VectorTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Vector.class;
    }

    @Override
    protected TypeReference<Vector<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Vector<Option<String>>>() {};
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Vector.ofAll(Arrays.asList(objects));
    }
}
