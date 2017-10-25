package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class LazyTest extends BaseTest {
    @Test
    public void test1() throws IOException {
        Lazy<?> src = Lazy.of(() -> 1);
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals("1", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test2() throws IOException {
        Lazy<?> src = Lazy.of(() -> null);
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals("null", json);
        Lazy<?> restored = mapper().readValue(json, Lazy.class);
        Assert.assertEquals(src, restored);
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.WRAPPER_OBJECT,
            property = "typeV")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = B.class)
    })
    private static abstract class V {
        public String g = "g";
    }

    private static class B extends V {
        public String h = "h";
    }

    private static class D {
        public Lazy<V> v = Lazy.of(B::new);
    }

    @Test
    public void testJsonTypeInfo() throws IOException {
        ObjectMapper mapper = mapper();
        String javaUtilValue = mapper.writeValueAsString(new D());
        Assert.assertEquals("{\"v\":{\"LazyTest$B\":{\"g\":\"g\",\"h\":\"h\"}}}", javaUtilValue);
        D restored = mapper.readValue(javaUtilValue, D.class);
        Assert.assertEquals("g", restored.v.get().g);
        Assert.assertEquals("h", ((B) restored.v.get()).h);
    }

    private static class Pojo {
        private Lazy<Tuple2<String, String>> x = Lazy.of(() -> Tuple.of("A", "B"));

        public Lazy<Tuple2<String, String>> getX() {
            return x;
        }
        public void setX(Lazy<Tuple2<String, String>> x) {
            this.x = x;
        }
    }

    @Test
    public void testPojo() throws Exception {
        final String json = mapper().writeValueAsString(new Pojo());
        Assert.assertEquals(json, "{\"x\":[\"A\",\"B\"]}");
        Pojo restored = mapper().readValue(json, Pojo.class);
        Assert.assertEquals(restored.getX().get()._1, "A");
        Assert.assertEquals(restored.getX().get()._2, "B");
    }
}
