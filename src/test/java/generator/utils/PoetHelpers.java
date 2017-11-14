package generator.utils;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
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
