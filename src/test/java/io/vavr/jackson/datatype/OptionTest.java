package io.vavr.jackson.datatype;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class OptionTest extends BaseTest {

    private static VavrModule.Settings optSettings =
            new VavrModule.Settings().useOptionInPlainFormat(false);

    @Test
    void test1() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        Assertions.assertEquals("[\"defined\",1]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test1null() throws IOException {
        Option<?> src = Option.some(null);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        Assertions.assertEquals("[\"defined\",null]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test2() throws IOException {
        Option<?> src = Option.none();
        String json = mapper(optSettings).writer().writeValueAsString(src);
        Assertions.assertEquals("[\"undefined\"]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        Assertions.assertEquals(src, restored);
        Option<?> plain = mapper(optSettings).readValue("null", Option.class);
        Assertions.assertEquals(src, plain);
    }

    @Test
    void test3() throws IOException {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"defined\", 2, 3]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void test4() throws IOException {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"defined\"]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void test5() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void test6() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"undefined\", 2, 3]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void test7() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"test\"]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void test8() throws IOException {
        String json = "null";
        Option<?> option = mapper(optSettings).readValue(json, Option.class);
        Assertions.assertTrue(option.isEmpty());
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

    @Test
    void testJsonTypeInfo1() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        Assertions.assertEquals("{\"f\":{\"card\":{\"type\":\"hello\"}}}", javaUtilValue);
        A restored = mapper().readValue(javaUtilValue, A.class);
        Assertions.assertEquals("hello", restored.f.get().type);
    }

    @Test
    void testJsonTypeInfo2() throws IOException {
        ObjectMapper mapper = mapper(optSettings);
        String javaUtilValue = mapper.writeValueAsString(new A());
        Assertions.assertEquals("{\"f\":[\"defined\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        A restored = mapper.readValue(javaUtilValue, A.class);
        Assertions.assertEquals("hello", restored.f.get().type);
    }
}
