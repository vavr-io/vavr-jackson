package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.collection.Set;
import javaslang.collection.TreeSet;
import org.junit.Test;

import java.io.IOException;
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

    @Test(expected = JsonMappingException.class)
    public void testGeneric1() throws IOException {
        mapper().readValue("[1, 2]", TreeSet.class);
    }

    @Test(expected = JsonMappingException.class)
    public void testGeneric2() throws IOException {
        mapper().readValue("[1, 2]", new TypeReference<TreeSet<Object>>() {});
    }

    static class Incomparable {
        private int i = 0;
        int getI() { return i; }
        void setI(int i) { this.i = i; }
    }

    @Test(expected = JsonMappingException.class)
    public void testGeneric3() throws IOException {
        mapper().readValue("[{\"i\":1}, {\"i\":2}]", new TypeReference<TreeSet<Incomparable>>() {});
    }
}
