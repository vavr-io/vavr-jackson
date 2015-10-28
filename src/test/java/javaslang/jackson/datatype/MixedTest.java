package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MixedTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        Object src = HashMap.empty().put("key1", List.of(1, 2)).put("key2", List.of(3, 4));
        String extended = mapper(false).writer().writeValueAsString(src);
        String compact = mapper(true).writer().writeValueAsString(src);
        Assert.assertEquals(mapper(false).readValue(extended, HashMap.class), src);
        Assert.assertEquals(mapper(false).readValue(compact, new TypeReference<HashMap<?, List<?>>>() {}), src);
    }

    @Test
    public void test2() throws IOException {
        Object src = List.of(HashMap.empty().put("key1", 1), HashMap.empty().put("key2", 2));
        String extended = mapper(false).writer().writeValueAsString(src);
        String compact = mapper(true).writer().writeValueAsString(src);
        Assert.assertEquals(mapper(false).readValue(extended, List.class), src);
        Assert.assertEquals(mapper(false).readValue(compact, new TypeReference<List<HashMap<?, ?>>>() {}), src);
    }

}
