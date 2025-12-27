package io.vavr.jackson.generated;

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
class SimplePojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = JsonMapper.builder().addModule(MAPPER_MODULE).build();

    @Test
    void tuple1OfString() throws Exception {
        String src0 = "A";
        Tuple1<String> src = Tuple.of(src0);
        String json = MAPPER.writeValueAsString(new Tuple1OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\"]}");
        Tuple1OfString restored = MAPPER.readValue(json, Tuple1OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple1OfTuple() throws Exception {
        String src00 = "B";
        Tuple1<String> src0 = Tuple.of(src00);
        Tuple1<Tuple1<String>> src = Tuple.of(src0);
        String json = MAPPER.writeValueAsString(new Tuple1OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"B\"]]}");
        Tuple1OfTuple restored = MAPPER.readValue(json, Tuple1OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple2OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        Tuple2<String, String> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(new Tuple2OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\"]}");
        Tuple2OfString restored = MAPPER.readValue(json, Tuple2OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple2OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        Tuple2<String, Tuple2<String, String>> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(new Tuple2OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"]]}");
        Tuple2OfTuple restored = MAPPER.readValue(json, Tuple2OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple3OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Tuple3<String, String, String> src = Tuple.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new Tuple3OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        Tuple3OfString restored = MAPPER.readValue(json, Tuple3OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple3OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> src = Tuple.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new Tuple3OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"]]}");
        Tuple3OfTuple restored = MAPPER.readValue(json, Tuple3OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple4OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        Tuple4<String, String, String, String> src = Tuple.of(src0, src1, src2, src3);
        String json = MAPPER.writeValueAsString(new Tuple4OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\",\"D\"]}");
        Tuple4OfString restored = MAPPER.readValue(json, Tuple4OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple4OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        String src30 = "F";
        String src31 = "1";
        Tuple2<String, String> src3 = Tuple.of(src30, src31);
        Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> src = Tuple.of(src0, src1, src2, src3);
        String json = MAPPER.writeValueAsString(new Tuple4OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"]]}");
        Tuple4OfTuple restored = MAPPER.readValue(json, Tuple4OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple5OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        Tuple5<String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4);
        String json = MAPPER.writeValueAsString(new Tuple5OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\"]}");
        Tuple5OfString restored = MAPPER.readValue(json, Tuple5OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple5OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        String src30 = "F";
        String src31 = "1";
        Tuple2<String, String> src3 = Tuple.of(src30, src31);
        String src4 = "A";
        Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> src = Tuple.of(src0, src1, src2, src3, src4);
        String json = MAPPER.writeValueAsString(new Tuple5OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\"]}");
        Tuple5OfTuple restored = MAPPER.readValue(json, Tuple5OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple6OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        Tuple6<String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5);
        String json = MAPPER.writeValueAsString(new Tuple6OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"]}");
        Tuple6OfString restored = MAPPER.readValue(json, Tuple6OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple6OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        String src30 = "F";
        String src31 = "1";
        Tuple2<String, String> src3 = Tuple.of(src30, src31);
        String src4 = "A";
        String src50 = "B";
        String src51 = "C";
        Tuple2<String, String> src5 = Tuple.of(src50, src51);
        Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> src = Tuple.of(src0, src1, src2, src3, src4, src5);
        String json = MAPPER.writeValueAsString(new Tuple6OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"]]}");
        Tuple6OfTuple restored = MAPPER.readValue(json, Tuple6OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple7OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        String src6 = "1";
        Tuple7<String, String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6);
        String json = MAPPER.writeValueAsString(new Tuple7OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"1\"]}");
        Tuple7OfString restored = MAPPER.readValue(json, Tuple7OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple7OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        String src30 = "F";
        String src31 = "1";
        Tuple2<String, String> src3 = Tuple.of(src30, src31);
        String src4 = "A";
        String src50 = "B";
        String src51 = "C";
        Tuple2<String, String> src5 = Tuple.of(src50, src51);
        String src60 = "D";
        String src61 = "E";
        Tuple2<String, String> src6 = Tuple.of(src60, src61);
        Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6);
        String json = MAPPER.writeValueAsString(new Tuple7OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"],[\"D\",\"E\"]]}");
        Tuple7OfTuple restored = MAPPER.readValue(json, Tuple7OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple8OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        String src6 = "1";
        String src7 = "2";
        Tuple8<String, String, String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6, src7);
        String json = MAPPER.writeValueAsString(new Tuple8OfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"1\",\"2\"]}");
        Tuple8OfString restored = MAPPER.readValue(json, Tuple8OfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void tuple8OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        String src30 = "F";
        String src31 = "1";
        Tuple2<String, String> src3 = Tuple.of(src30, src31);
        String src4 = "A";
        String src50 = "B";
        String src51 = "C";
        Tuple2<String, String> src5 = Tuple.of(src50, src51);
        String src60 = "D";
        String src61 = "E";
        Tuple2<String, String> src6 = Tuple.of(src60, src61);
        String src70 = "F";
        String src71 = "1";
        Tuple2<String, String> src7 = Tuple.of(src70, src71);
        Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6, src7);
        String json = MAPPER.writeValueAsString(new Tuple8OfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"]]}");
        Tuple8OfTuple restored = MAPPER.readValue(json, Tuple8OfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void arrayOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Array<String> src = Array.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ArrayOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        ArrayOfString restored = MAPPER.readValue(json, ArrayOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void arrayOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Array<Tuple2<String, String>> src = Array.of(src0);
        String json = MAPPER.writeValueAsString(new ArrayOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        ArrayOfTuple restored = MAPPER.readValue(json, ArrayOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void listOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        List<String> src = List.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ListOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        ListOfString restored = MAPPER.readValue(json, ListOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void listOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        List<Tuple2<String, String>> src = List.of(src0);
        String json = MAPPER.writeValueAsString(new ListOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        ListOfTuple restored = MAPPER.readValue(json, ListOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void queueOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Queue<String> src = Queue.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new QueueOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        QueueOfString restored = MAPPER.readValue(json, QueueOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void queueOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Queue<Tuple2<String, String>> src = Queue.of(src0);
        String json = MAPPER.writeValueAsString(new QueueOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        QueueOfTuple restored = MAPPER.readValue(json, QueueOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void streamOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Stream<String> src = Stream.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new StreamOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        StreamOfString restored = MAPPER.readValue(json, StreamOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void streamOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Stream<Tuple2<String, String>> src = Stream.of(src0);
        String json = MAPPER.writeValueAsString(new StreamOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        StreamOfTuple restored = MAPPER.readValue(json, StreamOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void vectorOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Vector<String> src = Vector.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new VectorOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        VectorOfString restored = MAPPER.readValue(json, VectorOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void vectorOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Vector<Tuple2<String, String>> src = Vector.of(src0);
        String json = MAPPER.writeValueAsString(new VectorOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        VectorOfTuple restored = MAPPER.readValue(json, VectorOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void priorityQueueOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        PriorityQueue<String> src = PriorityQueue.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new PriorityQueueOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        PriorityQueueOfString restored = MAPPER.readValue(json, PriorityQueueOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void hashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        HashSet<String> src = HashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new HashSetOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        HashSetOfString restored = MAPPER.readValue(json, HashSetOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void hashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        HashSet<Tuple2<String, String>> src = HashSet.of(src0);
        String json = MAPPER.writeValueAsString(new HashSetOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        HashSetOfTuple restored = MAPPER.readValue(json, HashSetOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void linkedHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        LinkedHashSet<String> src = LinkedHashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new LinkedHashSetOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        LinkedHashSetOfString restored = MAPPER.readValue(json, LinkedHashSetOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void linkedHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        LinkedHashSet<Tuple2<String, String>> src = LinkedHashSet.of(src0);
        String json = MAPPER.writeValueAsString(new LinkedHashSetOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[[\"A\",\"B\"]]}");
        LinkedHashSetOfTuple restored = MAPPER.readValue(json, LinkedHashSetOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void treeSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        TreeSet<String> src = TreeSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new TreeSetOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\",\"C\"]}");
        TreeSetOfString restored = MAPPER.readValue(json, TreeSetOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void hashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        HashMap<Integer, String> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new HashMapOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"1\":\"A\"}}");
        HashMapOfString restored = MAPPER.readValue(json, HashMapOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void hashMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        HashMap<Integer, Tuple2<String, String>> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new HashMapOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"1\":[\"A\",\"B\"]}}");
        HashMapOfTuple restored = MAPPER.readValue(json, HashMapOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void linkedHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, String> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new LinkedHashMapOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"1\":\"A\"}}");
        LinkedHashMapOfString restored = MAPPER.readValue(json, LinkedHashMapOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void linkedHashMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, Tuple2<String, String>> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new LinkedHashMapOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"1\":[\"A\",\"B\"]}}");
        LinkedHashMapOfTuple restored = MAPPER.readValue(json, LinkedHashMapOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void treeMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, String> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new TreeMapOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"1\":\"A\"}}");
        TreeMapOfString restored = MAPPER.readValue(json, TreeMapOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void treeMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, Tuple2<String, String>> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new TreeMapOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"1\":[\"A\",\"B\"]}}");
        TreeMapOfTuple restored = MAPPER.readValue(json, TreeMapOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void hashMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        HashMultimap<String, String> src = HashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new HashMultimapOfSeqString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"A\":[\"B\",\"C\"]}}");
        HashMultimapOfSeqString restored = MAPPER.readValue(json, HashMultimapOfSeqString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void hashMultimapOfSeqTuple() throws Exception {
        String src00 = "A";
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<String, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src110 = "C";
        String src111 = "D";
        Tuple2<String, String> src11 = Tuple.of(src110, src111);
        Tuple2<String, Tuple2<String, String>> src1 = Tuple.of(src10, src11);
        HashMultimap<String, Tuple2<String, String>> src = HashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new HashMultimapOfSeqTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        HashMultimapOfSeqTuple restored = MAPPER.readValue(json, HashMultimapOfSeqTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void linkedHashMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        LinkedHashMultimap<String, String> src = LinkedHashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new LinkedHashMultimapOfSeqString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"A\":[\"B\",\"C\"]}}");
        LinkedHashMultimapOfSeqString restored = MAPPER.readValue(json, LinkedHashMultimapOfSeqString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void linkedHashMultimapOfSeqTuple() throws Exception {
        String src00 = "A";
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<String, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src110 = "C";
        String src111 = "D";
        Tuple2<String, String> src11 = Tuple.of(src110, src111);
        Tuple2<String, Tuple2<String, String>> src1 = Tuple.of(src10, src11);
        LinkedHashMultimap<String, Tuple2<String, String>> src = LinkedHashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new LinkedHashMultimapOfSeqTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        LinkedHashMultimapOfSeqTuple restored = MAPPER.readValue(json, LinkedHashMultimapOfSeqTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void treeMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        TreeMultimap<String, String> src = TreeMultimap.withSet().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new TreeMultimapOfSeqString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"A\":[\"B\",\"C\"]}}");
        TreeMultimapOfSeqString restored = MAPPER.readValue(json, TreeMultimapOfSeqString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void treeMultimapOfSeqTuple() throws Exception {
        String src00 = "A";
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<String, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src110 = "C";
        String src111 = "D";
        Tuple2<String, String> src11 = Tuple.of(src110, src111);
        Tuple2<String, Tuple2<String, String>> src1 = Tuple.of(src10, src11);
        TreeMultimap<String, Tuple2<String, String>> src = TreeMultimap.withSet().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new TreeMultimapOfSeqTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        TreeMultimapOfSeqTuple restored = MAPPER.readValue(json, TreeMultimapOfSeqTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void optionOfString() throws Exception {
        String src0 = "A";
        Option<String> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(new OptionOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":\"A\"}");
        OptionOfString restored = MAPPER.readValue(json, OptionOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void optionOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Option<Tuple2<String, String>> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(new OptionOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\"]}");
        OptionOfTuple restored = MAPPER.readValue(json, OptionOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void lazyOfString() throws Exception {
        String src0 = "A";
        Lazy<String> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(new LazyOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":\"A\"}");
        LazyOfString restored = MAPPER.readValue(json, LazyOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void lazyOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Lazy<Tuple2<String, String>> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(new LazyOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"A\",\"B\"]}");
        LazyOfTuple restored = MAPPER.readValue(json, LazyOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void leftEitherOfString() throws Exception {
        String srcl = "A";
        Either<String, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(new LeftEitherOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"left\",\"A\"]}");
        LeftEitherOfString restored = MAPPER.readValue(json, LeftEitherOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void leftEitherOfTuple() throws Exception {
        String srcl0 = "A";
        String srcl1 = "B";
        Tuple2<String, String> srcl = Tuple.of(srcl0, srcl1);
        Either<Tuple2<String, String>, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(new LeftEitherOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"left\",[\"A\",\"B\"]]}");
        LeftEitherOfTuple restored = MAPPER.readValue(json, LeftEitherOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void rightEitherOfString() throws Exception {
        String srcr = "A";
        Either<Object, String> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(new RightEitherOfString().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"right\",\"A\"]}");
        RightEitherOfString restored = MAPPER.readValue(json, RightEitherOfString.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    @Test
    void rightEitherOfTuple() throws Exception {
        String srcr0 = "A";
        String srcr1 = "B";
        Tuple2<String, String> srcr = Tuple.of(srcr0, srcr1);
        Either<Object, Tuple2<String, String>> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(new RightEitherOfTuple().setValue(src));
        assertThat(json).isEqualTo("{\"value\":[\"right\",[\"A\",\"B\"]]}");
        RightEitherOfTuple restored = MAPPER.readValue(json, RightEitherOfTuple.class);
        assertThat(restored.getValue()).isEqualTo(src);
    }

    public static class Tuple1OfString {
        private Tuple1<String> v;

        public Tuple1<String> getValue() {
            return v;
        }

        public Tuple1OfString setValue(Tuple1<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple1OfTuple {
        private Tuple1<Tuple1<String>> v;

        public Tuple1<Tuple1<String>> getValue() {
            return v;
        }

        public Tuple1OfTuple setValue(Tuple1<Tuple1<String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple2OfString {
        private Tuple2<String, String> v;

        public Tuple2<String, String> getValue() {
            return v;
        }

        public Tuple2OfString setValue(Tuple2<String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple2OfTuple {
        private Tuple2<String, Tuple2<String, String>> v;

        public Tuple2<String, Tuple2<String, String>> getValue() {
            return v;
        }

        public Tuple2OfTuple setValue(Tuple2<String, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple3OfString {
        private Tuple3<String, String, String> v;

        public Tuple3<String, String, String> getValue() {
            return v;
        }

        public Tuple3OfString setValue(Tuple3<String, String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple3OfTuple {
        private Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> v;

        public Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> getValue() {
            return v;
        }

        public Tuple3OfTuple setValue(Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple4OfString {
        private Tuple4<String, String, String, String> v;

        public Tuple4<String, String, String, String> getValue() {
            return v;
        }

        public Tuple4OfString setValue(Tuple4<String, String, String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple4OfTuple {
        private Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v;

        public Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> getValue() {
            return v;
        }

        public Tuple4OfTuple setValue(Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple5OfString {
        private Tuple5<String, String, String, String, String> v;

        public Tuple5<String, String, String, String, String> getValue() {
            return v;
        }

        public Tuple5OfString setValue(Tuple5<String, String, String, String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple5OfTuple {
        private Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> v;

        public Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> getValue() {
            return v;
        }

        public Tuple5OfTuple setValue(Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple6OfString {
        private Tuple6<String, String, String, String, String, String> v;

        public Tuple6<String, String, String, String, String, String> getValue() {
            return v;
        }

        public Tuple6OfString setValue(Tuple6<String, String, String, String, String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple6OfTuple {
        private Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> v;

        public Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> getValue() {
            return v;
        }

        public Tuple6OfTuple setValue(Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple7OfString {
        private Tuple7<String, String, String, String, String, String, String> v;

        public Tuple7<String, String, String, String, String, String, String> getValue() {
            return v;
        }

        public Tuple7OfString setValue(Tuple7<String, String, String, String, String, String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple7OfTuple {
        private Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> v;

        public Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> getValue() {
            return v;
        }

        public Tuple7OfTuple setValue(Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple8OfString {
        private Tuple8<String, String, String, String, String, String, String, String> v;

        public Tuple8<String, String, String, String, String, String, String, String> getValue() {
            return v;
        }

        public Tuple8OfString setValue(Tuple8<String, String, String, String, String, String, String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class Tuple8OfTuple {
        private Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v;

        public Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> getValue() {
            return v;
        }

        public Tuple8OfTuple setValue(Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class ArrayOfString {
        private Array<String> v;

        public Array<String> getValue() {
            return v;
        }

        public ArrayOfString setValue(Array<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class ArrayOfTuple {
        private Array<Tuple2<String, String>> v;

        public Array<Tuple2<String, String>> getValue() {
            return v;
        }

        public ArrayOfTuple setValue(Array<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class ListOfString {
        private List<String> v;

        public List<String> getValue() {
            return v;
        }

        public ListOfString setValue(List<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class ListOfTuple {
        private List<Tuple2<String, String>> v;

        public List<Tuple2<String, String>> getValue() {
            return v;
        }

        public ListOfTuple setValue(List<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class QueueOfString {
        private Queue<String> v;

        public Queue<String> getValue() {
            return v;
        }

        public QueueOfString setValue(Queue<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class QueueOfTuple {
        private Queue<Tuple2<String, String>> v;

        public Queue<Tuple2<String, String>> getValue() {
            return v;
        }

        public QueueOfTuple setValue(Queue<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class StreamOfString {
        private Stream<String> v;

        public Stream<String> getValue() {
            return v;
        }

        public StreamOfString setValue(Stream<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class StreamOfTuple {
        private Stream<Tuple2<String, String>> v;

        public Stream<Tuple2<String, String>> getValue() {
            return v;
        }

        public StreamOfTuple setValue(Stream<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class VectorOfString {
        private Vector<String> v;

        public Vector<String> getValue() {
            return v;
        }

        public VectorOfString setValue(Vector<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class VectorOfTuple {
        private Vector<Tuple2<String, String>> v;

        public Vector<Tuple2<String, String>> getValue() {
            return v;
        }

        public VectorOfTuple setValue(Vector<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class PriorityQueueOfString {
        private PriorityQueue<String> v;

        public PriorityQueue<String> getValue() {
            return v;
        }

        public PriorityQueueOfString setValue(PriorityQueue<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashSetOfString {
        private HashSet<String> v;

        public HashSet<String> getValue() {
            return v;
        }

        public HashSetOfString setValue(HashSet<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashSetOfTuple {
        private HashSet<Tuple2<String, String>> v;

        public HashSet<Tuple2<String, String>> getValue() {
            return v;
        }

        public HashSetOfTuple setValue(HashSet<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashSetOfString {
        private LinkedHashSet<String> v;

        public LinkedHashSet<String> getValue() {
            return v;
        }

        public LinkedHashSetOfString setValue(LinkedHashSet<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashSetOfTuple {
        private LinkedHashSet<Tuple2<String, String>> v;

        public LinkedHashSet<Tuple2<String, String>> getValue() {
            return v;
        }

        public LinkedHashSetOfTuple setValue(LinkedHashSet<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeSetOfString {
        private TreeSet<String> v;

        public TreeSet<String> getValue() {
            return v;
        }

        public TreeSetOfString setValue(TreeSet<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMapOfString {
        private HashMap<Integer, String> v;

        public HashMap<Integer, String> getValue() {
            return v;
        }

        public HashMapOfString setValue(HashMap<Integer, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMapOfTuple {
        private HashMap<Integer, Tuple2<String, String>> v;

        public HashMap<Integer, Tuple2<String, String>> getValue() {
            return v;
        }

        public HashMapOfTuple setValue(HashMap<Integer, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMapOfString {
        private LinkedHashMap<Integer, String> v;

        public LinkedHashMap<Integer, String> getValue() {
            return v;
        }

        public LinkedHashMapOfString setValue(LinkedHashMap<Integer, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMapOfTuple {
        private LinkedHashMap<Integer, Tuple2<String, String>> v;

        public LinkedHashMap<Integer, Tuple2<String, String>> getValue() {
            return v;
        }

        public LinkedHashMapOfTuple setValue(LinkedHashMap<Integer, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMapOfString {
        private TreeMap<Integer, String> v;

        public TreeMap<Integer, String> getValue() {
            return v;
        }

        public TreeMapOfString setValue(TreeMap<Integer, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMapOfTuple {
        private TreeMap<Integer, Tuple2<String, String>> v;

        public TreeMap<Integer, Tuple2<String, String>> getValue() {
            return v;
        }

        public TreeMapOfTuple setValue(TreeMap<Integer, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMultimapOfSeqString {
        private HashMultimap<String, String> v;

        public HashMultimap<String, String> getValue() {
            return v;
        }

        public HashMultimapOfSeqString setValue(HashMultimap<String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class HashMultimapOfSeqTuple {
        private HashMultimap<String, Tuple2<String, String>> v;

        public HashMultimap<String, Tuple2<String, String>> getValue() {
            return v;
        }

        public HashMultimapOfSeqTuple setValue(HashMultimap<String, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMultimapOfSeqString {
        private LinkedHashMultimap<String, String> v;

        public LinkedHashMultimap<String, String> getValue() {
            return v;
        }

        public LinkedHashMultimapOfSeqString setValue(LinkedHashMultimap<String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class LinkedHashMultimapOfSeqTuple {
        private LinkedHashMultimap<String, Tuple2<String, String>> v;

        public LinkedHashMultimap<String, Tuple2<String, String>> getValue() {
            return v;
        }

        public LinkedHashMultimapOfSeqTuple setValue(LinkedHashMultimap<String, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMultimapOfSeqString {
        private TreeMultimap<String, String> v;

        public TreeMultimap<String, String> getValue() {
            return v;
        }

        public TreeMultimapOfSeqString setValue(TreeMultimap<String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class TreeMultimapOfSeqTuple {
        private TreeMultimap<String, Tuple2<String, String>> v;

        public TreeMultimap<String, Tuple2<String, String>> getValue() {
            return v;
        }

        public TreeMultimapOfSeqTuple setValue(TreeMultimap<String, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class OptionOfString {
        private Option<String> v;

        public Option<String> getValue() {
            return v;
        }

        public OptionOfString setValue(Option<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class OptionOfTuple {
        private Option<Tuple2<String, String>> v;

        public Option<Tuple2<String, String>> getValue() {
            return v;
        }

        public OptionOfTuple setValue(Option<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class LazyOfString {
        private Lazy<String> v;

        public Lazy<String> getValue() {
            return v;
        }

        public LazyOfString setValue(Lazy<String> v) {
            this.v = v;
            return this;
        }
    }

    public static class LazyOfTuple {
        private Lazy<Tuple2<String, String>> v;

        public Lazy<Tuple2<String, String>> getValue() {
            return v;
        }

        public LazyOfTuple setValue(Lazy<Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }

    public static class LeftEitherOfString {
        private Either<String, Object> v;

        public Either<String, Object> getValue() {
            return v;
        }

        public LeftEitherOfString setValue(Either<String, Object> v) {
            this.v = v;
            return this;
        }
    }

    public static class LeftEitherOfTuple {
        private Either<Tuple2<String, String>, Object> v;

        public Either<Tuple2<String, String>, Object> getValue() {
            return v;
        }

        public LeftEitherOfTuple setValue(Either<Tuple2<String, String>, Object> v) {
            this.v = v;
            return this;
        }
    }

    public static class RightEitherOfString {
        private Either<Object, String> v;

        public Either<Object, String> getValue() {
            return v;
        }

        public RightEitherOfString setValue(Either<Object, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class RightEitherOfTuple {
        private Either<Object, Tuple2<String, String>> v;

        public Either<Object, Tuple2<String, String>> getValue() {
            return v;
        }

        public RightEitherOfTuple setValue(Either<Object, Tuple2<String, String>> v) {
            this.v = v;
            return this;
        }
    }
}
