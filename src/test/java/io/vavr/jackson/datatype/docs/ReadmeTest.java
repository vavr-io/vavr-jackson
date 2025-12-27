package io.vavr.jackson.datatype.docs;

import io.vavr.collection.List;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ReadmeTest extends BaseTest {

    @Test
    void deser() {
        ObjectMapper mapper = mapper();

        // readme: Serialization/deserialization
        String json = mapper.writeValueAsString(List.of(1));
        List<Integer> restored = mapper.readValue(json, new TypeReference<List<Integer>>() {
        });
        // end of readme

        assertThat(json).isEqualTo("[1]");
        assertThat(restored).isEqualTo(List.of(1));
        assertThat(restored.toString()).isEqualTo("List(1)");
    }
}
