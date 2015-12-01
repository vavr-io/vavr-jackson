package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class MixedTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        Object src = HashMap.empty().put("key1", List.of(1, 2)).put("key2", List.of(3, 4));
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(json, new TypeReference<HashMap<?,List<?>>>() {}), src);
    }

    @Test
    public void test2() throws IOException {
        Object src = List.of(HashMap.empty().put("key1", 1), HashMap.empty().put("key2", 2));
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(json, new TypeReference<List<HashMap<?,?>>>() {}), src);
    }

    @Test
    public void test3() throws IOException {
        java.util.Map<String, String> javaHMap = new java.util.HashMap<>();
        javaHMap.put("1", "2");
        List<?> src = List.of(javaHMap);
        List<?> restored = mapper().readValue(mapper().writer().writeValueAsString(src), List.class);
        Assert.assertEquals(restored, src);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        mapper().readValue("[\"s\"]", new TypeReference<List<Integer>>() {});
    }

    @Test
    public void test5() throws IOException {
        Object src = List.of(List.of(1));
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(json, new TypeReference<List<List<?>>>() {}), src);
    }

    @Test
    public void test6() throws IOException {
        Object src = HashMap.empty().put("1", HashMap.empty().put("X", "Y"));
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(json, new TypeReference<HashMap<?, HashMap<?, ?>>>() {}), src);
    }

    @Test
    public void test7() throws IOException {
        Object src = List.of(Arrays.asList(1, 2));
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(json, List.class), src);
        Assert.assertEquals(mapper().readValue(json, new TypeReference<List<java.util.List<?>>>() {}), src);
    }
}
