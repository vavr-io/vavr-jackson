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
 * Test code generator for Vavr Jackson serialization and deserialization tests.
 * <p>
 * This class generates comprehensive JUnit test classes that validate Jackson's ability to
 * serialize and deserialize various Vavr data types. The generated tests are written to
 * {@code src/test/java/io/vavr/jackson/generated/} and ensure that the vavr-jackson module
 * correctly handles all supported Vavr types.
 * </p>
 *
 * <h2>Generated Test Types</h2>
 * <ul>
 *   <li><b>SimplePojoTest</b> - Tests simple POJOs containing Vavr types as fields</li>
 *   <li><b>ParameterizedPojoTest</b> - Tests generic POJOs with parameterized Vavr types</li>
 *   <li><b>PolymorphicPojoTest</b> - Tests POJOs with polymorphic types using Jackson annotations</li>
 *   <li><b>ExtFieldsPojoTest</b> - Tests POJOs with extended fields and inheritance hierarchies</li>
 * </ul>
 *
 * <h2>Tested Vavr Types</h2>
 * <p>The generator creates test cases for the following categories of Vavr types:</p>
 * <ul>
 *   <li><b>Tuples</b> - {@code Tuple1} through {@code Tuple8} with String and nested Tuple elements</li>
 *   <li><b>Sequential Collections</b> - {@code Array}, {@code List}, {@code Queue}, {@code Stream}, {@code Vector}</li>
 *   <li><b>Sets</b> - {@code HashSet}, {@code LinkedHashSet}, {@code TreeSet}, {@code PriorityQueue}</li>
 *   <li><b>Maps</b> - {@code HashMap}, {@code LinkedHashMap}, {@code TreeMap}</li>
 *   <li><b>Multimaps</b> - {@code HashMultimap}, {@code LinkedHashMultimap}, {@code TreeMultimap}</li>
 *   <li><b>Control Types</b> - {@code Option}, {@code Lazy}, {@code Either}</li>
 * </ul>
 *
 * <h2>Code Generation Process</h2>
 * <p>The generator uses <a href="https://github.com/square/javapoet">JavaPoet</a> to programmatically
 * create Java source files. Each test case:</p>
 * <ol>
 *   <li>Creates a POJO class with a field of the Vavr type being tested</li>
 *   <li>Initializes test data with appropriate values</li>
 *   <li>Serializes the POJO to JSON using Jackson</li>
 *   <li>Verifies the JSON matches the expected format</li>
 *   <li>Deserializes the JSON back to a POJO</li>
 *   <li>Asserts that the deserialized value equals the original</li>
 * </ol>
 *
 * <h2>Usage</h2>
 * <p>Run this class as a standalone Java application to regenerate all test classes:</p>
 * <pre>
 * java generator.Generator
 * </pre>
 * <p>Or execute it through Maven:</p>
 * <pre>
 * mvn exec:java -Dexec.mainClass="generator.Generator"
 * </pre>
 *
 * <h2>Output Location</h2>
 * <p>Generated test files are written to:</p>
 * <pre>
 * src/test/java/io/vavr/jackson/generated/
 * </pre>
 *
 * @author Ruslan Sennov
 * @see SimplePojo
 * @see ParameterizedPojo
 * @see PolymorphicPojo
 * @see ExtFieldsPojo
 */
public class Generator {

    /**
     * Generates all test classes for Vavr Jackson serialization and deserialization.
     * <p>
     * This method creates a comprehensive set of test cases covering various Vavr data types
     * and their combinations. The generated tests validate that the vavr-jackson module
     * correctly handles serialization to JSON and deserialization back to Java objects.
     * </p>
     *
     * <p>The test data includes:</p>
     * <ul>
     *   <li><b>Tuples</b> - Various tuple arities (1-8) containing Strings and nested Tuples</li>
     *   <li><b>Sequential Collections</b> - Array, List, Queue, Stream, Vector with String and Tuple elements</li>
     *   <li><b>Sets</b> - HashSet, LinkedHashSet, TreeSet with String and Tuple elements</li>
     *   <li><b>Maps</b> - HashMap, LinkedHashMap, TreeMap with Integer keys and String/Tuple values</li>
     *   <li><b>Multimaps</b> - HashMultimap, LinkedHashMultimap, TreeMultimap with String keys and values</li>
     *   <li><b>Control Types</b> - Option, Lazy, Either with String and Tuple contents</li>
     * </ul>
     *
     * <p>Each test case is named descriptively (e.g., "Tuple2OfString", "ArrayOfTuple",
     * "OptionOfString") and includes assertions for both serialization and deserialization
     * correctness.</p>
     *
     * @param args command line arguments (not used)
     * @throws IOException if an error occurs while writing the generated test files to disk
     */
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
