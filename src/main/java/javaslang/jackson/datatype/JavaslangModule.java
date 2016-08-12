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

    public static class Options {

        private boolean plainOption = true;
        private boolean deserializeNullAsEmptyCollection = false;

        public Options plainOption(boolean value) {
            plainOption = value;
            return this;
        }

        public Options deserializeNullAsEmptyCollection(boolean value) {
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

    private final Options options;

    public JavaslangModule() {
        this(new Options());
    }

    public JavaslangModule(Options options) {
        this.options = options;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new JavaslangSerializers(options));
        context.addDeserializers(new JavaslangDeserializers(options));
    }
}
