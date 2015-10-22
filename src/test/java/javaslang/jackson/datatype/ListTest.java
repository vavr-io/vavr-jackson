package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ListTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        String result = writer.writeValueAsString(List.of(1, 2.0, 3));
        Assert.assertEquals(result, genPlainJsonList(List.class, 1, 2.0, 3));
    }

    @Test
    public void test2() throws IOException {
        List<?> result = mapper().readValue("[\"!!\",2,3.0]", List.class);
        Assert.assertEquals(result, List.of("!!", 2, 3.0));
    }

    @Test
    public void test3() throws IOException {
        List<?> result = mapper().readValue(genPlainJsonList(List.class, 1, 2, 3), List.class);
        Assert.assertEquals(result, List.of(1, 2, 3));
    }
}
