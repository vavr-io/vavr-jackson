/**
 * Copyright 2015 The Javaslang Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.module.SimpleModule;
import javaslang.jackson.datatype.deserialize.JavaslangDeserializers;
import javaslang.jackson.datatype.serialize.JavaslangSerializers;

public class JavaslangModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public static class Settings {

        private boolean plainOption = true;
        private boolean deserializeNullAsEmptyCollection = false;

        public Settings plainOption(boolean value) {
            plainOption = value;
            return this;
        }

        public Settings deserializeNullAsEmptyCollection(boolean value) {
            deserializeNullAsEmptyCollection = value;
            return this;
        }

        public boolean plainOption() {
            return plainOption;
        }

        public boolean deserializeNullAsEmptyCollection() {
            return deserializeNullAsEmptyCollection;
        }
    }

    private final Settings settings;

    public JavaslangModule() {
        this(new Settings());
    }

    public JavaslangModule(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new JavaslangSerializers(settings));
        context.addDeserializers(new JavaslangDeserializers(settings));
    }
}
