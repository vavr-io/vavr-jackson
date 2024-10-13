package io.vavr.jackson.datatype;

import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionPlainTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeDefinedOptionPlainValue() throws IOException {
        Option<?> src = Option.of(1);
        String json = mapper().writer().writeValueAsString(src);
        assertEquals("1", json);
        Option<?> restored = mapper().readValue(json, Option.class);
        assertEquals(src, restored);
    }

    @Test
    void shouldSerializeAndDeserializeNoneOptionPlainValue() throws IOException {
        Option<?> src = Option.none();
        String json = mapper().writer().writeValueAsString(src);
        assertEquals("null", json);
        Option<?> restored = mapper().readValue(json, Option.class);
        assertEquals(src, restored);
    }
}
