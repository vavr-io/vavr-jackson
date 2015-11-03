package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.Set;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public abstract class SetTest extends BaseTest {

    abstract Class<?> clz();

    abstract Set<?> of(Object... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        Set<?> src = of(1, 2, 5);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(clz(), 1, 2, 5), json);
        Set<?> dst = (Set<?>) mapper().readValue(json, clz());
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectWriter writer = mapper(true).writer();
        Set<?> src = of(1, 2, 5);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(null, 1, 2, 5), json);
        Set<?> dst = (Set<?>) mapper().readValue(json, clz());
        Assert.assertEquals(of(1, 2, 5), dst);
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        mapper().readValue(crashJson(genJsonList(clz(), 1, 2, 5)), clz());
    }

}
