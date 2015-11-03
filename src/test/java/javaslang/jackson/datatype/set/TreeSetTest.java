package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import javaslang.collection.Set;
import javaslang.collection.TreeSet;

import java.util.Arrays;

public class TreeSetTest extends SetTest {
    @Override
    Class<?> clz() {
        return TreeSet.class;
    }

    @Override
    TypeReference<?> typeReference() {
        return new TypeReference<TreeSet<Integer>>() {};
    }

    @Override
    Set<Integer> of(Integer... objects) {
        return TreeSet.ofAll(Integer::compareTo, Arrays.asList(objects));
    }
}
