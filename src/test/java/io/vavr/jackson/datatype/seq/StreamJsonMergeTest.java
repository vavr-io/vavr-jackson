package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.Stream;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StreamJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithStream {
        @JsonMerge(OptBoolean.TRUE)
        public Stream<String> value = Stream.of("a", "b", "c");
    }

    static class TestJsonMergeWithStreamConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public Stream<String> value;

        protected TestJsonMergeWithStreamConstructor() {
            value = Stream.of("a", "b", "c");
        }

        public TestJsonMergeWithStreamConstructor(String d, String e) {
            value = Stream.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() {
        TestJsonMergeWithStream result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithStream.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() {
        TestJsonMergeWithStreamConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithStreamConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() {
        TestJsonMergeWithStreamConstructor result = mapper().readerForUpdating(new TestJsonMergeWithStreamConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
