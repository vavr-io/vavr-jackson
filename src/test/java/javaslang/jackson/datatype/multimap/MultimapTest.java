package javaslang.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javaslang.Tuple;
import javaslang.collection.HashMap;
import javaslang.collection.Multimap;
import javaslang.control.Option;
import javaslang.jackson.datatype.BaseTest;

import static java.util.Arrays.asList;

public abstract class MultimapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Multimap<K, V> emptyMap();

    protected abstract TypeReference<? extends Multimap<String, Option<Integer>>> typeReferenceWithOption();

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

    @Test
    public void testWithOption() throws Exception {
        Multimap<String, Option<Integer>> multimap = this.<String, Option<Integer>>emptyMap().put("1", Option.some(1)).put("1", Option.none());
        String json = genJsonMap(HashMap.of("1", asList(1, null)).toJavaMap());

        verifySerialization(typeReferenceWithOption(), javaslang.collection.List.of(Tuple.of(multimap, json)));
    }

    public static class Parameterized<T1, T2> {
        public Multimap<T1, T2> value;
        public Parameterized() {}
        public Parameterized(Multimap<T1, T2> value) {
            this.value = value;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWrappedParameterizedSome() throws IOException {
        String expected = "{\"value\":{\"1\":[2]}}";
        Parameterized<Integer, Integer> object = new Parameterized<>(this.<Integer, Integer>emptyMap().put(1, 2));
        Assert.assertEquals(expected, mapper().writeValueAsString(object));
        Parameterized<Integer, Integer> restored = mapper().readValue(expected, new TypeReference<Parameterized<Integer, Integer>>() {});
        Assert.assertEquals(restored.value.get(1).get().head(), (Integer) 2);
    }
}
