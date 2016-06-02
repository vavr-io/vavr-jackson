package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.control.Either;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
}
