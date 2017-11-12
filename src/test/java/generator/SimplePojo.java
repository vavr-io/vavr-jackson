package generator;

import com.squareup.javapoet.*;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static generator.utils.Initializer.initMapper;
import static generator.utils.Initializer.initValue;
import static generator.utils.Serializer.expectedJson;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class SimplePojo {

    public static void main(String[] args) throws IOException {

        TypeSpec.Builder pojoTest = TypeSpec.classBuilder("SimplePojoTest")
                .addJavadoc("generated\n")
                .addModifiers(Modifier.PUBLIC);
        initMapper(pojoTest, "MAPPER");

        addCase(pojoTest, "TupleOfString", Tuple.of("A", "B"));
        addCase(pojoTest, "TupleOfTuple", Tuple.of("A", Tuple.of("B", "C")));
        addCase(pojoTest, "ListOfString", List.of("A", "B", "C"));
        addCase(pojoTest, "ListOfTuple", List.of(Tuple.of("A", "B")));

        addCase(pojoTest, "HashSetOfString", HashSet.of("A", "B", "C"));
        addCase(pojoTest, "HashSetOfTuple", HashSet.of(Tuple.of("A", "B")));
        addCase(pojoTest, "LinkedHashSetOfString", LinkedHashSet.of("A", "B", "C"));
        addCase(pojoTest, "LinkedHashSetOfTuple", LinkedHashSet.of(Tuple.of("A", "B")));
        addCase(pojoTest, "TreeSetOfString", TreeSet.of("A", "B", "C"));
        addCase(pojoTest, "TreeSetOfTuple", TreeSet.of(Tuple.of("A", "B")));

        addCase(pojoTest, "HashMapOfString", HashMap.of(1, "A"));
        addCase(pojoTest, "HashMapOfTuple", HashMap.of(1, Tuple.of("A", "B")));
        addCase(pojoTest, "LinkedHashMapOfString", LinkedHashMap.of(1, "A"));
        addCase(pojoTest, "LinkedHashMapOfTuple", LinkedHashMap.of(1, Tuple.of("A", "B")));
        addCase(pojoTest, "TreeMapOfString", TreeMap.of(1, "A"));
        addCase(pojoTest, "TreeMapOfTuple", TreeMap.of(1, Tuple.of("A", "B")));

        addCase(pojoTest, "HashMultimapOfSeqString", HashMultimap.withSeq().of("A", "B", "A", "C"));
        addCase(pojoTest, "HashMultimapOfSeqTuple", HashMultimap.withSeq().of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));
        addCase(pojoTest, "LinkedHashMultimapOfSeqString", LinkedHashMultimap.withSeq().of("A", "B", "A", "C"));
        addCase(pojoTest, "LinkedHashMultimapOfSeqTuple", LinkedHashMultimap.withSeq().of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));
        addCase(pojoTest, "TreeMultimapOfSeqString", TreeMultimap.withSet().of("A", "B", "A", "C"));
        addCase(pojoTest, "TreeMultimapOfSeqTuple", TreeMultimap.withSet().of("A", Tuple.of("A", "B"), "A", Tuple.of("C", "D")));

        addCase(pojoTest, "OptionOfString", Option.of("A"));
        addCase(pojoTest, "OptionOfTuple", Option.of(Tuple.of("A", "B")));
        addCase(pojoTest, "LazyOfString", Lazy.of(() -> "A"));
        addCase(pojoTest, "LazyOfTuple", Lazy.of(() -> Tuple.of("A", "B")));

        addCase(pojoTest, "LeftEitherOfString", Either.left("A"));
        addCase(pojoTest, "LeftEitherOfTuple", Either.left(Tuple.of("A", "B")));
        addCase(pojoTest, "RightEitherOfString", Either.right("A"));
        addCase(pojoTest, "RightEitherOfTuple", Either.right(Tuple.of("A", "B")));

        JavaFile javaFile = JavaFile.builder("io.vavr.jackson.generated", pojoTest.build())
                .indent("    ")
                .build();

        javaFile.writeTo(new File("src/test/java"));
    }

    private static void addCase(TypeSpec.Builder builder, String pojoName, Object value) {
        addCase(builder, pojoName, value, 0);
    }

    private static void addCase(TypeSpec.Builder builder, String pojoName, Object value, int opts) {

        MethodSpec.Builder testBuilder = MethodSpec.methodBuilder("test" + pojoName)
                .addAnnotation(Test.class)
                .addModifiers(Modifier.PUBLIC)
                .addException(ClassName.get(Exception.class));
        TypeName valueTypeName = initValue(testBuilder, "src", value);
        MethodSpec testSpec = testBuilder
                .addStatement("$T json = MAPPER.writeValueAsString(new $L().setValue(src))", ClassName.get(String.class), pojoName)
                .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assert.class), "{\"value\":"+ expectedJson(value, opts) + "}")
                .addStatement("$L restored = MAPPER.readValue(json, $L.class)", pojoName, pojoName)
                .addStatement("$T.assertEquals(src, restored.getValue())", ClassName.get(Assert.class))
                .build();
        builder.addMethod(testSpec);

        TypeSpec pojoSpec = TypeSpec.classBuilder(pojoName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addField(FieldSpec.builder(valueTypeName, "v", Modifier.PRIVATE).build())
                .addMethod(MethodSpec.methodBuilder("getValue")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(valueTypeName)
                        .addStatement("return v")
                        .build())
                .addMethod(MethodSpec.methodBuilder("setValue")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(ClassName.get("", pojoName))
                        .addParameter(ParameterSpec.builder(valueTypeName, "v").build())
                        .addStatement("this.v = v")
                        .addStatement("return this")
                        .build())
                .build();

        builder.addType(pojoSpec);
    }
}
