package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ListTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        String result = writer.writeValueAsString(List.of(1, 2.0, 3));
        Assert.assertEquals(result, genPlainJsonList(List.class, 1, 2.0, 3));
    }

    @Test
    public void test2() throws IOException {
        List<?> result = mapper(false).readValue("[\"!!\",2,3.0]", new TypeReference<List<?>>() {});
        Assert.assertEquals(result, List.of("!!", 2, 3.0));
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        List<Double> result = mapper(false).readValue("[\"!!\",2,3.0]", new TypeReference<List<Double>>() {});
        Assert.assertEquals(result, List.of("!!", 2, 3.0));
    }

    @Test
    public void test4() throws IOException {
        List<?> result = mapper(false).readValue(genPlainJsonList(List.class, 1, 2, 3), List.class);
        Assert.assertEquals(result, List.of(1, 2, 3));
    }

}
