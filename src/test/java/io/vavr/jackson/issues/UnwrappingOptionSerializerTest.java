package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class UnwrappingOptionSerializerTest extends BaseTest {

    @Test
    void shouldUnwrapOptionContainingGenericBeanWithGenericTypeParam() {
        ObjectMapper mapper = mapper();
        FooGenericNested foo = new FooGenericNested();

        String json = mapper.writeValueAsString(foo);

        assertThat(json)
            .contains("\"value\":[\"a\",\"b\"]")
            .contains("\"extra\":\"test\"");
    }

    @Test
    void shouldUnwrapOptionContainingGenericBeanWithSimpleTypeParam() {
        ObjectMapper mapper = mapper();
        FooGenericSimple foo = new FooGenericSimple();

        String json = mapper.writeValueAsString(foo);

        assertThat(json)
            .contains("\"value\":\"hello\"")
            .contains("\"extra\":\"test\"");
    }

    static class GenericBean<T> {
        private final T value;
        private final String extra;

        GenericBean(T value, String extra) {
            this.value = value;
            this.extra = extra;
        }

        @JsonProperty("value")
        T getValue() {
            return value;
        }

        @JsonProperty("extra")
        String getExtra() {
            return extra;
        }
    }

    static class FooGenericNested {
        @JsonUnwrapped
        Option<GenericBean<java.util.List<String>>> bar() {
            return Option.of(new GenericBean<>(java.util.List.of("a", "b"), "test"));
        }
    }

    static class FooGenericSimple {
        @JsonUnwrapped
        Option<GenericBean<String>> bar() {
            return Option.of(new GenericBean<>("hello", "test"));
        }
    }

}
