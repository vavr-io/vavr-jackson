package javaslang.jackson.datatype.multimap;

import javaslang.collection.Multimap;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MultimapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Multimap<K, V> emptyMap();

    @Test
    public void test1() throws IOException {
        Multimap<Object, Object> javaslangObject = emptyMap().put("1", 2).put("2", 3).put("2", 4);
        java.util.Map<Object, List<Object>> javaObject = new java.util.HashMap<>();
        javaObject.put("1", Collections.singletonList(2));
        List<Object> list = new ArrayList<>();
        Collections.addAll(list, 3, 4);
        javaObject.put("2", list);

        String json = mapper().writer().writeValueAsString(javaslangObject);
        Assert.assertEquals(genJsonMap(javaObject), json);

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        Assert.assertEquals(restored, javaslangObject);
    }
}
