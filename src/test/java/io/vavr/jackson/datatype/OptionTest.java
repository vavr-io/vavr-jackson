package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OptionTest extends BaseTest {

    private static VavrModule.Settings optSettings =
            new VavrModule.Settings().useOptionInPlainFormat(false);

    @Test
    public void test1() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        Assert.assertEquals("[\"defined\",1]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test1null() throws IOException {
        Option<?> src = Option.some(null);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        Assert.assertEquals("[\"defined\",null]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test2() throws IOException {
        Option<?> src = Option.none();
        String json = mapper(optSettings).writer().writeValueAsString(src);
        Assert.assertEquals("[\"undefined\"]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        Assert.assertEquals(src, restored);
        Option<?> plain = mapper(optSettings).readValue("null", Option.class);
        Assert.assertEquals(src, plain);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        String json = "[\"defined\", 2, 3]";
        mapper(optSettings).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        String json = "[\"defined\"]";
        mapper(optSettings).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test5() throws IOException {
        String json = "[]";
        mapper(optSettings).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test6() throws IOException {
        String json = "[\"undefined\", 2, 3]";
        mapper(optSettings).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test7() throws IOException {
        String json = "[\"test\"]";
        mapper(optSettings).readValue(json, Option.class);
    }

    @Test
    public void test8() throws IOException {
        String json = "null";
        Option<?> option = mapper(optSettings).readValue(json, Option.class);
        Assert.assertTrue(option.isEmpty());
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.WRAPPER_OBJECT,
            property = "type")
    @JsonTypeName("card")
    private static class TestSerialize {
        public String type = "hello";
    }

    private static class A {
        public Option<TestSerialize> f = Option.of(new TestSerialize());
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.WRAPPER_OBJECT,
            property = "typeV")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = B.class)
    })
    private static abstract class V {
        public Option<TestSerialize> g = Option.of(new TestSerialize());
    }

    private static class B extends V {
        public Option<TestSerialize> h = Option.of(new TestSerialize());
    }

    private static class D {
        public Option<V> v = Option.of(new B());
    }

    @Test
    public void testJsonTypeInfo1() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        Assert.assertEquals("{\"f\":{\"card\":{\"type\":\"hello\"}}}", javaUtilValue);
        A restored = mapper().readValue(javaUtilValue, A.class);
        Assert.assertEquals("hello", restored.f.get().type);
    }

    @Test
    public void testJsonTypeInfo2() throws IOException {
        ObjectMapper mapper = mapper(optSettings);
        String javaUtilValue = mapper.writeValueAsString(new A());
        Assert.assertEquals("{\"f\":[\"defined\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        A restored = mapper.readValue(javaUtilValue, A.class);
        Assert.assertEquals("hello", restored.f.get().type);
    }

    @Test
    public void testJsonTypeInfo3() throws IOException {
        ObjectMapper mapper = mapper(optSettings);
        String javaUtilValue = mapper.writeValueAsString(new D());
        Assert.assertEquals("{\"v\":[\"defined\",{\"OptionTest$B\":{\"g\":[\"defined\",{\"card\":{\"type\":\"" +
                "hello\"}}],\"h\":[\"defined\",{\"card\":{\"type\":\"hello\"}}]}}]}", javaUtilValue);
        D restored = mapper.readValue(javaUtilValue, D.class);
        Assert.assertEquals("hello", restored.v.get().g.get().type);
    }

    public static class Parameterized<T> {
        public Option<T> value;
        public Parameterized() {}
        public Parameterized(Option<T> value) {
            this.value = value;
        }
    }

    @Test
    public void testWrappedParameterizedSome() throws IOException {
        String expected = "{\"value\":[\"defined\",1]}";
        Parameterized<Integer> object = new Parameterized<>(Option.some(1));
        Assert.assertEquals(expected, mapper(optSettings).writeValueAsString(object));
        Parameterized<Integer> restored = mapper(optSettings).readValue(expected, new TypeReference<Parameterized<Integer>>() {});
        Assert.assertEquals(restored.value.get(), (Integer) 1);
    }

    @Test
    public void testWrappedWildcardSome() throws IOException {
        String expected = "{\"value\":[\"defined\",1]}";
        Parameterized<?> object = new Parameterized<>(Option.some(1));
        Assert.assertEquals(expected, mapper(optSettings).writeValueAsString(object));
        Parameterized<?> restored = mapper(optSettings).readValue(expected, Parameterized.class);
        Assert.assertEquals(restored.value.get(), 1);
    }
}
