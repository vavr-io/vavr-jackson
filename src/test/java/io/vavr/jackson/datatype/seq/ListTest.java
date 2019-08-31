package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.type.TypeReference;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

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
    void testDefaultDeserialization() throws IOException {
        Assertions.assertEquals(mapper().readValue("[1]", Seq.class), List.of(1));
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
    void testJsonTypeInfo() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        Assertions.assertEquals(mapper().writeValueAsString(new B()), javaUtilValue);
        A restored = mapper().readValue(javaUtilValue, A.class);
        Assertions.assertEquals("hello", restored.f.head().type);
    }

}
