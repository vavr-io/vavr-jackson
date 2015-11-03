package javaslang.jackson.datatype.map;

import javaslang.collection.Map;
import javaslang.collection.TreeMap;

public class TreeMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return TreeMap.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    <K, V> Map<K, V> emptyMap() {
        return TreeMap.empty((o1, o2) -> ((Comparable<Object>) o1).compareTo(o2));
    }
}
