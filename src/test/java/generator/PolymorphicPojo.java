package generator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.squareup.javapoet.*;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import static generator.utils.Initializer.initMapper;
import static generator.utils.PoetHelpers.simplePojo;
import static generator.utils.Serializer.expectedJson;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class PolymorphicPojo {

    public static void main(String[] args) throws IOException {
        generate();
    }

    static void generate() throws IOException {

        TypeSpec.Builder pojoTest = TypeSpec.classBuilder("PolymorphicPojoTest")
                .addJavadoc("generated\n")
                .addModifiers(Modifier.PUBLIC);
        initMapper(pojoTest, "MAPPER");

        pojoTest.addType(TypeSpec.interfaceBuilder("I")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(JsonTypeInfo.class)
                        .addMember("use", "$T.NAME", JsonTypeInfo.Id.class)
                        .addMember("include", "$T.PROPERTY", JsonTypeInfo.As.class)
                        .addMember("property", "$S", "type")
                        .build())
                .addAnnotation(AnnotationSpec.builder(JsonSubTypes.class)
                        .addMember("value", "$L", AnnotationSpec.builder(JsonSubTypes.Type.class)
                                .addMember("value", "$T.class", ClassName.get("", "A"))
                                .addMember("name", "$S", "a")
                                .build())
                        .addMember("value", "$L", AnnotationSpec.builder(JsonSubTypes.Type.class)
                                .addMember("value", "$T.class", ClassName.get("", "B"))
                                .addMember("name", "$S", "b")
                                .build())
                        .build())
                .build());

        pojoTest.addType(TypeSpec.classBuilder("A")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .addSuperinterface(ClassName.get("", "I"))
                .build());

        pojoTest.addType(TypeSpec.classBuilder("B")
                .addModifiers(Modifier.STATIC)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ClassName.get("", "I"))
                .build());

        addSeqCase(pojoTest, List.class);
        addMapCase(pojoTest, HashMap.class);
        addMapCase(pojoTest, LinkedHashMap.class);
        addMapCase(pojoTest, TreeMap.class);
        addMultimapCase(pojoTest, HashMultimap.class);
        addTupleCase(pojoTest, Tuple1.class, 1);
        addTupleCase(pojoTest, Tuple2.class, 2);

        ParameterizedTypeName lazy = ParameterizedTypeName.get(ClassName.get(Lazy.class), ClassName.get("", "I"));
        addCase(pojoTest, lazy, HashMap.of("type", "a"),
                m -> m.addStatement("$T src = $T.of(A::new)", lazy, lazy.rawType),
                m -> m.addStatement("$T.assertTrue(restored.get() instanceof A)", ClassName.get(Assert.class)));

        ParameterizedTypeName option = ParameterizedTypeName.get(ClassName.get(Option.class), ClassName.get("", "I"));
        addCase(pojoTest, option, HashMap.of("type", "a"),
                m -> m.addStatement("$T src = $T.some(new A())", option, option.rawType),
                m -> m.addStatement("$T.assertTrue(restored.get() instanceof A)", ClassName.get(Assert.class)));

        ParameterizedTypeName either = ParameterizedTypeName.get(ClassName.get(Either.class), ClassName.get("", "I"), ClassName.get("", "I"));
        addCase(pojoTest, "Left", true, either, List.of("left", HashMap.of("type", "a")),
                m -> m.addStatement("$T src = $T.left(new A())", either, either.rawType),
                m -> m.addStatement("$T.assertTrue(restored.isLeft())", ClassName.get(Assert.class))
                        .addStatement("$T.assertTrue(restored.getLeft() instanceof A)", ClassName.get(Assert.class)));
        addCase(pojoTest, "Right", false, either, List.of("right", HashMap.of("type", "a")),
                m -> m.addStatement("$T src = $T.right(new A())", either, either.rawType),
                m -> m.addStatement("$T.assertTrue(restored.isRight())", ClassName.get(Assert.class))
                        .addStatement("$T.assertTrue(restored.get() instanceof A)", ClassName.get(Assert.class)));

        JavaFile javaFile = JavaFile.builder("io.vavr.jackson.generated", pojoTest.build())
                .indent("    ")
                .build();

        javaFile.writeTo(new File("src/test/java"));
    }

    private static void addTupleCase(TypeSpec.Builder builder, Class<?> clz, int arity) {
        TypeName[] types = new TypeName[arity];
        StringBuilder argsStr = new StringBuilder();
        List<Map<String, String>> args = List.empty();
        for (int i = 1; i <= arity; i++) {
            types[i-1] = ClassName.get("", "I");
            if (i > 1) {
                argsStr.append(", ");
            }
            argsStr.append((i % 2) != 0 ? "new A()" : "new B()");
            args = args.append((i % 2) != 0 ? HashMap.of("type", "a") : HashMap.of("type", "b"));
        }
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), types);
        addCase(builder, ptn, args,
                m -> m.addStatement("$T src = $T.of($L)", ptn, ClassName.get(Tuple.class), argsStr.toString()),
                m -> {
                    for (int i = 0; i < arity; i++) {
                        m.addStatement("$T.assertTrue(restored._$L instanceof $L)", ClassName.get(Assert.class), i + 1, (i % 2) == 0 ? "A" : "B");
                    }
                });
    }

    private static void addSeqCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get("", "I"));
        addCase(builder, ptn, List.of(HashMap.of("type", "a"), HashMap.of("type", "b")),
                m -> m.addStatement("$T src = $T.of(new A(), new B())", ptn, ptn.rawType),
                m -> m.addStatement("$T.assertTrue(restored.get(0) instanceof A)", ClassName.get(Assert.class))
                        .addStatement("$T.assertTrue(restored.get(1) instanceof B)", ClassName.get(Assert.class)));
    }

    private static void addMapCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get(String.class), ClassName.get("", "I"));
        addCase(builder, ptn, HashMap.of("a", HashMap.of("type", "a"), "b", HashMap.of("type", "b")),
                m -> m.addStatement("$T src = $T.of($S, new A(), $S, new B())", ptn, ptn.rawType, "a", "b"),
                m -> m.addStatement("$T.assertTrue(restored.get($S).get() instanceof A)", ClassName.get(Assert.class), "a")
                        .addStatement("$T.assertTrue(restored.get($S).get() instanceof B)", ClassName.get(Assert.class), "b"));
    }

    private static void addMultimapCase(TypeSpec.Builder builder, Class<?> clz) {
        ParameterizedTypeName ptn = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get(String.class), ClassName.get("", "I"));
        addCase(builder, ptn, HashMap.of("a", List.of(HashMap.of("type", "a")), "b", List.of(HashMap.of("type", "b"))),
                m -> m.addStatement("$T src = $T.withSeq().of($S, new A(), $S, new B())", ptn, ptn.rawType, "a", "b"),
                m -> m.addStatement("$T.assertTrue(restored.get($S).get().head() instanceof A)", ClassName.get(Assert.class), "a")
                        .addStatement("$T.assertTrue(restored.get($S).get().head() instanceof B)", ClassName.get(Assert.class), "b"));
    }

    private static void addCase(TypeSpec.Builder builder, ParameterizedTypeName ptn, Object value, Consumer<MethodSpec.Builder> init, Consumer<MethodSpec.Builder> check) {
        addCase(builder, "", true, ptn, value, init, check);
    }

    private static void addCase(TypeSpec.Builder builder, String namePostfix, boolean makePojo, ParameterizedTypeName ptn, Object value, Consumer<MethodSpec.Builder> init, Consumer<MethodSpec.Builder> check) {
        String pojoName = ptn.rawType.simpleName() +"Pojo";
        if (makePojo) {
            builder.addType(simplePojo(pojoName, ptn));
        }
        String json = expectedJson(HashMap.of("value", value));
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("test" + ptn.rawType.simpleName()  + namePostfix)
                .addAnnotation(Test.class)
                .addModifiers(Modifier.PUBLIC)
                .addException(ClassName.get(Exception.class));
        init.accept(methodBuilder);
        methodBuilder
                .addStatement("$T json = MAPPER.writeValueAsString(new $L().setValue(src))", ClassName.get(String.class), pojoName)
                .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assert.class), json)
                .addStatement("$L pojo = MAPPER.readValue(json, $L.class)", pojoName, pojoName)
                .addStatement("$T restored = pojo.getValue()", ptn);
        check.accept(methodBuilder);
        builder.addMethod(methodBuilder.build());
    }

}
