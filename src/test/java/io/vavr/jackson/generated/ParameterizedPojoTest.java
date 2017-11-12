package io.vavr.jackson.generated;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.List;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Stream;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import org.junit.Assert;
import org.junit.Test;

/**
 * generated
 */
public class ParameterizedPojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(MAPPER_MODULE);

    @Test
    public void testTupleOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        Tuple2<String, String> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple2Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\"]}");
        ParameterizedTuple2Pojo<java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple2Pojo<java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTupleOfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        Tuple2<String, Tuple2<String, String>> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple2Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"]]}");
        ParameterizedTuple2Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple2Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testListOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        List<String> src = List.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedListPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedListPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedListPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testListOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        List<Tuple2<String, String>> src = List.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedListPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedListPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedListPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testStreamOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Stream<String> src = Stream.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedStreamPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedStreamPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedStreamPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testStreamOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Stream<Tuple2<String, String>> src = Stream.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedStreamPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedStreamPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedStreamPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testPriorityQueueOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        PriorityQueue<String> src = PriorityQueue.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedPriorityQueuePojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedPriorityQueuePojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedPriorityQueuePojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testPriorityQueueOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        PriorityQueue<Tuple2<String, String>> src = PriorityQueue.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedPriorityQueuePojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedPriorityQueuePojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedPriorityQueuePojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        HashSet<String> src = HashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedHashSetPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedHashSetPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedHashSetPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        HashSet<Tuple2<String, String>> src = HashSet.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedHashSetPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedHashSetPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedHashSetPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        LinkedHashSet<String> src = LinkedHashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedLinkedHashSetPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedLinkedHashSetPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLinkedHashSetPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        LinkedHashSet<Tuple2<String, String>> src = LinkedHashSet.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedLinkedHashSetPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedLinkedHashSetPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLinkedHashSetPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        TreeSet<String> src = TreeSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedTreeSetPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedTreeSetPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTreeSetPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        TreeSet<Tuple2<String, String>> src = TreeSet.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedTreeSetPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedTreeSetPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTreeSetPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        HashMap<Integer, String> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedHashMapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":\"A\"}}");
        ParameterizedHashMapPojo<java.lang.Integer, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedHashMapPojo<java.lang.Integer, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        HashMap<Integer, Tuple2<String, String>> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedHashMapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":[\"A\",\"B\"]}}");
        ParameterizedHashMapPojo<java.lang.Integer, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedHashMapPojo<java.lang.Integer, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, String> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedLinkedHashMapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":\"A\"}}");
        ParameterizedLinkedHashMapPojo<java.lang.Integer, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLinkedHashMapPojo<java.lang.Integer, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, Tuple2<String, String>> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedLinkedHashMapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":[\"A\",\"B\"]}}");
        ParameterizedLinkedHashMapPojo<java.lang.Integer, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLinkedHashMapPojo<java.lang.Integer, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, String> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedTreeMapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":\"A\"}}");
        ParameterizedTreeMapPojo<java.lang.Integer, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTreeMapPojo<java.lang.Integer, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeMapOfTuple() throws Exception {
        Integer src00 = 1;
        String src010 = "A";
        String src011 = "B";
        Tuple2<String, String> src01 = Tuple.of(src010, src011);
        Tuple2<Integer, Tuple2<String, String>> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, Tuple2<String, String>> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedTreeMapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":[\"A\",\"B\"]}}");
        ParameterizedTreeMapPojo<java.lang.Integer, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTreeMapPojo<java.lang.Integer, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        HashMultimap<String, String> src = HashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new ParameterizedHashMultimapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[\"B\",\"C\"]}}");
        ParameterizedHashMultimapPojo<java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedHashMultimapPojo<java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashMultimapOfSeqTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedHashMultimapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        ParameterizedHashMultimapPojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedHashMultimapPojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        LinkedHashMultimap<String, String> src = LinkedHashMultimap.withSeq().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new ParameterizedLinkedHashMultimapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[\"B\",\"C\"]}}");
        ParameterizedLinkedHashMultimapPojo<java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLinkedHashMultimapPojo<java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashMultimapOfSeqTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedLinkedHashMultimapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        ParameterizedLinkedHashMultimapPojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLinkedHashMultimapPojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeMultimapOfSeqString() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        String src10 = "A";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        TreeMultimap<String, String> src = TreeMultimap.withSet().ofEntries(src0, src1);
        String json = MAPPER.writeValueAsString(new ParameterizedTreeMultimapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[\"B\",\"C\"]}}");
        ParameterizedTreeMultimapPojo<java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTreeMultimapPojo<java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeMultimapOfSeqTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedTreeMultimapPojo<>(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        ParameterizedTreeMultimapPojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTreeMultimapPojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testOptionOfString() throws Exception {
        String src0 = "A";
        Option<String> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedOptionPojo<>(src));
        Assert.assertEquals(json, "{\"value\":\"A\"}");
        ParameterizedOptionPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedOptionPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testOptionOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Option<Tuple2<String, String>> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedOptionPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\"]}");
        ParameterizedOptionPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedOptionPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLazyOfString() throws Exception {
        String src0 = "A";
        Lazy<String> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(new ParameterizedLazyPojo<>(src));
        Assert.assertEquals(json, "{\"value\":\"A\"}");
        ParameterizedLazyPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLazyPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLazyOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Lazy<Tuple2<String, String>> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(new ParameterizedLazyPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\"]}");
        ParameterizedLazyPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedLazyPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLeftEitherOfString() throws Exception {
        String srcl = "A";
        Either<String, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(new ParameterizedEitherPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"left\",\"A\"]}");
        ParameterizedEitherPojo<java.lang.String, java.lang.Object> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedEitherPojo<java.lang.String, java.lang.Object>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLeftEitherOfTuple() throws Exception {
        String srcl0 = "A";
        String srcl1 = "B";
        Tuple2<String, String> srcl = Tuple.of(srcl0, srcl1);
        Either<Tuple2<String, String>, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(new ParameterizedEitherPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"left\",[\"A\",\"B\"]]}");
        ParameterizedEitherPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.Object> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedEitherPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.Object>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testRightEitherOfString() throws Exception {
        String srcr = "A";
        Either<Object, String> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(new ParameterizedEitherPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"right\",\"A\"]}");
        ParameterizedEitherPojo<java.lang.Object, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedEitherPojo<java.lang.Object, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testRightEitherOfTuple() throws Exception {
        String srcr0 = "A";
        String srcr1 = "B";
        Tuple2<String, String> srcr = Tuple.of(srcr0, srcr1);
        Either<Object, Tuple2<String, String>> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(new ParameterizedEitherPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"right\",[\"A\",\"B\"]]}");
        ParameterizedEitherPojo<java.lang.Object, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedEitherPojo<java.lang.Object, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    public static class ParameterizedTuple2Pojo<T1, T2> {
        private Tuple2<T1, T2> v;

        public ParameterizedTuple2Pojo() {
        }

        public ParameterizedTuple2Pojo(Tuple2<T1, T2> v) {
            this.v = v;
        }

        public Tuple2<T1, T2> getValue() {
            return v;
        }

        public ParameterizedTuple2Pojo setValue(Tuple2<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedListPojo<T1> {
        private List<T1> v;

        public ParameterizedListPojo() {
        }

        public ParameterizedListPojo(List<T1> v) {
            this.v = v;
        }

        public List<T1> getValue() {
            return v;
        }

        public ParameterizedListPojo setValue(List<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedStreamPojo<T1> {
        private Stream<T1> v;

        public ParameterizedStreamPojo() {
        }

        public ParameterizedStreamPojo(Stream<T1> v) {
            this.v = v;
        }

        public Stream<T1> getValue() {
            return v;
        }

        public ParameterizedStreamPojo setValue(Stream<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedPriorityQueuePojo<T1> {
        private PriorityQueue<T1> v;

        public ParameterizedPriorityQueuePojo() {
        }

        public ParameterizedPriorityQueuePojo(PriorityQueue<T1> v) {
            this.v = v;
        }

        public PriorityQueue<T1> getValue() {
            return v;
        }

        public ParameterizedPriorityQueuePojo setValue(PriorityQueue<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedHashSetPojo<T1> {
        private HashSet<T1> v;

        public ParameterizedHashSetPojo() {
        }

        public ParameterizedHashSetPojo(HashSet<T1> v) {
            this.v = v;
        }

        public HashSet<T1> getValue() {
            return v;
        }

        public ParameterizedHashSetPojo setValue(HashSet<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedLinkedHashSetPojo<T1> {
        private LinkedHashSet<T1> v;

        public ParameterizedLinkedHashSetPojo() {
        }

        public ParameterizedLinkedHashSetPojo(LinkedHashSet<T1> v) {
            this.v = v;
        }

        public LinkedHashSet<T1> getValue() {
            return v;
        }

        public ParameterizedLinkedHashSetPojo setValue(LinkedHashSet<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTreeSetPojo<T1> {
        private TreeSet<T1> v;

        public ParameterizedTreeSetPojo() {
        }

        public ParameterizedTreeSetPojo(TreeSet<T1> v) {
            this.v = v;
        }

        public TreeSet<T1> getValue() {
            return v;
        }

        public ParameterizedTreeSetPojo setValue(TreeSet<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedHashMapPojo<T1, T2> {
        private HashMap<T1, T2> v;

        public ParameterizedHashMapPojo() {
        }

        public ParameterizedHashMapPojo(HashMap<T1, T2> v) {
            this.v = v;
        }

        public HashMap<T1, T2> getValue() {
            return v;
        }

        public ParameterizedHashMapPojo setValue(HashMap<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedLinkedHashMapPojo<T1, T2> {
        private LinkedHashMap<T1, T2> v;

        public ParameterizedLinkedHashMapPojo() {
        }

        public ParameterizedLinkedHashMapPojo(LinkedHashMap<T1, T2> v) {
            this.v = v;
        }

        public LinkedHashMap<T1, T2> getValue() {
            return v;
        }

        public ParameterizedLinkedHashMapPojo setValue(LinkedHashMap<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTreeMapPojo<T1, T2> {
        private TreeMap<T1, T2> v;

        public ParameterizedTreeMapPojo() {
        }

        public ParameterizedTreeMapPojo(TreeMap<T1, T2> v) {
            this.v = v;
        }

        public TreeMap<T1, T2> getValue() {
            return v;
        }

        public ParameterizedTreeMapPojo setValue(TreeMap<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedHashMultimapPojo<T1, T2> {
        private HashMultimap<T1, T2> v;

        public ParameterizedHashMultimapPojo() {
        }

        public ParameterizedHashMultimapPojo(HashMultimap<T1, T2> v) {
            this.v = v;
        }

        public HashMultimap<T1, T2> getValue() {
            return v;
        }

        public ParameterizedHashMultimapPojo setValue(HashMultimap<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedLinkedHashMultimapPojo<T1, T2> {
        private LinkedHashMultimap<T1, T2> v;

        public ParameterizedLinkedHashMultimapPojo() {
        }

        public ParameterizedLinkedHashMultimapPojo(LinkedHashMultimap<T1, T2> v) {
            this.v = v;
        }

        public LinkedHashMultimap<T1, T2> getValue() {
            return v;
        }

        public ParameterizedLinkedHashMultimapPojo setValue(LinkedHashMultimap<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTreeMultimapPojo<T1, T2> {
        private TreeMultimap<T1, T2> v;

        public ParameterizedTreeMultimapPojo() {
        }

        public ParameterizedTreeMultimapPojo(TreeMultimap<T1, T2> v) {
            this.v = v;
        }

        public TreeMultimap<T1, T2> getValue() {
            return v;
        }

        public ParameterizedTreeMultimapPojo setValue(TreeMultimap<T1, T2> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedOptionPojo<T1> {
        private Option<T1> v;

        public ParameterizedOptionPojo() {
        }

        public ParameterizedOptionPojo(Option<T1> v) {
            this.v = v;
        }

        public Option<T1> getValue() {
            return v;
        }

        public ParameterizedOptionPojo setValue(Option<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedLazyPojo<T1> {
        private Lazy<T1> v;

        public ParameterizedLazyPojo() {
        }

        public ParameterizedLazyPojo(Lazy<T1> v) {
            this.v = v;
        }

        public Lazy<T1> getValue() {
            return v;
        }

        public ParameterizedLazyPojo setValue(Lazy<T1> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedEitherPojo<T1, T2> {
        private Either<T1, T2> v;

        public ParameterizedEitherPojo() {
        }

        public ParameterizedEitherPojo(Either<T1, T2> v) {
            this.v = v;
        }

        public Either<T1, T2> getValue() {
            return v;
        }

        public ParameterizedEitherPojo setValue(Either<T1, T2> v) {
            this.v = v;
            return this;
        }
    }
}
