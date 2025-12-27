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
class ExtFieldsPojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = JsonMapper.builder().addModule(MAPPER_MODULE).build();

    @Test
    void array() {
        Array<A> src = Array.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new ArrayPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        ArrayPojo pojo = MAPPER.readValue(json, ArrayPojo.class);
        Array<A> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof B).isTrue();
        assertThat(restored.get(0).a).isEqualTo("a");
        assertThat(((B) restored.get(0)).b).isEqualTo("b");
    }

    @Test
    void list() {
        List<A> src = List.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new ListPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        ListPojo pojo = MAPPER.readValue(json, ListPojo.class);
        List<A> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof B).isTrue();
        assertThat(restored.get(0).a).isEqualTo("a");
        assertThat(((B) restored.get(0)).b).isEqualTo("b");
    }

    @Test
    void queue() {
        Queue<A> src = Queue.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new QueuePojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        QueuePojo pojo = MAPPER.readValue(json, QueuePojo.class);
        Queue<A> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof B).isTrue();
        assertThat(restored.get(0).a).isEqualTo("a");
        assertThat(((B) restored.get(0)).b).isEqualTo("b");
    }

    @Test
    void stream() {
        Stream<A> src = Stream.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new StreamPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        StreamPojo pojo = MAPPER.readValue(json, StreamPojo.class);
        Stream<A> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof B).isTrue();
        assertThat(restored.get(0).a).isEqualTo("a");
        assertThat(((B) restored.get(0)).b).isEqualTo("b");
    }

    @Test
    void vector() {
        Vector<A> src = Vector.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new VectorPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        VectorPojo pojo = MAPPER.readValue(json, VectorPojo.class);
        Vector<A> restored = pojo.getValue();
        assertThat(restored.get(0) instanceof B).isTrue();
        assertThat(restored.get(0).a).isEqualTo("a");
        assertThat(((B) restored.get(0)).b).isEqualTo("b");
    }

    @Test
    void hashSet() {
        HashSet<A> src = HashSet.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new HashSetPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        HashSetPojo pojo = MAPPER.readValue(json, HashSetPojo.class);
        HashSet<A> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
        assertThat(restored.head().a).isEqualTo("a");
        assertThat(((B) restored.head()).b).isEqualTo("b");
    }

    @Test
    void linkedHashSet() {
        LinkedHashSet<A> src = LinkedHashSet.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new LinkedHashSetPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        LinkedHashSetPojo pojo = MAPPER.readValue(json, LinkedHashSetPojo.class);
        LinkedHashSet<A> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
        assertThat(restored.head().a).isEqualTo("a");
        assertThat(((B) restored.head()).b).isEqualTo("b");
    }

    @Test
    void treeSet() {
        TreeSet<A> src = TreeSet.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new TreeSetPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        TreeSetPojo pojo = MAPPER.readValue(json, TreeSetPojo.class);
        TreeSet<A> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
        assertThat(restored.head().a).isEqualTo("a");
        assertThat(((B) restored.head()).b).isEqualTo("b");
    }

    @Test
    void priorityQueue() {
        PriorityQueue<A> src = PriorityQueue.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new PriorityQueuePojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        PriorityQueuePojo pojo = MAPPER.readValue(json, PriorityQueuePojo.class);
        PriorityQueue<A> restored = pojo.getValue();
        assertThat(restored.filter(e -> e instanceof B).length()).isEqualTo(1);
        assertThat(restored.head().a).isEqualTo("a");
        assertThat(((B) restored.head()).b).isEqualTo("b");
    }

    @Test
    void hashMap() {
        HashMap<String, A> src = HashMap.of("a", new B("a", "b"));
        String json = MAPPER.writeValueAsString(new HashMapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}}}");
        HashMapPojo pojo = MAPPER.readValue(json, HashMapPojo.class);
        HashMap<String, A> restored = pojo.getValue();
        assertThat(restored.get("a").get() instanceof B).isTrue();
        assertThat(restored.get("a").get().a).isEqualTo("a");
        assertThat(((B) restored.get("a").get()).b).isEqualTo("b");
    }

    @Test
    void linkedHashMap() {
        LinkedHashMap<String, A> src = LinkedHashMap.of("a", new B("a", "b"));
        String json = MAPPER.writeValueAsString(new LinkedHashMapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}}}");
        LinkedHashMapPojo pojo = MAPPER.readValue(json, LinkedHashMapPojo.class);
        LinkedHashMap<String, A> restored = pojo.getValue();
        assertThat(restored.get("a").get() instanceof B).isTrue();
        assertThat(restored.get("a").get().a).isEqualTo("a");
        assertThat(((B) restored.get("a").get()).b).isEqualTo("b");
    }

    @Test
    void treeMap() {
        TreeMap<String, A> src = TreeMap.of("a", new B("a", "b"));
        String json = MAPPER.writeValueAsString(new TreeMapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}}}");
        TreeMapPojo pojo = MAPPER.readValue(json, TreeMapPojo.class);
        TreeMap<String, A> restored = pojo.getValue();
        assertThat(restored.get("a").get() instanceof B).isTrue();
        assertThat(restored.get("a").get().a).isEqualTo("a");
        assertThat(((B) restored.get("a").get()).b).isEqualTo("b");
    }

    @Test
    void hashMultimap() {
        HashMultimap<String, A> src = HashMultimap.withSeq().of("a", new B("a", "b"));
        String json = MAPPER.writeValueAsString(new HashMultimapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}}");
        HashMultimapPojo pojo = MAPPER.readValue(json, HashMultimapPojo.class);
        HashMultimap<String, A> restored = pojo.getValue();
        assertThat(restored.get("a").get().head() instanceof B).isTrue();
        assertThat(restored.get("a").get().head().a).isEqualTo("a");
        assertThat(((B) restored.get("a").get().head()).b).isEqualTo("b");
    }

    @Test
    void linkedHashMultimap() {
        LinkedHashMultimap<String, A> src = LinkedHashMultimap.withSeq().of("a", new B("a", "b"));
        String json = MAPPER.writeValueAsString(new LinkedHashMultimapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}}");
        LinkedHashMultimapPojo pojo = MAPPER.readValue(json, LinkedHashMultimapPojo.class);
        LinkedHashMultimap<String, A> restored = pojo.getValue();
        assertThat(restored.get("a").get().head() instanceof B).isTrue();
        assertThat(restored.get("a").get().head().a).isEqualTo("a");
        assertThat(((B) restored.get("a").get().head()).b).isEqualTo("b");
    }

    @Test
    void treeMultimap() {
        TreeMultimap<String, A> src = TreeMultimap.withSeq().of("a", new B("a", "b"));
        String json = MAPPER.writeValueAsString(new TreeMultimapPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"a\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}}");
        TreeMultimapPojo pojo = MAPPER.readValue(json, TreeMultimapPojo.class);
        TreeMultimap<String, A> restored = pojo.getValue();
        assertThat(restored.get("a").get().head() instanceof B).isTrue();
        assertThat(restored.get("a").get().head().a).isEqualTo("a");
        assertThat(((B) restored.get("a").get().head()).b).isEqualTo("b");
    }

    @Test
    void tuple1() {
        Tuple1<A> src = Tuple.of(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple1Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple1Pojo pojo = MAPPER.readValue(json, Tuple1Pojo.class);
        Tuple1<A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
    }

    @Test
    void tuple2() {
        Tuple2<A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple2Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple2Pojo pojo = MAPPER.readValue(json, Tuple2Pojo.class);
        Tuple2<A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
    }

    @Test
    void tuple3() {
        Tuple3<A, A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple3Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple3Pojo pojo = MAPPER.readValue(json, Tuple3Pojo.class);
        Tuple3<A, A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
        assertThat(restored._3 instanceof B).isTrue();
        assertThat(restored._3.a).isEqualTo("a");
        assertThat(((B) restored._3).b).isEqualTo("b");
    }

    @Test
    void tuple4() {
        Tuple4<A, A, A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple4Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple4Pojo pojo = MAPPER.readValue(json, Tuple4Pojo.class);
        Tuple4<A, A, A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
        assertThat(restored._3 instanceof B).isTrue();
        assertThat(restored._3.a).isEqualTo("a");
        assertThat(((B) restored._3).b).isEqualTo("b");
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._4.a).isEqualTo("a");
        assertThat(((B) restored._4).b).isEqualTo("b");
    }

    @Test
    void tuple5() {
        Tuple5<A, A, A, A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple5Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple5Pojo pojo = MAPPER.readValue(json, Tuple5Pojo.class);
        Tuple5<A, A, A, A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
        assertThat(restored._3 instanceof B).isTrue();
        assertThat(restored._3.a).isEqualTo("a");
        assertThat(((B) restored._3).b).isEqualTo("b");
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._4.a).isEqualTo("a");
        assertThat(((B) restored._4).b).isEqualTo("b");
        assertThat(restored._5 instanceof B).isTrue();
        assertThat(restored._5.a).isEqualTo("a");
        assertThat(((B) restored._5).b).isEqualTo("b");
    }

    @Test
    void tuple6() {
        Tuple6<A, A, A, A, A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple6Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple6Pojo pojo = MAPPER.readValue(json, Tuple6Pojo.class);
        Tuple6<A, A, A, A, A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
        assertThat(restored._3 instanceof B).isTrue();
        assertThat(restored._3.a).isEqualTo("a");
        assertThat(((B) restored._3).b).isEqualTo("b");
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._4.a).isEqualTo("a");
        assertThat(((B) restored._4).b).isEqualTo("b");
        assertThat(restored._5 instanceof B).isTrue();
        assertThat(restored._5.a).isEqualTo("a");
        assertThat(((B) restored._5).b).isEqualTo("b");
        assertThat(restored._6 instanceof B).isTrue();
        assertThat(restored._6.a).isEqualTo("a");
        assertThat(((B) restored._6).b).isEqualTo("b");
    }

    @Test
    void tuple7() {
        Tuple7<A, A, A, A, A, A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple7Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple7Pojo pojo = MAPPER.readValue(json, Tuple7Pojo.class);
        Tuple7<A, A, A, A, A, A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
        assertThat(restored._3 instanceof B).isTrue();
        assertThat(restored._3.a).isEqualTo("a");
        assertThat(((B) restored._3).b).isEqualTo("b");
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._4.a).isEqualTo("a");
        assertThat(((B) restored._4).b).isEqualTo("b");
        assertThat(restored._5 instanceof B).isTrue();
        assertThat(restored._5.a).isEqualTo("a");
        assertThat(((B) restored._5).b).isEqualTo("b");
        assertThat(restored._6 instanceof B).isTrue();
        assertThat(restored._6.a).isEqualTo("a");
        assertThat(((B) restored._6).b).isEqualTo("b");
        assertThat(restored._7 instanceof B).isTrue();
        assertThat(restored._7.a).isEqualTo("a");
        assertThat(((B) restored._7).b).isEqualTo("b");
    }

    @Test
    void tuple8() {
        Tuple8<A, A, A, A, A, A, A, A> src = Tuple.of(new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"), new B("a", "b"));
        String json = MAPPER.writeValueAsString(new Tuple8Pojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}},{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        Tuple8Pojo pojo = MAPPER.readValue(json, Tuple8Pojo.class);
        Tuple8<A, A, A, A, A, A, A, A> restored = pojo.getValue();
        assertThat(restored._1 instanceof B).isTrue();
        assertThat(restored._1.a).isEqualTo("a");
        assertThat(((B) restored._1).b).isEqualTo("b");
        assertThat(restored._2 instanceof B).isTrue();
        assertThat(restored._2.a).isEqualTo("a");
        assertThat(((B) restored._2).b).isEqualTo("b");
        assertThat(restored._3 instanceof B).isTrue();
        assertThat(restored._3.a).isEqualTo("a");
        assertThat(((B) restored._3).b).isEqualTo("b");
        assertThat(restored._4 instanceof B).isTrue();
        assertThat(restored._4.a).isEqualTo("a");
        assertThat(((B) restored._4).b).isEqualTo("b");
        assertThat(restored._5 instanceof B).isTrue();
        assertThat(restored._5.a).isEqualTo("a");
        assertThat(((B) restored._5).b).isEqualTo("b");
        assertThat(restored._6 instanceof B).isTrue();
        assertThat(restored._6.a).isEqualTo("a");
        assertThat(((B) restored._6).b).isEqualTo("b");
        assertThat(restored._7 instanceof B).isTrue();
        assertThat(restored._7.a).isEqualTo("a");
        assertThat(((B) restored._7).b).isEqualTo("b");
        assertThat(restored._8 instanceof B).isTrue();
        assertThat(restored._8.a).isEqualTo("a");
        assertThat(((B) restored._8).b).isEqualTo("b");
    }

    @Test
    void lazy() {
        Lazy<A> src = Lazy.of(() -> new B("a", "b"));
        String json = MAPPER.writeValueAsString(new LazyPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}}");
        LazyPojo pojo = MAPPER.readValue(json, LazyPojo.class);
        Lazy<A> restored = pojo.getValue();
        assertThat(restored.get() instanceof B).isTrue();
        assertThat(restored.get().a).isEqualTo("a");
        assertThat(((B) restored.get()).b).isEqualTo("b");
    }

    @Test
    void option() {
        Option<A> src = Option.some(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new OptionPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}}");
        OptionPojo pojo = MAPPER.readValue(json, OptionPojo.class);
        Option<A> restored = pojo.getValue();
        assertThat(restored.get() instanceof B).isTrue();
        assertThat(restored.get().a).isEqualTo("a");
        assertThat(((B) restored.get()).b).isEqualTo("b");
    }

    @Test
    void eitherLeft() {
        Either<A, A> src = Either.left(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new EitherPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"left\",{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        EitherPojo pojo = MAPPER.readValue(json, EitherPojo.class);
        Either<A, A> restored = pojo.getValue();
        assertThat(restored.isLeft()).isTrue();
        assertThat(restored.getLeft() instanceof B).isTrue();
        assertThat(restored.getLeft().a).isEqualTo("a");
        assertThat(((B) restored.getLeft()).b).isEqualTo("b");
    }

    @Test
    void eitherRight() {
        Either<A, A> src = Either.right(new B("a", "b"));
        String json = MAPPER.writeValueAsString(new EitherPojo().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"right\",{\"ExtFieldsPojoTest$B\":{\"a\":\"a\",\"b\":\"b\"}}]}");
        EitherPojo pojo = MAPPER.readValue(json, EitherPojo.class);
        Either<A, A> restored = pojo.getValue();
        assertThat(restored.isRight()).isTrue();
        assertThat(restored.get() instanceof B).isTrue();
        assertThat(restored.get().a).isEqualTo("a");
        assertThat(((B) restored.get()).b).isEqualTo("b");
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
    )
    @JsonSubTypes(@JsonSubTypes.Type(B.class))
    public abstract static class A implements Comparable<A> {
        public String a;

        public int compareTo(A o) {
            return Integer.compare(hashCode(), o.hashCode());
        }
    }

    public static class B extends A {
        public String b;

        public B() {
        }

        public B(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class ArrayPojo {
        private Array<A> v;

        public Array<A> getValue() {
            return v;
        }

        public ArrayPojo setValue(Array<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class ListPojo {
        private List<A> v;

        public List<A> getValue() {
            return v;
        }

        public ListPojo setValue(List<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class QueuePojo {
        private Queue<A> v;

        public Queue<A> getValue() {
            return v;
        }

        public QueuePojo setValue(Queue<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class StreamPojo {
        private Stream<A> v;

        public Stream<A> getValue() {
            return v;
        }

        public StreamPojo setValue(Stream<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class VectorPojo {
        private Vector<A> v;

        public Vector<A> getValue() {
            return v;
        }

        public VectorPojo setValue(Vector<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashSetPojo {
        private HashSet<A> v;

        public HashSet<A> getValue() {
            return v;
        }

        public HashSetPojo setValue(HashSet<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashSetPojo {
        private LinkedHashSet<A> v;

        public LinkedHashSet<A> getValue() {
            return v;
        }

        public LinkedHashSetPojo setValue(LinkedHashSet<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeSetPojo {
        private TreeSet<A> v;

        public TreeSet<A> getValue() {
            return v;
        }

        public TreeSetPojo setValue(TreeSet<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class PriorityQueuePojo {
        private PriorityQueue<A> v;

        public PriorityQueue<A> getValue() {
            return v;
        }

        public PriorityQueuePojo setValue(PriorityQueue<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMapPojo {
        private HashMap<String, A> v;

        public HashMap<String, A> getValue() {
            return v;
        }

        public HashMapPojo setValue(HashMap<String, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMapPojo {
        private LinkedHashMap<String, A> v;

        public LinkedHashMap<String, A> getValue() {
            return v;
        }

        public LinkedHashMapPojo setValue(LinkedHashMap<String, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMapPojo {
        private TreeMap<String, A> v;

        public TreeMap<String, A> getValue() {
            return v;
        }

        public TreeMapPojo setValue(TreeMap<String, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMultimapPojo {
        private HashMultimap<String, A> v;

        public HashMultimap<String, A> getValue() {
            return v;
        }

        public HashMultimapPojo setValue(HashMultimap<String, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMultimapPojo {
        private LinkedHashMultimap<String, A> v;

        public LinkedHashMultimap<String, A> getValue() {
            return v;
        }

        public LinkedHashMultimapPojo setValue(LinkedHashMultimap<String, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMultimapPojo {
        private TreeMultimap<String, A> v;

        public TreeMultimap<String, A> getValue() {
            return v;
        }

        public TreeMultimapPojo setValue(TreeMultimap<String, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple1Pojo {
        private Tuple1<A> v;

        public Tuple1<A> getValue() {
            return v;
        }

        public Tuple1Pojo setValue(Tuple1<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple2Pojo {
        private Tuple2<A, A> v;

        public Tuple2<A, A> getValue() {
            return v;
        }

        public Tuple2Pojo setValue(Tuple2<A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple3Pojo {
        private Tuple3<A, A, A> v;

        public Tuple3<A, A, A> getValue() {
            return v;
        }

        public Tuple3Pojo setValue(Tuple3<A, A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple4Pojo {
        private Tuple4<A, A, A, A> v;

        public Tuple4<A, A, A, A> getValue() {
            return v;
        }

        public Tuple4Pojo setValue(Tuple4<A, A, A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple5Pojo {
        private Tuple5<A, A, A, A, A> v;

        public Tuple5<A, A, A, A, A> getValue() {
            return v;
        }

        public Tuple5Pojo setValue(Tuple5<A, A, A, A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple6Pojo {
        private Tuple6<A, A, A, A, A, A> v;

        public Tuple6<A, A, A, A, A, A> getValue() {
            return v;
        }

        public Tuple6Pojo setValue(Tuple6<A, A, A, A, A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple7Pojo {
        private Tuple7<A, A, A, A, A, A, A> v;

        public Tuple7<A, A, A, A, A, A, A> getValue() {
            return v;
        }

        public Tuple7Pojo setValue(Tuple7<A, A, A, A, A, A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple8Pojo {
        private Tuple8<A, A, A, A, A, A, A, A> v;

        public Tuple8<A, A, A, A, A, A, A, A> getValue() {
            return v;
        }

        public Tuple8Pojo setValue(Tuple8<A, A, A, A, A, A, A, A> v) {
            this.v = v;
            return this;
        }
    }

    public static class LazyPojo {
        private Lazy<A> v;

        public Lazy<A> getValue() {
            return v;
        }

        public LazyPojo setValue(Lazy<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class OptionPojo {
        private Option<A> v;

        public Option<A> getValue() {
            return v;
        }

        public OptionPojo setValue(Option<A> v) {
            this.v = v;
            return this;
        }
    }

    public static class EitherPojo {
        private Either<A, A> v;

        public Either<A, A> getValue() {
            return v;
        }

        public EitherPojo setValue(Either<A, A> v) {
            this.v = v;
            return this;
        }
    }
}
