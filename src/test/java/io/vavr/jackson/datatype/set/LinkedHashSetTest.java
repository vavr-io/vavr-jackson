package io.vavr.jackson.datatype.set;

import tools.jackson.core.type.TypeReference;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedHashSetTest extends SetTest {

    @Override
    protected Class<?> clz() {
        return LinkedHashSet.class;
    }

    @Override
    protected TypeReference<LinkedHashSet<Integer>> typeReference() {
        return new TypeReference<LinkedHashSet<Integer>>() {
        };
    }

    @Override
    protected TypeReference<LinkedHashSet<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<LinkedHashSet<Option<String>>>() {
        };
    }

    @Override
    protected Set<?> of(Object... objects) {
        return LinkedHashSet.ofAll(Arrays.asList(objects));
    }

    @Test
    void keepOrder() throws IOException {
        assertThat(LinkedHashSet.of(3, 2, 1)).isEqualTo(mapper().readValue("[3, 2, 1]", LinkedHashSet.class));
    }
}
