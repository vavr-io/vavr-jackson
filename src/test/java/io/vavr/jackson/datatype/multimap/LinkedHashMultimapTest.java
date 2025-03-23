package io.vavr.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedHashMultimapTest extends MultimapTest {
    @Override
    Class<?> clz() {
        return LinkedHashMultimap.class;
    }

    @Override
    <K, V> Multimap<K, V> emptyMap() {
        return LinkedHashMultimap.withSeq().empty();
    }

    @Override
    protected TypeReference<LinkedHashMultimap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<LinkedHashMultimap<String, Option<Integer>>>() {
        };
    }

    @Test
    void shouldKeepOrder() throws IOException {
        Multimap<Object, Object> vavrObject = emptyMap().put("2", 1).put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.LinkedHashMap<>();
        javaObject.put("2", Collections.singletonList(1));
        javaObject.put("1", Collections.singletonList(2));

        String json = mapper().writer().writeValueAsString(vavrObject);
        assertThat(json).isEqualTo(genJsonMap(javaObject));

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        assertThat(vavrObject).isEqualTo(restored);
    }
}
