package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.Vector;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VectorJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithVector {
        @JsonMerge(OptBoolean.TRUE)
        public Vector<String> value = Vector.of("a", "b", "c");
    }

    static class TestJsonMergeWithVectorConstructor {
        @JsonMerge(OptBoolean.TRUE)
        public Vector<String> value;

        protected TestJsonMergeWithVectorConstructor() {
            value = Vector.of("a", "b", "c");
        }

        public TestJsonMergeWithVectorConstructor(String d, String e) {
            value = Vector.of(d, e);
        }
    }

    @Test
    void shouldMergeSeq() throws Exception {
        TestJsonMergeWithVector result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithVector.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeSeqConstructor() throws Exception {
        TestJsonMergeWithVectorConstructor result = mapper().readValue(asJson("{'value':['d', 'e', 'f']}"), TestJsonMergeWithVectorConstructor.class);

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithVectorConstructor result = mapper().readerForUpdating(new TestJsonMergeWithVectorConstructor("a", "b"))
          .readValue(asJson("{'value':['c', 'd', 'e', 'f']}"));

        assertThat(result.value.toJavaList()).containsExactly("a", "b", "c", "d", "e", "f");
    }
}
