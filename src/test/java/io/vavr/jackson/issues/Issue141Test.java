package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.YearMonth;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class Issue141Test extends BaseTest {

    static class MyJavaOptionalClass {
        @JsonProperty("operatingMonth")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
        Optional<YearMonth> operatingMonth;
    }

    @Test
    void itShouldSerializeJavaOptionalYearMonthAsString() throws IOException {
        // Given an instance with java.util.Optional
        MyJavaOptionalClass obj = new MyJavaOptionalClass();
        obj.operatingMonth = Optional.of(YearMonth.of(2019, 12));

        // When serializing the instance using object mapper
        // with Java Time Module and JDK8 Module
        ObjectMapper objectMapper = mapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        String json = objectMapper.writeValueAsString(obj);

        // Then serialization is successful
        assertThat(json).isEqualTo("{\"operatingMonth\":\"12-2019\"}");

        // And deserialization is successful
        MyJavaOptionalClass obj2 = objectMapper.readValue(json, MyJavaOptionalClass.class);
        assertThat(obj2.operatingMonth).isEqualTo(Optional.of(YearMonth.of(2019, 12)));
    }

    static class MyVavrOptionalClassWithoutFormat {
        @JsonProperty("operatingMonth")
        Option<YearMonth> operatingMonth;
    }

    @Test
    void itShouldSerializeVavrOptionYearMonthAsStringWithoutJsonFormat() throws IOException {
        // Given an instance with io.vavr.control.Option
        MyVavrOptionalClassWithoutFormat obj = new MyVavrOptionalClassWithoutFormat();
        obj.operatingMonth = Option.of(YearMonth.of(2019, 12));

        // When serializing the instance using object mapper
        // with Java Time Module and VAVR Module
        ObjectMapper objectMapper = mapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new VavrModule());
        String json = objectMapper.writeValueAsString(obj);

        // Then serialization is successful
        assertThat(json).isEqualTo("{\"operatingMonth\":[2019,12]}");
        MyVavrOptionalClassWithoutFormat obj2 = objectMapper.readValue(json, MyVavrOptionalClassWithoutFormat.class);

        // And deserialization is successful
        assertThat(obj2.operatingMonth).isEqualTo(Option.of(YearMonth.of(2019, 12)));
    }

    static class MyVavrOptionClassWithFormat {
        @JsonProperty("operatingMonth")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
        Option<YearMonth> operatingMonth;
    }

    @Test
    void itShouldSerializeVavrOptionYearMonthAsString() throws IOException {
        // Given an instance with io.vavr.control.Option
        MyVavrOptionClassWithFormat obj = new MyVavrOptionClassWithFormat();
        obj.operatingMonth = Option.of(YearMonth.of(2019, 12));

        // When serializing the instance using object mapper
        // with Java Time Module and VAVR Module
        ObjectMapper objectMapper = mapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new VavrModule());
        String json = objectMapper.writeValueAsString(obj);

        // Then serialization is failed
        assertThat(json).isEqualTo("{\"operatingMonth\":\"12-2019\"}");
        MyVavrOptionClassWithFormat obj2 = objectMapper.readValue(json, MyVavrOptionClassWithFormat.class);

        // And deserialization is failed
        assertThat(obj2.operatingMonth).isEqualTo(Option.of(YearMonth.of(2019, 12)));
    }
}
