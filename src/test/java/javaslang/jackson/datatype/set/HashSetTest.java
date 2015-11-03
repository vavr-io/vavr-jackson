package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import javaslang.collection.HashSet;
import javaslang.collection.Set;

import java.util.Arrays;

public class HashSetTest extends SetTest {

    @Override
    Class<?> clz() {
        return HashSet.class;
    }

    @Override
    TypeReference<?> typeReference() {
        return new TypeReference<HashSet<Integer>>() {};
    }

    @Override
    Set<Integer> of(Integer... objects) {
        return HashSet.ofAll(Arrays.asList(objects));
    }
}
