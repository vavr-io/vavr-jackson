package io.vavr.jackson.datatype;

import tools.jackson.core.type.TypeReference;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.CheckedFunction3;
import io.vavr.CheckedFunction4;
import io.vavr.CheckedFunction5;
import io.vavr.CheckedFunction6;
import io.vavr.CheckedFunction7;
import io.vavr.CheckedFunction8;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.Function5;
import io.vavr.Function6;
import io.vavr.Function7;
import io.vavr.Function8;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class FunctionTest extends BaseTest {

    @Test
    void shouldThrowExceptionForInvalidFunctionSerialization() {
        Function1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        String broken = "\"00000000" + json.substring(1);
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
            mapper().readValue(broken, new TypeReference<Function1<String, String>>() {
            }));
    }

    @Test
    void shouldSerializeAndDeserializeFunction0() throws IOException {
        Function0<String> src = () -> "42";
        String json = mapper().writer().writeValueAsString(src);
        Function0<String> res = mapper().readValue(json, new TypeReference<Function0<String>>() {
        });
        assertThat(res.apply()).isEqualTo("42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction1() throws IOException {
        Function1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function1<String, String> res = mapper().readValue(json, new TypeReference<Function1<String, String>>() {
        });
        assertThat(res.apply("1/")).isEqualTo("1/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction2() throws IOException {
        Function2<String, String, String> src = (i1, i2) -> i1 + i2 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function2<String, String, String> res = mapper().readValue(json, new TypeReference<Function2<String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/")).isEqualTo("1/2/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction3() throws IOException {
        Function3<String, String, String, String> src = (i1, i2, i3) -> i1 + i2 + i3 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function3<String, String, String, String> res = mapper().readValue(json, new TypeReference<Function3<String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/")).isEqualTo("1/2/3/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction4() throws IOException {
        Function4<String, String, String, String, String> src = (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function4<String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function4<String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/")).isEqualTo("1/2/3/4/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction5() throws IOException {
        Function5<String, String, String, String, String, String> src = (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function5<String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function5<String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/")).isEqualTo("1/2/3/4/5/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction6() throws IOException {
        Function6<String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function6<String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function6<String, String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/", "6/")).isEqualTo("1/2/3/4/5/6/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction7() throws IOException {
        Function7<String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function7<String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function7<String, String, String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/")).isEqualTo("1/2/3/4/5/6/7/42");
    }

    @Test
    void shouldSerializeAndDeserializeFunction8() throws IOException {
        Function8<String, String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7, i8) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + 42;
        String json = mapper().writer().writeValueAsString(src);
        Function8<String, String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<Function8<String, String, String, String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/")).isEqualTo("1/2/3/4/5/6/7/8/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction0() throws Throwable {
        CheckedFunction0<String> src = () -> "42";
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction0<String> res = mapper().readValue(json, new TypeReference<CheckedFunction0<String>>() {
        });
        assertThat(res.apply()).isEqualTo("42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction1() throws Throwable {
        CheckedFunction1<String, String> src = i1 -> i1 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction1<String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction1<String, String>>() {
        });
        assertThat(res.apply("1/")).isEqualTo("1/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction2() throws Throwable {
        CheckedFunction2<String, String, String> src = (i1, i2) -> i1 + i2 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction2<String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction2<String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/")).isEqualTo("1/2/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction3() throws Throwable {
        CheckedFunction3<String, String, String, String> src = (i1, i2, i3) -> i1 + i2 + i3 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction3<String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction3<String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/")).isEqualTo("1/2/3/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction4() throws Throwable {
        CheckedFunction4<String, String, String, String, String> src = (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction4<String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction4<String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/")).isEqualTo("1/2/3/4/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction5() throws Throwable {
        CheckedFunction5<String, String, String, String, String, String> src = (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction5<String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction5<String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/")).isEqualTo("1/2/3/4/5/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction6() throws Throwable {
        CheckedFunction6<String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction6<String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction6<String, String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/", "6/")).isEqualTo("1/2/3/4/5/6/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction7() throws Throwable {
        CheckedFunction7<String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction7<String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction7<String, String, String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/")).isEqualTo("1/2/3/4/5/6/7/42");
    }

    @Test
    void shouldSerializeAndDeserializeCheckedFunction8() throws Throwable {
        CheckedFunction8<String, String, String, String, String, String, String, String, String> src = (i1, i2, i3, i4, i5, i6, i7, i8) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + 42;
        String json = mapper().writer().writeValueAsString(src);
        CheckedFunction8<String, String, String, String, String, String, String, String, String> res = mapper().readValue(json, new TypeReference<CheckedFunction8<String, String, String, String, String, String, String, String, String>>() {
        });
        assertThat(res.apply("1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/")).isEqualTo("1/2/3/4/5/6/7/8/42");
    }
}
