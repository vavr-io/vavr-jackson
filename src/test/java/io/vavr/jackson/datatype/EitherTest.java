package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import io.vavr.Tuple;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.some;

public class EitherTest extends BaseTest {

    @SuppressWarnings("unchecked")
    @Test
    void testWrapperObject() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(Either.class, WrapperObject.class);
        Either<Integer, Integer> src = Either.right(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToObject(Either.Right.class.getName(), plainJson));
        Either<Integer, Integer> restored = (Either<Integer, Integer>) mapper.readValue(wrappedJson, Either.class);
        Assertions.assertEquals(src, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    void test1() throws IOException {
        Either<String, Integer> left = Either.left("left");
        String json = mapper().writer().writeValueAsString(left);
        Either<String, Integer> restored = mapper().readValue(json, Either.class);
        Assertions.assertEquals(left, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    void test2() throws IOException {
        Either<String, Integer> right = Either.right(1);
        String json = mapper().writer().writeValueAsString(right);
        Either<String, Integer> restored = mapper().readValue(json, Either.class);
        Assertions.assertEquals(right, restored);
    }

    @Test
    void test3() throws IOException {
        Assertions.assertThrows(JsonMappingException.class, () -> {
            String json = "[\"right\", 2, 3]";
            mapper().readValue(json, Either.class);
        });
    }

    @Test
    void test4() throws IOException {
        Assertions.assertThrows(JsonMappingException.class, () -> {
            String json = "[\"right\"]";
            mapper().readValue(json, Either.class);
        });
    }

    @Test
    void test5() throws IOException {
        Assertions.assertThrows(JsonMappingException.class, () -> {
            String json = "[\"lEft\", 42]";
            mapper().readValue(json, Either.class);
        });
    }

    @Test
    void test6() throws IOException {
        Assertions.assertThrows(JsonMappingException.class, () -> {
            String json = "[42, 42]";
            mapper().readValue(json, Either.class);
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    void test7() throws IOException {
        Either<List<Integer>, Set<Double>> either = Either.left(List.of(42));
        String json = mapper().writer().writeValueAsString(either);
        Either<List<Integer>, Set<Double>> restored = mapper().readValue(json, new TypeReference<Either<List<Integer>, Set<Double>>>() {});
        Assertions.assertEquals(either, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    void test8() throws IOException {
        Either<List<Integer>, Set<Double>> either = Either.right(HashSet.of(42.0));
        String json = mapper().writer().writeValueAsString(either);
        Either<List<Integer>, Set<Double>> restored = mapper().readValue(json, new TypeReference<Either<List<Integer>, Set<Double>>>() {});
        Assertions.assertEquals(either, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testNullSerialization() throws IOException {
        Either<String, Integer> left = Either.left(null);
        String leftJson = mapper().writer().writeValueAsString(left);
        Either<String, Integer> restoredLeft = mapper().readValue(leftJson, Either.class);
        Assertions.assertEquals(left, restoredLeft);
        Either<String, Integer> right = Either.left(null);
        String rightJson = mapper().writer().writeValueAsString(right);
        Either<String, Integer> restoredRight = mapper().readValue(rightJson, Either.class);
        Assertions.assertEquals(right, restoredRight);
    }

    @Test
    void testWithOption() throws IOException {
        TypeReference<Either<Option<String>, Option<String>>> typeReference = new TypeReference<Either<Option<String>, Option<String>>>() {};
        verifySerialization(typeReference, List.of(
                Tuple.of(Either.left(none()), genJsonList("left", null)),
                Tuple.of(Either.right(none()), genJsonList("right", null)),
                Tuple.of(Either.left(some("value")), genJsonList("left", "value")),
                Tuple.of(Either.right(some("value")), genJsonList("right", "value"))
        ));
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

    @Test
    void testJsonTypeInfo() throws IOException {
        String javaUtilValue;
        javaUtilValue = mapper().writeValueAsString(new Left());
        Assertions.assertEquals("{\"f\":[\"left\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        Left restoredLeft = mapper().readValue(javaUtilValue, Left.class);
        Assertions.assertEquals("hello", restoredLeft.f.getLeft().type);
        javaUtilValue = mapper().writeValueAsString(new Right());
        Assertions.assertEquals("{\"f\":[\"right\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        Right restoredRight = mapper().readValue(javaUtilValue, Right.class);
        Assertions.assertEquals("hello", restoredRight.f.get().type);
    }

    @Test
    void testDeserializingScalaEither() throws IOException {
        final scala.util.Either<String, BigInteger> left = scala.util.Left.apply("test");
        final scala.util.Either<String, BigInteger> right = scala.util.Right.apply(BigInteger.ONE);
        final ObjectMapper mapper = mapper().registerModule(new DefaultScalaModule());

        final String serializedLeft = mapper.writeValueAsString(left);
        final Either<String, BigInteger> deserializedLeft =
                mapper.readValue(serializedLeft, new TypeReference<Either<String, BigInteger>>() { });
        Assertions.assertEquals("test", deserializedLeft.getLeft());

        final String serializedRight = mapper.writeValueAsString(right);
        final Either<String, BigInteger> deserializedRight =
                mapper.readValue(serializedRight, new TypeReference<Either<String, BigInteger>>() { });
        Assertions.assertEquals(BigInteger.ONE, deserializedRight.get());
    }
}
