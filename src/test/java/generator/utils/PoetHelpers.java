package generator.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class PoetHelpers {

    public static TypeSpec simplePojo(String pojoName, TypeName valueTypeName) {
        return pojoClass(pojoName, valueTypeName)
                .addMethod(getter(valueTypeName).build())
                .addMethod(setter(pojoName, valueTypeName))
                .build();
    }

    public static TypeSpec jsonCreatorPojo(String pojoName, TypeName valueTypeName) {
        return pojoClass(pojoName, valueTypeName)
                .addMethod(getter(valueTypeName)
                    .addAnnotation(JsonValue.class)
                    .build())
                .addMethod(creator(pojoName, valueTypeName))
                .build();
    }

    private static MethodSpec creator(String pojoName, TypeName valueTypeName) {
        return MethodSpec.methodBuilder("create")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addAnnotation(JsonCreator.class)
                .returns(ClassName.get("", pojoName))
                .addParameter(ParameterSpec.builder(valueTypeName, "v").build())
                .addStatement(pojoName + " i = new " + pojoName + "()")
                .addStatement("i.v = v")
                .addStatement("return i")
                .build();
    }

    private static TypeSpec.Builder pojoClass(String pojoName, TypeName valueTypeName) {
        return TypeSpec.classBuilder(pojoName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addField(FieldSpec.builder(valueTypeName, "v", Modifier.PRIVATE).build());
    }

    private static MethodSpec.Builder getter(TypeName valueTypeName) {
        return MethodSpec.methodBuilder("getValue")
                .addModifiers(Modifier.PUBLIC)
                .returns(valueTypeName)
                .addStatement("return v");
    }

    private static MethodSpec setter(String pojoName, TypeName valueTypeName) {
        return MethodSpec.methodBuilder("setValue")
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get("", pojoName))
                .addParameter(ParameterSpec.builder(valueTypeName, "v").build())
                .addStatement("this.v = v")
                .addStatement("return this")
                .build();
    }
}
