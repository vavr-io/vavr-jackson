package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeqJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithSeq {
        @JsonMerge(OptBoolean.TRUE)
        public Seq<String> value = List.of("a", "b", "c");
    }

    static class TestJsonMergeWithSeqConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public Seq<String> value;

        protected TestJsonMergeWithSeqConstructor() {
            value = List.of("a", "b", "c");
        }

        public TestJsonMergeWithSeqConstructor(String d, String e) {
            value = List.of(d, e);
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
