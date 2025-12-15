package generator.utils;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * @author Ruslan Sennov</a>
 */
public class PoetHelpers {

    public static TypeSpec simplePojo(String pojoName, TypeName valueTypeName) {
        return TypeSpec.classBuilder(pojoName)
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
    }
}
