package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.CharSeq;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CharSeqTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        CharSeq src = CharSeq.of('a', 'b', 'c');
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonObject(CharSeq.class, "\"abc\""), json);
        CharSeq dst = mapper().readValue(json, CharSeq.class);
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectWriter writer = mapper(true).writer();
        CharSeq src = CharSeq.of('a', 'b', 'c');
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonObject(null, "\"abc\""), json);
        CharSeq dst = mapper().readValue(json, CharSeq.class);
        Assert.assertEquals(src, dst);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        mapper().readValue(crashJson(genJsonObject(CharSeq.class, "\"abc\"")), CharSeq.class);
    }
}
