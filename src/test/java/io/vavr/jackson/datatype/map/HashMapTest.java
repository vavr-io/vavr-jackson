package io.vavr.jackson.datatype.map;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return HashMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return HashMap.empty();
    }

    @Override
    protected TypeReference<HashMap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<HashMap<String, Option<Integer>>>() {
        };
    }

    @Test
    void defaultDeserialization() {
        assertThat(HashMap.empty().put("1", "2")).isEqualTo(mapper().readValue("{\"1\":\"2\"}", Map.class));
    }
}
