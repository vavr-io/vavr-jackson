package javaslang.jackson.datatype.seq;

import javaslang.collection.Stack;

import java.util.Arrays;

public class StackTest extends SeqTest {

    @Override
    protected Class<?> clz() {
        return Stack.class;
    }

    @Override
    protected Stack<?> of(Object... objects) {
        return Stack.ofAll(Arrays.asList(objects));
    }

}
