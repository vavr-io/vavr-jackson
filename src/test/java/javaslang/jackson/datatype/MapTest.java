package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MapTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        String result = writer.writeValueAsString(HashMap.empty().put(Tuple.of(1, 2), List.of(3, 4)));
        Assert.assertEquals("{\"@class\":\"javaslang.collection.HashMap\",\"@data\":{\"(1, 2)\":{\"@class\":\"javaslang.collection.List\",\"@data\":[3,4]}}}", result);
        HashMap<String, List> map = mapper(false).readValue(result, new TypeReference<HashMap<String, List>>() {});
        Assert.assertEquals(HashMap.empty().put("(1, 2)", List.of(3, 4)), map);
    }
}
