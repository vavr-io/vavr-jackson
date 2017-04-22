package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
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
}
