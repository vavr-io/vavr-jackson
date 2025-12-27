package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import java.util.Date;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Serialize of List of Date does not follow the pattern defined in {@code @JsonFormat}
 * <a href="https://github.com/vavr-io/vavr-jackson/issues/154">GitHub issue</a>
 */
class Issue154Test {

    private static class MyVavrClass {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
        private List<Date> dates;
    }

    private static class MyJavaClass {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
        private java.util.List<Date> dates;
    }

    @Test
    void itShouldSerializeVavrListWithVavrModule() {
        MyVavrClass myClass = new MyVavrClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L));

        ObjectMapper mapper = JsonMapper.builder().addModule(new VavrModule()).build();

        String json = mapper.writeValueAsString(myClass);
        assertThat(json).isEqualTo("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}");
    }

    @Test
    void itShouldSerializeVavrListWithVavrModuleAndJavaTimeModule() {
        MyVavrClass myClass = new MyVavrClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L));

        ObjectMapper mapper = JsonMapper.builder().addModule(new VavrModule()).build();

        String json = mapper.writeValueAsString(myClass);
        assertThat(json).isEqualTo("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}");
    }

    @Test
    void itShouldSerializeJavaListWithJavaTimeModule() {
        MyJavaClass myClass = new MyJavaClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L)).asJava();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(myClass);
        assertThat(json).isEqualTo("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}");
    }

    @Test
    void itShouldSerializeJavaListWithoutModule() {
        MyJavaClass myClass = new MyJavaClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L)).asJava();

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(myClass);
        assertThat(json).isEqualTo("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}");
    }
}
