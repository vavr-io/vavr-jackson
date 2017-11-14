package io.vavr.jackson.generated;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.List;
import io.vavr.collection.TreeMap;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import java.lang.Exception;
import java.lang.String;
import org.junit.Assert;
import org.junit.Test;

/**
 * generated
 */
public class PolymorphicPojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(MAPPER_MODULE);

    @Test
    public void testList() throws Exception {
        List<I> src = List.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new ListPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        ListPojo pojo = MAPPER.readValue(json, ListPojo.class);
        List<I> restored = pojo.getValue();
        Assert.assertTrue(restored.get(0) instanceof A);
        Assert.assertTrue(restored.get(1) instanceof B);
    }

    @Test
    public void testHashMap() throws Exception {
        HashMap<String, I> src = HashMap.of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new HashMapPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}}");
        HashMapPojo pojo = MAPPER.readValue(json, HashMapPojo.class);
        HashMap<String, I> restored = pojo.getValue();
        Assert.assertTrue(restored.get("a").get() instanceof A);
        Assert.assertTrue(restored.get("b").get() instanceof B);
    }

    @Test
    public void testLinkedHashMap() throws Exception {
        LinkedHashMap<String, I> src = LinkedHashMap.of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new LinkedHashMapPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}}");
        LinkedHashMapPojo pojo = MAPPER.readValue(json, LinkedHashMapPojo.class);
        LinkedHashMap<String, I> restored = pojo.getValue();
        Assert.assertTrue(restored.get("a").get() instanceof A);
        Assert.assertTrue(restored.get("b").get() instanceof B);
    }

    @Test
    public void testTreeMap() throws Exception {
        TreeMap<String, I> src = TreeMap.of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new TreeMapPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}}");
        TreeMapPojo pojo = MAPPER.readValue(json, TreeMapPojo.class);
        TreeMap<String, I> restored = pojo.getValue();
        Assert.assertTrue(restored.get("a").get() instanceof A);
        Assert.assertTrue(restored.get("b").get() instanceof B);
    }

    @Test
    public void testHashMultimap() throws Exception {
        HashMultimap<String, I> src = HashMultimap.withSeq().of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new HashMultimapPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"a\":[{\"type\":\"a\"}],\"b\":[{\"type\":\"b\"}]}}");
        HashMultimapPojo pojo = MAPPER.readValue(json, HashMultimapPojo.class);
        HashMultimap<String, I> restored = pojo.getValue();
        Assert.assertTrue(restored.get("a").get().head() instanceof A);
        Assert.assertTrue(restored.get("b").get().head() instanceof B);
    }

    @Test
    public void testTuple1() throws Exception {
        Tuple1<I> src = Tuple.of(new A());
        String json = MAPPER.writeValueAsString(new Tuple1Pojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":[{\"type\":\"a\"}]}");
        Tuple1Pojo pojo = MAPPER.readValue(json, Tuple1Pojo.class);
        Tuple1<I> restored = pojo.getValue();
        Assert.assertTrue(restored._1 instanceof A);
    }

    @Test
    public void testTuple2() throws Exception {
        Tuple2<I, I> src = Tuple.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new Tuple2Pojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        Tuple2Pojo pojo = MAPPER.readValue(json, Tuple2Pojo.class);
        Tuple2<I, I> restored = pojo.getValue();
        Assert.assertTrue(restored._1 instanceof A);
        Assert.assertTrue(restored._2 instanceof B);
    }

    @Test
    public void testLazy() throws Exception {
        Lazy<I> src = Lazy.of(A::new);
        String json = MAPPER.writeValueAsString(new LazyPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"type\":\"a\"}}");
        LazyPojo pojo = MAPPER.readValue(json, LazyPojo.class);
        Lazy<I> restored = pojo.getValue();
        Assert.assertTrue(restored.get() instanceof A);
    }

    @Test
    public void testOption() throws Exception {
        Option<I> src = Option.some(new A());
        String json = MAPPER.writeValueAsString(new OptionPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"type\":\"a\"}}");
        OptionPojo pojo = MAPPER.readValue(json, OptionPojo.class);
        Option<I> restored = pojo.getValue();
        Assert.assertTrue(restored.get() instanceof A);
    }

    @Test
    public void testEitherLeft() throws Exception {
        Either<I, I> src = Either.left(new A());
        String json = MAPPER.writeValueAsString(new EitherPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"left\",{\"type\":\"a\"}]}");
        EitherPojo pojo = MAPPER.readValue(json, EitherPojo.class);
        Either<I, I> restored = pojo.getValue();
        Assert.assertTrue(restored.isLeft());
        Assert.assertTrue(restored.getLeft() instanceof A);
    }

    @Test
    public void testEitherRight() throws Exception {
        Either<I, I> src = Either.right(new A());
        String json = MAPPER.writeValueAsString(new EitherPojo().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"right\",{\"type\":\"a\"}]}");
        EitherPojo pojo = MAPPER.readValue(json, EitherPojo.class);
        Either<I, I> restored = pojo.getValue();
        Assert.assertTrue(restored.isRight());
        Assert.assertTrue(restored.get() instanceof A);
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = A.class, name = "a"),
            @JsonSubTypes.Type(value = B.class, name = "b")
    })
    public interface I {
    }

    public static class A implements I {
    }

    public static class B implements I {
    }

    public static class ListPojo {
        private List<I> v;

        public List<I> getValue() {
            return v;
        }

        public ListPojo setValue(List<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMapPojo {
        private HashMap<String, I> v;

        public HashMap<String, I> getValue() {
            return v;
        }

        public HashMapPojo setValue(HashMap<String, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMapPojo {
        private LinkedHashMap<String, I> v;

        public LinkedHashMap<String, I> getValue() {
            return v;
        }

        public LinkedHashMapPojo setValue(LinkedHashMap<String, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMapPojo {
        private TreeMap<String, I> v;

        public TreeMap<String, I> getValue() {
            return v;
        }

        public TreeMapPojo setValue(TreeMap<String, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMultimapPojo {
        private HashMultimap<String, I> v;

        public HashMultimap<String, I> getValue() {
            return v;
        }

        public HashMultimapPojo setValue(HashMultimap<String, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple1Pojo {
        private Tuple1<I> v;

        public Tuple1<I> getValue() {
            return v;
        }

        public Tuple1Pojo setValue(Tuple1<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple2Pojo {
        private Tuple2<I, I> v;

        public Tuple2<I, I> getValue() {
            return v;
        }

        public Tuple2Pojo setValue(Tuple2<I, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class LazyPojo {
        private Lazy<I> v;

        public Lazy<I> getValue() {
            return v;
        }

        public LazyPojo setValue(Lazy<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class OptionPojo {
        private Option<I> v;

        public Option<I> getValue() {
            return v;
        }

        public OptionPojo setValue(Option<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class EitherPojo {
        private Either<I, I> v;

        public Either<I, I> getValue() {
            return v;
        }

        public EitherPojo setValue(Either<I, I> v) {
            this.v = v;
            return this;
        }
    }
}
