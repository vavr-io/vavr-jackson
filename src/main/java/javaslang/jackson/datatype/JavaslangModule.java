package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JavaslangModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new JavaslangSerializers());
        context.addDeserializers(new JavaslangDeserializers());
    }
}
