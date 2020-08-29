package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Can not deserialize explicit Option.Some or Option.None
 * https://github.com/vavr-io/vavr-jackson/issues/165
 */
class Issue165Test {

    static class MyOptionNormalType {
        @JsonProperty
        Option<String> p;
    }

    static class MyOptionSomeType {
        @JsonProperty
        Option.Some<String> p;
    }

    @Test
    void itShouldSerializeOptions() {
        ObjectMapper mapper = new ObjectMapper().registerModule(new VavrModule());

        assertTrue(mapper.canDeserialize(mapper.constructType(MyOptionNormalType.class)));
        assertTrue(mapper.canDeserialize(mapper.constructType(MyOptionSomeType.class)));

    }

}
