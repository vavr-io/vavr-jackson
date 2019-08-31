package io.vavr.jackson.datatype;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.vavr.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FunctionTest extends BaseTest {

    @Test
    void testError() {
        assertThrows(IllegalStateException.class, () -> {
            Function1<String, String> src = i1 -> i1 + 42;
            String json = mapper().writer().writeValueAsString(src);
            String broken = "\"00000000" + json.substring(1);
            mapper().readValue(broken, new TypeReference<Function1<String, String>>() {});
        });
    }

    @Test
    void testF0() throws IOException {
        Function0<String> src = () -> "42";
        String json = mapper().writer().writeValueAsString(src);
        Function0<String> res = mapper().readValue(json, new TypeReference<Function0<String>>() {});
        Assertions.assertEquals(res.apply(), "42");
    }

    @Test
    void testF1() throws IOException {
        Function1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function1<String, String> res = mapper().readValue(json, new TypeReference<Function1<String, String>>() {});
        Assertions.assertEquals(res.apply("1/"), "1/42");
    }

    @Test
    void testF2() throws IOException {
        Function2<String, String, String> src = (i1, i2) -> i1 + i2 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function2<String, String, String> res = mapper().readValue(json, new TypeReference<Function2<String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/"), "1/2/42");
    }

    @Test
    void testF3() throws IOException {
        Function3<String, String, String, String> src = (i1, i2, i3) -> i1 + i2 + i3 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function3<String, String, String, String> res = mapper().readValue(json, new TypeReference<Function3<String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/"), "1/2/3/42");
    }

    @Test
    void testF4() throws IOException {
        Function4<String, String, String, String, String> src = (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function4<String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function4<String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/"), "1/2/3/4/42");
    }

    @Test
    void testF5() throws IOException {
        Function5<String, String, String, String, String, String> src = (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function5<String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function5<String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/"), "1/2/3/4/5/42");
    }

    @Test
    void testF6() throws IOException {
        Function6<String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function6<String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function6<String, String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/", "6/"), "1/2/3/4/5/6/42");
    }

    @Test
    void testF7() throws IOException {
        Function7<String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function7<String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function7<String, String, String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/"), "1/2/3/4/5/6/7/42");
    }

    @Test
    void testF8() throws IOException {
        Function8<String, String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7, i8) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function8<String, String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function8<String, String, String, String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/"), "1/2/3/4/5/6/7/8/42");
    }

    @Test
    void testC0() throws Throwable {
        CheckedFunction0<String> src = () -> "42";
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction0<String> res = mapper().readValue(json, new TypeReference<CheckedFunction0<String>>() {});
        Assertions.assertEquals(res.apply(), "42");
    }

    @Test
    void testC1() throws Throwable {
        CheckedFunction1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction1<String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction1<String, String>>() {});
        Assertions.assertEquals(res.apply("1/"), "1/42");
    }

    @Test
    void testC2() throws Throwable {
        CheckedFunction2<String, String, String> src = (i1, i2) -> i1 + i2 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction2<String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction2<String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/"), "1/2/42");
    }

    @Test
    void testC3() throws Throwable {
        CheckedFunction3<String, String, String, String> src = (i1, i2, i3) -> i1 + i2 + i3 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction3<String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction3<String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/"), "1/2/3/42");
    }

    @Test
    void testC4() throws Throwable {
        CheckedFunction4<String, String, String, String, String> src = (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction4<String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction4<String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/"), "1/2/3/4/42");
    }

    @Test
    void testC5() throws Throwable {
        CheckedFunction5<String, String, String, String, String, String> src = (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction5<String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction5<String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/"), "1/2/3/4/5/42");
    }

    @Test
    void testC6() throws Throwable {
        CheckedFunction6<String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction6<String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction6<String, String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/", "6/"), "1/2/3/4/5/6/42");
    }

    @Test
    void testC7() throws Throwable {
        CheckedFunction7<String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction7<String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction7<String, String, String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/"), "1/2/3/4/5/6/7/42");
    }

    @Test
    void testC8() throws Throwable {
        CheckedFunction8<String, String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7, i8) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction8<String, String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction8<String, String, String, String, String, String, String, String, String>>() {});
        Assertions.assertEquals(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/"), "1/2/3/4/5/6/7/8/42");
    }

}
