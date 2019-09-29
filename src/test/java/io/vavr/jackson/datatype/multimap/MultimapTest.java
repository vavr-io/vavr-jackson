package io.vavr.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class MultimapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Multimap<K, V> emptyMap();

    protected abstract TypeReference<? extends Multimap<String, Option<Integer>>> typeReferenceWithOption();

    @Test
    public void test1() throws IOException {
        Multimap<Object, Object> vavrObject = emptyMap().put("1", 2).put("2", 3).put("2", 4);
        java.util.Map<Object, List<Object>> javaObject = new java.util.HashMap<>();
        javaObject.put("1", Collections.singletonList(2));
        List<Object> list = new ArrayList<>();
        Collections.addAll(list, 3, 4);
        javaObject.put("2", list);

        String json = mapper().writer().writeValueAsString(vavrObject);
        Assert.assertEquals(genJsonMap(javaObject), json);

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        Assert.assertEquals(restored, vavrObject);
    }

    @Test
    public void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        Multimap<Object, Object> src = emptyMap().put("1", 2).put("2", 3).put("2", 4);
        Multimap<?, ?> restored = (Multimap<?, ?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test(expected = JsonMappingException.class)
    public void testDeserializeValueNull() throws IOException {
        mapper().readValue("{\"k\":null}", clz());
    }

    @Test
    public void testWithOption() throws Exception {
        Multimap<String, Option<Integer>> multimap = this.<String, Option<Integer>>emptyMap().put("1", Option.some(1)).put("1", Option.none());
        String json = genJsonMap(HashMap.of("1", asList(1, null)).toJavaMap());

        verifySerialization(typeReferenceWithOption(), io.vavr.collection.List.of(Tuple.of(multimap, json)));
    }
}
