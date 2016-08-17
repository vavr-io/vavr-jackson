package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javaslang.jackson.datatype.JavaslangModule;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import javaslang.Tuple;
import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.control.Option;
import javaslang.jackson.datatype.BaseTest;

public abstract class SetTest extends BaseTest {

    protected abstract Class<?> clz();

    protected abstract TypeReference<? extends Set<Integer>> typeReference();

    protected abstract TypeReference<? extends Set<Option<String>>> typeReferenceWithOption();

    protected abstract Set<?> of(Object... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Set<?> src = of(1, 2, 5);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(1, 2, 5), json);
        Set<?> dst = mapper().readValue(json, typeReference());
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

    @Test
    public void test4() throws IOException {
        JavaslangModule.Settings settings = new JavaslangModule.Settings();
        settings.deserializeNullAsEmptyCollection(true);
        ObjectMapper mapper = mapper(settings);
        Set<?> restored = mapper.readValue("null", typeReference());
        Assert.assertTrue(restored.isEmpty());
    }

    @Test
    public void test5() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("null", typeReference());
        Assert.assertNull(restored);
    }

    @Test
    public void test6() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("[]", typeReference());
        Assert.assertTrue(restored.isEmpty());
        Assert.assertTrue(clz().isAssignableFrom(restored.getClass()));
    }

    @Test
    public void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(of(Option.some("value")), genJsonList("value")),
                Tuple.of(of(Option.none()), genJsonList((Object) null))
        ));
    }
}
