package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import javaslang.Tuple;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;
import javaslang.jackson.datatype.BaseTest;

public abstract class SeqTest extends BaseTest {

    protected abstract Class<?> clz();

    protected abstract TypeReference<? extends Seq<Option<String>>> typeReferenceWithOption();

    protected String implClzName() {
        return clz().getName();
    }

    protected abstract Seq<?> of(Object... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Seq<?> src = of(1, null, 2.0, "s");
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(1, null, 2.0, "s"), json);
        Seq<?> dst = (Seq<?>) mapper().readValue(json, clz());
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Seq<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(implClzName(), plainJson));
        Seq<?> restored = (Seq<?>) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Seq<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(implClzName(), plainJson));
        Seq<?> restored = (Seq<?>) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test4() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> restored = (Seq<?>) mapper.readValue("null", clz());
        Assert.assertTrue(restored.isEmpty());
    }

    @Test
    public void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(of(Option.some("value")), genJsonList("value")),
                Tuple.of(of(Option.none()), genJsonList((Object) null))
        ));
    }
}
