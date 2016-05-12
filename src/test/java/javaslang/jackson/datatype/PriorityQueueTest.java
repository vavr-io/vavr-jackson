package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.PriorityQueue;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PriorityQueueTest extends BaseTest {

    @Test(expected = JsonMappingException.class)
    public void testGeneric1() throws IOException {
        mapper().readValue("[1, 2]", PriorityQueue.class);
    }

    @Test(expected = JsonMappingException.class)
    public void testGeneric2() throws IOException {
        mapper().readValue("[1, 2]", new TypeReference<PriorityQueue<Object>>() {});
    }

    static class Incomparable {
        private int i = 0;
        int getI() { return i; }
        void setI(int i) { this.i = i; }
    }

    @Test(expected = JsonMappingException.class)
    public void testGeneric3() throws IOException {
        mapper().readValue("[{\"i\":1}, {\"i\":2}]", new TypeReference<PriorityQueue<PriorityQueueTest.Incomparable>>() {});
    }

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        PriorityQueue<Integer> src = PriorityQueue.of(1, 5, 8);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(1, 5, 8), json);
        PriorityQueue<Integer> dst = mapper().readValue(json, new TypeReference<PriorityQueue<Integer>>() {});
        Assert.assertEquals(src, dst);
    }

}
