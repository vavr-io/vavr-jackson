package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.Array;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithArray {
        @JsonMerge(OptBoolean.TRUE)
        public Array<String> value = Array.of("a", "b", "c");
    }

    static class TestJsonMergeWithArrayConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public Array<String> value;

        protected TestJsonMergeWithArrayConstructor() {
            value = Array.of("a", "b", "c");
        }

        public TestJsonMergeWithArrayConstructor(String d, String e) {
            value = Array.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() throws Exception {
        TestJsonMergeWithArray result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithArray.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() throws Exception {
        TestJsonMergeWithArrayConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithArrayConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithArrayConstructor result = mapper().readerForUpdating(new TestJsonMergeWithArrayConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
