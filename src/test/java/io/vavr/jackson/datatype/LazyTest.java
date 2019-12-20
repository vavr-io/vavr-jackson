package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LazyTest extends BaseTest {
    @Test
    void test1() throws IOException {
        Lazy<?> src = Lazy.of(() -> 1);
        String json = mapper().writer().writeValueAsString(src);
        Assertions.assertEquals("1", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test2() throws IOException {
        Lazy<?> src = Lazy.of(() -> null);
        String json = mapper().writer().writeValueAsString(src);
        Assertions.assertEquals("null", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assertions.assertEquals(src, restored);
    }

}
