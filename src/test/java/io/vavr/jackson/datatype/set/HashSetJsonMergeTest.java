package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.HashSet;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HashSetJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithHashSet {
        @JsonMerge(OptBoolean.TRUE)
        public HashSet<String> value = HashSet.of("a", "b", "c");
    }

    static class TestJsonMergeWithHashSetConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public HashSet<String> value;

        protected TestJsonMergeWithHashSetConstructor() {
            value = HashSet.of("a", "b", "c");
        }

        public TestJsonMergeWithHashSetConstructor(String d, String e) {
            value = HashSet.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() {
        TestJsonMergeWithHashSet result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithHashSet.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() {
        TestJsonMergeWithHashSetConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithHashSetConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() {
        TestJsonMergeWithHashSetConstructor result = mapper().readerForUpdating(new TestJsonMergeWithHashSetConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
