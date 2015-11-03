package javaslang.jackson.datatype.set;

import javaslang.collection.HashSet;
import javaslang.collection.Set;

import java.util.Arrays;

public class HashSetTest extends SetTest {
    @Override
    Class<?> clz() {
        return HashSet.class;
    }

    @Override
    Set<?> of(Object... objects) {
        return HashSet.ofAll(Arrays.asList(objects));
    }
}
