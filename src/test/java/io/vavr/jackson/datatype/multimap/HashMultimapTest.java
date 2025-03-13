package io.vavr.jackson.datatype.multimap;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;

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
