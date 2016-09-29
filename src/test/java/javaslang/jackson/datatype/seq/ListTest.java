package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;

public class ListTest extends SeqTest {

    @Override
    protected Class<?> clz() {
        return List.class;
    }

    @Override
    protected TypeReference<List<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<List<Option<String>>>() {};
    }

    @Override
    protected String implClzName() {
        return List.Cons.class.getName();
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return List.ofAll(Arrays.asList(objects));
    }

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("[1]", Seq.class), List.of(1));
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type")
    @JsonTypeName("card")
    private static class TestSerialize {
        public String type = "hello";
    }

    private static class A {
        public List<TestSerialize> f = List.of(new TestSerialize());
    }
    private static class B {
        public java.util.List<TestSerialize> f = List.of(new TestSerialize()).toJavaList();
    }

    @Test
    public void testJsonTypeInfo() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(
            new A());
        Assert.assertEquals(
            mapper().writeValueAsString(new B()), javaUtilValue);
    }

}
