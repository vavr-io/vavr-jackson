package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import javaslang.collection.List;
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

        String extendedJson = mapper(false).writer().writeValueAsString(javaslangObject);
        String compactJson = mapper(true).writer().writeValueAsString(emptyMap().put("1", 2));

        Assert.assertEquals(genJsonMap(clz(), javaObject), extendedJson);
        Assert.assertEquals(genJsonMap(null, javaObject), compactJson);

        Map<?, ?> extendedResult = (Map<?, ?>) mapper().readValue(extendedJson, clz());
        java.util.Map<?, ?> compactResult = (java.util.Map<?, ?>) mapper().readValue(compactJson, java.util.HashMap.class);

        Assert.assertEquals(extendedResult, javaslangObject);
        Assert.assertEquals(compactResult, javaObject);
    }

    @Test(expected = JsonMappingException.class)
    public void test2() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        String json = writer.writeValueAsString(emptyMap().put(Tuple.of(1, 2), List.of(3, 4)));
        mapper().readValue(json, Tuple.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        mapper().readValue(crashJson(genJsonMap(clz(), new java.util.HashMap<>())), clz());
    }

    @Test(expected = JsonParseException.class)
    public void test4() throws IOException {
        mapper().readValue("{1: 1}", clz());
    }
}
