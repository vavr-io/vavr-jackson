package io.vavr.jackson.datatype.multimap;

import io.vavr.collection.Multimap;
import io.vavr.collection.TreeMultimap;
import io.vavr.control.Option;
import tools.jackson.core.type.TypeReference;

public class TreeMultimapTest extends MultimapTest {
    @Override
    Class<?> clz() {
        return TreeMultimap.class;
    }

    @Override
    <K, V> Multimap<K, V> emptyMap() {
        return TreeMultimap.withSeq().empty((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }

    @Override
    protected TypeReference<TreeMultimap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<TreeMultimap<String, Option<Integer>>>() {
        };
    }
}
