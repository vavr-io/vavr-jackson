package javaslang.jackson.datatype.map;

import javaslang.collection.HashMap;
import javaslang.collection.Map;

public class HashMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return HashMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return HashMap.empty();
    }
}
