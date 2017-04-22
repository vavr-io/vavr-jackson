/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
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
