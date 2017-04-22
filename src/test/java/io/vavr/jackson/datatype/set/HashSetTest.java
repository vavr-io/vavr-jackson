package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public class HashSetTest extends SetTest {

    @Override
    protected Class<?> clz() {
        return HashSet.class;
    }

    @Override
    protected TypeReference<HashSet<Integer>> typeReference() {
        return new TypeReference<HashSet<Integer>>() {};
    }

    @Override
    protected TypeReference<HashSet<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<HashSet<Option<String>>>() {};
    }

    @Override
    protected Set<?> of(Object... objects) {
        return HashSet.ofAll(Arrays.asList(objects));
    }

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("[1]", Set.class), HashSet.of(1));
    }
}
