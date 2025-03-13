package io.vavr.jackson.datatype.tuples;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.vavr.Tuple;
import io.vavr.Tuple0;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple8;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TupleXTest extends BaseTest {

    @Test
    void test0() throws IOException {
        Tuple0 tuple0 = Tuple0.instance();
        String json = mapper().writer().writeValueAsString(tuple0);
        Assertions.assertEquals(mapper().readValue(json, Tuple0.class), tuple0);
    }

    @Test
    void test9() {
        assertThrows(JsonMappingException.class, () -> {
            String wrongJson = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
            mapper().readValue(wrongJson, Tuple8.class);
        });
    }

    @Test
    void test10() {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[1, 2, 3]";
            mapper().readValue(json, Tuple2.class);
        });
    }

    @Test
    void test11() throws IOException {
        assertThrows(JsonMappingException.class, () -> {
            String json = "[1, 2]";
            mapper().readValue(json, Tuple3.class);
        });
    }

    @Test
    void testJsonTypeInfo1() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        Assertions.assertEquals("{\"f\":[{\"card\":{\"type\":\"hello\"}},{\"card\":{\"type\":\"hello\"}}]}", javaUtilValue);
        A restored = mapper().readValue(javaUtilValue, A.class);
        Assertions.assertEquals("hello", restored.f._1.type);
        Assertions.assertEquals("hello", restored.f._2.type);
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
}
