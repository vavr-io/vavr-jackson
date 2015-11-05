package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("[1]", Set.class), HashSet.empty().add(1)); // TODO: HashSet.of(1)
    }
}
