package io.vavr.jackson.datatype.docs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadmeTest extends BaseTest {

    @Test
    void testDeser() throws Exception {
        ObjectMapper mapper = mapper();

        // readme: Serialization/deserialization
        String json = mapper.writeValueAsString(List.of(1));
        List<Integer> restored = mapper.readValue(json, new TypeReference<List<Integer>>() {});
        // end of readme

        assertEquals("[1]", json);
        assertEquals(List.of(1), restored);
        assertEquals("List(1)", restored.toString());
    }
}
