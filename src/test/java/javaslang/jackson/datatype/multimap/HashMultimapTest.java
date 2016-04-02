package javaslang.jackson.datatype.multimap;

import javaslang.collection.HashMultimap;
import javaslang.collection.Multimap;

public class HashMultimapTest extends MultimapTest {
    @Override
    Class<?> clz() {
        return HashMultimap.class;
    }

    @Override
    <K, V> Multimap<K, V> emptyMap() {
        return HashMultimap.withSeq().empty();
    }
}
