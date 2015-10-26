package javaslang.jackson.datatype.seq;

import javaslang.collection.List;
import javaslang.collection.Seq;

import java.util.Arrays;

public class ListTest extends SeqTest {

    @Override
    protected Class<?> clz() {
        return List.class;
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return List.ofAll(Arrays.asList(objects));
    }

}
