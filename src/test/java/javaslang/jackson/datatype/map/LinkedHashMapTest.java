package javaslang.jackson.datatype.map;

import javaslang.collection.LinkedHashMap;
import javaslang.collection.Map;

public class LinkedHashMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return LinkedHashMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return LinkedHashMap.empty();
    }
}
