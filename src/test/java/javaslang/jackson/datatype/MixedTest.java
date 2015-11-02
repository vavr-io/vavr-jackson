package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.Value;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MixedTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        Object src = HashMap.empty().put("key1", List.of(1, 2)).put("key2", List.of(3, 4));
        String extended = mapper(false).writer().writeValueAsString(src);
        String compact = mapper(true).writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(extended, HashMap.class), src);
        Assert.assertEquals(mapper().readValue(compact, new TypeReference<HashMap<?, List<?>>>() {}), src);
    }

    @Test
    public void test2() throws IOException {
        Object src = List.of(HashMap.empty().put("key1", 1), HashMap.empty().put("key2", 2));
        String extended = mapper(false).writer().writeValueAsString(src);
        String compact = mapper(true).writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(extended, List.class), src);
        Assert.assertEquals(mapper().readValue(compact, new TypeReference<List<HashMap<?, ?>>>() {}), src);
    }

    @Test
    public void test3() throws IOException {
        java.util.Map<String, String> javaHMap = new java.util.HashMap<>();
        javaHMap.put("1", "2");
        List<?> src = List.of(javaHMap);
        List<?> extendedRestored = mapper().readValue(mapper(false).writer().writeValueAsString(src), List.class);
        List<?> compactRestored = mapper().readValue(mapper(true).writer().writeValueAsString(src), List.class);
        Assert.assertEquals(extendedRestored, src);
        Assert.assertEquals(compactRestored, src);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        mapper().readValue("[\"s\"]", new TypeReference<List<Integer>>() {});
    }

    @Test
    public void test5() throws IOException {
        Assert.assertEquals(mapper().readValue("[{\"@data\":1}]", new TypeReference<List<Integer>>() {}), List.of(1));
    }

    @Test
    public void test6() throws IOException {
        String json = mapper(false).writer().writeValueAsString(List.of(List.of(1)));
        Assert.assertEquals(List.of(List.of(1)), mapper().readValue(json, Value.class));
    }

    @Test(expected = JsonMappingException.class)
    public void test7() throws IOException {
        String json = mapper(false).writer().writeValueAsString(List.of(List.of(1)));
        Assert.assertEquals(List.of(List.of(1)), mapper().readValue(crashJson(json), Value.class));
    }
}
