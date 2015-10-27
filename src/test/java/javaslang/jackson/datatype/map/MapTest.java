package javaslang.jackson.datatype.map;

import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public abstract class MapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Map<K, V> emptyMap();

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper(false).writer();
        String result = writer.writeValueAsString(emptyMap().put(Tuple.of(1, 2), List.of(3, 4)));
        Assert.assertEquals("{\"@class\":\"" + clz().getCanonicalName() + "\",\"@data\":{\"(1, 2)\":{\"@class\":\"javaslang.collection.List\",\"@data\":[3,4]}}}", result);
        Map<?, ?> map = (Map<?, ?>) mapper(false).readValue(result, clz());
        Assert.assertEquals(emptyMap().put("(1, 2)", List.of(3, 4)), map);
    }
}
