package generator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
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
import io.vavr.collection.Map;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import io.vavr.collection.Vector;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import javax.lang.model.element.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static generator.utils.Initializer.initMapper;
import static generator.utils.PoetHelpers.simplePojo;
import static generator.utils.Serializer.expectedJson;

/**
 * @author Ruslan Sennov</a>
 */
public class ExtFieldsPojo {

    public static void main(String[] args) throws IOException {
        generate();
    }

    static void generate() throws IOException {

        TypeSpec.Builder pojoTest = TypeSpec.classBuilder("ExtFieldsPojoTest")
            .addJavadoc("generated\n")
            .addModifiers(Modifier.PUBLIC);
        initMapper(pojoTest, "MAPPER");

        pojoTest.addType(TypeSpec.classBuilder("A")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT, Modifier.STATIC)
            .addSuperinterface(ParameterizedTypeName.get(ClassName.get(Comparable.class), ClassName.get("", "A")))
            .addMethod(MethodSpec.methodBuilder("compareTo")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(ClassName.get("", "A"), "o").build())
                .returns(ClassName.INT)
                .addStatement("return Integer.compare(hashCode(), o.hashCode())")
                .build())
            .addAnnotation(AnnotationSpec.builder(JsonTypeInfo.class)
                .addMember("use", "$T.NAME", JsonTypeInfo.Id.class)
                .addMember("include", "$T.WRAPPER_OBJECT", JsonTypeInfo.As.class)
                .build())
            .addAnnotation(AnnotationSpec.builder(JsonSubTypes.class)
                .addMember("value", "$L", AnnotationSpec.builder(JsonSubTypes.Type.class)
                    .addMember("value", "$T.class", ClassName.get("", "B"))
                    .build())
                .build())
            .addField(FieldSpec.builder(ClassName.get(String.class), "a", Modifier.PUBLIC).build())
            .build());

        pojoTest.addType(TypeSpec.classBuilder("B")
            .addModifiers(Modifier.PUBLIC)
            .addModifiers(Modifier.STATIC)
            .superclass(ClassName.get("", "A"))
            .addField(FieldSpec.builder(ClassName.get(String.class), "b", Modifier.PUBLIC).build())
            .addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build())
            .addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(ClassName.get(String.class), "a").build())
                .addParameter(ParameterSpec.builder(ClassName.get(String.class), "b").build())
                .addStatement("this.a = a")
                .addStatement("this.b = b")
                .build())
            .build());

        addSeqCase(pojoTest, Array.class);
        addSeqCase(pojoTest, List.class);
        addSeqCase(pojoTest, Queue.class);
        addSeqCase(pojoTest, Stream.class);
        addSeqCase(pojoTest, Vector.class);

        addSetCase(pojoTest, HashSet.class);
        addSetCase(pojoTest, LinkedHashSet.class);
        addSetCase(pojoTest, TreeSet.class);
        addSetCase(pojoTest, PriorityQueue.class);

        addMapCase(pojoTest, HashMap.class);
        addMapCase(pojoTest, LinkedHashMap.class);
        addMapCase(pojoTest, TreeMap.class);

        addMultimapCase(pojoTest, HashMultimap.class);
        addMultimapCase(pojoTest, LinkedHashMultimap.class);
        addMultimapCase(pojoTest, TreeMultimap.class);

        addTupleCase(pojoTest, Tuple1.class, 1);
        addTupleCase(pojoTest, Tuple2.class, 2);
        addTupleCase(pojoTest, Tuple3.class, 3);
        addTupleCase(pojoTest, Tuple4.class, 4);
        addTupleCase(pojoTest, Tuple5.class, 5);
        addTupleCase(pojoTest, Tuple6.class, 6);
        addTupleCase(pojoTest, Tuple7.class, 7);
        addTupleCase(pojoTest, Tuple8.class, 8);

        ParameterizedTypeName lazy = ParameterizedTypeName.get(ClassName.get(Lazy.class), ClassName.get("", "A"));
        addCase(pojoTest, lazy, HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b")),
            m -> m.addStatement("$T src = $T.of(() -> new B($S, $S))", lazy, lazy.rawType, "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.get() instanceof B)", ClassName.get(Assertions.class))
                .addStatement("$T.assertEquals(restored.get().a, $S)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(((B) restored.get()).b, $S)", ClassName.get(Assertions.class), "b"));

        ParameterizedTypeName option = ParameterizedTypeName.get(ClassName.get(Option.class), ClassName.get("", "A"));
        addCase(pojoTest, option, HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b")),
            m -> m.addStatement("$T src = $T.some(new B($S, $S))", option, option.rawType, "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.get() instanceof B)", ClassName.get(Assertions.class))
                .addStatement("$T.assertEquals(restored.get().a, $S)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(((B) restored.get()).b, $S)", ClassName.get(Assertions.class), "b"));

        ParameterizedTypeName either = ParameterizedTypeName.get(ClassName.get(Either.class), ClassName.get("", "A"), ClassName.get("", "A"));
        addCase(pojoTest, "Left", true, either, List.of("left", HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b"))),
            m -> m.addStatement("$T src = $T.left(new B($S, $S))", either, either.rawType, "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.isLeft())", ClassName.get(Assertions.class))
                .addStatement("$T.assertTrue(restored.getLeft() instanceof B)", ClassName.get(Assertions.class))
                .addStatement("$T.assertEquals(restored.getLeft().a, $S)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(((B) restored.getLeft()).b, $S)", ClassName.get(Assertions.class), "b"));
        addCase(pojoTest, "Right", false, either, List.of("right", HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b"))),
            m -> m.addStatement("$T src = $T.right(new B($S, $S))", either, either.rawType, "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.isRight())", ClassName.get(Assertions.class))
                .addStatement("$T.assertTrue(restored.get() instanceof B)", ClassName.get(Assertions.class))
                .addStatement("$T.assertEquals(restored.get().a, $S)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(((B) restored.get()).b, $S)", ClassName.get(Assertions.class), "b"));

        JavaFile javaFile = JavaFile.builder("io.vavr.jackson.generated", pojoTest.build())
            .indent("    ")
            .skipJavaLangImports(true)
            .build();

        javaFile.writeTo(new File("src/test/java"));
    }

    private static void addTupleCase(TypeSpec.Builder builder, Class<?> clz, int arity) {
        TypeName[] types = new TypeName[arity];
        StringBuilder argsStr = new StringBuilder();
        List<Map<String, Map<String, String>>> args = List.empty();
        for (int i = 1; i <= arity; i++) {
            types[i - 1] = ClassName.get("", "A");
            if (i > 1) {
                argsStr.append(", ");
            }
            argsStr.append("new B(\"a\", \"b\")");
            args = args.append(HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b")));
        }
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), types);
        addCase(builder, ptn, args,
            m -> m.addStatement("$T src = $T.of($L)", ptn, ClassName.get(Tuple.class), argsStr.toString()),
            m -> {
                for (int i = 0; i < arity; i++) {
                    m.addStatement("$T.assertTrue(restored._$L instanceof $L)", ClassName.get(Assertions.class), i + 1, "B")
                        .addStatement("$T.assertEquals(restored._$L.a, $S)", ClassName.get(Assertions.class), i + 1, "a")
                        .addStatement("$T.assertEquals(((B) restored._$L).b, $S)", ClassName.get(Assertions.class), i + 1, "b");
                }
            });
    }

    private static void addSeqCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get("", "A"));
        addCase(builder, ptn, List.of(HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b"))),
            m -> m.addStatement("$T src = $T.of(new B($S, $S))", ptn, ptn.rawType, "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.get(0) instanceof B)", ClassName.get(Assertions.class))
                .addStatement("$T.assertEquals(restored.get(0).a, $S)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(((B) restored.get(0)).b, $S)", ClassName.get(Assertions.class), "b"));
    }

    private static void addSetCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get("", "A"));
        addCase(builder, ptn, List.of(HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b"))),
            m -> m.addStatement("$T src = $T.of(new B($S, $S))", ptn, ptn.rawType, "a", "b"),
            m -> m.addStatement("$T.assertEquals(restored.filter(e -> e instanceof B).length(), 1)", ClassName.get(Assertions.class))
                .addStatement("$T.assertEquals(restored.head().a, $S)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(((B) restored.head()).b, $S)", ClassName.get(Assertions.class), "b"));
    }

    private static void addMapCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get(String.class), ClassName.get("", "A"));
        addCase(builder, ptn, HashMap.of("a", HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b"))),
            m -> m.addStatement("$T src = $T.of($S, new B($S, $S))", ptn, ptn.rawType, "a", "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.get($S).get() instanceof B)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(restored.get($S).get().a, $S)", ClassName.get(Assertions.class), "a", "a")
                .addStatement("$T.assertEquals(((B) restored.get($S).get()).b, $S)", ClassName.get(Assertions.class), "a", "b"));
    }

    private static void addMultimapCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get(String.class), ClassName.get("", "A"));
        addCase(builder, ptn, HashMap.of("a", List.of(HashMap.of("ExtFieldsPojoTest$B", HashMap.of("a", "a", "b", "b")))),
            m -> m.addStatement("$T src = $T.withSeq().of($S, new B($S, $S))", ptn, ptn.rawType, "a", "a", "b"),
            m -> m.addStatement("$T.assertTrue(restored.get($S).get().head() instanceof B)", ClassName.get(Assertions.class), "a")
                .addStatement("$T.assertEquals(restored.get($S).get().head().a, $S)", ClassName.get(Assertions.class), "a", "a")
                .addStatement("$T.assertEquals(((B) restored.get($S).get().head()).b, $S)", ClassName.get(Assertions.class), "a", "b"));
    }

    private static void addCase(TypeSpec.Builder builder, ParameterizedTypeName ptn, Object value, Consumer<MethodSpec.Builder> init, Consumer<MethodSpec.Builder> check) {
        addCase(builder, "", true, ptn, value, init, check);
    }

    private static void addCase(TypeSpec.Builder builder, String namePostfix, boolean makePojo, ParameterizedTypeName ptn, Object value, Consumer<MethodSpec.Builder> init, Consumer<MethodSpec.Builder> check) {
        String pojoName = ptn.rawType.simpleName() + "Pojo";
        if (makePojo) {
            builder.addType(simplePojo(pojoName, ptn));
        }
        String json = expectedJson(HashMap.of("value", value));
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("test" + ptn.rawType.simpleName() + namePostfix)
            .addAnnotation(Test.class)
            .addException(ClassName.get(Exception.class));
        init.accept(methodBuilder);
        methodBuilder
            .addStatement("$T json = MAPPER.writeValueAsString(new $L().setValue(src))", ClassName.get(String.class), pojoName)
            .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assertions.class), json)
            .addStatement("$L pojo = MAPPER.readValue(json, $L.class)", pojoName, pojoName)
            .addStatement("$T restored = pojo.getValue()", ptn);
        check.accept(methodBuilder);
        builder.addMethod(methodBuilder.build());
    }
}
