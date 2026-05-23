package io.vavr.jackson.datatype.serialize;

import io.vavr.Lazy;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VavrValueSerializerTest {

    @Test
    void shouldNotSwallowNonJacksonExceptions() {
        JsonMapper mapper = JsonMapper.builder()
            .addModule(new VavrModule())
            .build();

        Lazy<Object> lazy = Lazy.of(() -> {
            throw new NullPointerException("bug");
        });

        assertThatThrownBy(() -> mapper.writeValueAsString(lazy))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("bug");
    }
}
