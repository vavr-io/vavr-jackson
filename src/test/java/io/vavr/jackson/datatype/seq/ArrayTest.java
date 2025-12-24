package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.vavr.collection.Array;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayTest extends SeqTest {
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

    static class FrenchDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
        Array<Date> dates;
    }

    @Test
    void serializeWithContext() throws IOException {
        // Given an object containing dates to serialize
        FrenchDates src = new FrenchDates();
        src.dates = Array.of(new Date(1591308000000L));

        // When serializing them using object mapper
        // with VAVR module and Java Time module
        ObjectMapper mapper = mapper();
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        assertThat(json).isEqualTo("{\"dates\":[\"05/06/2020\"]}");

        // And the deserialization is successful
        FrenchDates restored = mapper.readValue(json, FrenchDates.class);
        assertThat(restored.dates).isEqualTo(src.dates);
    }
}
