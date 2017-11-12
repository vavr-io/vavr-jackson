package io.vavr.jackson.generated;

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
public class SimplePojoTest {
    private static final VavrModule MAPPER_MODULE = new VavrModule();

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(MAPPER_MODULE);

    @Test
    public void testTupleOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        Tuple2<String, String> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(new TupleOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\"]}");
        TupleOfString restored = MAPPER.readValue(json, TupleOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTupleOfTuple() throws Exception {
        String src0 = "A";
        String src10 = "B";
        String src11 = "C";
        Tuple2<String, String> src1 = Tuple.of(src10, src11);
        Tuple2<String, Tuple2<String, String>> src = Tuple.of(src0, src1);
        String json = MAPPER.writeValueAsString(new TupleOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",[\"B\",\"C\"]]}");
        TupleOfTuple restored = MAPPER.readValue(json, TupleOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testListOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        List<String> src = List.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new ListOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        ListOfString restored = MAPPER.readValue(json, ListOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testListOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        List<Tuple2<String, String>> src = List.of(src0);
        String json = MAPPER.writeValueAsString(new ListOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        ListOfTuple restored = MAPPER.readValue(json, ListOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        HashSet<String> src = HashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new HashSetOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        HashSetOfString restored = MAPPER.readValue(json, HashSetOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        HashSet<Tuple2<String, String>> src = HashSet.of(src0);
        String json = MAPPER.writeValueAsString(new HashSetOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        HashSetOfTuple restored = MAPPER.readValue(json, HashSetOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        LinkedHashSet<String> src = LinkedHashSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new LinkedHashSetOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        LinkedHashSetOfString restored = MAPPER.readValue(json, LinkedHashSetOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        LinkedHashSet<Tuple2<String, String>> src = LinkedHashSet.of(src0);
        String json = MAPPER.writeValueAsString(new LinkedHashSetOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        LinkedHashSetOfTuple restored = MAPPER.readValue(json, LinkedHashSetOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeSetOfString() throws Exception {
        String src0 = "A";
        String src1 = "B";
        String src2 = "C";
        TreeSet<String> src = TreeSet.of(src0, src1, src2);
        String json = MAPPER.writeValueAsString(new TreeSetOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\",\"C\"]}");
        TreeSetOfString restored = MAPPER.readValue(json, TreeSetOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeSetOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        TreeSet<Tuple2<String, String>> src = TreeSet.of(src0);
        String json = MAPPER.writeValueAsString(new TreeSetOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[[\"A\",\"B\"]]}");
        TreeSetOfTuple restored = MAPPER.readValue(json, TreeSetOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        HashMap<Integer, String> src = HashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new HashMapOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":\"A\"}}");
        HashMapOfString restored = MAPPER.readValue(json, HashMapOfString.class);
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
        String json = MAPPER.writeValueAsString(new HashMapOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":[\"A\",\"B\"]}}");
        HashMapOfTuple restored = MAPPER.readValue(json, HashMapOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLinkedHashMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        LinkedHashMap<Integer, String> src = LinkedHashMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new LinkedHashMapOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":\"A\"}}");
        LinkedHashMapOfString restored = MAPPER.readValue(json, LinkedHashMapOfString.class);
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
        String json = MAPPER.writeValueAsString(new LinkedHashMapOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":[\"A\",\"B\"]}}");
        LinkedHashMapOfTuple restored = MAPPER.readValue(json, LinkedHashMapOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testTreeMapOfString() throws Exception {
        Integer src00 = 1;
        String src01 = "A";
        Tuple2<Integer, String> src0 = Tuple.of(src00, src01);
        TreeMap<Integer, String> src = TreeMap.ofEntries(src0);
        String json = MAPPER.writeValueAsString(new TreeMapOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":\"A\"}}");
        TreeMapOfString restored = MAPPER.readValue(json, TreeMapOfString.class);
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
        String json = MAPPER.writeValueAsString(new TreeMapOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"1\":[\"A\",\"B\"]}}");
        TreeMapOfTuple restored = MAPPER.readValue(json, TreeMapOfTuple.class);
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
        String json = MAPPER.writeValueAsString(new HashMultimapOfSeqString().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[\"B\",\"C\"]}}");
        HashMultimapOfSeqString restored = MAPPER.readValue(json, HashMultimapOfSeqString.class);
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
        String json = MAPPER.writeValueAsString(new HashMultimapOfSeqTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        HashMultimapOfSeqTuple restored = MAPPER.readValue(json, HashMultimapOfSeqTuple.class);
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
        String json = MAPPER.writeValueAsString(new LinkedHashMultimapOfSeqString().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[\"B\",\"C\"]}}");
        LinkedHashMultimapOfSeqString restored = MAPPER.readValue(json, LinkedHashMultimapOfSeqString.class);
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
        String json = MAPPER.writeValueAsString(new LinkedHashMultimapOfSeqTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        LinkedHashMultimapOfSeqTuple restored = MAPPER.readValue(json, LinkedHashMultimapOfSeqTuple.class);
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
        String json = MAPPER.writeValueAsString(new TreeMultimapOfSeqString().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[\"B\",\"C\"]}}");
        TreeMultimapOfSeqString restored = MAPPER.readValue(json, TreeMultimapOfSeqString.class);
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
        String json = MAPPER.writeValueAsString(new TreeMultimapOfSeqTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":{\"A\":[[\"A\",\"B\"],[\"C\",\"D\"]]}}");
        TreeMultimapOfSeqTuple restored = MAPPER.readValue(json, TreeMultimapOfSeqTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testOptionOfString() throws Exception {
        String src0 = "A";
        Option<String> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(new OptionOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":\"A\"}");
        OptionOfString restored = MAPPER.readValue(json, OptionOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testOptionOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Option<Tuple2<String, String>> src = Option.of(src0);
        String json = MAPPER.writeValueAsString(new OptionOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\"]}");
        OptionOfTuple restored = MAPPER.readValue(json, OptionOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLazyOfString() throws Exception {
        String src0 = "A";
        Lazy<String> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(new LazyOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":\"A\"}");
        LazyOfString restored = MAPPER.readValue(json, LazyOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLazyOfTuple() throws Exception {
        String src00 = "A";
        String src01 = "B";
        Tuple2<String, String> src0 = Tuple.of(src00, src01);
        Lazy<Tuple2<String, String>> src = Lazy.of(() -> src0);
        String json = MAPPER.writeValueAsString(new LazyOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"A\",\"B\"]}");
        LazyOfTuple restored = MAPPER.readValue(json, LazyOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLeftEitherOfString() throws Exception {
        String srcl = "A";
        Either<String, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(new LeftEitherOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"left\",\"A\"]}");
        LeftEitherOfString restored = MAPPER.readValue(json, LeftEitherOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testLeftEitherOfTuple() throws Exception {
        String srcl0 = "A";
        String srcl1 = "B";
        Tuple2<String, String> srcl = Tuple.of(srcl0, srcl1);
        Either<Tuple2<String, String>, Object> src = Either.left(srcl);
        String json = MAPPER.writeValueAsString(new LeftEitherOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"left\",[\"A\",\"B\"]]}");
        LeftEitherOfTuple restored = MAPPER.readValue(json, LeftEitherOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testRightEitherOfString() throws Exception {
        String srcr = "A";
        Either<Object, String> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(new RightEitherOfString().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"right\",\"A\"]}");
        RightEitherOfString restored = MAPPER.readValue(json, RightEitherOfString.class);
        Assert.assertEquals(src, restored.getValue());
    }

    @Test
    public void testRightEitherOfTuple() throws Exception {
        String srcr0 = "A";
        String srcr1 = "B";
        Tuple2<String, String> srcr = Tuple.of(srcr0, srcr1);
        Either<Object, Tuple2<String, String>> src = Either.right(srcr);
        String json = MAPPER.writeValueAsString(new RightEitherOfTuple().setValue(src));
        Assert.assertEquals(json, "{\"value\":[\"right\",[\"A\",\"B\"]]}");
        RightEitherOfTuple restored = MAPPER.readValue(json, RightEitherOfTuple.class);
        Assert.assertEquals(src, restored.getValue());
    }

    public static class TupleOfString {
        private Tuple2<String, String> v;

        public Tuple2<String, String> getValue() {
            return v;
        }

        public TupleOfString setValue(Tuple2<String, String> v) {
            this.v = v;
            return this;
        }
    }

    public static class TupleOfTuple {
        private Tuple2<String, Tuple2<String, String>> v;

        public Tuple2<String, Tuple2<String, String>> getValue() {
            return v;
        }

        public TupleOfTuple setValue(Tuple2<String, Tuple2<String, String>> v) {
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

    public static class TreeSetOfTuple {
        private TreeSet<Tuple2<String, String>> v;

        public TreeSet<Tuple2<String, String>> getValue() {
            return v;
        }

        public TreeSetOfTuple setValue(TreeSet<Tuple2<String, String>> v) {
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
