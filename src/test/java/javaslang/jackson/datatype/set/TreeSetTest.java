package javaslang.jackson.datatype.set;

import javaslang.collection.Set;
import javaslang.collection.TreeSet;

import java.util.Arrays;

public class TreeSetTest extends SetTest {
    @Override
    Class<?> clz() {
        return TreeSet.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    Set<?> of(Object... objects) {
        return TreeSet.ofAll((o1, o2) -> ((Comparable<Object>) o1).compareTo(o2), Arrays.asList(objects));
    }
}
