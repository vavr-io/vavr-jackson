package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Issue149Test extends BaseTest {

    @Test
    void shouldHandleJsonUnwrapped() {
        ObjectMapper mapper = mapper();
        Foo foo = new Foo();

        String json = mapper.writeValueAsString(foo);

        assertThat(json).isEqualTo("{\"bar\":\"bar-value\"}");
    }

    @Test
    void shouldFailWhenUnwrappingCollections() {
        ObjectMapper mapper = mapper();
        FooList fooList = new FooList();

        assertThatThrownBy(() -> mapper.writeValueAsString(fooList)).hasMessageStartingWith("Cannot unwrap a non-bean object");
    }

    @Test
    void shouldFailWhenContainingTry() {
        ObjectMapper mapper = mapper();
        FooTry fooTry = new FooTry();

        assertThatThrownBy(() -> mapper.writeValueAsString(fooTry)).hasRootCauseMessage("getCause on Success");
    }

    @Test
    void itFailsOnOptionEither() {
        ObjectMapper mapper = mapper();
        FooEither fooEither = new FooEither();

        assertThatThrownBy(() -> mapper.writeValueAsString(fooEither)).hasMessageStartingWith("Cannot unwrap a non-bean object");
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
