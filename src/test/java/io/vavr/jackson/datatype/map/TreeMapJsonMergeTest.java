package io.vavr.jackson.datatype.map;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.Tuple;
import io.vavr.collection.TreeMap;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TreeMapJsonMergeTest extends BaseTest {

    static class TestJsonMergeWithTreeMap {
        @JsonMerge(OptBoolean.TRUE)
        public TreeMap<String, String> value = TreeMap.of("foo1", "bar1");
    }

    static class TestJsonMergeWithNestedMap {
        @JsonMerge(OptBoolean.TRUE)
        public TreeMap<String, TreeMap<String, String>> value = TreeMap.of("foo1", TreeMap.of("foo_nested", "bar_nested"));
    }

    @Test
    void shouldMergeString() throws Exception {
        TestJsonMergeWithTreeMap result = mapper().readValue(asJson("{'value': {'foo2':'bar2'}}"), TestJsonMergeWithTreeMap.class);

        assertThat(result.value)
            .hasSize(2)
            .containsExactly(
                Tuple.of("foo1", "bar1"),
                Tuple.of("foo2", "bar2"));
    }

    @Test
    void shouldMergeNested() {
        TestJsonMergeWithNestedMap result = mapper().readValue(asJson("{'value': {'foo1': {'foo_nested2': 'bar_nested2'}}}"), TestJsonMergeWithNestedMap.class);

        assertThat(result.value)
            .hasSize(1)
            .containsExactly(Tuple.of("foo1", TreeMap.of(
                "foo_nested", "bar_nested",
                "foo_nested2", "bar_nested2")));
    }
}
