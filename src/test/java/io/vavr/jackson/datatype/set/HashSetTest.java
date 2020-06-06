package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class HashSetTest extends SetTest {

    @Override
    protected Class<?> clz() {
        return HashSet.class;
    }

    @Override
    protected TypeReference<HashSet<Integer>> typeReference() {
        return new TypeReference<HashSet<Integer>>() {};
    }

    @Override
    protected TypeReference<HashSet<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<HashSet<Option<String>>>() {};
    }

    @Override
    protected Set<?> of(Object... objects) {
        return HashSet.ofAll(Arrays.asList(objects));
    }

    @Test
    void testDefaultDeserialization() throws IOException {
        Assertions.assertEquals(mapper().readValue("[1]", Set.class), HashSet.of(1));
    }

    static class FrenchDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
        Set<Date> dates;
    }

    @Test
    void testSerializeWithContext() throws IOException {
        // Given an object containing dates to serialize
        FrenchDates src = new FrenchDates();
        src.dates = HashSet.of(new Date(1591308000000L));

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
}
