package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        Assert.assertEquals(src, restored);
    }
}
