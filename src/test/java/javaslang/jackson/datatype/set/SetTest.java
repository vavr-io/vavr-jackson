package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.collection.Set;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public abstract class SetTest extends BaseTest {

    abstract Class<?> clz();

    abstract TypeReference<?> typeReference();

    abstract Set<Integer> of(Integer... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Set<Integer> src = of(1, 2, 5);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(1, 2, 5), json);
        Set<Integer> dst = mapper().readValue(json, typeReference());
        Assert.assertEquals(src, dst);
    }
}
