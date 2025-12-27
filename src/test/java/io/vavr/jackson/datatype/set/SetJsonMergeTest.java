package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SetJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithSet {
        @JsonMerge(OptBoolean.TRUE)
        public Set<String> value = HashSet.of("a", "b", "c");
    }

    static class TestJsonMergeWithSetConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public Set<String> value;

        protected TestJsonMergeWithSetConstructor() {
            value = HashSet.of("a", "b", "c");
        }

        public TestJsonMergeWithSetConstructor(String d, String e) {
            value = HashSet.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() {
        TestJsonMergeWithSet result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithSet.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() {
        TestJsonMergeWithSetConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithSetConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() {
        TestJsonMergeWithSetConstructor result = mapper().readerForUpdating(new TestJsonMergeWithSetConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
