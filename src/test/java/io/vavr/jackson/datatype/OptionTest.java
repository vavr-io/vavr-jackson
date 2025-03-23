package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class OptionTest extends BaseTest {

    private static VavrModule.Settings optSettings = new VavrModule.Settings().useOptionInPlainFormat(false);

    @Test
    void shouldSerializeAndDeserializeDefinedOptionWithValue() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        assertThat(json).isEqualTo("[\"defined\",1]");
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeDefinedOptionWithNull() throws IOException {
        Option<?> src = Option.some(null);
        String json = mapper(optSettings).writer().writeValueAsString(src);
        assertThat(json).isEqualTo("[\"defined\",null]");
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeNoneOption() throws IOException {
        Option<?> src = Option.none();
        String json = mapper(optSettings).writer().writeValueAsString(src);
        assertThat(json).isEqualTo("[\"undefined\"]");
        Option<?> restored = mapper(optSettings).readValue(json, Option.class);
        assertThat(restored).isEqualTo(src);
        Option<?> plain = mapper(optSettings).readValue("null", Option.class);
        assertThat(plain).isEqualTo(src);
    }

    @Test
    void shouldThrowExceptionForInvalidOptionWithExtraValue() {
        String json = "[\"defined\", 2, 3]";
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper(optSettings).readValue(json, Option.class));
    }

    @Test
    void shouldThrowExceptionForDefinedOptionWithoutValue() {
        String json = "[\"defined\"]";
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper(optSettings).readValue(json, Option.class));
    }

    @Test
    void shouldThrowExceptionForEmptyArrayOption() {
        String json = "[]";
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper(optSettings).readValue(json, Option.class));
    }

    @Test
    void shouldThrowExceptionForUndefinedOptionWithExtraValues() {
        String json = "[\"undefined\", 2, 3]";
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper(optSettings).readValue(json, Option.class));
    }

    @Test
    void shouldThrowExceptionForUnrecognizedOptionType() {
        String json = "[\"test\"]";
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper(optSettings).readValue(json, Option.class));
    }

    @Test
    void shouldDeserializeNullToEmptyOption() throws IOException {
        String json = "null";
        Option<?> option = mapper(optSettings).readValue(json, Option.class);
        assertThat(option.isEmpty()).isTrue();
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
        assertThat(javaUtilValue).isEqualTo("{\"f\":{\"card\":{\"type\":\"hello\"}}}");
        A restored = mapper().readValue(javaUtilValue, A.class);
        assertThat(restored.f.get().type).isEqualTo("hello");
    }

    @Test
    void shouldSerializeAndDeserializeWithOptionEnabledJsonTypeInfo() throws IOException {
        ObjectMapper mapper = mapper(optSettings);
        String javaUtilValue = mapper.writeValueAsString(new A());
        assertThat(javaUtilValue).isEqualTo("{\"f\":[\"defined\",{\"card\":{\"type\":\"hello\"}}]}");
        A restored = mapper.readValue(javaUtilValue, A.class);
        assertThat(restored.f.get().type).isEqualTo("hello");
    }
}
