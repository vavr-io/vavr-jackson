package javaslang.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import javaslang.Tuple;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.control.Either;
import javaslang.control.Option;

import static javaslang.control.Option.none;
import static javaslang.control.Option.some;

public class EitherTest extends BaseTest {

    @SuppressWarnings("unchecked")
    @Test
    public void test1() throws IOException {
        Either<String, Integer> left = Either.left("left");
        String json = mapper().writer().writeValueAsString(left);
        Either<String, Integer> restored = mapper().readValue(json, Either.class);
        Assert.assertEquals(left, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test2() throws IOException {
        Either<String, Integer> right = Either.right(1);
        String json = mapper().writer().writeValueAsString(right);
        Either<String, Integer> restored = mapper().readValue(json, Either.class);
        Assert.assertEquals(right, restored);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        String json = "[\"right\", 2, 3]";
        mapper().readValue(json, Either.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        String json = "[\"right\"]";
        mapper().readValue(json, Either.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test5() throws IOException {
        String json = "[\"lEft\", 42]";
        mapper().readValue(json, Either.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test6() throws IOException {
        String json = "[42, 42]";
        mapper().readValue(json, Either.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test7() throws IOException {
        Either<List<Integer>, Set<Double>> either = Either.left(List.of(42));
        String json = mapper().writer().writeValueAsString(either);
        Either<List<Integer>, Set<Double>> restored = mapper().readValue(json, new TypeReference<Either<List<Integer>, Set<Double>>>() {});
        Assert.assertEquals(either, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test8() throws IOException {
        Either<List<Integer>, Set<Double>> either = Either.right(HashSet.of(42.0));
        String json = mapper().writer().writeValueAsString(either);
        Either<List<Integer>, Set<Double>> restored = mapper().readValue(json, new TypeReference<Either<List<Integer>, Set<Double>>>() {});
        Assert.assertEquals(either, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNullSerialization() throws IOException {
        Either<String, Integer> left = Either.left(null);
        String leftJson = mapper().writer().writeValueAsString(left);
        Either<String, Integer> restoredLeft = mapper().readValue(leftJson, Either.class);
        Assert.assertEquals(left, restoredLeft);
        Either<String, Integer> right = Either.left(null);
        String rightJson = mapper().writer().writeValueAsString(right);
        Either<String, Integer> restoredRight = mapper().readValue(rightJson, Either.class);
        Assert.assertEquals(right, restoredRight);
    }

    @Test
    public void testWithOption() throws IOException {
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
    public void testJsonTypeInfo() throws IOException {
        String javaUtilValue;
        javaUtilValue = mapper().writeValueAsString(new Left());
        Assert.assertEquals("{\"f\":[\"left\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        Left restoredLeft = mapper().readValue(javaUtilValue, Left.class);
        Assert.assertEquals("hello", restoredLeft.f.left().get().type);
        javaUtilValue = mapper().writeValueAsString(new Right());
        Assert.assertEquals("{\"f\":[\"right\",{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        Right restoredRight = mapper().readValue(javaUtilValue, Right.class);
        Assert.assertEquals("hello", restoredRight.f.right().get().type);
    }

    public static class Parameterized<L, R> {
        public Either<L, R> value;
        public Parameterized(Either<L, R> value) {
            this.value = value;
        }
    }

    @Test
    public void writeWrappedParameterizedSome() throws IOException {
        Parameterized<Integer, String> object = new Parameterized<>(Either.left(1));
        Assert.assertEquals("{\"value\":[\"left\",1]}", mapper().writeValueAsString(object));
    }

    @Test
    public void writeWrappedWildcardSome() throws IOException {
        Parameterized<?, ?> object = new Parameterized<>(Either.left(1));
        Assert.assertEquals("{\"value\":[\"left\",1]}", mapper().writeValueAsString(object));
    }

}
