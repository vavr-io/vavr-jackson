/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2017 Vavr, http://vavr.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vavr.jackson.datatype;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vavr.jackson.datatype.deserialize.VavrDeserializers;
import io.vavr.jackson.datatype.serialize.VavrSerializers;

public class VavrModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public static class Settings {

        private boolean plainOption = true;
        private boolean deserializeNullAsEmptyCollection = false;

        public Settings useOptionInPlainFormat(boolean value) {
            plainOption = value;
            return this;
        }

        public Settings deserializeNullAsEmptyCollection(boolean value) {
            deserializeNullAsEmptyCollection = value;
            return this;
        }

        public boolean useOptionInPlainFormat() {
            return plainOption;
        }

        public boolean deserializeNullAsEmptyCollection() {
            return deserializeNullAsEmptyCollection;
        }
    }

    private final Settings settings;

    public VavrModule() {
        this(new Settings());
    }

    public VavrModule(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new VavrSerializers(settings));
        context.addDeserializers(new VavrDeserializers(settings));
        context.addTypeModifier(new VavrTypeModifier());
    }
}
