package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.vavr.Lazy;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.jsontype.TypeSerializer;
import tools.jackson.databind.ser.std.StdSerializer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * OptionSerializer and LazySerializer ignore @JsonSerialize(contentUsing=...)
 * because their createContextual() doesn't resolve content serializer overrides
 * from property annotations — unlike Jackson's built-in ReferenceTypeSerializer.
 *
 * Additionally, even if the custom serializer were resolved, serialize() calls
 * valueSerializer.serialize() instead of valueSerializer.serializeWithType()
 * when valueTypeSerializer is non-null, which would drop the type discriminator.
 */
public class ContentUsingPolymorphicTest extends BaseTest {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public interface Animal {
        String name();
    }

    public record Dog(String name) implements Animal {}

    public record Cat(String name) implements Animal {}

    public static class UpperCaseAnimalSerializer extends StdSerializer<Animal> {
        public UpperCaseAnimalSerializer() {
            super(Animal.class);
        }

        @Override
        public void serialize(Animal value, JsonGenerator gen, SerializationContext ctxt) {
            gen.writeStartObject();
            gen.writeName("name");
            gen.writeString(value.name().toUpperCase());
            gen.writeEndObject();
        }

        @Override
        public void serializeWithType(Animal value, JsonGenerator gen, SerializationContext ctxt,
                                      TypeSerializer typeSer) {
            var typeId = typeSer.typeId(value, JsonToken.START_OBJECT);
            typeSer.writeTypePrefix(gen, ctxt, typeId);
            gen.writeName("name");
            gen.writeString(value.name().toUpperCase());
            typeSer.writeTypeSuffix(gen, ctxt, typeId);
        }
    }

    public record JavaOptionalBox(
        @JsonSerialize(contentUsing = UpperCaseAnimalSerializer.class)
        java.util.Optional<Animal> animal
    ) {}

    @Test
    void javaOptionalRespectsContentUsing() {
        var pojo = new JavaOptionalBox(Optional.of(new Dog("Rex")));
        String json = mapper().writeValueAsString(pojo);

        assertThat(json)
            .describedAs("java.util.Optional correctly picks up @JsonSerialize(contentUsing=...)")
            .contains("\"name\":\"REX\"")
            .contains("\"@type\":\"dog\"");
    }

    public record VavrOptionBox(
        @JsonSerialize(contentUsing = UpperCaseAnimalSerializer.class)
        Option<Animal> animal
    ) {}

    @Test
    void vavrOptionShouldRespectContentUsing() {
        var pojo = new VavrOptionBox(Option.of(new Dog("Rex")));
        String json = mapper().writeValueAsString(pojo);

        // BUG: OptionSerializer.createContextual() doesn't resolve the contentUsing
        // annotation, so the custom serializer is never used.
        // Jackson's ReferenceTypeSerializer handles this correctly (see control test above).
        assertThat(json)
            .describedAs("vavr Option should use the custom serializer from @JsonSerialize(contentUsing=...) " +
                "but ignores it — name should be uppercased")
            .contains("\"name\":\"REX\"");
    }

    public record VavrLazyBox(
        @JsonSerialize(contentUsing = UpperCaseAnimalSerializer.class)
        Lazy<Animal> animal
    ) {}

    @Test
    void vavrLazyShouldRespectContentUsing() {
        var pojo = new VavrLazyBox(Lazy.of(() -> new Cat("Whiskers")));
        String json = mapper().writeValueAsString(pojo);

        assertThat(json)
            .describedAs("vavr Lazy should use the custom serializer from @JsonSerialize(contentUsing=...) " +
                "but ignores it - name should be uppercased")
            .contains("\"name\":\"WHISKERS\"");
    }
}
