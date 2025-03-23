package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.TreeSet;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TreeSetJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithTreeSet {
        @JsonMerge(OptBoolean.TRUE)
        public TreeSet<String> value = TreeSet.of("a", "b", "c");
    }

    static class TestJsonMergeWithTreeSetConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public TreeSet<String> value;

        protected TestJsonMergeWithTreeSetConstructor() {
            value = TreeSet.of("a", "b", "c");
        }

        public TestJsonMergeWithTreeSetConstructor(String d, String e) {
            value = TreeSet.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() throws Exception {
        TestJsonMergeWithTreeSet result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithTreeSet.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() throws Exception {
        TestJsonMergeWithTreeSetConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithTreeSetConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithTreeSetConstructor result = mapper().readerForUpdating(new TestJsonMergeWithTreeSetConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
