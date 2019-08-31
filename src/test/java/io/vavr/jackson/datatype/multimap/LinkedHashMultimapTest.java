package io.vavr.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;

public class LinkedHashMultimapTest extends MultimapTest {
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
        return new TypeReference<LinkedHashMultimap<String, Option<Integer>>>() {};
    }

    @Test
    public void shouldKeepOrder() throws IOException {
        Multimap<Object, Object> vavrObject = emptyMap().put("2", 1).put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.LinkedHashMap<>();
        javaObject.put("2", Collections.singletonList(1));
        javaObject.put("1", Collections.singletonList(2));

        String json = mapper().writer().writeValueAsString(vavrObject);
        Assertions.assertEquals(genJsonMap(javaObject), json);

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        Assertions.assertEquals(restored, vavrObject);
    }
}
