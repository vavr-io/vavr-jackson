package io.vavr.jackson.generated;

import com.fasterxml.jackson.core.type.TypeReference;
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
    public void testTuple1OfString() throws Exception {
        String src0 = "A";
        Tuple1<String> src = Tuple.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple1Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\"]}");
        ParameterizedTuple1Pojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple1Pojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple1OfTuple() throws Exception {
        String src00 = "B";
        Tuple1<String> src0 = Tuple.of(src00);
        Tuple1<Tuple1<String>> src = Tuple.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple1Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"B\"]]}");
        ParameterizedTuple1Pojo<io.vavr.Tuple1<java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple1Pojo<io.vavr.Tuple1<java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple2OfString() throws Exception {
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
    public void testTuple2OfTuple() throws Exception {
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
    public void testTuple3OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Tuple3<String, String, String> src = Tuple.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple3Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedTuple3Pojo<java.lang.String, java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple3Pojo<java.lang.String, java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple3OfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        String src20 = "D";
        String src21 = "E";
        Tuple2<String, String> src2 = Tuple.of(src20, src21);
        Tuple3<String, Tuple2<String, String>, Tuple2<String, String>> src = Tuple.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple3Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"]]}");
        ParameterizedTuple3Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple3Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple4OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        Tuple4<String, String, String, String> src = Tuple.of(src0, src1, src2, src3);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple4Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\",\"D\"]}");
        ParameterizedTuple4Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple4Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple4OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedTuple4Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"]]}");
        ParameterizedTuple4Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple4Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple5OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        Tuple5<String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple5Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\"]}");
        ParameterizedTuple5Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple5Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple5OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedTuple5Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\"]}");
        ParameterizedTuple5Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple5Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple6OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        Tuple6<String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple6Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"]}");
        ParameterizedTuple6Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple6Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple6OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedTuple6Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"]]}");
        ParameterizedTuple6Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple6Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple7OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        String src6 = "1";
        Tuple7<String, String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple7Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"1\"]}");
        ParameterizedTuple7Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple7Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple7OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedTuple7Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"],[\"D\",\"E\"]]}");
        ParameterizedTuple7Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple7Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple8OfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        String src3 = "D";
        String src4 = "E";
        String src5 = "F";
        String src6 = "1";
        String src7 = "2";
        Tuple8<String, String, String, String, String, String, String, String> src = Tuple.of(src0, src1, src2, src3, src4, src5, src6, src7);
        String json = MAPPER.writeValueAsString(new ParameterizedTuple8Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"1\",\"2\"]}");
        ParameterizedTuple8Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple8Pojo<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTuple8OfTuple() throws Exception {
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
        String json = MAPPER.writeValueAsString(new ParameterizedTuple8Pojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"],\"A\",[\"B\",\"C\"],[\"D\",\"E\"],[\"F\",\"1\"]]}");
        ParameterizedTuple8Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedTuple8Pojo<java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, java.lang.String, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>, io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testArrayOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Array<String> src = Array.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedArrayPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedArrayPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedArrayPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testArrayOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Array<Tuple2<String, String>> src = Array.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedArrayPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedArrayPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedArrayPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
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
    public void testQueueOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Queue<String> src = Queue.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedQueuePojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedQueuePojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedQueuePojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testQueueOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Queue<Tuple2<String, String>> src = Queue.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedQueuePojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedQueuePojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedQueuePojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
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
    public void testVectorOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        Vector<String> src = Vector.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ParameterizedVectorPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ParameterizedVectorPojo<java.lang.String> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedVectorPojo<java.lang.String>>(){});
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testVectorOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Vector<Tuple2<String, String>> src = Vector.of(src0);
        String json = MAPPER.writeValueAsString(new ParameterizedVectorPojo<>(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ParameterizedVectorPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>> restored = 
                MAPPER.readValue(json, new TypeReference<ParameterizedVectorPojo<io.vavr.Tuple2<java.lang.String, java.lang.String>>>(){});
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

    public static class ParameterizedTuple1Pojo<T1> {
        private Tuple1<T1> v;

        public ParameterizedTuple1Pojo() {
        }

        public ParameterizedTuple1Pojo(Tuple1<T1> v) {
            this.v = v;
        }

        public Tuple1<T1> getValue() {
            return v;
        }

        public ParameterizedTuple1Pojo setValue(Tuple1<T1> v) {
            this.v = v;
            return this;
        }
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

    public static class ParameterizedTuple3Pojo<T1, T2, T3> {
        private Tuple3<T1, T2, T3> v;

        public ParameterizedTuple3Pojo() {
        }

        public ParameterizedTuple3Pojo(Tuple3<T1, T2, T3> v) {
            this.v = v;
        }

        public Tuple3<T1, T2, T3> getValue() {
            return v;
        }

        public ParameterizedTuple3Pojo setValue(Tuple3<T1, T2, T3> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTuple4Pojo<T1, T2, T3, T4> {
        private Tuple4<T1, T2, T3, T4> v;

        public ParameterizedTuple4Pojo() {
        }

        public ParameterizedTuple4Pojo(Tuple4<T1, T2, T3, T4> v) {
            this.v = v;
        }

        public Tuple4<T1, T2, T3, T4> getValue() {
            return v;
        }

        public ParameterizedTuple4Pojo setValue(Tuple4<T1, T2, T3, T4> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTuple5Pojo<T1, T2, T3, T4, T5> {
        private Tuple5<T1, T2, T3, T4, T5> v;

        public ParameterizedTuple5Pojo() {
        }

        public ParameterizedTuple5Pojo(Tuple5<T1, T2, T3, T4, T5> v) {
            this.v = v;
        }

        public Tuple5<T1, T2, T3, T4, T5> getValue() {
            return v;
        }

        public ParameterizedTuple5Pojo setValue(Tuple5<T1, T2, T3, T4, T5> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTuple6Pojo<T1, T2, T3, T4, T5, T6> {
        private Tuple6<T1, T2, T3, T4, T5, T6> v;

        public ParameterizedTuple6Pojo() {
        }

        public ParameterizedTuple6Pojo(Tuple6<T1, T2, T3, T4, T5, T6> v) {
            this.v = v;
        }

        public Tuple6<T1, T2, T3, T4, T5, T6> getValue() {
            return v;
        }

        public ParameterizedTuple6Pojo setValue(Tuple6<T1, T2, T3, T4, T5, T6> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTuple7Pojo<T1, T2, T3, T4, T5, T6, T7> {
        private Tuple7<T1, T2, T3, T4, T5, T6, T7> v;

        public ParameterizedTuple7Pojo() {
        }

        public ParameterizedTuple7Pojo(Tuple7<T1, T2, T3, T4, T5, T6, T7> v) {
            this.v = v;
        }

        public Tuple7<T1, T2, T3, T4, T5, T6, T7> getValue() {
            return v;
        }

        public ParameterizedTuple7Pojo setValue(Tuple7<T1, T2, T3, T4, T5, T6, T7> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedTuple8Pojo<T1, T2, T3, T4, T5, T6, T7, T8> {
        private Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> v;

        public ParameterizedTuple8Pojo() {
        }

        public ParameterizedTuple8Pojo(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> v) {
            this.v = v;
        }

        public Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> getValue() {
            return v;
        }

        public ParameterizedTuple8Pojo setValue(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> v) {
            this.v = v;
            return this;
        }
    }

    public static class ParameterizedArrayPojo<T1> {
        private Array<T1> v;

        public ParameterizedArrayPojo() {
        }

        public ParameterizedArrayPojo(Array<T1> v) {
            this.v = v;
        }

        public Array<T1> getValue() {
            return v;
        }

        public ParameterizedArrayPojo setValue(Array<T1> v) {
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

    public static class ParameterizedQueuePojo<T1> {
        private Queue<T1> v;

        public ParameterizedQueuePojo() {
        }

        public ParameterizedQueuePojo(Queue<T1> v) {
            this.v = v;
        }

        public Queue<T1> getValue() {
            return v;
        }

        public ParameterizedQueuePojo setValue(Queue<T1> v) {
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

    public static class ParameterizedVectorPojo<T1> {
        private Vector<T1> v;

        public ParameterizedVectorPojo() {
        }

        public ParameterizedVectorPojo(Vector<T1> v) {
            this.v = v;
        }

        public Vector<T1> getValue() {
            return v;
        }

        public ParameterizedVectorPojo setValue(Vector<T1> v) {
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
