package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.module.SimpleModule;
import javaslang.jackson.datatype.deserialize.JavaslangDeserializers;
import javaslang.jackson.datatype.serialize.JavaslangSerializers;

public class JavaslangModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new JavaslangSerializers());
        context.addDeserializers(new JavaslangDeserializers());
    }
}
