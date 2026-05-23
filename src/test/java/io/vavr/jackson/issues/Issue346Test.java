package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class Issue346Test extends BaseTest {

    public record Scenario<T>(String description, TypeReference<T> typeReference, T object, String json) {

        @Override
        public String toString() {
            return description;
        }
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    @JsonSubTypes({
        @JsonSubTypes.Type(value = ImplType.class, name = "I")
    })
    public interface BaseType {}

    public record ImplType(int v) implements BaseType {}

    public record BaseTypeBox(BaseType item) {}

    public record ImplTypeBox(ImplType item) {}

    public record Box<T>(T item) {}

    public record OptionImplTypeBox(Option<ImplType> item) {}

    public record OptionBaseTypeBox(Option<BaseType> item) {}

    public static Stream<Scenario<?>> getScenarios() {
        return Stream.of(
            /* 1 baseline without vavr */
            new Scenario<>(
                "ImplType as BaseType",
                new TypeReference<BaseType>() {},
                new ImplType(1),
                "{\"@type\":\"I\",\"v\":1}"
            ),
            /* 2 baseline without vavr  */
            new Scenario<>(
                "ImplType as ImplType",
                new TypeReference<>() {},
                new ImplType(1),
                "{\"@type\":\"I\",\"v\":1}"
            ),
            /* 3 baseline without vavr  */
            new Scenario<>(
                "BaseTypeBox holding ImplType",
                new TypeReference<>() {},
                new BaseTypeBox(new ImplType(1)),
                "{\"item\":{\"@type\":\"I\",\"v\":1}}"
            ),
            /* 4 baseline without vavr  */
            new Scenario<>(
                "ImplTypeBox holding ImplType",
                new TypeReference<>() {},
                new ImplTypeBox(new ImplType(1)),
                "{\"item\":{\"@type\":\"I\",\"v\":1}}"
            ),
            /* 5 */
            new Scenario<>(
                "Option<ImplType> as Option<ImplType>",
                new TypeReference<Option<ImplType>>() {},
                Option.of(new ImplType(1)),
                "{\"@type\":\"I\",\"v\":1}"
            ),
            /* 6 */
            new Scenario<>(
                "Option<ImplType> as Option<BaseType>",
                new TypeReference<Option<BaseType>>() {},
                Option.of(new ImplType(1)),
                "{\"@type\":\"I\",\"v\":1}"
            ),
            /* 7 */
            new Scenario<>(
                "OptionImplTypeBox holding Option<ImplType>",
                new TypeReference<>() {},
                new OptionImplTypeBox(Option.of(new ImplType(1))),
                "{\"item\":{\"@type\":\"I\",\"v\":1}}"
            ),
            /* 8 */
            new Scenario<>(
                "OptionBaseTypeBox holding Option<ImplType>",
                new TypeReference<>() {},
                new OptionBaseTypeBox(Option.of(new ImplType(1))),
                "{\"item\":{\"@type\":\"I\",\"v\":1}}"
            ),
            /* 9 */
            new Scenario<>(
                "Box<Option<ImplType>> holding Option<ImplType>",
                new TypeReference<>() {},
                new Box<>(Option.of(new ImplType(1))),
                "{\"item\":{\"@type\":\"I\",\"v\":1}}"
            ),
            /* 10 */
            new Scenario<>(
                "Box<Option<BaseType>> holding Option<ImplType>",
                new TypeReference<Box<Option<BaseType>>>() {},
                new Box<>(Option.of(new ImplType(1))),
                "{\"item\":{\"@type\":\"I\",\"v\":1}}"
            )
        );
    }

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = mapper();
    }

    @ParameterizedTest
    @MethodSource("getScenarios")
    public void shouldSerializeObjectsCorrectly(Scenario<?> scenario) {
        assertThat(
            objectMapper.writerFor(scenario.typeReference())
                .writeValueAsString(scenario.object())
        )
            .isEqualTo(scenario.json());
    }

    @ParameterizedTest
    @MethodSource("getScenarios")
    public void shouldDeserializeStringsIntoObjectsCorrectly(Scenario<?> scenario) {
        assertThat(
            objectMapper.readValue(scenario.json(), scenario.typeReference())
        )
            .isEqualTo(scenario.object());
    }

}
