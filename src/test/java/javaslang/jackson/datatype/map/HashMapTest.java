package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import javaslang.collection.HashMap;
import javaslang.collection.Map;
import javaslang.control.Option;

public class HashMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return HashMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return HashMap.empty();
    }

    @Override
    protected TypeReference<HashMap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<HashMap<String, Option<Integer>>>() {};
    }

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("{\"1\":\"2\"}", Map.class), HashMap.empty().put("1", "2"));
    }
}
