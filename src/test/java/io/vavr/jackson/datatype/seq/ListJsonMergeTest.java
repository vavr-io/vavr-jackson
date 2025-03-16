package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithList {
        @JsonMerge(OptBoolean.TRUE)
        public List<String> value = List.of("a", "b", "c");
    }

    static class TestJsonMergeWithListConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public List<String> value;

        protected TestJsonMergeWithListConstructor() {
            value = List.of("a", "b", "c");
        }

        public TestJsonMergeWithListConstructor(String d, String e) {
            value = List.of(d, e);
        }
    }

    @Test
    public void shouldMergeSeq() throws Exception {
        TestJsonMergeWithList result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithList.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    public void shouldMergeSeqConstructor() throws Exception {
        TestJsonMergeWithListConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithListConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    public void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithListConstructor result = mapper().readerForUpdating(new TestJsonMergeWithListConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
