package javaslang.jackson.datatype.map;

import javaslang.collection.Map;
import javaslang.collection.TreeMap;

public class TreeMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return TreeMap.class;
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }
}
