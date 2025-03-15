package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionJsonMergeTest extends BaseTest {
    static class TestJsonMergeWithString {
        @JsonMerge(OptBoolean.TRUE)
        public Option<String> value = Option.of("default");
    }

    static class TestJsonMergeWithPojo {
        @JsonMerge(OptBoolean.TRUE)
        public final Option<POJO> value;

        protected TestJsonMergeWithPojo() {
            value = Option.of(new POJO(7, 2));
        }

        public TestJsonMergeWithPojo(int x, int y) {
            value = Option.of(new POJO(x, y));
        }
    }

    static class POJO {
        public int x, y;

        protected POJO() {
        }

        public POJO(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void shouldMergeString() throws Exception {
        TestJsonMergeWithString result = mapper().readValue(asJson("{'value':'override'}"), TestJsonMergeWithString.class);

        assertThat(result.value.get()).isEqualTo("override");
    }

    @Test
    public void shouldMergePojo() throws Exception {
        TestJsonMergeWithPojo result = mapper().readValue(asJson("{'value':{'y':-6}}"), TestJsonMergeWithPojo.class);
        assertThat(result.value.get().x).isEqualTo(7);
        assertThat(result.value.get().y).isEqualTo(-6);
    }

    @Test
    public void shouldMergeWhileRetainingValues() throws Exception {
        TestJsonMergeWithPojo result = mapper().readerForUpdating(new TestJsonMergeWithPojo(10, 20))
            .readValue(asJson("{'value':{'x':11}}"));

        assertThat(result.value.get().x).isEqualTo(11);
        assertThat(result.value.get().y).isEqualTo(20);
    }
}
