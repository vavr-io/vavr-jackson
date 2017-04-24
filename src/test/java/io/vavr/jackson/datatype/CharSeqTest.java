package io.vavr.jackson.datatype;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.vavr.collection.CharSeq;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CharSeqTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        CharSeq src = CharSeq.of("abc");
        String json = writer.writeValueAsString(src);
        Assert.assertEquals("\"abc\"", json);
        CharSeq dst = mapper().readValue(json, CharSeq.class);
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(CharSeq.class, WrapperObject.class);
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(CharSeq.class.getName(), plainJson));
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(CharSeq.class, WrapperArray.class);
        CharSeq src = CharSeq.of("abc");
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(CharSeq.class.getName(), plainJson));
        CharSeq restored = mapper.readValue(wrappedJson, CharSeq.class);
        Assert.assertEquals(src, restored);
    }

    @Test(expected = JsonMappingException.class)
    public void test4() throws IOException {
        mapper().readValue("42", CharSeq.class);
    }
}
