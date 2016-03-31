package javaslang.jackson.datatype.multimap;

import javaslang.collection.LinkedHashMultimap;
import javaslang.collection.Multimap;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

public class LinkedHashMultimapTest extends MultimapTest {
    @Override
    Class<?> clz() {
        return LinkedHashMultimap.class;
    }

    @Override
    <K, V> Multimap<K, V> emptyMap() {
        return LinkedHashMultimap.withSeq().empty();
    }

    @Test
    public void shouldKeepOrder() throws IOException {
        Multimap<Object, Object> javaslangObject = emptyMap().put("2", 1).put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.LinkedHashMap<>();
        javaObject.put("2", Collections.singletonList(1));
        javaObject.put("1", Collections.singletonList(2));

        String json = mapper().writer().writeValueAsString(javaslangObject);
        Assert.assertEquals(genJsonMap(javaObject), json);

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        Assert.assertEquals(restored, javaslangObject);
    }
}
