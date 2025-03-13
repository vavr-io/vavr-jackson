package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.collection.Array;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class ArrayTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Array.class;
    }

    @Override
    protected TypeReference<Array<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Array<Option<String>>>() {
        };
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Array.ofAll(Arrays.asList(objects));
    }

    @Test
    void testSerializeWithContext() throws IOException {
        // Given an object containing dates to serialize
        FrenchDates src = new FrenchDates();
        src.dates = Array.of(new Date(1591308000000L));

        // When serializing them using object mapper
        // with VAVR module and Java Time module
        ObjectMapper mapper = mapper().registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        Assertions.assertEquals("{\"dates\":[\"05/06/2020\"]}", json);

        // And the deserialization is successful
        FrenchDates restored = mapper.readValue(json, FrenchDates.class);
        Assertions.assertEquals(src.dates, restored.dates);
    }

    static class FrenchDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
        Array<Date> dates;
    }
}
