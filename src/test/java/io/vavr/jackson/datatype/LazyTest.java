package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.Lazy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class LazyTest extends BaseTest {
    @Test
    void test1() throws IOException {
        Lazy<?> src = Lazy.of(() -> 1);
        String json = mapper().writer().writeValueAsString(src);
        Assertions.assertEquals("1", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test2() throws IOException {
        Lazy<?> src = Lazy.of(() -> null);
        String json = mapper().writer().writeValueAsString(src);
        Assertions.assertEquals("null", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void testSerializeWithoutContext() throws IOException {
        // Given a lazy date
        Lazy<?> src = Lazy.of(() -> LocalDate.of(2019, 12, 25));

        // When serializing the date using object mapper
        // with Java Time module and VAVR module
        ObjectMapper mapper = mapper().registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        Assertions.assertEquals("[2019,12,25]", json);

        // And the deserialization is successful
        Lazy<?> src2 = mapper.readValue(json, new TypeReference<Lazy<LocalDate>>() {
        });
        Assertions.assertEquals(src, src2);
    }

    static class FrenchDate {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @JsonProperty("date")
        Lazy<LocalDate> value;
    }

    @Test
    void testSerializeWithContext() throws IOException {
        // Given a lazy date with specific format
        FrenchDate src = new FrenchDate();
        src.value = Lazy.of(() -> LocalDate.of(2019, 12, 25));

        // When serializing the date using object mapper
        // with Java Time module and VAVR module
        ObjectMapper mapper = mapper().registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        Assertions.assertEquals("{\"date\":\"25/12/2019\"}", json); // FIXME

        // And the deserialization is successful
        FrenchDate src2 = mapper.readValue(json, FrenchDate.class);
        Assertions.assertEquals(src.value, src2.value);
    }

}
