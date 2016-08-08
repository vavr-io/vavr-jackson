package javaslang.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.collection.HashMultimap;
import javaslang.collection.Multimap;
import javaslang.control.Option;

public class HashMultimapTest extends MultimapTest {
    @Override
    Class<?> clz() {
        return HashMultimap.class;
    }

    @Override
    <K, V> Multimap<K, V> emptyMap() {
        return HashMultimap.withSeq().empty();
    }

    @Override
    protected TypeReference<HashMultimap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<HashMultimap<String, Option<Integer>>>() {};
    }
}
