package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.databind.JsonMappingException;
import javaslang.Tuple;
import javaslang.Tuple0;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TupleXTest extends BaseTest {

    @Test
    public void test0() throws IOException {
        Tuple0 tuple0 = Tuple0.instance();
        String json = mapper().writer().writeValueAsString(tuple0);
        Assert.assertEquals(mapper().readValue(json, Tuple.class), tuple0);
    }

    @Test(expected = JsonMappingException.class)
    public void test9() throws IOException {
        String wrongJson = "{\"_1\":1,\"_2\":2,\"_3\":2,\"_4\":2,\"_5\":2,\"_6\":2,\"_7\":2,\"_8\":2,\"_9\":2}";
        mapper().readValue(wrongJson, Tuple.class);
    }
}
