package generator;

import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.io.IOException;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class Generator {

    public static void main(String[] args) throws IOException {
        java.util.Map<String, Object> cases = new java.util.LinkedHashMap<>();

        cases.put("TupleOfString", Tuple.of("A", "B"));
        cases.put("TupleOfTuple", Tuple.of("A", Tuple.of("B", "C")));

        cases.put("ListOfString", List.of("A", "B", "C"));
        cases.put("ListOfTuple", List.of(Tuple.of("A", "B")));
        cases.put("StreamOfString", Stream.of("A", "B", "C"));
        cases.put("StreamOfTuple", Stream.of(Tuple.of("A", "B")));
        cases.put("PriorityQueueOfString", PriorityQueue.of("A", "B", "C"));
        cases.put("PriorityQueueOfTuple", PriorityQueue.of(Tuple.of("A", "B")));

        cases.put("HashSetOfString", HashSet.of("A", "B", "C"));
        cases.put("HashSetOfTuple", HashSet.of(Tuple.of("A", "B")));
        cases.put("LinkedHashSetOfString", LinkedHashSet.of("A", "B", "C"));
        cases.put("LinkedHashSetOfTuple", LinkedHashSet.of(Tuple.of("A", "B")));
        cases.put("TreeSetOfString", TreeSet.of("A", "B", "C"));
        cases.put("TreeSetOfTuple", TreeSet.of(Tuple.of("A", "B")));

        cases.put("HashMapOfString", HashMap.of(1, "A"));
        cases.put("HashMapOfTuple", HashMap.of(1, Tuple.of("A", "B")));
        cases.put("LinkedHashMapOfString", LinkedHashMap.of(1, "A"));
        cases.put("LinkedHashMapOfTuple", LinkedHashMap.of(1, Tuple.of("A", "B")));
        cases.put("TreeMapOfString", TreeMap.of(1, "A"));
        cases.put("TreeMapOfTuple", TreeMap.of(1, Tuple.of("A", "B")));

        cases.put("HashMultimapOfSeqString", HashMultimap.withSeq().of("A", "B", "A", "C"));
        cases.put("HashMultimapOfSeqTuple", HashMultimap.withSeq().of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));
        cases.put("LinkedHashMultimapOfSeqString", LinkedHashMultimap.withSeq().of("A", "B", "A", "C"));
        cases.put("LinkedHashMultimapOfSeqTuple", LinkedHashMultimap.withSeq().of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));
        cases.put("TreeMultimapOfSeqString", TreeMultimap.withSet().of("A", "B", "A", "C"));
        cases.put("TreeMultimapOfSeqTuple", TreeMultimap.withSet().of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));

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
    }
}
