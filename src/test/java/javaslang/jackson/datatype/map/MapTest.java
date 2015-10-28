package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public abstract class MapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Map<K, V> emptyMap();

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        String result = writer.writeValueAsString(emptyMap().put(Tuple.of(1, 2), List.of(3, 4)));
        java.util.Map<Object, Object> jsmap = new HashMap<>();
        jsmap.put("(1, 2)", List.of(3, 4));
        Assert.assertEquals(genJsonMap(clz(), jsmap), result);
        Map<?, ?> map = (Map<?, ?>) mapper(false).readValue(result, clz());
        Assert.assertEquals(emptyMap().put("(1, 2)", List.of(3, 4)), map);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        mapper(false).readValue(crashJson(genJsonMap(clz(), new HashMap<>())), clz());
    }
}
