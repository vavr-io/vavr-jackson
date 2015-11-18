package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import javaslang.collection.LinkedHashSet;
import javaslang.collection.Set;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class LinkedHashSetTest extends SetTest {

    @Override
    Class<?> clz() {
        return LinkedHashSet.class;
    }

    @Override
    TypeReference<?> typeReference() {
        return new TypeReference<LinkedHashSet<Integer>>() {};
    }

    @Override
    Set<Integer> of(Integer... objects) {
        return LinkedHashSet.ofAll(Arrays.asList(objects));
    }

    @Test
    public void testKeepOrder() throws IOException {
        Assert.assertEquals(mapper().readValue("[3, 2, 1]", LinkedHashSet.class), LinkedHashSet.ofAll(3, 2, 1));
    }
}
