package io.vavr.jackson.datatype.map;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.vavr.Tuple;
import io.vavr.collection.LinkedHashMap;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedHashMapJsonMergeTest extends BaseTest {

    static class TestJsonMergeWithLinkedHashMap {
        @JsonMerge(OptBoolean.TRUE)
        public LinkedHashMap<String, String> value = LinkedHashMap.of("foo1", "bar1");
    }

    static class TestJsonMergeWithNestedMap {
        @JsonMerge(OptBoolean.TRUE)
        public LinkedHashMap<String, LinkedHashMap<String, String>> value = LinkedHashMap.of("foo1", LinkedHashMap.of("foo_nested", "bar_nested"));
    }

    @Test
    public void shouldMergeString() throws Exception {
        TestJsonMergeWithLinkedHashMap result = mapper().readValue(asJson("{'value': {'foo2':'bar2'}}"), TestJsonMergeWithLinkedHashMap.class);

        assertThat(result.value)
            .hasSize(2)
            .containsExactly(
                Tuple.of("foo1", "bar1"),
                Tuple.of("foo2", "bar2"));
    }

    @Test
    public void shouldMergeNested() throws JsonProcessingException {
        TestJsonMergeWithNestedMap result = mapper().readValue(asJson("{'value': {'foo1': {'foo_nested2': 'bar_nested2'}}}"), TestJsonMergeWithNestedMap.class);

        assertThat(result.value)
            .hasSize(1)
            .containsExactly(Tuple.of("foo1", LinkedHashMap.of(
                "foo_nested", "bar_nested",
                "foo_nested2", "bar_nested2")));
    }
}
