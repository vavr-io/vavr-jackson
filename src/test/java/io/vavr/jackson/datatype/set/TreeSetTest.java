package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.vavr.collection.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

import io.vavr.control.Option;

import static org.junit.Assert.assertEquals;

public class TreeSetTest extends SetTest {
    @Override
    protected Class<?> clz() {
        return TreeSet.class;
    }

    @Override
    protected TypeReference<TreeSet<Integer>> typeReference() {
        return new TypeReference<TreeSet<Integer>>() {
        };
    }

    @Override
    protected TypeReference<TreeSet<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<TreeSet<Option<String>>>() {
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Set<?> of(Object... objects) {
        List<Comparable<Object>> comparables = List.of(objects).map(value -> (Comparable<Object>) value);
        return TreeSet.ofAll(Comparator.naturalOrder(), comparables);
    }

    static class Clazz{
        private SortedSet<Integer> set;
        public SortedSet<Integer> getSet() {return set;}
        public void setSet(SortedSet<Integer> set) {this.set = set;}
        public boolean equals(Object o){
            return Objects.equals(set, ((Clazz)o).set);
        }
        public int hashCode(){
            return set.hashCode();
        }
    }

    @Test
    public void testDeserializeToSortedSet() throws IOException {
        Clazz c = new Clazz();
        c.setSet(TreeSet.of(1, 3, 5));
        Clazz dc = mapper().readValue(mapper().writeValueAsString(c), Clazz.class);
        assertEquals(c, dc);
    }

    @Test(expected = JsonMappingException.class)
    public void testGeneric1() throws IOException {
        mapper().readValue("[1, 2]", TreeSet.class);
    }

    @Test(expected = JsonMappingException.class)
    public void testGeneric2() throws IOException {
        mapper().readValue("[1, 2]", new TypeReference<TreeSet<Object>>() {});
    }

    @Override
    public void testWithOption() throws IOException {
        // there is nothing to test, because Option is not Comparable and we cannot deserialize a TreeSet<Option<? extends Comparable<?>>
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
