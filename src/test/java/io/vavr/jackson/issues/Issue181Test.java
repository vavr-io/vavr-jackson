package io.vavr.jackson.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

/**
 * Can not deserialize explicit Try.Success or Try.Failure
 * https://github.com/vavr-io/vavr-jackson/issues/165
 */
class Issue181Test {

    static class MyTryNormalType {
        @JsonProperty("p")
        Try<String> p;

        @JsonCreator
        MyTryNormalType(@JsonProperty("p") Try<String> p) {
            this.p = p;
        }
    }

    static class MyTryCompositeType {
        @JsonProperty("p")
        Try<Option<String>> p;

        @JsonCreator
        MyTryCompositeType(@JsonProperty("p") Try<Option<String>> p) {
            this.p = p;
        }
    }

    static class MyTrySuccessType {
        @JsonProperty("p")
        Try.Success<String> p;

        @JsonCreator
        MyTrySuccessType(@JsonProperty("p") Try.Success<String> p) {
            this.p = p;
        }
    }

    static class MyTryFailureType {
        @JsonProperty("p")
        Try.Failure<String> p;

        @JsonCreator
        MyTryFailureType(@JsonProperty("p") Try.Failure<String> p) {
            this.p = p;
        }
    }

    @Test
    void itShouldSerializeTrys() throws Exception {
        ObjectMapper mapper = new ObjectMapper().registerModule(new VavrModule());

        assertTrue(mapper.canDeserialize(mapper.constructType(MyTryNormalType.class)));
        assertTrue(mapper.canDeserialize(mapper.constructType(MyTryCompositeType.class)));
        assertTrue(mapper.canDeserialize(mapper.constructType(MyTrySuccessType.class)));
        assertTrue(mapper.canDeserialize(mapper.constructType(MyTryFailureType.class)));

        MyTryNormalType normal1 = new MyTryNormalType(Try.of(() -> "vavr"));
        MyTryNormalType normal2 = mapper.readValue(mapper.writeValueAsString(normal1), MyTryNormalType.class);
        assertEquals(normal1.p, normal2.p);

        MyTryCompositeType composite1 = new MyTryCompositeType(Try.of(() -> Option.some("vavr")));
        MyTryCompositeType composite2 = mapper.readValue(mapper.writeValueAsString(normal1), MyTryCompositeType.class);
        assertEquals(composite1.p, composite2.p);

        MyTrySuccessType success1 = new MyTrySuccessType((Try.Success<String>) Try.success("vavr"));
        MyTrySuccessType success2 = mapper.readValue(mapper.writeValueAsString(success1), MyTrySuccessType.class);
        assertEquals(success1.p, success2.p);

        MyTryFailureType failure1 = new MyTryFailureType((Try.Failure<String>) Try.<String>failure(new Throwable("throwable", new Exception("cause"))));
        MyTryFailureType failure2 = mapper.readValue(mapper.writeValueAsString(failure1), MyTryFailureType.class);
        assertEquals(failure1.p.isFailure(), failure2.p.isFailure());

        assertEquals(failure1.p.getCause().getMessage(), failure2.p.getCause().getMessage());
        assertEquals(failure1.p.getCause().getStackTrace().length, failure2.p.getCause().getStackTrace().length);

        assertEquals(failure1.p.getCause().getCause().getMessage(), failure2.p.getCause().getCause().getMessage());
    }

}
