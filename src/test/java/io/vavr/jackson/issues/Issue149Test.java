package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        // Given a mapper and a Foo object
        ObjectMapper mapper = mapper();
        Foo foo = new Foo();

        // When serializing the Foo object
        String json = mapper.writeValueAsString(foo);

        // Then the JSON should not contain the "bar" wrapper
        assertThat(json).isEqualTo("{\"bar\":\"bar-value\"}");
    }

    /**
     * Collections cannot be unwrapped.
     * See {@code JsonUnwrapped} documentation.
     *
     * @throws JsonProcessingException
     */
    @Test
    public void itFailsWithAnOptionList() {
        // Given a mapper and a FooList object
        ObjectMapper mapper = mapper();
        FooList fooList = new FooList();

        // When serializing the FooList object
        assertThatThrownBy(() -> mapper.writeValueAsString(fooList))
            .hasRootCauseMessage("Cannot unwrap a non-bean object");
    }

    @Test
    public void itFailsOnAnOptionTry() {
        // Given a mapper and a FooTry object
        ObjectMapper mapper = mapper();
        FooTry fooTry = new FooTry();

        // When serializing the FooTry object
        assertThatThrownBy(() -> mapper.writeValueAsString(fooTry))
            .hasRootCauseMessage("getCause on Success");
    }

    @Test
    public void itFailsOnOptionEither() {
        // Given a mapper and a FooEither object
        ObjectMapper mapper = mapper();
        FooEither fooEither = new FooEither();

        // When serializing the FooEither object
        assertThatThrownBy(() -> mapper.writeValueAsString(fooEither))
            .hasRootCauseMessage("Cannot unwrap a non-bean object");
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

    static class FooList {
        @JsonUnwrapped
        Option<List<Bar>> bar() {
            return Option.of(List.of(new Bar()));
        }
    }

    static class FooTry {
        @JsonUnwrapped
        Option<Try<Bar>> bar() {
            return Option.of(Try.of(() -> new Bar()));
        }
    }

    static class FooEither {
        @JsonUnwrapped
        Option<io.vavr.control.Either<String, Bar>> bar() {
            return Option.of(io.vavr.control.Either.right(new Bar()));
        }
    }
}
