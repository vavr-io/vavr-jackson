package io.vavr.jackson.datatype.seq;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.jackson.datatype.VavrModule;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Arrays;

public abstract class SeqTest extends BaseTest {

    protected abstract Class<?> clz();

    protected abstract TypeReference<? extends Seq<Option<String>>> typeReferenceWithOption();

    protected String implClzName() {
        return clz().getName();
    }

    protected abstract Seq<?> of(Object... objects);

    @Test
    void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Seq<?> src = of(1, null, 2.0, "s");
        String json = writer.writeValueAsString(src);
        Assertions.assertEquals(genJsonList(1, null, 2.0, "s"), json);
        Seq<?> dst = (Seq<?>) mapper().readValue(json, clz());
        Assertions.assertEquals(src, dst);
    }

    @Test
    void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Seq<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToObject(implClzName(), plainJson));
        Seq<?> restored = (Seq<?>) mapper.readValue(wrappedJson, clz());
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Seq<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assertions.assertEquals(wrappedJson, wrapToArray(implClzName(), plainJson));
        Seq<?> restored = (Seq<?>) mapper.readValue(wrappedJson, clz());
        Assertions.assertEquals(src, restored);
    }

    @Test
    void test4() throws IOException {
        VavrModule.Settings settings = new VavrModule.Settings();
        settings.deserializeNullAsEmptyCollection(true);
        ObjectMapper mapper = mapper(settings);
        Seq<?> restored = (Seq<?>) mapper.readValue("null", clz());
        Assertions.assertTrue(restored.isEmpty());
    }

    @Test
    void test5() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> restored = (Seq<?>) mapper.readValue("null", clz());
        Assertions.assertNull(restored);
    }

    @Test
    void test6() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> restored = (Seq<?>) mapper.readValue("[]", clz());
        Assertions.assertTrue(restored.isEmpty());
        Assertions.assertTrue(clz().isAssignableFrom(restored.getClass()));
    }

    @Test
    void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> src = of(1);
        Seq<?> restored = (Seq<?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    void testExpectedStartArrayToken() {
        assertThrows(JsonMappingException.class, () -> {
            ObjectMapper mapper = mapper();
            mapper.readValue("42", clz());
        });
    }

    @Test
    void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(of(Option.some("value")), genJsonList("value")),
                Tuple.of(of(Option.none()), genJsonList((Object) null))
        ));
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeVavr {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public Seq<Integer> transitTypes;

        public JaxbXmlSerializeVavr init(Seq<Integer> slangSet) {
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
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init((Seq<Integer>) of(1, 2, 3)));
        Assertions.assertEquals(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))), javaUtilValue);
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.size(), 3);
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeVavr {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public Seq<Integer> transitTypes;

        public XmlSerializeVavr init(Seq<Integer> slangSet) {
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
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init((Seq<Integer>) of(1, 2, 3)));
        Assertions.assertEquals(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))), javaUtilValue);
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        Assertions.assertEquals(restored.transitTypes.size(), 3);
    }
}
