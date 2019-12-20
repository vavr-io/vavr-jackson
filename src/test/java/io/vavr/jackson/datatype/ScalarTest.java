package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.vavr.collection.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ScalarTest extends BaseTest {

    @Test
    void test1() throws IOException {
        List<Integer> l1 = mapper().readValue("[1]", new TypeReference<List<Integer>>() {});
        Assertions.assertEquals(l1, List.of(1));
        List<Boolean> l2 = mapper().readValue("[true]", new TypeReference<List<Boolean>>() {});
        Assertions.assertEquals(l2, List.of(true));
        List<Boolean> l3 = mapper().readValue("[false]", new TypeReference<List<Boolean>>() {});
        Assertions.assertEquals(l3, List.of(false));
        List<Float> l4 = mapper().readValue("[2.4]", new TypeReference<List<Float>>() {});
        Assertions.assertEquals(l4, List.of(2.4f));
        List<Double> l5 = mapper().readValue("[2.4]", new TypeReference<List<Double>>() {});
        Assertions.assertEquals(l5, List.of(2.4));
        List<BigDecimal> l5d = mapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS).readValue("[2.4]", new TypeReference<List<BigDecimal>>() {});
        Assertions.assertEquals(l5d, List.of(BigDecimal.valueOf(2.4)));
        List<String> l6 = mapper().readValue("[\"1\"]", new TypeReference<List<String>>() {});
        Assertions.assertEquals(l6, List.of("1"));
        List<Long> l7 = mapper().readValue("[24]", new TypeReference<List<Long>>() {});
        Assertions.assertEquals(l7, List.of(24L));
        List<Long> l8 = mapper().readValue("[" + Long.MAX_VALUE + "]", new TypeReference<List<Long>>() {});
        Assertions.assertEquals(l8, List.of(Long.MAX_VALUE));
        List<BigInteger> l9 = mapper().readValue("[1234567890123456789012]", new TypeReference<List<BigInteger>>() {});
        Assertions.assertEquals(l9, List.of(new BigInteger("1234567890123456789012")));
    }

    @Test
    void test2() throws IOException {
        Assertions.assertEquals(mapper().readValue("[1.3]", new TypeReference<List<Integer>>() {}), List.of(1));
    }

    @Test
    void test3() throws IOException {
        Assertions.assertEquals(mapper().readValue("[1]", new TypeReference<List<String>>() {}), List.of("1"));
    }
}
