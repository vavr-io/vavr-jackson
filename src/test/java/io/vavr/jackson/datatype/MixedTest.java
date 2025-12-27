package io.vavr.jackson.datatype;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DatabindException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class MixedTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeVavrHashMapWithListValues() {
        Object src = HashMap.empty().put("key1", List.of(1, 2)).put("key2", List.of(3, 4));
        String json = mapper().writer().writeValueAsString(src);
        assertThat(src).isEqualTo(mapper().readValue(json, new TypeReference<HashMap<?, List<?>>>() {
        }));
    }

    @Test
    void shouldSerializeAndDeserializeListOfVavrHashMaps() {
        Object src = List.of(HashMap.empty().put("key1", 1), HashMap.empty().put("key2", 2));
        String json = mapper().writer().writeValueAsString(src);
        assertThat(src).isEqualTo(mapper().readValue(json, new TypeReference<List<HashMap<?, ?>>>() {
        }));
    }

    @Test
    void shouldSerializeAndDeserializeListContainingJavaMap() {
        java.util.Map<String, String> javaHMap = new java.util.HashMap<>();
        javaHMap.put("1", "2");
        List<?> src = List.of(javaHMap);
        List<?> restored = mapper().readValue(mapper().writer().writeValueAsString(src), List.class);
        assertThat(src).isEqualTo(restored);
    }

    @Test
    void shouldThrowDatabindExceptionForInvalidListElements() {
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue("[\"s\"]", new TypeReference<List<Integer>>() {
            }));
    }

    @Test
    void shouldSerializeAndDeserializeNestedVavrLists() {
        Object src = List.of(List.of(1));
        String json = mapper().writer().writeValueAsString(src);
        assertThat(src).isEqualTo(mapper().readValue(json, new TypeReference<List<List<?>>>() {
        }));
    }

    @Test
    void shouldSerializeAndDeserializeVavrHashMapWithNestedHashMapValues() {
        Object src = HashMap.empty().put("1", HashMap.empty().put("X", "Y"));
        String json = mapper().writer().writeValueAsString(src);
        assertThat(src).isEqualTo(mapper().readValue(json, new TypeReference<HashMap<?, HashMap<?, ?>>>() {
        }));
    }

    @Test
    void shouldSerializeAndDeserializeListContainingJavaList() {
        Object src = List.of(Arrays.asList(1, 2));
        String json = mapper().writer().writeValueAsString(src);
        assertThat(src).isEqualTo(mapper().readValue(json, List.class));
        assertThat(src).isEqualTo(mapper().readValue(json, new TypeReference<List<java.util.List<?>>>() {
        }));
    }
}
