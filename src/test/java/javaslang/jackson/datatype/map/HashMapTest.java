package javaslang.jackson.datatype.map;

import javaslang.collection.HashMap;
import javaslang.collection.Map;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HashMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return HashMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return HashMap.empty();
    }

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("{\"1\":\"2\"}", Map.class), HashMap.empty().put("1", "2"));
    }
}
