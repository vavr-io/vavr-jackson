package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.collection.Map;
import javaslang.collection.TreeMap;
import javaslang.control.Option;

public class TreeMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return TreeMap.class;
    }

    @Override
    protected TypeReference<TreeMap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<TreeMap<String, Option<Integer>>>() {};
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }
}
