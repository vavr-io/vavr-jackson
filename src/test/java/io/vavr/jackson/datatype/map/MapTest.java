package io.vavr.jackson.datatype.map;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

public abstract class MapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Map<K, V> emptyMap();

    protected abstract TypeReference<? extends Map<String, Option<Integer>>> typeReferenceWithOption();

    @Test
    void test1() throws IOException {
        Map<Object, Object> vavrObject = emptyMap().put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.HashMap<>();
        javaObject.put("1", 2);

        String json = mapper().writer().writeValueAsString(vavrObject);
        Assertions.assertEquals(genJsonMap(javaObject), json);

        Map<?, ?> restored = (Map<?, ?>) mapper().readValue(json, clz());
        Assertions.assertEquals(restored, vavrObject);
    }

    @Test
    void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test4() {
        assertThrows(JsonParseException.class, () -> mapper().readValue("{1: 1}", clz()));
    }

    @Test
    void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        Map<?, ?> src = emptyMap().put("1", 2);
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(emptyMap().put("1", Option.some(1)), genJsonMap(HashMap.of("1", 1).toJavaMap())),
                Tuple.of(emptyMap().put("1", Option.none()), genJsonMap(HashMap.of("1", null).toJavaMap()))
        ));
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaUtil {
        @XmlElement(name = "transitType")
        public java.util.Map<String, String> transitTypes;

        public JaxbXmlSerializeJavaUtil init(java.util.Map<String, String> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeVavr {
        @XmlElement(name = "transitType")
        public Map<String, String> transitTypes;

        public JaxbXmlSerializeVavr init(Map<String, String> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

//    @Test
    void testJaxbXmlSerialization() throws IOException {
        java.util.Map<String, String> javaInit = new java.util.HashMap<>();
        javaInit.put("key1", "1");
        javaInit.put("key2", "2");
        Map<String, String> slangInit = this.<String, String>emptyMap().put("key1", "1").put("key2", "2");
        ObjectMapper mapper = xmlMapperJaxb();
        String javaJson = mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(javaInit));
        String slangJson = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init(slangInit));
        Assertions.assertEquals(javaJson, slangJson);
        JaxbXmlSerializeVavr restored = mapper.readValue(slangJson, JaxbXmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.get("key1").get(), "1");
        Assertions.assertEquals(restored.transitTypes.get("key2").get(), "2");
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeJavaUtil {
        @JsonProperty("transitType")
        public java.util.Map<String, String> transitTypes;

        public XmlSerializeJavaUtil init(java.util.Map<String, String> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeVavr {
        @JsonProperty("transitType")
        public Map<String, String> transitTypes;

        public XmlSerializeVavr init(Map<String, String> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

//    @Test
    void testXmlSerialization() throws IOException {
        java.util.Map<String, String> javaInit = new java.util.HashMap<>();
        javaInit.put("key1", "1");
        javaInit.put("key2", "2");
        Map<String, String> slangInit = this.<String, String>emptyMap().put("key1", "1").put("key2", "2");
        ObjectMapper mapper = xmlMapper();
        String javaJson = mapper.writeValueAsString(new XmlSerializeJavaUtil().init(javaInit));
        String slangJson = mapper.writeValueAsString(new XmlSerializeVavr().init(slangInit));
        Assertions.assertEquals(javaJson, slangJson);
        XmlSerializeVavr restored = mapper.readValue(slangJson, XmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.get("key1").get(), "1");
        Assertions.assertEquals(restored.transitTypes.get("key2").get(), "2");
    }
}
