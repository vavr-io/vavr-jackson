package io.vavr.jackson.datatype.serialize;

import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

class OptionSerializerTest {

    @Test
    void withResolvedShouldReturnSameInstanceWhenNothingChanged() {
        JsonMapper mapper = JsonMapper.builder()
            .addModule(new VavrModule())
            .build();

        SerializationContext context = mapper._serializationContext();
        JavaType type = mapper.getTypeFactory().constructParametricType(Option.class, String.class);
        ValueSerializer<Object> serializer = context.findValueSerializer(type);

        assertThat(serializer).isInstanceOf(OptionSerializer.class);

        OptionSerializer resolved = (OptionSerializer) (ValueSerializer<?>)
            ((OptionSerializer) (ValueSerializer<?>) serializer).createContextual(context, null);

        ValueSerializer<?> resolvedAgain = resolved.createContextual(context, null);

        assertThat(resolvedAgain).isSameAs(resolved);
    }
}
