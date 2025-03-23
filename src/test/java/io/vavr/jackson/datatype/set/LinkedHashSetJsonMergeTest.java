package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.LinkedHashSet;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedHashSetJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithLinkedHashSet {
        @JsonMerge(OptBoolean.TRUE)
        public LinkedHashSet<String> value = LinkedHashSet.of("a", "b", "c");
    }

    static class TestJsonMergeWithLinkedHashSetConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public LinkedHashSet<String> value;

        protected TestJsonMergeWithLinkedHashSetConstructor() {
            value = LinkedHashSet.of("a", "b", "c");
        }

        public TestJsonMergeWithLinkedHashSetConstructor(String d, String e) {
            value = LinkedHashSet.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() throws Exception {
        TestJsonMergeWithLinkedHashSet result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithLinkedHashSet.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() throws Exception {
        TestJsonMergeWithLinkedHashSetConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithLinkedHashSetConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithLinkedHashSetConstructor result = mapper().readerForUpdating(new TestJsonMergeWithLinkedHashSetConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
