package io.vavr.jackson.datatype;

import io.vavr.collection.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;

import static org.assertj.core.api.Assertions.assertThat;

class ScalarTest extends BaseTest {

    @Test
    void shouldDeserializeIntegerList() {
        List<Integer> l1 = mapper().readValue("[1]", new TypeReference<List<Integer>>() {
        });
        assertThat(List.of(1)).isEqualTo(l1);
    }

    @Test
    void shouldDeserializeBooleanList() {
        List<Boolean> l2 = mapper().readValue("[true]", new TypeReference<List<Boolean>>() {
        });
        assertThat(List.of(true)).isEqualTo(l2);

        List<Boolean> l3 = mapper().readValue("[false]", new TypeReference<List<Boolean>>() {
        });
        assertThat(List.of(false)).isEqualTo(l3);
    }

    @Test
    void shouldDeserializeFloatList() {
        List<Float> l4 = mapper().readValue("[2.4]", new TypeReference<List<Float>>() {
        });
        assertThat(List.of(2.4f)).isEqualTo(l4);
    }

    @Test
    void shouldDeserializeDoubleAndBigDecimalList() {
        List<Double> l5 = mapper().readValue("[2.4]", new TypeReference<List<Double>>() {
        });
        assertThat(List.of(2.4)).isEqualTo(l5);

        List<BigDecimal> l5d = mapper().rebuild().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS).build()
            .readValue("[2.4]", new TypeReference<List<BigDecimal>>() {
            });
        assertThat(List.of(BigDecimal.valueOf(2.4))).isEqualTo(l5d);
    }

    @Test
    void shouldDeserializeStringList() {
        List<String> l6 = mapper().readValue("[\"1\"]", new TypeReference<List<String>>() {
        });
        assertThat(List.of("1")).isEqualTo(l6);
    }

    @Test
    void shouldDeserializeLongList() {
        List<Long> l7 = mapper().readValue("[24]", new TypeReference<List<Long>>() {
        });
        assertThat(List.of(24L)).isEqualTo(l7);

        List<Long> l8 = mapper().readValue("[" + Long.MAX_VALUE + "]", new TypeReference<List<Long>>() {
        });
        assertThat(List.of(Long.MAX_VALUE)).isEqualTo(l8);
    }

    @Test
    void shouldDeserializeBigIntegerList() {
        List<BigInteger> l9 = mapper().readValue("[1234567890123456789012]", new TypeReference<List<BigInteger>>() {
        });
        assertThat(List.of(new BigInteger("1234567890123456789012"))).isEqualTo(l9);
    }

    @Test
    void shouldDeserializeFloatToIntegerList() {
        assertThat(List.of(1)).isEqualTo(mapper().readValue("[1.3]", new TypeReference<List<Integer>>() {
        }));
    }

    @Test
    void shouldDeserializeIntegerToStringList() {
        assertThat(List.of("1")).isEqualTo(mapper().readValue("[1]", new TypeReference<List<String>>() {
        }));
    }
}
