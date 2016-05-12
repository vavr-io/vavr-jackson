package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.PriorityQueue;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PriorityQueueTest extends BaseTest {

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
