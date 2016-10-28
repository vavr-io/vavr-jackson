package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import javaslang.Tuple;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.control.Option;
import javaslang.jackson.datatype.BaseTest;

public abstract class MapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Map<K, V> emptyMap();

    protected abstract TypeReference<? extends Map<String, Option<Integer>>> typeReferenceWithOption();

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

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    @Test(expected = JsonParseException.class)
    public void test4() throws IOException {
        mapper().readValue("{1: 1}", clz());
    }

    @Test
    public void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(emptyMap().put("1", Option.some(1)), genJsonMap(HashMap.of("1", 1).toJavaMap())),
                Tuple.of(emptyMap().put("1", Option.none()), genJsonMap(HashMap.of("1", null).toJavaMap()))
        ));
    }

    public static class Parameterized<T1, T2> {
        public Map<T1, T2> value;
        public Parameterized() {}
        public Parameterized(Map<T1, T2> value) {
            this.value = value;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWrappedParameterizedSome() throws IOException {
        String expected = "{\"value\":{\"1\":2}}";
        Parameterized<Integer, Integer> object = new Parameterized<>(this.<Integer, Integer>emptyMap().put(1, 2));
        Assert.assertEquals(expected, mapper().writeValueAsString(object));
        Parameterized<Integer, Integer> restored = mapper().readValue(expected, new TypeReference<Parameterized<Integer, Integer>>() {});
        Assert.assertEquals(restored.value.get(1).get(), (Integer) 2);
    }
}
