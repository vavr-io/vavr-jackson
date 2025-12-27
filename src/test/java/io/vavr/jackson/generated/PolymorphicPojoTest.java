package io.vavr.jackson.generated;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.Tuple5;
import io.vavr.Tuple6;
import io.vavr.Tuple7;
import io.vavr.Tuple8;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.List;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import io.vavr.collection.Vector;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * generated
 */
class PolymorphicPojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = JsonMapper.builder().addModule(MAPPER_MODULE).build();

    @Test
    void array() throws Exception {
        Array<I> src = Array.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new ArrayPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        ArrayPojo pojo = MAPPER.readValue(json, ArrayPojo.class);
        Array<I> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof A).isTrue();
        assertThat(restored.get(1) instanceof B).isTrue();
    }

    @Test
    void list() throws Exception {
        List<I> src = List.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new ListPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        ListPojo pojo = MAPPER.readValue(json, ListPojo.class);
        List<I> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof A).isTrue();
        assertThat(restored.get(1) instanceof B).isTrue();
    }

    @Test
    void queue() throws Exception {
        Queue<I> src = Queue.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new QueuePojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        QueuePojo pojo = MAPPER.readValue(json, QueuePojo.class);
        Queue<I> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof A).isTrue();
        assertThat(restored.get(1) instanceof B).isTrue();
    }

    @Test
    void stream() throws Exception {
        Stream<I> src = Stream.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new StreamPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        StreamPojo pojo = MAPPER.readValue(json, StreamPojo.class);
        Stream<I> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof A).isTrue();
        assertThat(restored.get(1) instanceof B).isTrue();
    }

    @Test
    void vector() throws Exception {
        Vector<I> src = Vector.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new VectorPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        VectorPojo pojo = MAPPER.readValue(json, VectorPojo.class);
        Vector<I> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof A).isTrue();
        assertThat(restored.get(1) instanceof B).isTrue();
    }

    @Test
    void hashSet() throws Exception {
        HashSet<I> src = HashSet.of(new B());
        String json = MAPPER.writeValueAsString(new HashSetPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"b\"}]}");
        HashSetPojo pojo = MAPPER.readValue(json, HashSetPojo.class);
        HashSet<I> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
    }

    @Test
    void linkedHashSet() throws Exception {
        LinkedHashSet<I> src = LinkedHashSet.of(new B());
        String json = MAPPER.writeValueAsString(new LinkedHashSetPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"b\"}]}");
        LinkedHashSetPojo pojo = MAPPER.readValue(json, LinkedHashSetPojo.class);
        LinkedHashSet<I> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
    }

    @Test
    void treeSet() throws Exception {
        TreeSet<I> src = TreeSet.of(new B());
        String json = MAPPER.writeValueAsString(new TreeSetPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"b\"}]}");
        TreeSetPojo pojo = MAPPER.readValue(json, TreeSetPojo.class);
        TreeSet<I> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
    }

    @Test
    void priorityQueue() throws Exception {
        PriorityQueue<I> src = PriorityQueue.of(new B());
        String json = MAPPER.writeValueAsString(new PriorityQueuePojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"b\"}]}");
        PriorityQueuePojo pojo = MAPPER.readValue(json, PriorityQueuePojo.class);
        PriorityQueue<I> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
    }

    @Test
    void hashMap() throws Exception {
        HashMap<String, I> src = HashMap.of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new HashMapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}}");
        HashMapPojo pojo = MAPPER.readValue(json, HashMapPojo.class);
        HashMap<String, I> restored = pojo.getValue();
        assertThat(restored.get("a").get() instanceof A).isTrue();
        assertThat(restored.get("b").get() instanceof B).isTrue();
    }

    @Test
    void linkedHashMap() throws Exception {
        LinkedHashMap<String, I> src = LinkedHashMap.of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new LinkedHashMapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}}");
        LinkedHashMapPojo pojo = MAPPER.readValue(json, LinkedHashMapPojo.class);
        LinkedHashMap<String, I> restored = pojo.getValue();
        assertThat(restored.get("a").get() instanceof A).isTrue();
        assertThat(restored.get("b").get() instanceof B).isTrue();
    }

    @Test
    void treeMap() throws Exception {
        TreeMap<String, I> src = TreeMap.of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new TreeMapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}}");
        TreeMapPojo pojo = MAPPER.readValue(json, TreeMapPojo.class);
        TreeMap<String, I> restored = pojo.getValue();
        assertThat(restored.get("a").get() instanceof A).isTrue();
        assertThat(restored.get("b").get() instanceof B).isTrue();
    }

    @Test
    void hashMultimap() throws Exception {
        HashMultimap<String, I> src = HashMultimap.withSeq().of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new HashMultimapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":[{\"type\":\"a\"}],\"b\":[{\"type\":\"b\"}]}}");
        HashMultimapPojo pojo = MAPPER.readValue(json, HashMultimapPojo.class);
        HashMultimap<String, I> restored = pojo.getValue();
        assertThat(restored.get("a").get().head() instanceof A).isTrue();
        assertThat(restored.get("b").get().head() instanceof B).isTrue();
    }

    @Test
    void linkedHashMultimap() throws Exception {
        LinkedHashMultimap<String, I> src = LinkedHashMultimap.withSeq().of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new LinkedHashMultimapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":[{\"type\":\"a\"}],\"b\":[{\"type\":\"b\"}]}}");
        LinkedHashMultimapPojo pojo = MAPPER.readValue(json, LinkedHashMultimapPojo.class);
        LinkedHashMultimap<String, I> restored = pojo.getValue();
        assertThat(restored.get("a").get().head() instanceof A).isTrue();
        assertThat(restored.get("b").get().head() instanceof B).isTrue();
    }

    @Test
    void treeMultimap() throws Exception {
        TreeMultimap<String, I> src = TreeMultimap.withSeq().of("a", new A(), "b", new B());
        String json = MAPPER.writeValueAsString(new TreeMultimapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":[{\"type\":\"a\"}],\"b\":[{\"type\":\"b\"}]}}");
        TreeMultimapPojo pojo = MAPPER.readValue(json, TreeMultimapPojo.class);
        TreeMultimap<String, I> restored = pojo.getValue();
        assertThat(restored.get("a").get().head() instanceof A).isTrue();
        assertThat(restored.get("b").get().head() instanceof B).isTrue();
    }

    @Test
    void tuple1() throws Exception {
        Tuple1<I> src = Tuple.of(new A());
        String json = MAPPER.writeValueAsString(new Tuple1Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"}]}");
        Tuple1Pojo pojo = MAPPER.readValue(json, Tuple1Pojo.class);
        Tuple1<I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
    }

    @Test
    void tuple2() throws Exception {
        Tuple2<I, I> src = Tuple.of(new A(), new B());
        String json = MAPPER.writeValueAsString(new Tuple2Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"}]}");
        Tuple2Pojo pojo = MAPPER.readValue(json, Tuple2Pojo.class);
        Tuple2<I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
    }

    @Test
    void tuple3() throws Exception {
        Tuple3<I, I, I> src = Tuple.of(new A(), new B(), new A());
        String json = MAPPER.writeValueAsString(new Tuple3Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"}]}");
        Tuple3Pojo pojo = MAPPER.readValue(json, Tuple3Pojo.class);
        Tuple3<I, I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._3 instanceof A).isTrue();
    }

    @Test
    void tuple4() throws Exception {
        Tuple4<I, I, I, I> src = Tuple.of(new A(), new B(), new A(), new B());
        String json = MAPPER.writeValueAsString(new Tuple4Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"}]}");
        Tuple4Pojo pojo = MAPPER.readValue(json, Tuple4Pojo.class);
        Tuple4<I, I, I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._3 instanceof A).isTrue();
        assertThat(restored._4 instanceof B).isTrue();
    }

    @Test
    void tuple5() throws Exception {
        Tuple5<I, I, I, I, I> src = Tuple.of(new A(), new B(), new A(), new B(), new A());
        String json = MAPPER.writeValueAsString(new Tuple5Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"}]}");
        Tuple5Pojo pojo = MAPPER.readValue(json, Tuple5Pojo.class);
        Tuple5<I, I, I, I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._3 instanceof A).isTrue();
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._5 instanceof A).isTrue();
    }

    @Test
    void tuple6() throws Exception {
        Tuple6<I, I, I, I, I, I> src = Tuple.of(new A(), new B(), new A(), new B(), new A(), new B());
        String json = MAPPER.writeValueAsString(new Tuple6Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"}]}");
        Tuple6Pojo pojo = MAPPER.readValue(json, Tuple6Pojo.class);
        Tuple6<I, I, I, I, I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._3 instanceof A).isTrue();
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._5 instanceof A).isTrue();
        assertThat(restored._6 instanceof B).isTrue();
    }

    @Test
    void tuple7() throws Exception {
        Tuple7<I, I, I, I, I, I, I> src = Tuple.of(new A(), new B(), new A(), new B(), new A(), new B(), new A());
        String json = MAPPER.writeValueAsString(new Tuple7Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"}]}");
        Tuple7Pojo pojo = MAPPER.readValue(json, Tuple7Pojo.class);
        Tuple7<I, I, I, I, I, I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._3 instanceof A).isTrue();
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._5 instanceof A).isTrue();
        assertThat(restored._6 instanceof B).isTrue();
        assertThat(restored._7 instanceof A).isTrue();
    }

    @Test
    void tuple8() throws Exception {
        Tuple8<I, I, I, I, I, I, I, I> src = Tuple.of(new A(), new B(), new A(), new B(), new A(), new B(), new A(), new B());
        String json = MAPPER.writeValueAsString(new Tuple8Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"},{\"type\":\"a\"},{\"type\":\"b\"}]}");
        Tuple8Pojo pojo = MAPPER.readValue(json, Tuple8Pojo.class);
        Tuple8<I, I, I, I, I, I, I, I> restored = pojo.getValue();
        assertThat(restored._1 instanceof A).isTrue();
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._3 instanceof A).isTrue();
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._5 instanceof A).isTrue();
        assertThat(restored._6 instanceof B).isTrue();
        assertThat(restored._7 instanceof A).isTrue();
        assertThat(restored._8 instanceof B).isTrue();
    }

    @Test
    void lazy() throws Exception {
        Lazy<I> src = Lazy.of(A::new);
        String json = MAPPER.writeValueAsString(new LazyPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"type\":\"a\"}}");
        LazyPojo pojo = MAPPER.readValue(json, LazyPojo.class);
        Lazy<I> restored = pojo.getValue();
        assertThat(restored.get() instanceof A).isTrue();
    }

    @Test
    void option() throws Exception {
        Option<I> src = Option.some(new A());
        String json = MAPPER.writeValueAsString(new OptionPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"type\":\"a\"}}");
        OptionPojo pojo = MAPPER.readValue(json, OptionPojo.class);
        Option<I> restored = pojo.getValue();
        assertThat(restored.get() instanceof A).isTrue();
    }

    @Test
    void eitherLeft() throws Exception {
        Either<I, I> src = Either.left(new A());
        String json = MAPPER.writeValueAsString(new EitherPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"left\",{\"type\":\"a\"}]}");
        EitherPojo pojo = MAPPER.readValue(json, EitherPojo.class);
        Either<I, I> restored = pojo.getValue();
        assertThat(restored.isLeft()).isTrue();
        assertThat(restored.getLeft() instanceof A).isTrue();
    }

    @Test
    void eitherRight() throws Exception {
        Either<I, I> src = Either.right(new A());
        String json = MAPPER.writeValueAsString(new EitherPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"right\",{\"type\":\"a\"}]}");
        EitherPojo pojo = MAPPER.readValue(json, EitherPojo.class);
        Either<I, I> restored = pojo.getValue();
        assertThat(restored.isRight()).isTrue();
        assertThat(restored.get() instanceof A).isTrue();
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
    public interface I extends Comparable<I> {
        default int compareTo(I o) {
            return Integer.compare(hashCode(), o.hashCode());
        }
    }

    public static class A implements I {
    }

    public static class B implements I {
    }

    public static class ArrayPojo {
        private Array<I> v;

        public Array<I> getValue() {
            return v;
        }

        public ArrayPojo setValue(Array<I> v) {
            this.v = v;
            return this;
        }
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

    public static class QueuePojo {
        private Queue<I> v;

        public Queue<I> getValue() {
            return v;
        }

        public QueuePojo setValue(Queue<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class StreamPojo {
        private Stream<I> v;

        public Stream<I> getValue() {
            return v;
        }

        public StreamPojo setValue(Stream<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class VectorPojo {
        private Vector<I> v;

        public Vector<I> getValue() {
            return v;
        }

        public VectorPojo setValue(Vector<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashSetPojo {
        private HashSet<I> v;

        public HashSet<I> getValue() {
            return v;
        }

        public HashSetPojo setValue(HashSet<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashSetPojo {
        private LinkedHashSet<I> v;

        public LinkedHashSet<I> getValue() {
            return v;
        }

        public LinkedHashSetPojo setValue(LinkedHashSet<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeSetPojo {
        private TreeSet<I> v;

        public TreeSet<I> getValue() {
            return v;
        }

        public TreeSetPojo setValue(TreeSet<I> v) {
            this.v = v;
            return this;
        }
    }

    public static class PriorityQueuePojo {
        private PriorityQueue<I> v;

        public PriorityQueue<I> getValue() {
            return v;
        }

        public PriorityQueuePojo setValue(PriorityQueue<I> v) {
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

    public static class LinkedHashMultimapPojo {
        private LinkedHashMultimap<String, I> v;

        public LinkedHashMultimap<String, I> getValue() {
            return v;
        }

        public LinkedHashMultimapPojo setValue(LinkedHashMultimap<String, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMultimapPojo {
        private TreeMultimap<String, I> v;

        public TreeMultimap<String, I> getValue() {
            return v;
        }

        public TreeMultimapPojo setValue(TreeMultimap<String, I> v) {
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

    public static class Tuple3Pojo {
        private Tuple3<I, I, I> v;

        public Tuple3<I, I, I> getValue() {
            return v;
        }

        public Tuple3Pojo setValue(Tuple3<I, I, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple4Pojo {
        private Tuple4<I, I, I, I> v;

        public Tuple4<I, I, I, I> getValue() {
            return v;
        }

        public Tuple4Pojo setValue(Tuple4<I, I, I, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple5Pojo {
        private Tuple5<I, I, I, I, I> v;

        public Tuple5<I, I, I, I, I> getValue() {
            return v;
        }

        public Tuple5Pojo setValue(Tuple5<I, I, I, I, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple6Pojo {
        private Tuple6<I, I, I, I, I, I> v;

        public Tuple6<I, I, I, I, I, I> getValue() {
            return v;
        }

        public Tuple6Pojo setValue(Tuple6<I, I, I, I, I, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple7Pojo {
        private Tuple7<I, I, I, I, I, I, I> v;

        public Tuple7<I, I, I, I, I, I, I> getValue() {
            return v;
        }

        public Tuple7Pojo setValue(Tuple7<I, I, I, I, I, I, I> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple8Pojo {
        private Tuple8<I, I, I, I, I, I, I, I> v;

        public Tuple8<I, I, I, I, I, I, I, I> getValue() {
            return v;
        }

        public Tuple8Pojo setValue(Tuple8<I, I, I, I, I, I, I, I> v) {
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
