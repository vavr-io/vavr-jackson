package io.vavr.jackson.datatype.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

public abstract class MapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Map<K, V> emptyMap();

    protected abstract TypeReference<? extends Map<String, Option<Integer>>> typeReferenceWithOption();

    @Test
    public void test1() throws IOException {
        Map<Object, Object> vavrObject = emptyMap().put("1", 2);
        java.util.Map<Object, Object> javaObject = new java.util.HashMap<>();
        javaObject.put("1", 2);

        String json = mapper().writer().writeValueAsString(vavrObject);
        Assert.assertEquals(genJsonMap(javaObject), json);

        Map<?, ?> restored = (Map<?, ?>) mapper().readValue(json, clz());
        Assert.assertEquals(restored, vavrObject);
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    // Issue 138: Cannot deserialize to Map<String, String>
    // https://github.com/vavr-io/vavr-jackson/issues/138
    @Test
    public void testDeserializeNullValue() throws IOException {
        Map<String, String> stringStringMap = mapper().readValue("{\"1\":null}", new TypeReference<Map<String, String>>() {});
        Map<String, Object> stringObjectMap = mapper().readValue("{\"1\":null}", new TypeReference<Map<String, Object>>() {});

        Assert.assertEquals(emptyMap().put("1", null), stringStringMap);
        Assert.assertEquals(emptyMap().put("1", null), stringObjectMap);
    }

    @Test(expected = JsonParseException.class)
    public void test4() throws IOException {
        mapper().readValue("{1: 1}", clz());
    }

    @Test
    public void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        Map<?, ?> src = emptyMap().put("1", 2);
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    public void testWithOption() throws Exception {
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
    public void testJaxbXmlSerialization() throws IOException {
        java.util.Map<String, String> javaInit = new java.util.HashMap<>();
        javaInit.put("key1", "1");
        javaInit.put("key2", "2");
        Map<String, String> slangInit = this.<String, String>emptyMap().put("key1", "1").put("key2", "2");
        ObjectMapper mapper = xmlMapperJaxb();
        String javaJson = mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(javaInit));
        String slangJson = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init(slangInit));
        Assert.assertEquals(javaJson, slangJson);
        JaxbXmlSerializeVavr restored = mapper.readValue(slangJson, JaxbXmlSerializeVavr.class);
        Assert.assertEquals(restored.transitTypes.get("key1").get(), "1");
        Assert.assertEquals(restored.transitTypes.get("key2").get(), "2");
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
    public void testXmlSerialization() throws IOException {
        java.util.Map<String, String> javaInit = new java.util.HashMap<>();
        javaInit.put("key1", "1");
        javaInit.put("key2", "2");
        Map<String, String> slangInit = this.<String, String>emptyMap().put("key1", "1").put("key2", "2");
        ObjectMapper mapper = xmlMapper();
        String javaJson = mapper.writeValueAsString(new XmlSerializeJavaUtil().init(javaInit));
        String slangJson = mapper.writeValueAsString(new XmlSerializeVavr().init(slangInit));
        Assert.assertEquals(javaJson, slangJson);
        XmlSerializeVavr restored = mapper.readValue(slangJson, XmlSerializeVavr.class);
        Assert.assertEquals(restored.transitTypes.get("key1").get(), "1");
        Assert.assertEquals(restored.transitTypes.get("key2").get(), "2");
    }
}
