package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TupleTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        String result = writer.writeValueAsString(Tuple.of(1, 2.0, 3));
        Assert.assertEquals("{\"@class\":\"javaslang.Tuple3\",\"@data\":{\"_1\":1,\"_2\":2.0,\"_3\":3}}", result);
    }

}
