package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Serialize of List of Date does not follow pattern defined in {@code @JsonFormat}
 * https://github.com/vavr-io/vavr-jackson/issues/154
 */
public class Issue154Test {

    private static class MyVavrClass {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
        private List<Date> dates;
    }

    private static class MyJavaClass {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
        private java.util.List<Date> dates;
    }

    @Test
    void itShouldSerializeVavrListWithVavrModule() throws Exception {
        MyVavrClass myClass = new MyVavrClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());

        String json = mapper.writeValueAsString(myClass);
        assertEquals("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}", json);
    }

    @Test
    void itShouldSerializeVavrListWithVavrModuleAndJavaTimeModule() throws Exception {
        MyVavrClass myClass = new MyVavrClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        mapper.registerModule(new JavaTimeModule());

        String json = mapper.writeValueAsString(myClass);
        assertEquals("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}", json);
    }

    @Test
    void itShouldSerializeJavaListWithJavaTimeModule() throws Exception {
        MyJavaClass myClass = new MyJavaClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L)).asJava();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String json = mapper.writeValueAsString(myClass);
        assertEquals("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}", json);
    }

    @Test
    void itShouldSerializeJavaListWithoutModule() throws Exception {
        MyJavaClass myClass = new MyJavaClass();
        myClass.dates = List.of(new Date(1591221600000L), new Date(1591308000000L)).asJava();

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(myClass);
        assertEquals("{\"dates\":[\"2020-06-04\",\"2020-06-05\"]}", json);
    }
}
