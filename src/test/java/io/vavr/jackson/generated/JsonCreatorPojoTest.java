package io.vavr.jackson.generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * generated
 */
public class JsonCreatorPojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(MAPPER_MODULE);

    @Test
    void testJsonCreatorTuple1OfString() throws Exception {
        String src0 = "A";
        Tuple1<String> src = Tuple.of(src0);
        String json = MAPPER.writeValueAsString(Tuple1OfString.create(src));
        Assertions.assertEquals(json, "[\"A\"]");
        Tuple1OfString restored = MAPPER.readValue(json, Tuple1OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple1OfTuple() throws Exception {
        String src00 = "B";
        Tuple1<String> src0 = Tuple.of(src00);
        Tuple1<Tuple1<String>> src = Tuple.of(src0);
        String json = MAPPER.writeValueAsString(Tuple1OfTuple.create(src));
        Assertions.assertEquals(json, "[[\"B\"]]");
        Tuple1OfTuple restored = MAPPER.readValue(json, Tuple1OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple2OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        Tuple2<String, String> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(Tuple2OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\"]");
        Tuple2OfString restored = MAPPER.readValue(json, Tuple2OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple2OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        Tuple2<String, Tuple2<String, String>> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(Tuple2OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"]]");
        Tuple2OfTuple restored = MAPPER.readValue(json, Tuple2OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple3OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Tuple3<String, String, String> src = Tuple.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(Tuple3OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        Tuple3OfString restored = MAPPER.readValue(json, Tuple3OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple3OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> src = Tuple.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(Tuple3OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"],[\"D\",\"E\"]]");
        Tuple3OfTuple restored = MAPPER.readValue(json, Tuple3OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple4OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        Tuple4<String, String, String, String> src = Tuple.of(src0, src1, src2, src3);
        String json = MAPPER.writeValueAsString(Tuple4OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\",\"D\"]");
        Tuple4OfString restored = MAPPER.readValue(json, Tuple4OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple4OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(Tuple4OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"]]");
        Tuple4OfTuple restored = MAPPER.readValue(json, Tuple4OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple5OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        Tuple5<String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4);
        String json = MAPPER.writeValueAsString(Tuple5OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\",\"D\",\"E\"]");
        Tuple5OfString restored = MAPPER.readValue(json, Tuple5OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple5OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(Tuple5OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\"]");
        Tuple5OfTuple restored = MAPPER.readValue(json, Tuple5OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple6OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        Tuple6<String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5);
        String json = MAPPER.writeValueAsString(Tuple6OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"]");
        Tuple6OfString restored = MAPPER.readValue(json, Tuple6OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple6OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(Tuple6OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"]]");
        Tuple6OfTuple restored = MAPPER.readValue(json, Tuple6OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple7OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        String src6 = "1";
        Tuple7<String, String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6);
        String json = MAPPER.writeValueAsString(Tuple7OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"1\"]");
        Tuple7OfString restored = MAPPER.readValue(json, Tuple7OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple7OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(Tuple7OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"],[\"D\",\"E\"]]");
        Tuple7OfTuple restored = MAPPER.readValue(json, Tuple7OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple8OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        String src6 = "1";
        String src7 = "2";
        Tuple8<String, String, String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6, src7);
        String json = MAPPER.writeValueAsString(Tuple8OfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"1\",\"2\"]");
        Tuple8OfString restored = MAPPER.readValue(json, Tuple8OfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTuple8OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(Tuple8OfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"]]");
        Tuple8OfTuple restored = MAPPER.readValue(json, Tuple8OfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorArrayOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Array<String> src = Array.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(ArrayOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        ArrayOfString restored = MAPPER.readValue(json, ArrayOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorArrayOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Array<Tuple2<String, String>> src = Array.of(src0);
        String json = MAPPER.writeValueAsString(ArrayOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        ArrayOfTuple restored = MAPPER.readValue(json, ArrayOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorListOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        List<String> src = List.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(ListOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        ListOfString restored = MAPPER.readValue(json, ListOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorListOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        List<Tuple2<String, String>> src = List.of(src0);
        String json = MAPPER.writeValueAsString(ListOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        ListOfTuple restored = MAPPER.readValue(json, ListOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorQueueOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Queue<String> src = Queue.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(QueueOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        QueueOfString restored = MAPPER.readValue(json, QueueOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorQueueOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Queue<Tuple2<String, String>> src = Queue.of(src0);
        String json = MAPPER.writeValueAsString(QueueOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        QueueOfTuple restored = MAPPER.readValue(json, QueueOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorStreamOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Stream<String> src = Stream.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(StreamOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        StreamOfString restored = MAPPER.readValue(json, StreamOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorStreamOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Stream<Tuple2<String, String>> src = Stream.of(src0);
        String json = MAPPER.writeValueAsString(StreamOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        StreamOfTuple restored = MAPPER.readValue(json, StreamOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorVectorOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Vector<String> src = Vector.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(VectorOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        VectorOfString restored = MAPPER.readValue(json, VectorOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorVectorOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Vector<Tuple2<String, String>> src = Vector.of(src0);
        String json = MAPPER.writeValueAsString(VectorOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        VectorOfTuple restored = MAPPER.readValue(json, VectorOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorPriorityQueueOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        PriorityQueue<String> src = PriorityQueue.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(PriorityQueueOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        PriorityQueueOfString restored = MAPPER.readValue(json, PriorityQueueOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        HashSet<String> src = HashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(HashSetOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        HashSetOfString restored = MAPPER.readValue(json, HashSetOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        HashSet<Tuple2<String, String>> src = HashSet.of(src0);
        String json = MAPPER.writeValueAsString(HashSetOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        HashSetOfTuple restored = MAPPER.readValue(json, HashSetOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLinkedHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        LinkedHashSet<String> src = LinkedHashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(LinkedHashSetOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        LinkedHashSetOfString restored = MAPPER.readValue(json, LinkedHashSetOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLinkedHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        LinkedHashSet<Tuple2<String, String>> src = LinkedHashSet.of(src0);
        String json = MAPPER.writeValueAsString(LinkedHashSetOfTuple.create(src));
        Assertions.assertEquals(json, "[[\"A\",\"B\"]]");
        LinkedHashSetOfTuple restored = MAPPER.readValue(json, LinkedHashSetOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTreeSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        TreeSet<String> src = TreeSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(TreeSetOfString.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\",\"C\"]");
        TreeSetOfString restored = MAPPER.readValue(json, TreeSetOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        HashMap<Integer, String> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(HashMapOfString.create(src));
        Assertions.assertEquals(json, "{\"1\":\"A\"}");
        HashMapOfString restored = MAPPER.readValue(json, HashMapOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorHashMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        HashMap<Integer, Tuple2<String, String>> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(HashMapOfTuple.create(src));
        Assertions.assertEquals(json, "{\"1\":[\"A\",\"B\"]}");
        HashMapOfTuple restored = MAPPER.readValue(json, HashMapOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLinkedHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, String> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(LinkedHashMapOfString.create(src));
        Assertions.assertEquals(json, "{\"1\":\"A\"}");
        LinkedHashMapOfString restored = MAPPER.readValue(json, LinkedHashMapOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLinkedHashMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, Tuple2<String, String>> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(LinkedHashMapOfTuple.create(src));
        Assertions.assertEquals(json, "{\"1\":[\"A\",\"B\"]}");
        LinkedHashMapOfTuple restored = MAPPER.readValue(json, LinkedHashMapOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTreeMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, String> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(TreeMapOfString.create(src));
        Assertions.assertEquals(json, "{\"1\":\"A\"}");
        TreeMapOfString restored = MAPPER.readValue(json, TreeMapOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTreeMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, Tuple2<String, String>> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(TreeMapOfTuple.create(src));
        Assertions.assertEquals(json, "{\"1\":[\"A\",\"B\"]}");
        TreeMapOfTuple restored = MAPPER.readValue(json, TreeMapOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorHashMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        HashMultimap<String, String> src = HashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(HashMultimapOfSeqString.create(src));
        Assertions.assertEquals(json, "{\"A\":[\"B\",\"C\"]}");
        HashMultimapOfSeqString restored = MAPPER.readValue(json, HashMultimapOfSeqString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorHashMultimapOfSeqTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(HashMultimapOfSeqTuple.create(src));
        Assertions.assertEquals(json, "{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}");
        HashMultimapOfSeqTuple restored = MAPPER.readValue(json, HashMultimapOfSeqTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLinkedHashMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        LinkedHashMultimap<String, String> src = LinkedHashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(LinkedHashMultimapOfSeqString.create(src));
        Assertions.assertEquals(json, "{\"A\":[\"B\",\"C\"]}");
        LinkedHashMultimapOfSeqString restored = MAPPER.readValue(json, LinkedHashMultimapOfSeqString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLinkedHashMultimapOfSeqTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(LinkedHashMultimapOfSeqTuple.create(src));
        Assertions.assertEquals(json, "{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}");
        LinkedHashMultimapOfSeqTuple restored = MAPPER.readValue(json, LinkedHashMultimapOfSeqTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTreeMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        TreeMultimap<String, String> src = TreeMultimap.withSet().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(TreeMultimapOfSeqString.create(src));
        Assertions.assertEquals(json, "{\"A\":[\"B\",\"C\"]}");
        TreeMultimapOfSeqString restored = MAPPER.readValue(json, TreeMultimapOfSeqString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorTreeMultimapOfSeqTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(TreeMultimapOfSeqTuple.create(src));
        Assertions.assertEquals(json, "{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}");
        TreeMultimapOfSeqTuple restored = MAPPER.readValue(json, TreeMultimapOfSeqTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorOptionOfString() throws Exception {
        String src0 = "A";
        Option<String> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(OptionOfString.create(src));
        Assertions.assertEquals(json, "\"A\"");
        OptionOfString restored = MAPPER.readValue(json, OptionOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorOptionOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Option<Tuple2<String, String>> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(OptionOfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\"]");
        OptionOfTuple restored = MAPPER.readValue(json, OptionOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLazyOfString() throws Exception {
        String src0 = "A";
        Lazy<String> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(LazyOfString.create(src));
        Assertions.assertEquals(json, "\"A\"");
        LazyOfString restored = MAPPER.readValue(json, LazyOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLazyOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Lazy<Tuple2<String, String>> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(LazyOfTuple.create(src));
        Assertions.assertEquals(json, "[\"A\",\"B\"]");
        LazyOfTuple restored = MAPPER.readValue(json, LazyOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLeftEitherOfString() throws Exception {
        String srcl = "A";
        Either<String, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(LeftEitherOfString.create(src));
        Assertions.assertEquals(json, "[\"left\",\"A\"]");
        LeftEitherOfString restored = MAPPER.readValue(json, LeftEitherOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorLeftEitherOfTuple() throws Exception {
        String srcl0 = "A";
        String srcl1 = "B";
        Tuple2<String, String> srcl = Tuple.of(srcl0, srcl1);
        Either<Tuple2<String, String>, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(LeftEitherOfTuple.create(src));
        Assertions.assertEquals(json, "[\"left\",[\"A\",\"B\"]]");
        LeftEitherOfTuple restored = MAPPER.readValue(json, LeftEitherOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorRightEitherOfString() throws Exception {
        String srcr = "A";
        Either<Object, String> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(RightEitherOfString.create(src));
        Assertions.assertEquals(json, "[\"right\",\"A\"]");
        RightEitherOfString restored = MAPPER.readValue(json, RightEitherOfString.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    @Test
    void testJsonCreatorRightEitherOfTuple() throws Exception {
        String srcr0 = "A";
        String srcr1 = "B";
        Tuple2<String, String> srcr = Tuple.of(srcr0, srcr1);
        Either<Object, Tuple2<String, String>> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(RightEitherOfTuple.create(src));
        Assertions.assertEquals(json, "[\"right\",[\"A\",\"B\"]]");
        RightEitherOfTuple restored = MAPPER.readValue(json, RightEitherOfTuple.class);
        Assertions.assertEquals(src, restored.getValue());
    }

    public static class Tuple1OfString {
        private Tuple1<String> v;

        @JsonValue
        public Tuple1<String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple1OfString create(Tuple1<String> v) {
            Tuple1OfString i = new Tuple1OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple1OfTuple {
        private Tuple1<Tuple1<String>> v;

        @JsonValue
        public Tuple1<Tuple1<String>> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple1OfTuple create(Tuple1<Tuple1<String>> v) {
            Tuple1OfTuple i = new Tuple1OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple2OfString {
        private Tuple2<String, String> v;

        @JsonValue
        public Tuple2<String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple2OfString create(Tuple2<String, String> v) {
            Tuple2OfString i = new Tuple2OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple2OfTuple {
        private Tuple2<String, Tuple2<String, String>> v;

        @JsonValue
        public Tuple2<String, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple2OfTuple create(Tuple2<String, Tuple2<String, String>> v) {
            Tuple2OfTuple i = new Tuple2OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple3OfString {
        private Tuple3<String, String, String> v;

        @JsonValue
        public Tuple3<String, String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple3OfString create(Tuple3<String, String, String> v) {
            Tuple3OfString i = new Tuple3OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple3OfTuple {
        private Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> v;

        @JsonValue
        public Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple3OfTuple create(
                Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> v) {
            Tuple3OfTuple i = new Tuple3OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple4OfString {
        private Tuple4<String, String, String, String> v;

        @JsonValue
        public Tuple4<String, String, String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple4OfString create(Tuple4<String, String, String, String> v) {
            Tuple4OfString i = new Tuple4OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple4OfTuple {
        private Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v;

        @JsonValue
        public Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> getValue(
                ) {
            return v;
        }

        @JsonCreator
        public static Tuple4OfTuple create(
                Tuple4<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v) {
            Tuple4OfTuple i = new Tuple4OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple5OfString {
        private Tuple5<String, String, String, String, String> v;

        @JsonValue
        public Tuple5<String, String, String, String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple5OfString create(Tuple5<String, String, String, String, String> v) {
            Tuple5OfString i = new Tuple5OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple5OfTuple {
        private Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> v;

        @JsonValue
        public Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> getValue(
                ) {
            return v;
        }

        @JsonCreator
        public static Tuple5OfTuple create(
                Tuple5<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String> v) {
            Tuple5OfTuple i = new Tuple5OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple6OfString {
        private Tuple6<String, String, String, String, String, String> v;

        @JsonValue
        public Tuple6<String, String, String, String, String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple6OfString create(
                Tuple6<String, String, String, String, String, String> v) {
            Tuple6OfString i = new Tuple6OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple6OfTuple {
        private Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> v;

        @JsonValue
        public Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> getValue(
                ) {
            return v;
        }

        @JsonCreator
        public static Tuple6OfTuple create(
                Tuple6<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>> v) {
            Tuple6OfTuple i = new Tuple6OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple7OfString {
        private Tuple7<String, String, String, String, String, String, String> v;

        @JsonValue
        public Tuple7<String, String, String, String, String, String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple7OfString create(
                Tuple7<String, String, String, String, String, String, String> v) {
            Tuple7OfString i = new Tuple7OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple7OfTuple {
        private Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> v;

        @JsonValue
        public Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> getValue(
                ) {
            return v;
        }

        @JsonCreator
        public static Tuple7OfTuple create(
                Tuple7<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>> v) {
            Tuple7OfTuple i = new Tuple7OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class Tuple8OfString {
        private Tuple8<String, String, String, String, String, String, String, String> v;

        @JsonValue
        public Tuple8<String, String, String, String, String, String, String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static Tuple8OfString create(
                Tuple8<String, String, String, String, String, String, String, String> v) {
            Tuple8OfString i = new Tuple8OfString();
            i.v = v;
            return i;
        }
    }

    public static class Tuple8OfTuple {
        private Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v;

        @JsonValue
        public Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> getValue(
                ) {
            return v;
        }

        @JsonCreator
        public static Tuple8OfTuple create(
                Tuple8<String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>, String, Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>> v) {
            Tuple8OfTuple i = new Tuple8OfTuple();
            i.v = v;
            return i;
        }
    }

    public static class ArrayOfString {
        private Array<String> v;

        @JsonValue
        public Array<String> getValue() {
            return v;
        }

        @JsonCreator
        public static ArrayOfString create(Array<String> v) {
            ArrayOfString i = new ArrayOfString();
            i.v = v;
            return i;
        }
    }

    public static class ArrayOfTuple {
        private Array<Tuple2<String, String>> v;

        @JsonValue
        public Array<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static ArrayOfTuple create(Array<Tuple2<String, String>> v) {
            ArrayOfTuple i = new ArrayOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class ListOfString {
        private List<String> v;

        @JsonValue
        public List<String> getValue() {
            return v;
        }

        @JsonCreator
        public static ListOfString create(List<String> v) {
            ListOfString i = new ListOfString();
            i.v = v;
            return i;
        }
    }

    public static class ListOfTuple {
        private List<Tuple2<String, String>> v;

        @JsonValue
        public List<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static ListOfTuple create(List<Tuple2<String, String>> v) {
            ListOfTuple i = new ListOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class QueueOfString {
        private Queue<String> v;

        @JsonValue
        public Queue<String> getValue() {
            return v;
        }

        @JsonCreator
        public static QueueOfString create(Queue<String> v) {
            QueueOfString i = new QueueOfString();
            i.v = v;
            return i;
        }
    }

    public static class QueueOfTuple {
        private Queue<Tuple2<String, String>> v;

        @JsonValue
        public Queue<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static QueueOfTuple create(Queue<Tuple2<String, String>> v) {
            QueueOfTuple i = new QueueOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class StreamOfString {
        private Stream<String> v;

        @JsonValue
        public Stream<String> getValue() {
            return v;
        }

        @JsonCreator
        public static StreamOfString create(Stream<String> v) {
            StreamOfString i = new StreamOfString();
            i.v = v;
            return i;
        }
    }

    public static class StreamOfTuple {
        private Stream<Tuple2<String, String>> v;

        @JsonValue
        public Stream<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static StreamOfTuple create(Stream<Tuple2<String, String>> v) {
            StreamOfTuple i = new StreamOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class VectorOfString {
        private Vector<String> v;

        @JsonValue
        public Vector<String> getValue() {
            return v;
        }

        @JsonCreator
        public static VectorOfString create(Vector<String> v) {
            VectorOfString i = new VectorOfString();
            i.v = v;
            return i;
        }
    }

    public static class VectorOfTuple {
        private Vector<Tuple2<String, String>> v;

        @JsonValue
        public Vector<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static VectorOfTuple create(Vector<Tuple2<String, String>> v) {
            VectorOfTuple i = new VectorOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class PriorityQueueOfString {
        private PriorityQueue<String> v;

        @JsonValue
        public PriorityQueue<String> getValue() {
            return v;
        }

        @JsonCreator
        public static PriorityQueueOfString create(PriorityQueue<String> v) {
            PriorityQueueOfString i = new PriorityQueueOfString();
            i.v = v;
            return i;
        }
    }

    public static class HashSetOfString {
        private HashSet<String> v;

        @JsonValue
        public HashSet<String> getValue() {
            return v;
        }

        @JsonCreator
        public static HashSetOfString create(HashSet<String> v) {
            HashSetOfString i = new HashSetOfString();
            i.v = v;
            return i;
        }
    }

    public static class HashSetOfTuple {
        private HashSet<Tuple2<String, String>> v;

        @JsonValue
        public HashSet<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static HashSetOfTuple create(HashSet<Tuple2<String, String>> v) {
            HashSetOfTuple i = new HashSetOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class LinkedHashSetOfString {
        private LinkedHashSet<String> v;

        @JsonValue
        public LinkedHashSet<String> getValue() {
            return v;
        }

        @JsonCreator
        public static LinkedHashSetOfString create(LinkedHashSet<String> v) {
            LinkedHashSetOfString i = new LinkedHashSetOfString();
            i.v = v;
            return i;
        }
    }

    public static class LinkedHashSetOfTuple {
        private LinkedHashSet<Tuple2<String, String>> v;

        @JsonValue
        public LinkedHashSet<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static LinkedHashSetOfTuple create(LinkedHashSet<Tuple2<String, String>> v) {
            LinkedHashSetOfTuple i = new LinkedHashSetOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class TreeSetOfString {
        private TreeSet<String> v;

        @JsonValue
        public TreeSet<String> getValue() {
            return v;
        }

        @JsonCreator
        public static TreeSetOfString create(TreeSet<String> v) {
            TreeSetOfString i = new TreeSetOfString();
            i.v = v;
            return i;
        }
    }

    public static class HashMapOfString {
        private HashMap<Integer, String> v;

        @JsonValue
        public HashMap<Integer, String> getValue() {
            return v;
        }

        @JsonCreator
        public static HashMapOfString create(HashMap<Integer, String> v) {
            HashMapOfString i = new HashMapOfString();
            i.v = v;
            return i;
        }
    }

    public static class HashMapOfTuple {
        private HashMap<Integer, Tuple2<String, String>> v;

        @JsonValue
        public HashMap<Integer, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static HashMapOfTuple create(HashMap<Integer, Tuple2<String, String>> v) {
            HashMapOfTuple i = new HashMapOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class LinkedHashMapOfString {
        private LinkedHashMap<Integer, String> v;

        @JsonValue
        public LinkedHashMap<Integer, String> getValue() {
            return v;
        }

        @JsonCreator
        public static LinkedHashMapOfString create(LinkedHashMap<Integer, String> v) {
            LinkedHashMapOfString i = new LinkedHashMapOfString();
            i.v = v;
            return i;
        }
    }

    public static class LinkedHashMapOfTuple {
        private LinkedHashMap<Integer, Tuple2<String, String>> v;

        @JsonValue
        public LinkedHashMap<Integer, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static LinkedHashMapOfTuple create(
                LinkedHashMap<Integer, Tuple2<String, String>> v) {
            LinkedHashMapOfTuple i = new LinkedHashMapOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class TreeMapOfString {
        private TreeMap<Integer, String> v;

        @JsonValue
        public TreeMap<Integer, String> getValue() {
            return v;
        }

        @JsonCreator
        public static TreeMapOfString create(TreeMap<Integer, String> v) {
            TreeMapOfString i = new TreeMapOfString();
            i.v = v;
            return i;
        }
    }

    public static class TreeMapOfTuple {
        private TreeMap<Integer, Tuple2<String, String>> v;

        @JsonValue
        public TreeMap<Integer, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static TreeMapOfTuple create(TreeMap<Integer, Tuple2<String, String>> v) {
            TreeMapOfTuple i = new TreeMapOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class HashMultimapOfSeqString {
        private HashMultimap<String, String> v;

        @JsonValue
        public HashMultimap<String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static HashMultimapOfSeqString create(HashMultimap<String, String> v) {
            HashMultimapOfSeqString i = new HashMultimapOfSeqString();
            i.v = v;
            return i;
        }
    }

    public static class HashMultimapOfSeqTuple {
        private HashMultimap<String, Tuple2<String, String>> v;

        @JsonValue
        public HashMultimap<String, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static HashMultimapOfSeqTuple create(
                HashMultimap<String, Tuple2<String, String>> v) {
            HashMultimapOfSeqTuple i = new HashMultimapOfSeqTuple();
            i.v = v;
            return i;
        }
    }

    public static class LinkedHashMultimapOfSeqString {
        private LinkedHashMultimap<String, String> v;

        @JsonValue
        public LinkedHashMultimap<String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static LinkedHashMultimapOfSeqString create(LinkedHashMultimap<String, String> v) {
            LinkedHashMultimapOfSeqString i = new LinkedHashMultimapOfSeqString();
            i.v = v;
            return i;
        }
    }

    public static class LinkedHashMultimapOfSeqTuple {
        private LinkedHashMultimap<String, Tuple2<String, String>> v;

        @JsonValue
        public LinkedHashMultimap<String, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static LinkedHashMultimapOfSeqTuple create(
                LinkedHashMultimap<String, Tuple2<String, String>> v) {
            LinkedHashMultimapOfSeqTuple i = new LinkedHashMultimapOfSeqTuple();
            i.v = v;
            return i;
        }
    }

    public static class TreeMultimapOfSeqString {
        private TreeMultimap<String, String> v;

        @JsonValue
        public TreeMultimap<String, String> getValue() {
            return v;
        }

        @JsonCreator
        public static TreeMultimapOfSeqString create(TreeMultimap<String, String> v) {
            TreeMultimapOfSeqString i = new TreeMultimapOfSeqString();
            i.v = v;
            return i;
        }
    }

    public static class TreeMultimapOfSeqTuple {
        private TreeMultimap<String, Tuple2<String, String>> v;

        @JsonValue
        public TreeMultimap<String, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static TreeMultimapOfSeqTuple create(
                TreeMultimap<String, Tuple2<String, String>> v) {
            TreeMultimapOfSeqTuple i = new TreeMultimapOfSeqTuple();
            i.v = v;
            return i;
        }
    }

    public static class OptionOfString {
        private Option<String> v;

        @JsonValue
        public Option<String> getValue() {
            return v;
        }

        @JsonCreator
        public static OptionOfString create(Option<String> v) {
            OptionOfString i = new OptionOfString();
            i.v = v;
            return i;
        }
    }

    public static class OptionOfTuple {
        private Option<Tuple2<String, String>> v;

        @JsonValue
        public Option<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static OptionOfTuple create(Option<Tuple2<String, String>> v) {
            OptionOfTuple i = new OptionOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class LazyOfString {
        private Lazy<String> v;

        @JsonValue
        public Lazy<String> getValue() {
            return v;
        }

        @JsonCreator
        public static LazyOfString create(Lazy<String> v) {
            LazyOfString i = new LazyOfString();
            i.v = v;
            return i;
        }
    }

    public static class LazyOfTuple {
        private Lazy<Tuple2<String, String>> v;

        @JsonValue
        public Lazy<Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static LazyOfTuple create(Lazy<Tuple2<String, String>> v) {
            LazyOfTuple i = new LazyOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class LeftEitherOfString {
        private Either<String, Object> v;

        @JsonValue
        public Either<String, Object> getValue() {
            return v;
        }

        @JsonCreator
        public static LeftEitherOfString create(Either<String, Object> v) {
            LeftEitherOfString i = new LeftEitherOfString();
            i.v = v;
            return i;
        }
    }

    public static class LeftEitherOfTuple {
        private Either<Tuple2<String, String>, Object> v;

        @JsonValue
        public Either<Tuple2<String, String>, Object> getValue() {
            return v;
        }

        @JsonCreator
        public static LeftEitherOfTuple create(Either<Tuple2<String, String>, Object> v) {
            LeftEitherOfTuple i = new LeftEitherOfTuple();
            i.v = v;
            return i;
        }
    }

    public static class RightEitherOfString {
        private Either<Object, String> v;

        @JsonValue
        public Either<Object, String> getValue() {
            return v;
        }

        @JsonCreator
        public static RightEitherOfString create(Either<Object, String> v) {
            RightEitherOfString i = new RightEitherOfString();
            i.v = v;
            return i;
        }
    }

    public static class RightEitherOfTuple {
        private Either<Object, Tuple2<String, String>> v;

        @JsonValue
        public Either<Object, Tuple2<String, String>> getValue() {
            return v;
        }

        @JsonCreator
        public static RightEitherOfTuple create(Either<Object, Tuple2<String, String>> v) {
            RightEitherOfTuple i = new RightEitherOfTuple();
            i.v = v;
            return i;
        }
    }
}
