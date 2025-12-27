package io.vavr.jackson.datatype;

import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OptionPlainTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeDefinedOptionPlainValue() {
        Option<?> src = Option.of(1);
        String json = mapper().writer().writeValueAsString(src);
        assertThat(json).isEqualTo("1");
        Option<?> restored = mapper().readValue(json, Option.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeNoneOptionPlainValue() {
        Option<?> src = Option.none();
        String json = mapper().writer().writeValueAsString(src);
        assertThat(json).isEqualTo("null");
        Option<?> restored = mapper().readValue(json, Option.class);
        assertThat(restored).isEqualTo(src);
    }
}
