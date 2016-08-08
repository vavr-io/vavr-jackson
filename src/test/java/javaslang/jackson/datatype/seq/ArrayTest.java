package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;

import javaslang.collection.Array;
import javaslang.collection.Seq;
import javaslang.control.Option;

public class ArrayTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Array.class;
    }

    @Override
    protected TypeReference<Array<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Array<Option<String>>>() {};
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Array.ofAll(Arrays.asList(objects));
    }
}
