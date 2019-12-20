package io.vavr.jackson.generated;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * generated
 */
public class BindingClassTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(MAPPER_MODULE);

    @Test
    void testLazyClass() throws Exception {
        LazyClass src = new LazyClass(Lazy.of(ImplementedClass::new));
        String json = MAPPER.writeValueAsString(src);
        LazyClass restored = MAPPER.readValue(json, LazyClass.class);
        Assertions.assertEquals(restored.value.get().getClass(), ImplementedClass.class);
    }

    @Test
    void testOptionClass() throws Exception {
        OptionClass src = new OptionClass(Option.of(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        OptionClass restored = MAPPER.readValue(json, OptionClass.class);
        Assertions.assertEquals(restored.value.get().getClass(), ImplementedClass.class);
    }

    @Test
    void testTuple1Class() throws Exception {
        Tuple1Class src = new Tuple1Class(new Tuple1<>(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        Tuple1Class restored = MAPPER.readValue(json, Tuple1Class.class);
        Assertions.assertEquals(restored.value._1.getClass(), ImplementedClass.class);
    }

    @Test
    void testListClass() throws Exception {
        ListClass src = new ListClass(List.of(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        ListClass restored = MAPPER.readValue(json, ListClass.class);
        Assertions.assertEquals(restored.value.head().getClass(), ImplementedClass.class);
    }

    @Test
    void testHashSetClass() throws Exception {
        HashSetClass src = new HashSetClass(HashSet.of(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        HashSetClass restored = MAPPER.readValue(json, HashSetClass.class);
        Assertions.assertEquals(restored.value.head().getClass(), ImplementedClass.class);
    }

    @Test
    void testEitherClass() throws Exception {
        EitherClass src = new EitherClass(Either.right(new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        EitherClass restored = MAPPER.readValue(json, EitherClass.class);
        Assertions.assertEquals(restored.value.get().getClass(), ImplementedClass.class);
    }

    @Test
    void testHashMapClass() throws Exception {
        HashMapClass src = new HashMapClass(HashMap.of(42, new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        HashMapClass restored = MAPPER.readValue(json, HashMapClass.class);
        Assertions.assertEquals(restored.value.head()._2.getClass(), ImplementedClass.class);
    }

    @Test
    void testTuple2Class() throws Exception {
        Tuple2Class src = new Tuple2Class(new Tuple2<>(42, new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        Tuple2Class restored = MAPPER.readValue(json, Tuple2Class.class);
        Assertions.assertEquals(restored.value._2.getClass(), ImplementedClass.class);
    }

    @Test
    void testHashMultimapClass() throws Exception {
        HashMultimapClass src = new HashMultimapClass(HashMultimap.withSeq().of(42, new ImplementedClass()));
        String json = MAPPER.writeValueAsString(src);
        HashMultimapClass restored = MAPPER.readValue(json, HashMultimapClass.class);
        Assertions.assertEquals(restored.value.head()._2.getClass(), ImplementedClass.class);
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

    public static class LazyClass {
        @JsonProperty("value")
        final Lazy<MyInterface<Integer>> value;

        LazyClass(@JsonProperty("value") Lazy<MyInterface<Integer>> value) {
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

    public static class HashSetClass {
        @JsonProperty("value")
        final HashSet<MyInterface<Integer>> value;

        HashSetClass(@JsonProperty("value") HashSet<MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class EitherClass {
        @JsonProperty("value")
        final Either<Integer, MyInterface<Integer>> value;

        EitherClass(@JsonProperty("value") Either<Integer, MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class HashMapClass {
        @JsonProperty("value")
        final HashMap<Integer, MyInterface<Integer>> value;

        HashMapClass(@JsonProperty("value") HashMap<Integer, MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class Tuple2Class {
        @JsonProperty("value")
        final Tuple2<Integer, MyInterface<Integer>> value;

        Tuple2Class(@JsonProperty("value") Tuple2<Integer, MyInterface<Integer>> value) {
            this.value = value;
        }
    }

    public static class HashMultimapClass {
        @JsonProperty("value")
        final HashMultimap<Integer, MyInterface<Integer>> value;

        HashMultimapClass(@JsonProperty("value") HashMultimap<Integer, MyInterface<Integer>> value) {
            this.value = value;
        }
    }
}
