package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class ScalarTest extends BaseTest {

    @Test
    void shouldDeserializeIntegerList() throws IOException {
        List<Integer> l1 = mapper().readValue("[1]", new TypeReference<List<Integer>>() {
        });
        assertThat(List.of(1)).isEqualTo(l1);
    }

    @Test
    void shouldDeserializeBooleanList() throws IOException {
        List<Boolean> l2 = mapper().readValue("[true]", new TypeReference<List<Boolean>>() {
        });
        assertThat(List.of(true)).isEqualTo(l2);

        List<Boolean> l3 = mapper().readValue("[false]", new TypeReference<List<Boolean>>() {
        });
        assertThat(List.of(false)).isEqualTo(l3);
    }

    @Test
    void shouldDeserializeFloatList() throws IOException {
        List<Float> l4 = mapper().readValue("[2.4]", new TypeReference<List<Float>>() {
        });
        assertThat(List.of(2.4f)).isEqualTo(l4);
    }

    @Test
    void shouldDeserializeDoubleAndBigDecimalList() throws IOException {
        List<Double> l5 = mapper().readValue("[2.4]", new TypeReference<List<Double>>() {
        });
        assertThat(List.of(2.4)).isEqualTo(l5);

        List<BigDecimal> l5d = mapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            .readValue("[2.4]", new TypeReference<List<BigDecimal>>() {
            });
        assertThat(List.of(BigDecimal.valueOf(2.4))).isEqualTo(l5d);
    }

    @Test
    void shouldDeserializeStringList() throws IOException {
        List<String> l6 = mapper().readValue("[\"1\"]", new TypeReference<List<String>>() {
        });
        assertThat(List.of("1")).isEqualTo(l6);
    }

    @Test
    void shouldDeserializeLongList() throws IOException {
        List<Long> l7 = mapper().readValue("[24]", new TypeReference<List<Long>>() {
        });
        assertThat(List.of(24L)).isEqualTo(l7);

        List<Long> l8 = mapper().readValue("[" + Long.MAX_VALUE + "]", new TypeReference<List<Long>>() {
        });
        assertThat(List.of(Long.MAX_VALUE)).isEqualTo(l8);
    }

    @Test
    void shouldDeserializeBigIntegerList() throws IOException {
        List<BigInteger> l9 = mapper().readValue("[1234567890123456789012]", new TypeReference<List<BigInteger>>() {
        });
        assertThat(List.of(new BigInteger("1234567890123456789012"))).isEqualTo(l9);
    }

    @Test
    void shouldDeserializeFloatToIntegerList() throws IOException {
        assertThat(List.of(1)).isEqualTo(mapper().readValue("[1.3]", new TypeReference<List<Integer>>() {
        }));
    }

    @Test
    void shouldDeserializeIntegerToStringList() throws IOException {
        assertThat(List.of("1")).isEqualTo(mapper().readValue("[1]", new TypeReference<List<String>>() {
        }));
    }
}
