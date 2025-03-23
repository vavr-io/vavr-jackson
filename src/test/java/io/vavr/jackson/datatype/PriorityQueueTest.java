package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.collection.PriorityQueue;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class PriorityQueueTest extends BaseTest {

    @Test
    void shouldThrowExceptionForGenericDeserializationWithIntegers() {
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> mapper().readValue("[1, 2]", PriorityQueue.class));
    }

    @Test
    void shouldThrowExceptionForGenericDeserializationWithTypeReference() throws IOException {
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper().readValue("[1, 2]", new TypeReference<PriorityQueue<Object>>() {
            }));
    }

    @Test
    void shouldThrowExceptionForIncomparableDeserialization() {
        assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() ->
            mapper().readValue("[{\"i\":1}, {\"i\":2}]", new TypeReference<PriorityQueue<PriorityQueueTest.Incomparable>>() {
            }));
    }

    @Test
    void shouldSerializeAndDeserializePriorityQueueOfIntegers() throws IOException {
        ObjectWriter writer = mapper().writer();
        PriorityQueue<Integer> src = PriorityQueue.of(1, 5, 8);
        String json = writer.writeValueAsString(src);
        assertThat(json).isEqualTo(genJsonList(1, 5, 8));
        PriorityQueue<Integer> dst = mapper().readValue(json, new TypeReference<PriorityQueue<Integer>>() {
        });
        assertThat(dst).isEqualTo(src);
    }

    @Test
    void serializable() throws IOException {
        ObjectMapper mapper = mapper();
        PriorityQueue<Integer> src = PriorityQueue.of(1);
        PriorityQueue<Integer> restored = mapper.readValue(mapper.writeValueAsString(src), new TypeReference<PriorityQueue<Integer>>() {
        });
        checkSerialization(restored);
    }

    static class Incomparable {
        private int i = 0;

        int getI() {
            return i;
        }

        void setI(int i) {
            this.i = i;
        }
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeVavr {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public PriorityQueue<Integer> transitTypes;

        public JaxbXmlSerializeVavr init(PriorityQueue<Integer> slangSet) {
            transitTypes = slangSet;
            return this;
        }
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaUtil {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public java.util.Collection<Integer> transitTypes;

        public JaxbXmlSerializeJavaUtil init(java.util.Collection<Integer> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void jaxbXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapperJaxb();
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init(PriorityQueue.of(1, 2, 3)));
        assertThat(javaUtilValue).isEqualTo(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))));
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        assertThat(restored.transitTypes.size()).isEqualTo(3);
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeVavr {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public PriorityQueue<Integer> transitTypes;

        public XmlSerializeVavr init(PriorityQueue<Integer> slangSet) {
            transitTypes = slangSet;
            return this;
        }
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeJavaUtil {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public java.util.Collection<Integer> transitTypes;

        public XmlSerializeJavaUtil init(java.util.Collection<Integer> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void xmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapper();
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init(PriorityQueue.of(1, 2, 3)));
        assertThat(javaUtilValue).isEqualTo(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))));
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        assertThat(restored.transitTypes.size()).isEqualTo(3);
    }

    static class FrenchDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
        PriorityQueue<Date> dates;
    }

    @Test
    void serializeWithContext() throws IOException {
        // Given an object containing dates to serialize
        FrenchDates src = new FrenchDates();
        src.dates = PriorityQueue.of(new Date(1591308000000L));

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
