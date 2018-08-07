package generator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.squareup.javapoet.*;
import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static generator.utils.Initializer.initMapper;
import static generator.utils.Initializer.initValue;
import static generator.utils.Initializer.publicVavrClass;
import static generator.utils.Serializer.expectedJson;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class ParameterizedPojo {

    public static void main(String[] args) throws IOException {
        java.util.Map<String, Object> cases = new java.util.HashMap<>();
        cases.put("TupleOfString", Tuple.of("A", "B"));
        cases.put("TupleOfTuple", Tuple.of("A", Tuple.of("A", "B")));
        generate(cases);
    }

    static void generate(java.util.Map<String, Object> cases) throws IOException {

        TypeSpec.Builder pojoTest = TypeSpec.classBuilder("ParameterizedPojoTest")
                .addJavadoc("generated\n")
                .addModifiers(Modifier.PUBLIC);
        initMapper(pojoTest, "MAPPER");

        cases.forEach((k, v) -> addCase(pojoTest, k, v));

        JavaFile javaFile = JavaFile.builder("io.vavr.jackson.generated", pojoTest.build())
                .indent("    ")
                .build();

        javaFile.writeTo(new File("src/test/java"));
    }

    private static final java.util.Set<Class> generated = new java.util.HashSet<>();

    private static void addCase(TypeSpec.Builder builder, String pojoName, Object value) {
        Class<?> clz = publicVavrClass(value.getClass());
        if (!generated.contains(clz)) {
            int arity;
            if (clz == Tuple0.class) {
                arity = 0;
            } else if (clz == Tuple1.class) {
                arity = 1;
            } else if (clz == Tuple2.class) {
                arity = 2;
            } else if (clz == Tuple3.class) {
                arity = 3;
            } else if (clz == Tuple4.class) {
                arity = 4;
            } else if (clz == Tuple5.class) {
                arity = 5;
            } else if (clz == Tuple6.class) {
                arity = 6;
            } else if (clz == Tuple7.class) {
                arity = 7;
            } else if (clz == Tuple8.class) {
                arity = 8;
            } else if (Map.class.isAssignableFrom(clz) || Multimap.class.isAssignableFrom(clz) || Either.class.isAssignableFrom(clz)) {
                arity = 2;
            } else {
                arity = 1;
            }
            addPojo(builder, clz, arity);
            generated.add(clz);
        }
        addCase(builder, pojoName, value, clz.getSimpleName(), 0);
    }

    private static void addCase(TypeSpec.Builder builder, String pojoName, Object value, String clz, int opts) {

        MethodSpec.Builder testBuilder = MethodSpec.methodBuilder("test" + pojoName)
                .addAnnotation(Test.class)
                .addModifiers(Modifier.PUBLIC)
                .addException(ClassName.get(Exception.class));
        TypeName valueTypeName = initValue(testBuilder, "src", value);
        String genericts = ((ParameterizedTypeName) valueTypeName).typeArguments.stream().map(TypeName::toString).collect(Collectors.joining(", "));
        MethodSpec testSpec = testBuilder
                .addStatement("$T json = MAPPER.writeValueAsString(new Parameterized$LPojo<>(src))", ClassName.get(String.class), clz)
                .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assert.class), "{\"value\":" + expectedJson(value, opts) + "}")
                .addStatement("Parameterized$LPojo<$L> restored = \n" +
                "MAPPER.readValue(json, new $T<Parameterized$LPojo<$L>>(){})", clz, genericts, ClassName.get(TypeReference.class), clz, genericts)
                .addStatement("$T.assertEquals(src, restored.getValue())", ClassName.get(Assert.class))
                .build();
        builder.addMethod(testSpec);
    }

    private static void addPojo(TypeSpec.Builder builder, Class<?> clz, int arity) {
        String pojoName = "Parameterized" + clz.getSimpleName() + "Pojo";
        TypeSpec.Builder pojoSpec = TypeSpec.classBuilder(pojoName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
        TypeName[] types = new TypeName[arity];
        for (int i = 1; i <= arity; i++) {
            types[i-1] = TypeVariableName.get("T" + i);
            pojoSpec.addTypeVariable(TypeVariableName.get("T" + i));
        }
        TypeName type = ParameterizedTypeName.get(ClassName.get(clz), types);
        pojoSpec
                .addField(FieldSpec.builder(type, "v", Modifier.PRIVATE).build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterSpec.builder(type, "v").build())
                        .addStatement("this.v = v")
                        .build())
                .addMethod(MethodSpec.methodBuilder("getValue")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(type)
                        .addStatement("return v")
                        .build())
                .addMethod(MethodSpec.methodBuilder("setValue")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(ClassName.get("", pojoName))
                        .addParameter(ParameterSpec.builder(type, "v").build())
                        .addStatement("this.v = v")
                        .addStatement("return this")
                        .build())
                .build();

        builder.addType(pojoSpec.build());
    }
}
