package javaslang.jackson.datatype.multimap;

import javaslang.collection.Multimap;
import javaslang.collection.TreeMultimap;

public class TreeMultimapTest extends MultimapTest {
    @Override
    Class<?> clz() {
        return TreeMultimap.class;
    }

    @Override
    <K, V> Multimap<K, V> emptyMap() {
        return TreeMultimap.withSeq().empty((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }
}
