package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Can not deserialize explicit Option.Some or Option.None
 * https://github.com/vavr-io/vavr-jackson/issues/165
 */
class Issue165Test {

    static class MyOptionNormalType {
        @JsonProperty("p")
        Option<String> p;

        @JsonCreator
        MyOptionNormalType(@JsonProperty("p") Option<String> p) {
            this.p = p;
        }
    }

    static class MyOptionSomeType {
        @JsonProperty("p")
        Option.Some<String> p;

        @JsonCreator
        MyOptionSomeType(@JsonProperty("p") Option.Some<String> p) {
            this.p = p;
        }
    }

    static class MyOptionNoneType {
        @JsonProperty("p")
        Option.None<String> p;

        @JsonCreator
        MyOptionNoneType(@JsonProperty("p") Option.None<String> p) {
            this.p = p;
        }
    }

    @Test
    void itShouldSerializeOptions() throws Exception {
        ObjectMapper mapper = new ObjectMapper().registerModule(new VavrModule());

        assertTrue(mapper.canDeserialize(mapper.constructType(MyOptionNormalType.class)));
        assertTrue(mapper.canDeserialize(mapper.constructType(MyOptionSomeType.class)));
        assertTrue(mapper.canDeserialize(mapper.constructType(MyOptionNoneType.class)));

        MyOptionNormalType normal1 = new MyOptionNormalType(Option.of("vavr"));
        MyOptionNormalType normal2 = mapper.readValue(mapper.writeValueAsString(normal1), MyOptionNormalType.class);
        assertEquals(normal1.p, normal2.p);

        MyOptionSomeType some1 = new MyOptionSomeType((Option.Some<String>) Option.some("vavr"));
        MyOptionSomeType some2 = mapper.readValue(mapper.writeValueAsString(some1), MyOptionSomeType.class);
        assertEquals(some1.p, some2.p);

        MyOptionNoneType none1 = new MyOptionNoneType((Option.None<String>) Option.<String>none());
        MyOptionNoneType none2 = mapper.readValue(mapper.writeValueAsString(none1), MyOptionNoneType.class);
        assertEquals(none1.p, none2.p);
    }

}
