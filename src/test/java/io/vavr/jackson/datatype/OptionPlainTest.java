package io.vavr.jackson.datatype;

import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class OptionPlainTest extends BaseTest {

    @Test
    void test1() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper().writer().writeValueAsString(src);
        Assertions.assertEquals("1", json);
        Option<?> restored = mapper().readValue(json, Option.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test2() throws IOException {
        Option<?> src = Option.none();
        String json = mapper().writer().writeValueAsString(src);
        Assertions.assertEquals("null", json);
        Option<?> restored = mapper().readValue(json, Option.class);
        Assertions.assertEquals(src, restored);
    }
}
