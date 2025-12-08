package io.vavr.jackson.datatype.tuples;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import tools.jackson.databind.DatabindException;
import io.vavr.Tuple;
import io.vavr.Tuple0;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple8;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class TupleXTest extends BaseTest {

    @Test
    void test0() throws IOException {
        Tuple0 tuple0 = Tuple0.instance();
        String json = mapper().writer().writeValueAsString(tuple0);
        assertThat(tuple0).isEqualTo(mapper().readValue(json, Tuple0.class));
    }

    @Test
    void test9() {
        String wrongJson = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(wrongJson, Tuple8.class));
    }

    @Test
    void test10() {
        String json = "[1, 2, 3]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(json, Tuple2.class));
    }

    @Test
    void test11() throws IOException {
        String json = "[1, 2]";
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper().readValue(json, Tuple3.class));
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
        public Tuple2<TestSerialize, TestSerialize> f = Tuple.of(new TestSerialize(), new TestSerialize());
    }

    @Test
    void jsonTypeInfo1() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        assertThat(javaUtilValue).isEqualTo("{\"f\":[{\"card\":{\"type\":\"hello\"}},{\"card\":{\"type\":\"hello\"}}]}");
        A restored = mapper().readValue(javaUtilValue, A.class);
        assertThat(restored.f._1.type).isEqualTo("hello");
        assertThat(restored.f._2.type).isEqualTo("hello");
    }
}
