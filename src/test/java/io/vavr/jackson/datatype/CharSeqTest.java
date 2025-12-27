package io.vavr.jackson.datatype;

import io.vavr.collection.CharSeq;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

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
    void shouldSerializeAndDeserializeWrappedCharSeqAsObject()  throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(CharSeq.class, WrapperObject.class).build();
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(CharSeq.class.getName(), plainJson)).isEqualTo(wrappedJson);
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        assertThat((Iterable) restored).isEqualTo(src);
    }

    @Test
    void shouldSerializeAndDeserializeWrappedCharSeqAsArray()  throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(CharSeq.class, WrapperArray.class).build();
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToArray(CharSeq.class.getName(), plainJson)).isEqualTo(wrappedJson);
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        assertThat((Iterable) restored).isEqualTo(src);
    }

    @Test
    void shouldThrowExceptionWhenDeserializingInvalidCharSeq() {
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() -> mapper().readValue("42", CharSeq.class));
    }
}
