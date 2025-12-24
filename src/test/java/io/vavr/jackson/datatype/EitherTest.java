package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.module.scala.DefaultScalaModule;
import io.vavr.Tuple;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static tools.jackson.databind.DeserializationFeature.FAIL_ON_TRAILING_TOKENS;

class EitherTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeWrappedEitherObject() throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(Either.class, WrapperObject.class).build();
        Either<Integer, Integer> src = Either.right(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(Either.Right.class.getName(), plainJson)).isEqualTo(wrappedJson);
        Either<Integer, Integer> restored = (Either<Integer, Integer>) mapper.readValue(wrappedJson, Either.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeLeftEither() throws IOException {
        Either<String, Integer> left = Either.left("left");
        String json = mapper().writer().writeValueAsString(left);
        Either<String, Integer> restored = mapper().readValue(json, Either.class);
        assertThat(restored).isEqualTo(left);
    }

    @Test
    void shouldSerializeAndDeserializeRightEither() throws IOException {
        Either<String, Integer> right = Either.right(1);
        String json = mapper().writer().writeValueAsString(right);
        Either<String, Integer> restored = mapper().readValue(json, Either.class);
        assertThat(restored).isEqualTo(right);
    }

    @Test
    void shouldThrowExceptionWhenInvalidJsonHasExtraRightValue() throws IOException {
        String json = "[\"right\", 2, 3]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(json, Either.class));
    }

    @Test
    void shouldThrowExceptionWhenInvalidJsonHasMissingRightValue() throws IOException {
        String json = "[\"right\"]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(json, Either.class));
    }

    @Test
    void shouldThrowExceptionWhenInvalidJsonHasMalformedLeftType() throws IOException {
        String json = "[\"lEft\", 42]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(json, Either.class));
    }

    @Test
    void shouldThrowExceptionWhenInvalidJsonHasNonStringLeftOrRight() throws IOException {
        String json = "[42, 42]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(json, Either.class));
    }

    @Test
    void shouldSerializeAndDeserializeLeftEitherWithGenericTypes() throws IOException {
        Either<List<Integer>, Set<Double>> either = Either.left(List.of(42));
        String json = mapper().writer().writeValueAsString(either);
        Either<List<Integer>, Set<Double>> restored = mapper().readValue(json, new TypeReference<Either<List<Integer>, Set<Double>>>() {
        });
        assertThat(restored).isEqualTo(either);
    }

    @Test
    void shouldSerializeAndDeserializeRightEitherWithGenericTypes() throws IOException {
        Either<List<Integer>, Set<Double>> either = Either.right(HashSet.of(42.0));
        String json = mapper().writer().writeValueAsString(either);
        Either<List<Integer>, Set<Double>> restored = mapper().readValue(json, new TypeReference<Either<List<Integer>, Set<Double>>>() {
        });
        assertThat(restored).isEqualTo(either);
    }

    @Test
    void shouldHandleNullInLeftEitherDuringSerialization() throws IOException {
        Either<String, Integer> left = Either.left(null);
        String leftJson = mapper().writer().writeValueAsString(left);
        Either<String, Integer> restoredLeft = mapper().readValue(leftJson, Either.class);
        assertThat(restoredLeft).isEqualTo(left);
    }

    @Test
    void shouldHandleNullInRightEitherDuringSerialization() throws IOException {
        Either<String, Integer> right = Either.left(null);
        String rightJson = mapper().writer().writeValueAsString(right);
        Either<String, Integer> restoredRight = mapper().readValue(rightJson, Either.class);
        assertThat(restoredRight).isEqualTo(right);
    }

    @Test
    void shouldSerializeAndDeserializeEitherWithOption() throws IOException {
        TypeReference<Either<Option<String>, Option<String>>> typeReference = new TypeReference<Either<Option<String>, Option<String>>>() {
        };
        verifySerialization(typeReference, List.of(
            Tuple.of(Either.left(none()), genJsonList("left", null)),
            Tuple.of(Either.right(none()), genJsonList("right", null)),
            Tuple.of(Either.left(some("value")), genJsonList("left", "value")),
            Tuple.of(Either.right(some("value")), genJsonList("right", "value"))
        ));
    }

    @Test
    void shouldSerializeAndDeserializeCustomTypeWithJsonTypeInfo() throws IOException {
        String javaUtilValue;
        javaUtilValue = mapper().writeValueAsString(new Left());
        assertThat(javaUtilValue).isEqualTo("{\"f\":[\"left\",{\"card\":{\"type\":\"hello\"}}]}");
        Left restoredLeft = mapper().readValue(javaUtilValue, Left.class);
        assertThat(restoredLeft.f.getLeft().type).isEqualTo("hello");
        javaUtilValue = mapper().writeValueAsString(new Right());
        assertThat(javaUtilValue).isEqualTo("{\"f\":[\"right\",{\"card\":{\"type\":\"hello\"}}]}");
        Right restoredRight = mapper().readValue(javaUtilValue, Right.class);
        assertThat(restoredRight.f.get().type).isEqualTo("hello");
    }

    @Test
    void shouldSerializeAndDeserializeScalaEither() throws IOException {
        final scala.util.Either<String, BigInteger> left = scala.util.Left.apply("test");
        final scala.util.Either<String, BigInteger> right = scala.util.Right.apply(BigInteger.ONE);
        final ObjectMapper mapper = mapper().rebuild().addModule(new DefaultScalaModule()).build();

        final String serializedLeft = mapper.writeValueAsString(left);
        final Either<String, BigInteger> deserializedLeft =
            mapper.readValue(serializedLeft, new TypeReference<Either<String, BigInteger>>() {
            });
        assertThat(deserializedLeft.getLeft()).isEqualTo("test");

        final String serializedRight = mapper.writeValueAsString(right);
        final Either<String, BigInteger> deserializedRight =
            mapper.readValue(serializedRight, new TypeReference<Either<String, BigInteger>>() {
            });
        assertThat(deserializedRight.get()).isEqualTo(BigInteger.ONE);
    }

    @Test
    void shouldThrowExceptionWhenInvalidScalaEither() {
        ObjectMapper mapper = mapper().rebuild().disable(FAIL_ON_TRAILING_TOKENS).build();

        String leftJson = "{\"left\": 42, \"x\": 5}";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper.readValue(leftJson, Either.class));

        String rightJson = "{\"right\": 42, \"x\": 5}";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper.readValue(rightJson, Either.class));
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type")
    @JsonTypeName("card")
    private static class TestSerialize {
        public String type = "hello";
    }

    private static class Left {
        public Either<TestSerialize, TestSerialize> f = Either.left(new TestSerialize());
    }

    private static class Right {
        public Either<TestSerialize, TestSerialize> f = Either.right(new TestSerialize());
    }
}
