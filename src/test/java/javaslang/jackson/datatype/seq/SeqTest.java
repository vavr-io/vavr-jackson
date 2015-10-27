package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.Seq;
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
        Seq<?> src = of(1, 2.0, of(3, 4));
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(clz(), 1, 2.0, of(3, 4)), json);
        Seq<?> dst = (Seq<?>) mapper(false).readValue(json, clz());
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectWriter writer = mapper(true).writer();
        Seq<?> src = of(1, 2.0, of(3, 4));
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(null, 1, 2.0, of(3, 4)), json);
        Seq<?> dst1 = (Seq<?>) mapper(false).readValue(json, clz());
        Assert.assertEquals(of(1, 2.0, Arrays.asList(3, 4)), dst1);
        java.util.List<?> dst2 = (java.util.List<?>) mapper(false).readValue(json, Object.class);
        Assert.assertEquals(Arrays.asList(1, 2.0, Arrays.asList(3, 4)), dst2);
    }

}
