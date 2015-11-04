package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.Seq;
import javaslang.collection.Stack;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public abstract class SeqTest extends BaseTest {

    abstract Class<?> clz();

    abstract Seq<?> of(Object... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Seq<?> src = of(1, null, 2.0f, "s");
        String json = writer.writeValueAsString(src);
        if(clz() != Stack.class) { // TODO In case of Stack we have List.class in json instead of expected Stack.class
            Assert.assertEquals(genJsonList(1, null, 2.0f, "s"), json);
        }
        Seq<?> dst = (Seq<?>) mapper().readValue(json, clz());
        Assert.assertEquals(src, dst);
    }
}
