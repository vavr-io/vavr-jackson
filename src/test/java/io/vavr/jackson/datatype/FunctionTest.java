package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vavr.Function1;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FunctionTest extends BaseTest {

    @Test(expected = IllegalStateException.class)
    public void testError() throws IOException {
        Function1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        String broken = "\"00000000" + json.substring(1);
        mapper().readValue(broken, new TypeReference<Function1<String, String>>() {});
    }

    @Test
    public void test1() throws IOException {
        Function1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function1<String, String> res = mapper().readValue(json, new TypeReference<Function1<String, String>>() {});
        Assert.assertEquals(res.apply(""), "42");
    }

}
