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
        ObjectWriter writer = mapper().writer();
        CharSeq src = CharSeq.of('a', 'b', 'c');
        String json = writer.writeValueAsString(src);
        Assert.assertEquals("\"abc\"", json);
        CharSeq dst = mapper().readValue(json, CharSeq.class);
        Assert.assertEquals(src, dst);
    }
}
