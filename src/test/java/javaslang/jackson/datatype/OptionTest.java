package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OptionTest extends BaseTest {

    private static JavaslangModule.Options opts = new JavaslangModule.Options().plainOption(false);

    @Test
    public void test1() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper(opts).writer().writeValueAsString(src);
        Assert.assertEquals("[\"defined\",1]", json);
        Option<?> restored = mapper(opts).readValue(json, Option.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test1null() throws IOException {
        Option<?> src = Option.some(null);
        String json = mapper(opts).writer().writeValueAsString(src);
        Assert.assertEquals("[\"defined\",null]", json);
        Option<?> restored = mapper(opts).readValue(json, Option.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test2() throws IOException {
        Option<?> src = Option.none();
        String json = mapper(opts).writer().writeValueAsString(src);
        Assert.assertEquals("[\"undefined\"]", json);
        Option<?> restored = mapper(opts).readValue(json, Option.class);
        Assert.assertEquals(src, restored);
        Option<?> plain = mapper(opts).readValue("null", Option.class);
        Assert.assertEquals(src, plain);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        String json = "[\"defined\", 2, 3]";
        mapper(opts).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        String json = "[\"defined\"]";
        mapper(opts).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test5() throws IOException {
        String json = "[]";
        mapper(opts).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test6() throws IOException {
        String json = "[\"undefined\", 2, 3]";
        mapper(opts).readValue(json, Option.class);
    }

    @Test(expected = JsonMappingException.class)
    public void test7() throws IOException {
        String json = "[\"test\"]";
        mapper(opts).readValue(json, Option.class);
    }

}
