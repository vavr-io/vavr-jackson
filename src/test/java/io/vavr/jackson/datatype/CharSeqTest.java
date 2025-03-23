package io.vavr.jackson.datatype;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.vavr.collection.CharSeq;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class CharSeqTest extends BaseTest {

    @Test
    void shouldSerializeAndDeserializeCharSeq() throws IOException {
        ObjectWriter writer = mapper().writer();
        CharSeq src = CharSeq.of("abc");
        String json = writer.writeValueAsString(src);
        assertThat(json).isEqualTo("\"abc\"");
        CharSeq dst = mapper().readValue(json, CharSeq.class);
        assertThat((Iterable) dst).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeWrappedCharSeqAsObject() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(CharSeq.class, WrapperObject.class);
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(CharSeq.class.getName(), plainJson)).isEqualTo(wrappedJson);
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        assertThat((Iterable) restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeWrappedCharSeqAsArray() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(CharSeq.class, WrapperArray.class);
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToArray(CharSeq.class.getName(), plainJson)).isEqualTo(wrappedJson);
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        assertThat((Iterable) restored).isEqualTo(src);
    }

    @Test
    void shouldThrowExceptionWhenDeserializingInvalidCharSeq() {
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> mapper().readValue("42", CharSeq.class));
    }
}
