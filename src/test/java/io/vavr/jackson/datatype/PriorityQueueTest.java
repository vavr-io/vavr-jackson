package io.vavr.jackson.datatype;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.collection.PriorityQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Arrays;

public class PriorityQueueTest extends BaseTest {

    @Test
    void testGeneric1() {
        assertThrows(JsonMappingException.class, () -> mapper().readValue("[1, 2]", PriorityQueue.class));
    }

    @Test
    void testGeneric2() throws IOException {
        assertThrows(JsonMappingException.class, () -> {
            mapper().readValue("[1, 2]", new TypeReference<PriorityQueue<Object>>() {});
        });
    }

    static class Incomparable {
        private int i = 0;
        int getI() { return i; }
        void setI(int i) { this.i = i; }
    }

    @Test
    void testGeneric3() {
        assertThrows(JsonMappingException.class, () -> {
            mapper().readValue("[{\"i\":1}, {\"i\":2}]", new TypeReference<PriorityQueue<PriorityQueueTest.Incomparable>>() {});
        });
    }

    @Test
    void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        PriorityQueue<Integer> src = PriorityQueue.of(1, 5, 8);
        String json = writer.writeValueAsString(src);
        Assertions.assertEquals(genJsonList(1, 5, 8), json);
        PriorityQueue<Integer> dst = mapper().readValue(json, new TypeReference<PriorityQueue<Integer>>() {});
        Assertions.assertEquals(src, dst);
    }

    @Test
    void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        PriorityQueue<Integer> src = PriorityQueue.of(1);
        PriorityQueue<Integer> restored = mapper.readValue(mapper.writeValueAsString(src), new TypeReference<PriorityQueue<Integer>>() {});
        checkSerialization(restored);
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
    void testJaxbXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapperJaxb();
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init(PriorityQueue.of(1, 2, 3)));
        Assertions.assertEquals(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))), javaUtilValue);
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.size(), 3);
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
    void testXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapper();
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init(PriorityQueue.of(1, 2, 3)));
        Assertions.assertEquals(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))), javaUtilValue);
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.size(), 3);
    }
}
