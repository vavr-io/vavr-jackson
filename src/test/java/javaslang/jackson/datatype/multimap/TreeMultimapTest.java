package javaslang.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.collection.Multimap;
import javaslang.collection.TreeMultimap;
import javaslang.control.Option;

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
        return new TypeReference<TreeMultimap<String, Option<Integer>>>() {};
    }
}
