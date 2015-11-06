package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.core.JsonParseException;
import javaslang.collection.Map;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public abstract class MapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Map<K, V> emptyMap();

    @Test
    public void test1() throws IOException {
        Map<Object, Object> javaslangObject = emptyMap().put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.HashMap<>();
        javaObject.put("1", 2);

        String json = mapper().writer().writeValueAsString(javaslangObject);
        Assert.assertEquals(genJsonMap(javaObject), json);

        Map<?, ?> restored = (Map<?, ?>) mapper().readValue(json, clz());
        Assert.assertEquals(restored, javaslangObject);
    }

    @Test(expected = JsonParseException.class)
    public void test4() throws IOException {
        mapper().readValue("{1: 1}", clz());
    }
}
