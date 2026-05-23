package io.vavr.jackson.datatype;

import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class SerializeWithTypeTest extends BaseTest {

    private final VavrModule.Settings nonPlainOption = new VavrModule.Settings().useOptionInPlainFormat(false);

    @Test
    void tupleWrapperObject() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Tuple2.class, WrapperObject.class).build();
        Tuple2<Integer, String> src = Tuple.of(1, "a");
        String json = mapper.writeValueAsString(src);
        assertThat(json).isEqualTo("{\"io.vavr.Tuple2\":[1,\"a\"]}");
        Tuple2<?, ?> restored = (Tuple2<?, ?>) mapper.readValue(json, Tuple2.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void tupleWrapperArray() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Tuple2.class, WrapperArray.class).build();
        Tuple2<Integer, String> src = Tuple.of(1, "a");
        String json = mapper.writeValueAsString(src);
        assertThat(json).isEqualTo("[\"io.vavr.Tuple2\",[1,\"a\"]]");
        Tuple2<?, ?> restored = (Tuple2<?, ?>) mapper.readValue(json, Tuple2.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void eitherRightWrapperObject() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Either.class, WrapperObject.class).build();
        Either<String, Integer> src = Either.right(42);
        String json = mapper.writeValueAsString(src);
        assertThat(json).isEqualTo("{\"io.vavr.control.Either$Right\":[\"right\",42]}");
        Either<?, ?> restored = (Either<?, ?>) mapper.readValue(json, Either.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void eitherLeftWrapperArray() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Either.class, WrapperArray.class).build();
        Either<String, Integer> src = Either.left("err");
        String json = mapper.writeValueAsString(src);
        assertThat(json).isEqualTo("[\"io.vavr.control.Either$Left\",[\"left\",\"err\"]]");
        Either<?, ?> restored = (Either<?, ?>) mapper.readValue(json, Either.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void nonPlainOptionDefinedWrapperObject() {
        ObjectMapper mapper = mapper(nonPlainOption).rebuild().addMixIn(Option.class, WrapperObject.class).build();
        Option<Integer> src = Option.of(42);
        String json = mapper.writeValueAsString(src);
        assertThat(json).contains("[\"defined\",42]");
    }

    @Test
    void nonPlainOptionUndefinedWrapperArray() {
        ObjectMapper mapper = mapper(nonPlainOption).rebuild().addMixIn(Option.class, WrapperArray.class).build();
        Option<Integer> src = Option.none();
        String json = mapper.writeValueAsString(src);
        assertThat(json).contains("[\"undefined\"]");
    }

    @Test
    void plainOptionDefinedWrapperObject() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Option.class, WrapperObject.class).build();
        Option<Integer> src = Option.of(42);
        String json = mapper.writeValueAsString(src);
        assertThat(json).contains("42");
    }

    @Test
    void lazyWrapperObject() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Lazy.class, WrapperObject.class).build();
        Lazy<Integer> src = Lazy.of(() -> 42);
        String json = mapper.writeValueAsString(src);
        assertThat(json).contains("42");
    }

    @Test
    void listWrapperObject() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(List.class, WrapperObject.class).build();
        List<Integer> src = List.of(1, 2, 3);
        String json = mapper.writeValueAsString(src);
        assertThat(json).matches("\\{\"[^\"]+\":\\[1,2,3]}");
        List<?> restored = (List<?>) mapper.readValue(json, List.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void listWrapperArray() {
        ObjectMapper mapper = mapper().rebuild().addMixIn(List.class, WrapperArray.class).build();
        List<Integer> src = List.of(1, 2, 3);
        String json = mapper.writeValueAsString(src);
        assertThat(json).matches("\\[\"[^\"]+\",\\[1,2,3]]");
        List<?> restored = (List<?>) mapper.readValue(json, List.class);
        assertThat(restored).isEqualTo(src);
    }
}
