package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.JsonMappingException;
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
        String json = "[\"test\", 2, 3]";
        mapper().readValue(json, Either.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        String json = "[\"test\"]";
        mapper().readValue(json, Either.class);
    }

}
