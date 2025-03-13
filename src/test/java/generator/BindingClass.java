package generator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import com.squareup.javapoet.TypeVariableName;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static generator.utils.Initializer.initMapper;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class BindingClass {

    public static void main(String[] args) throws IOException {
        java.util.Map<Class<?>, Tuple2<String, String>> cases1 = new java.util.HashMap<>();
        cases1.put(List.class, Tuple.of("List.of(new ImplementedClass())", "head()"));
        cases1.put(HashSet.class, Tuple.of("HashSet.of(new ImplementedClass())", "head()"));
        cases1.put(Option.class, Tuple.of("Option.of(new ImplementedClass())", "get()"));
        cases1.put(Lazy.class, Tuple.of("Lazy.of(ImplementedClass::new)", "get()"));
        cases1.put(Tuple1.class, Tuple.of("new Tuple1<>(new ImplementedClass())", "_1"));
        java.util.Map<Class<?>, Tuple2<String, String>> cases2 = new java.util.HashMap<>();
        cases2.put(Either.class, Tuple.of("Either.right(new ImplementedClass())", "get()"));
        cases2.put(HashMap.class, Tuple.of("HashMap.of(42, new ImplementedClass())", "head()._2"));
        cases2.put(HashMultimap.class, Tuple.of("HashMultimap.withSeq().of(42, new ImplementedClass())", "head()._2"));
        cases2.put(Tuple2.class, Tuple.of("new Tuple2<>(42, new ImplementedClass())", "_2"));
        generate(cases1, cases2);
    }

    private static void generate(java.util.Map<Class<?>, Tuple2<String, String>> cases1, java.util.Map<Class<?>, Tuple2<String, String>> cases2) throws IOException {

        TypeSpec.Builder pojoTest = TypeSpec.classBuilder("BindingClassTest")
            .addJavadoc("generated\n")
            .addModifiers(Modifier.PUBLIC);
        initMapper(pojoTest, "MAPPER");

        pojoTest.addType(TypeSpec.interfaceBuilder("MyInterface")
            .addModifiers(Modifier.PUBLIC)
            .addTypeVariable(TypeVariableName.get("T"))
            .addAnnotation(AnnotationSpec.builder(JsonTypeInfo.class)
                .addMember("use", "$T.CLASS", JsonTypeInfo.Id.class)
                .addMember("include", "$T.PROPERTY", JsonTypeInfo.As.class)
                .addMember("property", "$S", "@class")
                .build())
            .addAnnotation(AnnotationSpec.builder(JsonAutoDetect.class)
                .addMember("fieldVisibility", "$T.ANY", JsonAutoDetect.Visibility.class)
                .addMember("getterVisibility", "$T.NONE", JsonAutoDetect.Visibility.class)
                .addMember("setterVisibility", "$T.NONE", JsonAutoDetect.Visibility.class)
                .build())
            .addMethod(MethodSpec.methodBuilder("myMethod")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(TypeVariableName.get("T"), "value").build())
                .returns(TypeName.BOOLEAN)
                .build())
            .build());
        pojoTest.addType(TypeSpec.classBuilder("ImplementedClass")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addSuperinterface(ParameterizedTypeName.get(ClassName.get("", "MyInterface"), ClassName.get(Integer.class)))
            .addMethod(MethodSpec.methodBuilder("myMethod")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(ClassName.get(Integer.class), "value").build())
                .returns(TypeName.BOOLEAN)
                .addStatement("return false")
                .build())
            .build());
        cases1.forEach((k, v) -> addCase1(pojoTest, k, v));
        cases2.forEach((k, v) -> addCase2(pojoTest, k, v));

        JavaFile javaFile = JavaFile.builder("io.vavr.jackson.generated", pojoTest.build())
            .indent("    ")
            .skipJavaLangImports(true)
            .build();

        javaFile.writeTo(new File("src/test/java"));
    }

    private static void addCase1(TypeSpec.Builder builder, Class<?> clz, Tuple2<String, String> m) {

        MethodSpec.Builder testBuilder = MethodSpec.methodBuilder("test" + clz.getSimpleName() + "Class")
            .addAnnotation(Test.class)
            .addModifiers(Modifier.PUBLIC)
            .addException(ClassName.get(Exception.class));
        MethodSpec testSpec = testBuilder
            .addStatement("$L src = new $L(" + m._1 + ")", clz.getSimpleName() + "Class", clz.getSimpleName() + "Class")
            .addStatement("$T json = MAPPER.writeValueAsString(src)", ClassName.get(String.class))
//                .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assertions.class), "{\"value\":"+ expectedJson(value, opts) + "}")
            .addStatement("$L restored = MAPPER.readValue(json, $L.class)", clz.getSimpleName() + "Class", clz.getSimpleName() + "Class")
            .addStatement("$T.assertEquals(restored.value." + m._2 + ".getClass(), $L)", ClassName.get(Assertions.class), "ImplementedClass.class")
            .build();
        builder.addMethod(testSpec);
        TypeName valueTypeName = ParameterizedTypeName.get(ClassName.get(clz),
            ParameterizedTypeName.get(ClassName.get("", "MyInterface"), ClassName.get(Integer.class)));
        builder.addType(TypeSpec.classBuilder(clz.getSimpleName() + "Class")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addField(FieldSpec.builder(valueTypeName, "value", Modifier.FINAL)
                .addAnnotation(AnnotationSpec.builder(JsonProperty.class).addMember("value", "$S", "value").build())
                .build())
            .addMethod(MethodSpec.constructorBuilder()
                .addParameter(ParameterSpec.builder(valueTypeName, "value")
                    .addAnnotation(AnnotationSpec.builder(JsonProperty.class).addMember("value", "$S", "value").build())
                    .build())
                .addStatement("this.value = value")
                .build())
            .build());
    }

    private static void addCase2(TypeSpec.Builder builder, Class<?> clz, Tuple2<String, String> m) {

        MethodSpec.Builder testBuilder = MethodSpec.methodBuilder("test" + clz.getSimpleName() + "Class")
            .addAnnotation(Test.class)
            .addModifiers(Modifier.PUBLIC)
            .addException(ClassName.get(Exception.class));
        MethodSpec testSpec = testBuilder
            .addStatement("$L src = new $L(" + m._1 + ")", clz.getSimpleName() + "Class", clz.getSimpleName() + "Class")
            .addStatement("$T json = MAPPER.writeValueAsString(src)", ClassName.get(String.class))
//                .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assertions.class), "{\"value\":"+ expectedJson(value, opts) + "}")
            .addStatement("$L restored = MAPPER.readValue(json, $L.class)", clz.getSimpleName() + "Class", clz.getSimpleName() + "Class")
            .addStatement("$T.assertEquals(restored.value." + m._2 + ".getClass(), $L)", ClassName.get(Assertions.class), "ImplementedClass.class")
            .build();
        builder.addMethod(testSpec);
        TypeName valueTypeName = ParameterizedTypeName.get(ClassName.get(clz), ClassName.get(Integer.class),
            ParameterizedTypeName.get(ClassName.get("", "MyInterface"), ClassName.get(Integer.class)));
        builder.addType(TypeSpec.classBuilder(clz.getSimpleName() + "Class")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addField(FieldSpec.builder(valueTypeName, "value", Modifier.FINAL)
                .addAnnotation(AnnotationSpec.builder(JsonProperty.class).addMember("value", "$S", "value").build())
                .build())
            .addMethod(MethodSpec.constructorBuilder()
                .addParameter(ParameterSpec.builder(valueTypeName, "value")
                    .addAnnotation(AnnotationSpec.builder(JsonProperty.class).addMember("value", "$S", "value").build())
                    .build())
                .addStatement("this.value = value")
                .build())
            .build());
    }
}
