package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionTest extends BaseTest {

    private static VavrModule.Settings optSettings = new VavrModule.Settings().useOptionInPlainFormat(false);

    @Test
    void shouldSerializeAndDeserializeDefinedOptionWithValue() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        assertEquals("[\"defined\",1]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        assertEquals(src, restored);
    }

    @Test
    void shouldSerializeAndDeserializeDefinedOptionWithNull() throws IOException {
        Option<?> src = Option.some(null);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        assertEquals("[\"defined\",null]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        assertEquals(src, restored);
    }

    @Test
    void shouldSerializeAndDeserializeNoneOption() throws IOException {
        Option<?> src = Option.none();
        String json = mapper(optSettings).writer().writeValueAsString(src);
        assertEquals("[\"undefined\"]", json);
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        assertEquals(src, restored);
        Option<?> plain = mapper(optSettings).readValue("null", Option.class);
        assertEquals(src, plain);
    }

    @Test
    void shouldThrowExceptionForInvalidOptionWithExtraValue() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"defined\", 2, 3]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void shouldThrowExceptionForDefinedOptionWithoutValue() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"defined\"]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void shouldThrowExceptionForEmptyArrayOption() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void shouldThrowExceptionForUndefinedOptionWithExtraValues() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"undefined\", 2, 3]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void shouldThrowExceptionForUnrecognizedOptionType() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[\"test\"]";
            mapper(optSettings).readValue(json, Option.class);
        });
    }

    @Test
    void shouldDeserializeNullToEmptyOption() throws IOException {
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
    void shouldSerializeAndDeserializeWithDefaultJsonTypeInfo() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        assertEquals("{\"f\":{\"card\":{\"type\":\"hello\"}}}", javaUtilValue);
        A restored = mapper().readValue(javaUtilValue, A.class);
        assertEquals("hello", restored.f.get().type);
    }

    @Test
    void shouldSerializeAndDeserializeWithOptionEnabledJsonTypeInfo() throws IOException {
        ObjectMapper mapper = mapper(optSettings);
        String javaUtilValue = mapper.writeValueAsString(new A());
        assertEquals("{\"f\":[\"defined\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        A restored = mapper.readValue(javaUtilValue, A.class);
        assertEquals("hello", restored.f.get().type);
    }
}
