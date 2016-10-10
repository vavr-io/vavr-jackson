package javaslang.jackson.datatype;

import javaslang.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OptionPlainTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals("1", json);
        Option<?> restored = mapper().readValue(json, Option.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test2() throws IOException {
        Option<?> src = Option.none();
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals("null", json);
        Option<?> restored = mapper().readValue(json, Option.class);
        Assert.assertEquals(src, restored);
    }

    public static class Parameterized<T> {
        public Option<T> value;
        public Parameterized(Option<T> value) {
            this.value = value;
        }
    }

    @Test
    public void writeWrappedParameterizedSome() throws IOException {
        Parameterized<Integer> object = new Parameterized<>(Option.some(1));
        Assert.assertEquals("{\"value\":1}", mapper().writeValueAsString(object));
    }


}
