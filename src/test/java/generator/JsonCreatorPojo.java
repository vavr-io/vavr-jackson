package generator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.vavr.collection.PriorityQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static generator.utils.Initializer.initMapper;
import static generator.utils.Initializer.initValue;
import static generator.utils.PoetHelpers.jsonCreatorPojo;
import static generator.utils.Serializer.expectedJson;

/**
 * @author <a href="mailto:philipp.dargel@neuland-bfi.de">Philipp Dargel</a>
 */
public class JsonCreatorPojo {

    public static void main(String[] args) throws IOException {
        java.util.Map<String, Object> cases = new java.util.HashMap<>();
        cases.put("PriorityQueueOfString", PriorityQueue.of("A", "B"));
        generate(cases);
    }

    static void generate(java.util.Map<String, Object> cases) throws IOException {

        TypeSpec.Builder pojoTest = TypeSpec.classBuilder("JsonCreatorPojoTest")
                .addJavadoc("generated\n")
                .addModifiers(Modifier.PUBLIC);
        initMapper(pojoTest, "MAPPER");

        cases.forEach((k, v) -> addCase(pojoTest, k, v));

        JavaFile javaFile = JavaFile.builder("io.vavr.jackson.generated", pojoTest.build())
                .indent("    ")
                .skipJavaLangImports(true)
                .build();

        javaFile.writeTo(new File("src/test/java"));
    }

    private static void addCase(TypeSpec.Builder builder, String pojoName, Object value) {
        addCase(builder, pojoName, value, 0);
    }

    private static void addCase(TypeSpec.Builder builder, String pojoName, Object value, int opts) {

        MethodSpec.Builder testBuilder = MethodSpec.methodBuilder("testJsonCreator" + pojoName)
                .addAnnotation(Test.class)
                .addException(ClassName.get(Exception.class));
        TypeName valueTypeName = initValue(testBuilder, "src", value);
        MethodSpec testSpec = testBuilder
                .addStatement("$T json = MAPPER.writeValueAsString($L.create(src))", ClassName.get(String.class), pojoName)
                .addStatement("$T.assertEquals(json, $S)", ClassName.get(Assertions.class), expectedJson(value, opts))
                .addStatement("$L restored = MAPPER.readValue(json, $L.class)", pojoName, pojoName)
                .addStatement("$T.assertEquals(src, restored.getValue())", ClassName.get(Assertions.class))
                .build();
        builder.addMethod(testSpec);
        builder.addType(jsonCreatorPojo(pojoName, valueTypeName));
    }
}
