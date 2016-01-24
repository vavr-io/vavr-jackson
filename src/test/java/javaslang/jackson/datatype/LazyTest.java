package javaslang.jackson.datatype;

import javaslang.Lazy;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class LazyTest extends BaseTest {
    @Test
    public void test1() throws IOException {
        Lazy<?> src = Lazy.of(() -> 1);
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals("1", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test2() throws IOException {
        Lazy<?> src = Lazy.of(() -> null);
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals("null", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assert.assertEquals(src, restored);
    }
}
