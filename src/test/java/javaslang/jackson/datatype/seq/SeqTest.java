package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.Seq;
import javaslang.collection.Stack;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public abstract class SeqTest extends BaseTest {

    abstract Class<?> clz();

    abstract Seq<?> of(Object... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        Seq<?> src = of(1, null, 2.0f, of(3, 4));
        String json = writer.writeValueAsString(src);
        if(clz() != Stack.class) { // TODO In case of Stack we have List.class in json instead of expected Stack.class
            Assert.assertEquals(genJsonList(clz(), 1, null, 2.0f, of(3, 4)), json);
        }
        Seq<?> dst = (Seq<?>) mapper(false).readValue(json, clz());
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectWriter writer = mapper(true).writer();
        Seq<?> src = of(1, null, 2.0f, of(3, 4));
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(null, 1, null, 2.0f, of(3, 4)), json);
        Seq<?> dst = (Seq<?>) mapper(false).readValue(json, clz());
        Assert.assertEquals(of(1, null, 2.0f, Arrays.asList(3, 4)), dst);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        mapper(false).readValue(crashJson(genJsonList(clz(), 1, 2.0f, of(3, 4))), clz());
    }

}
