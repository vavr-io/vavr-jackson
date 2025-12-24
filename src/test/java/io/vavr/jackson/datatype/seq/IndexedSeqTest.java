package io.vavr.jackson.datatype.seq;

import io.vavr.collection.Array;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import java.util.Arrays;
import tools.jackson.core.type.TypeReference;

public class IndexedSeqTest extends SeqTest {
    @Override
    protected Class<?> clz() {
        return Array.class;
    }

    @Override
    protected TypeReference<IndexedSeq<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<IndexedSeq<Option<String>>>() {
        };
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return Array.ofAll(Arrays.asList(objects));
    }
}
