package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
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
        public Parameterized() {}
        public Parameterized(Option<T> value) {
            this.value = value;
        }
    }

    @Test
    public void testWrappedParameterizedSome() throws IOException {
        String expected = "{\"value\":1}";
        Parameterized<Integer> object = new Parameterized<>(Option.some(1));
        Assert.assertEquals(expected, mapper().writeValueAsString(object));
        Parameterized<Integer> restored = mapper().readValue(expected, new TypeReference<Parameterized<Integer>>() {});
        Assert.assertEquals(restored.value.get(), (Integer) 1);
    }

    @Test
    public void testWrappedWildcardSome() throws IOException {
        String expected = "{\"value\":1}";
        Parameterized<?> object = new Parameterized<>(Option.some(1));
        Assert.assertEquals(expected, mapper().writeValueAsString(object));
        Parameterized<?> restored = mapper().readValue(expected, Parameterized.class);
        Assert.assertEquals(restored.value.get(), 1);
    }

    private static class Pojo {
        private Option<Tuple2<String, String>> x = Option.of(Tuple.of("A", "B"));

        public Option<Tuple2<String, String>> getX() {
            return x;
        }
        public void setX(Option<Tuple2<String, String>> x) {
            this.x = x;
        }
    }

    @Test
    public void test() throws Exception {
        final String json = mapper().writeValueAsString(new Pojo());
        Assert.assertEquals(json, "{\"x\":[\"A\",\"B\"]}");
        Pojo restored = mapper().readValue(json, Pojo.class);
        Assert.assertEquals(restored.getX().get()._1, "A");
        Assert.assertEquals(restored.getX().get()._2, "B");
    }

}
