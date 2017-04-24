package io.vavr.jackson.datatype.map;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class LinkedHashMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return LinkedHashMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return LinkedHashMap.empty();
    }

    @Override
    protected TypeReference<LinkedHashMap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<LinkedHashMap<String, Option<Integer>>>() {};
    }

    @Test
    public void shouldKeepOrder() throws IOException {
        Map<Object, Object> vavrObject = emptyMap().put("2", 1).put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.LinkedHashMap<>();
        javaObject.put("2", 1);
        javaObject.put("1", 2);

        String json = mapper().writer().writeValueAsString(vavrObject);
        Assert.assertEquals(genJsonMap(javaObject), json);

        Map<?, ?> restored = (Map<?, ?>) mapper().readValue(json, clz());
        Assert.assertEquals(restored, vavrObject);
    }
}
