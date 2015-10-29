package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.module.SimpleModule;
import javaslang.jackson.datatype.deserialize.JavaslangDeserializers;
import javaslang.jackson.datatype.serialize.JavaslangSerializers;

public class JavaslangModule extends SimpleModule {

    private static final long serialVersionUID = 1L;
    private final Config cfg;

    public JavaslangModule() {
        this(new Config());
    }

    public JavaslangModule(Config cfg) {
        this.cfg = cfg;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new JavaslangSerializers(cfg.compact));
        context.addDeserializers(new JavaslangDeserializers());
    }

    public static class Config {
        private boolean compact = false;

        public void setCompact(boolean compact) {
            this.compact = compact;
        }
    }
}
