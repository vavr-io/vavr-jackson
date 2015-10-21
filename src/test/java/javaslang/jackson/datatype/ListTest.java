package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ListTest {

    protected ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());
        return mapper;
    }

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        String result = writer.writeValueAsString(List.of(1, 2, 3));
        Assert.assertEquals("[1,2,3]", result);
    }

    @Test
    public void test2() throws IOException {
        List<?> result = mapper().readValue("[1,2,3]", List.class);
        Assert.assertEquals(List.of(1, 2, 3), result);
    }
}
