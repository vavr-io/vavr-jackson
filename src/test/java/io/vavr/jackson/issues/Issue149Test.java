package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Vavr-Jackson should respect the @JsonUnwrapped annotation.
 *
 * Given:
 * <pre>{@code
 * class Foo {
 *     @JsonUnwrapped
 *     Option<Bar> getBar()
 * }
 *
 * class Bar {
 *     @JsonProperty("bar")
 *     String getValue()
 * }
 * }</pre>
 *
 * Returns
 * <pre>{@code
 * {
 *    bar: {
 *        bar: ...
 *    }
 * }
 * }</pre>
 *
 * Instead of expected:
 * <pre>{@code
 * {
 *    bar: ...
 * }
 * }</pre>
 */
public class Issue149Test extends BaseTest {

    @Test
    public void itShouldRespectTheJsonUnwrappedAnnotation() throws JsonProcessingException {

        ObjectMapper mapper = mapper();
        Foo foo = new Foo();

        String json = mapper.writeValueAsString(foo);
//        String json2 = mapper.writeValueAsString(new Foo2());
//        System.out.println(json2);

        assertThat(json).isEqualTo("{\"bar\":\"bar-value\"}");
    }

    static class Foo {
        @JsonUnwrapped
        Option<Bar> bar() {
            return Option.of(new Bar());
        }
    }

    static class Bar {
        @JsonProperty("bar")
        String getValue() {
            return "bar-value";
        }
    }

    static class Foo2 {
        @JsonUnwrapped
        Bar bar() {
            return new Bar();
        }
    }
}
