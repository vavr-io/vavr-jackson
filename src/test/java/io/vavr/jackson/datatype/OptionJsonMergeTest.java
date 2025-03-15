package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionJsonMergeTest extends BaseTest {
    static class MergedStringReference {
        @JsonMerge(OptBoolean.TRUE)
        public Option<String> value = Option.of("default");
    }

    static class MergedPOJOReference {
        @JsonMerge(OptBoolean.TRUE)
        public final Option<POJO> value;

        protected MergedPOJOReference() {
            value = Option.of(new POJO(7, 2));
        }

        public MergedPOJOReference(int x, int y) {
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
        MergedStringReference result = mapper().readValue(asJson("{'value':'override'}"), MergedStringReference.class);
        assertEquals("override", result.value.get());
    }

    @Test
    public void shouldMergePojo() throws Exception {
        MergedPOJOReference result = mapper().readValue(asJson("{'value':{'y':-6}}"), MergedPOJOReference.class);
        assertEquals(7, result.value.get().x);
        assertEquals(-6, result.value.get().y);
    }

    @Test
    public void shouldMergeWhileRetainingValues() throws Exception {
        MergedPOJOReference result = mapper().readerForUpdating(new MergedPOJOReference(10, 20)).readValue(asJson("{'value':{'x':11}}"));
        assertEquals(11, result.value.get().x);
        assertEquals(20, result.value.get().y);
    }
}
