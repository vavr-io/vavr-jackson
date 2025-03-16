package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.Array;
import io.vavr.collection.IndexedSeq;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexedSeqJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithSeq {
        @JsonMerge(OptBoolean.TRUE)
        public IndexedSeq<String> value = Array.of("a", "b", "c");
    }

    static class TestJsonMergeWithSeqConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public IndexedSeq<String> value;

        protected TestJsonMergeWithSeqConstructor() {
            value = Array.of("a", "b", "c");
        }

        public TestJsonMergeWithSeqConstructor(String d, String e) {
            value = Array.of(d, e);
        }
    }

    @Test
    public void shouldMergeSeq() throws Exception {
        TestJsonMergeWithSeq result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithSeq.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    public void shouldMergeSeqConstructor() throws Exception {
        TestJsonMergeWithSeqConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithSeqConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    public void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithSeqConstructor result = mapper().readerForUpdating(new TestJsonMergeWithSeqConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
