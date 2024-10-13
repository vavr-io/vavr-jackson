package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScalarTest extends BaseTest {

    @Test
    void shouldDeserializeIntegerList() throws IOException {
        List<Integer> l1 = mapper().readValue("[1]", new TypeReference<List<Integer>>() {});
        assertEquals(l1, List.of(1));
    }

    @Test
    void shouldDeserializeBooleanList() throws IOException {
        List<Boolean> l2 = mapper().readValue("[true]", new TypeReference<List<Boolean>>() {});
        assertEquals(l2, List.of(true));

        List<Boolean> l3 = mapper().readValue("[false]", new TypeReference<List<Boolean>>() {});
        assertEquals(l3, List.of(false));
    }

    @Test
    void shouldDeserializeFloatList() throws IOException {
        List<Float> l4 = mapper().readValue("[2.4]", new TypeReference<List<Float>>() {});
        assertEquals(l4, List.of(2.4f));
    }

    @Test
    void shouldDeserializeDoubleAndBigDecimalList() throws IOException {
        List<Double> l5 = mapper().readValue("[2.4]", new TypeReference<List<Double>>() {});
        assertEquals(l5, List.of(2.4));

        List<BigDecimal> l5d = mapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
          .readValue("[2.4]", new TypeReference<List<BigDecimal>>() {});
        assertEquals(l5d, List.of(BigDecimal.valueOf(2.4)));
    }

    @Test
    void shouldDeserializeStringList() throws IOException {
        List<String> l6 = mapper().readValue("[\"1\"]", new TypeReference<List<String>>() {});
        assertEquals(l6, List.of("1"));
    }

    @Test
    void shouldDeserializeLongList() throws IOException {
        List<Long> l7 = mapper().readValue("[24]", new TypeReference<List<Long>>() {});
        assertEquals(l7, List.of(24L));

        List<Long> l8 = mapper().readValue("[" + Long.MAX_VALUE + "]", new TypeReference<List<Long>>() {});
        assertEquals(l8, List.of(Long.MAX_VALUE));
    }

    @Test
    void shouldDeserializeBigIntegerList() throws IOException {
        List<BigInteger> l9 = mapper().readValue("[1234567890123456789012]", new TypeReference<List<BigInteger>>() {});
        assertEquals(l9, List.of(new BigInteger("1234567890123456789012")));
    }

    @Test
    void shouldDeserializeFloatToIntegerList() throws IOException {
        assertEquals(mapper().readValue("[1.3]", new TypeReference<List<Integer>>() {}), List.of(1));
    }

    @Test
    void shouldDeserializeIntegerToStringList() throws IOException {
        assertEquals(mapper().readValue("[1]", new TypeReference<List<String>>() {}), List.of("1"));
    }
}
