package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class ListTest extends SeqTest {

    @Override
    protected Class<?> clz() {
        return List.class;
    }

    @Override
    protected TypeReference<List<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<List<Option<String>>>() {
        };
    }

    @Override
    protected String implClzName() {
        return List.Cons.class.getName();
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return List.ofAll(Arrays.asList(objects));
    }

    @Test
    void defaultDeserialization() throws IOException {
        assertThat(List.of(1)).isEqualTo(mapper().readValue("[1]", Seq.class));
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type")
    @JsonTypeName("card")
    private static class TestSerialize {
        public String type = "hello";
    }

    private static class A {
        public List<TestSerialize> f = List.of(new TestSerialize());
    }

    private static class B {
        public java.util.List<TestSerialize> f = List.of(new TestSerialize()).toJavaList();
    }

    @Test
    void jsonTypeInfo() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        assertThat(javaUtilValue).isEqualTo(mapper().writeValueAsString(new B()));
        A restored = mapper().readValue(javaUtilValue, A.class);
        assertThat(restored.f.head().type).isEqualTo("hello");
    }

    static class FrenchDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
        List<Date> dates;
    }

    @Test
    void serializeWithContext() throws IOException {
        // Given an object containing dates to serialize
        FrenchDates src = new FrenchDates();
        src.dates = List.of(new Date(1591308000000L));

        // When serializing them using object mapper
        // with VAVR module and Java Time module
        ObjectMapper mapper = mapper().registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(src);

        // Then the serialization is successful
        assertThat(json).isEqualTo("{\"dates\":[\"05/06/2020\"]}");

        // And the deserialization is successful
        FrenchDates restored = mapper.readValue(json, FrenchDates.class);
        assertThat(restored.dates).isEqualTo(src.dates);
    }
}
