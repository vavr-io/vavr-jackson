package io.vavr.jackson.generated;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple1;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import org.junit.Assert;
import org.junit.Test;

/**
 * generated
 */
public class BindingClassTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(MAPPER_MODULE);

    @Test
    public void testTuple1Class() throws Exception {
        Tuple1Class src = new Tuple1Class(new Tuple1<>(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        Tuple1Class restored = MAPPER.readValue(json, Tuple1Class.class);
        Assert.assertEquals(restored.value._1.getClass(), ImplementedClass.class);
    }

    @Test
    public void testListClass() throws Exception {
        ListClass src = new ListClass(List.of(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        ListClass restored = MAPPER.readValue(json, ListClass.class);
        Assert.assertEquals(restored.value.head().getClass(), ImplementedClass.class);
    }

    @Test
    public void testOptionClass() throws Exception {
        OptionClass src = new OptionClass(Option.of(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        OptionClass restored = MAPPER.readValue(json, OptionClass.class);
        Assert.assertEquals(restored.value.get().getClass(), ImplementedClass.class);
    }

    @Test
    public void testHashSetClass() throws Exception {
        HashSetClass src = new HashSetClass(HashSet.of(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        HashSetClass restored = MAPPER.readValue(json, HashSetClass.class);
        Assert.assertEquals(restored.value.head().getClass(), ImplementedClass.class);
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.CLASS,
            include = JsonTypeInfo.As.PROPERTY,
            property = "@class"
    )
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    public interface MyInterface<T> {
        boolean myMethod(T value);
    }

    public static class ImplementedClass implements MyInterface<Integer> {
        @Override
        public boolean myMethod(Integer value) {
            return false;
        }
    }

    public static class Tuple1Class {
        @JsonProperty("value")
        final Tuple1<MyInterface<Integer>> value;

        Tuple1Class(@JsonProperty("value") Tuple1<MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class ListClass {
        @JsonProperty("value")
        final List<MyInterface<Integer>> value;

        ListClass(@JsonProperty("value") List<MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class OptionClass {
        @JsonProperty("value")
        final Option<MyInterface<Integer>> value;

        OptionClass(@JsonProperty("value") Option<MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class HashSetClass {
        @JsonProperty("value")
        final HashSet<MyInterface<Integer>> value;

        HashSetClass(@JsonProperty("value") HashSet<MyInterface<Integer>> value) {
            this.value = value;
        }
    }
}
