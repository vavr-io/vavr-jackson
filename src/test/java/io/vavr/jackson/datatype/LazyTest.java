package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.vavr.Lazy;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.cfg.DateTimeFeature;

import static org.assertj.core.api.Assertions.assertThat;

class LazyTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeLazyWithValue() {
        Lazy<?> src = Lazy.of(() -> 1);
        String json = mapper().writer().writeValueAsString(src);
        assertThat(json).isEqualTo("1");
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeLazyWithNullValue() {
        Lazy<?> src = Lazy.of(() -> null);
        String json = mapper().writer().writeValueAsString(src);
        assertThat(json).isEqualTo("null");
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeTwoLevelLazy() {
        Lazy<Lazy<Integer>> src = Lazy.of(() -> Lazy.of(() -> 1));
        String json = mapper().writeValueAsString(src);
        assertThat(json).isEqualTo("1");
        Lazy<?> restored = mapper().readValue(json, new TypeReference<Lazy<Lazy<Integer>>>() {
        });
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeLazyWithLocalDateWithoutContext() {
        // Given a lazy date
        Lazy<?> src = Lazy.of(() -> LocalDate.of(2019, 12, 25));

        // When serializing the date using object mapper
        // with Java Time module and VAVR module
        ObjectMapper mapper = mapper().rebuild().enable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS).build();
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        assertThat(json).isEqualTo("[2019,12,25]");

        // And the deserialization is successful
        Lazy<?> src2 = mapper.readValue(json, new TypeReference<Lazy<LocalDate>>() {
        });
        assertThat(src2).isEqualTo(src);
    }

    static class FrenchDate {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @JsonProperty("date")
        Lazy<LocalDate> value;
    }

    @Test
    void serializeWithContext() {
        // Given a lazy date with specific format
        FrenchDate src = new FrenchDate();
        src.value = Lazy.of(() -> LocalDate.of(2019, 12, 25));

        // When serializing the date using object mapper
        // with Java Time module and VAVR module
        ObjectMapper mapper = mapper();
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        assertThat(json).isEqualTo("{\"date\":\"25/12/2019\"}");

        // And the deserialization is successful
        FrenchDate src2 = mapper.readValue(json, FrenchDate.class);
        assertThat(src2.value).isEqualTo(src.value);
    }

    static class WrapperForStaticTyping {
        @JsonSerialize(typing = JsonSerialize.Typing.STATIC)
        public Lazy<Base> getValue() {
            return Lazy.of(Derived::new);
        }
    }

    static class WrapperForDynamicTyping {
        @JsonSerialize(typing = JsonSerialize.Typing.DYNAMIC)
        public Lazy<Base> getValue() {
            return Lazy.of(Derived::new);
        }
    }

    static class Base {
        public int getA() {
            return 1;
        }
    }

    @JsonPropertyOrder({"a", "b"})
    static class Derived extends Base {
        public int getB() {
            return 2;
        }
    }

    @Test
    void serializeWithStaticTyping() {
        // Given an instance with lazy value configured with static typing
        WrapperForStaticTyping src = new WrapperForStaticTyping();

        // When serializing it using object mapper with VAVR Module
        String json = mapper().writeValueAsString(src);

        // Then the serialization is successful
        assertThat(json).isEqualTo("{\"value\":{\"a\":1}}");
    }

    @Test
    void serializeWithDynamicTyping() {
        // Given an instance with lazy value configured with dynamic typing
        WrapperForDynamicTyping src = new WrapperForDynamicTyping();

        // When serializing it using object mapper with VAVR Module
        String json = mapper().writeValueAsString(src);

        // Then the serialization is successful
        assertThat(json).isEqualTo("{\"value\":{\"a\":1,\"b\":2}}");
    }
}
