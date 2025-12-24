package io.vavr.jackson.datatype.multimap;

import io.vavr.collection.HashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import tools.jackson.core.type.TypeReference;

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
        return new TypeReference<HashMultimap<String, Option<Integer>>>() {
        };
    }
}
