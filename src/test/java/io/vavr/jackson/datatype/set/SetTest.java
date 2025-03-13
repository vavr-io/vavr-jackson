package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Arrays;

public abstract class SetTest extends BaseTest {

    protected abstract Class<?> clz();

    protected abstract TypeReference<? extends Set<Integer>> typeReference();

    protected abstract TypeReference<? extends Set<Option<String>>> typeReferenceWithOption();

    protected abstract Set<?> of(Object... objects);

    @Test
    void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Set<?> src = of(1, 2, 5);
        String json = writer.writeValueAsString(src);
        Assertions.assertEquals(genJsonList(1, 2, 5), json);
        Set<?> dst = mapper().readValue(json, typeReference());
        Assertions.assertEquals(src, dst);
    }

    @Test
    void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test4() throws IOException {
        VavrModule.Settings settings = new VavrModule.Settings();
        settings.deserializeNullAsEmptyCollection(true);
        ObjectMapper mapper = mapper(settings);
        Set<?> restored = mapper.readValue("null", typeReference());
        Assertions.assertTrue(restored.isEmpty());
    }

    @Test
    void test5() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("null", typeReference());
        Assertions.assertNull(restored);
    }

    @Test
    void test6() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("[]", typeReference());
        Assertions.assertTrue(restored.isEmpty());
        Assertions.assertTrue(clz().isAssignableFrom(restored.getClass()));
    }

    @Test
    void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> src = of(1);
        Set<?> restored = mapper.readValue(mapper.writeValueAsString(src), typeReference());
        checkSerialization(restored);
    }

    @Test
    void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
            Tuple.of(of(Option.some("value")), genJsonList("value")),
            Tuple.of(of(Option.none()), genJsonList((Object) null))
        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testJaxbXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapperJaxb();
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init((Set<Integer>) of(1, 2, 3)));
        Assertions.assertEquals(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(new java.util.HashSet<>(Arrays.asList(1, 2, 3)))), javaUtilValue);
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.size(), 3);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapper();
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init((Set<Integer>) of(1, 2, 3)));
        Assertions.assertEquals(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(new java.util.HashSet<>(Arrays.asList(1, 2, 3)))), javaUtilValue);
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.size(), 3);
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeVavr {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public Set<Integer> transitTypes;

        public JaxbXmlSerializeVavr init(Set<Integer> slangSet) {
            transitTypes = slangSet;
            return this;
        }
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaUtil {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public java.util.Set<Integer> transitTypes;

        public JaxbXmlSerializeJavaUtil init(java.util.Set<Integer> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeVavr {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public Set<Integer> transitTypes;

        public XmlSerializeVavr init(Set<Integer> slangSet) {
            transitTypes = slangSet;
            return this;
        }
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeJavaUtil {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public java.util.Set<Integer> transitTypes;

        public XmlSerializeJavaUtil init(java.util.Set<Integer> javaSet) {
            transitTypes = javaSet;
            return this;
        }
    }
}
