package io.vavr.jackson.datatype;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.vavr.collection.CharSeq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CharSeqTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeCharSeq() throws IOException {
        ObjectWriter writer = mapper().writer();
        CharSeq src = CharSeq.of("abc");
        String json = writer.writeValueAsString(src);
        Assertions.assertEquals("\"abc\"", json);
        CharSeq dst = mapper().readValue(json, CharSeq.class);
        Assertions.assertEquals(src, dst);
    }

    @Test
    void shouldSerializeAndDeserializeWrappedCharSeqAsObject() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(CharSeq.class, WrapperObject.class);
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToObject(CharSeq.class.getName(), plainJson));
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void shouldSerializeAndDeserializeWrappedCharSeqAsArray() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(CharSeq.class, WrapperArray.class);
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToArray(CharSeq.class.getName(), plainJson));
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void shouldThrowExceptionWhenDeserializingInvalidCharSeq() {
        assertThrows(JsonMappingException.class, () -> mapper().readValue("42", CharSeq.class));
    }
}
