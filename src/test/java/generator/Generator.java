package generator;

import io.vavr.Lazy;
import io.vavr.Tuple;
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
import java.io.IOException;

/**
 * @author Ruslan Sennov</a>
 */
public class Generator {

    public static void main(String[] args) throws IOException {
        java.util.Map<String, Object> cases = new java.util.LinkedHashMap<>();

        cases.put("Tuple1OfString", Tuple.of("A"));
        cases.put("Tuple1OfTuple", Tuple.of(Tuple.of("B")));
        cases.put("Tuple2OfString", Tuple.of("A", "B"));
        cases.put("Tuple2OfTuple", Tuple.of("A", Tuple.of("B", "C")));
        cases.put("Tuple3OfString", Tuple.of("A", "B", "C"));
        cases.put("Tuple3OfTuple", Tuple.of("A", Tuple.of("B", "C"), Tuple.of("D", "E")));
        cases.put("Tuple4OfString", Tuple.of("A", "B", "C", "D"));
        cases.put("Tuple4OfTuple", Tuple.of("A", Tuple.of("B", "C"), Tuple.of("D", "E"), Tuple.of("F", "1")));
        cases.put("Tuple5OfString", Tuple.of("A", "B", "C", "D", "E"));
        cases.put("Tuple5OfTuple", Tuple.of("A", Tuple.of("B", "C"), Tuple.of("D", "E"), Tuple.of("F", "1"), "A"));
        cases.put("Tuple6OfString", Tuple.of("A", "B", "C", "D", "E", "F"));
        cases.put("Tuple6OfTuple", Tuple.of("A", Tuple.of("B", "C"), Tuple.of("D", "E"), Tuple.of("F", "1"), "A", Tuple.of("B", "C")));
        cases.put("Tuple7OfString", Tuple.of("A", "B", "C", "D", "E", "F", "1"));
        cases.put("Tuple7OfTuple", Tuple.of("A", Tuple.of("B", "C"), Tuple.of("D", "E"), Tuple.of("F", "1"), "A", Tuple.of("B", "C"), Tuple.of("D", "E")));
        cases.put("Tuple8OfString", Tuple.of("A", "B", "C", "D", "E", "F", "1", "2"));
        cases.put("Tuple8OfTuple", Tuple.of("A", Tuple.of("B", "C"), Tuple.of("D", "E"), Tuple.of("F", "1"), "A", Tuple.of("B", "C"), Tuple.of("D", "E"), Tuple.of("F", "1")));

        cases.put("ArrayOfString", Array.of("A", "B", "C"));
        cases.put("ArrayOfTuple", Array.of(Tuple.of("A", "B")));
        cases.put("ListOfString", List.of("A", "B", "C"));
        cases.put("ListOfTuple", List.of(Tuple.of("A", "B")));
        cases.put("QueueOfString", Queue.of("A", "B", "C"));
        cases.put("QueueOfTuple", Queue.of(Tuple.of("A", "B")));
        cases.put("StreamOfString", Stream.of("A", "B", "C"));
        cases.put("StreamOfTuple", Stream.of(Tuple.of("A", "B")));
        cases.put("VectorOfString", Vector.of("A", "B", "C"));
        cases.put("VectorOfTuple", Vector.of(Tuple.of("A", "B")));

        cases.put("PriorityQueueOfString", PriorityQueue.of("A", "B", "C"));
        cases.put("HashSetOfString", HashSet.of("A", "B", "C"));
        cases.put("HashSetOfTuple", HashSet.of(Tuple.of("A", "B")));
        cases.put("LinkedHashSetOfString", LinkedHashSet.of("A", "B", "C"));
        cases.put("LinkedHashSetOfTuple", LinkedHashSet.of(Tuple.of("A", "B")));
        cases.put("TreeSetOfString", TreeSet.of("A", "B", "C"));

        cases.put("HashMapOfString", HashMap.of(1, "A"));
        cases.put("HashMapOfTuple", HashMap.of(1, Tuple.of("A", "B")));
        cases.put("LinkedHashMapOfString", LinkedHashMap.of(1, "A"));
        cases.put("LinkedHashMapOfTuple", LinkedHashMap.of(1, Tuple.of("A", "B")));
        cases.put("TreeMapOfString", TreeMap.of(1, "A"));
        cases.put("TreeMapOfTuple", TreeMap.of(1, Tuple.of("A", "B")));

        cases.put("HashMultimapOfSeqString", HashMultimap.withSeq().of("A", "B", "A", "C"));
        cases.put("HashMultimapOfSeqTuple", HashMultimap.withSeq()
            .of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));
        cases.put("LinkedHashMultimapOfSeqString", LinkedHashMultimap.withSeq().of("A", "B", "A", "C"));
        cases.put("LinkedHashMultimapOfSeqTuple", LinkedHashMultimap.withSeq()
            .of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));
        cases.put("TreeMultimapOfSeqString", TreeMultimap.withSet().of("A", "B", "A", "C"));
        cases.put("TreeMultimapOfSeqTuple", TreeMultimap.withSet()
            .of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));

        cases.put("OptionOfString", Option.of("A"));
        cases.put("OptionOfTuple", Option.of(Tuple.of("A", "B")));
        cases.put("LazyOfString", Lazy.of(() -> "A"));
        cases.put("LazyOfTuple", Lazy.of(() -> Tuple.of("A", "B")));

        cases.put("LeftEitherOfString", Either.left("A"));
        cases.put("LeftEitherOfTuple", Either.left(Tuple.of("A", "B")));
        cases.put("RightEitherOfString", Either.right("A"));
        cases.put("RightEitherOfTuple", Either.right(Tuple.of("A", "B")));

        SimplePojo.generate(cases);
        ParameterizedPojo.generate(cases);
        PolymorphicPojo.generate();
        ExtFieldsPojo.generate();
    }
}
