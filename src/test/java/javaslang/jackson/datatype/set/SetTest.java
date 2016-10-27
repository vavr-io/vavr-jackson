package javaslang.jackson.datatype.set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javaslang.jackson.datatype.JavaslangModule;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.IOException;

import javaslang.Tuple;
import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.collection.HashSet;
import javaslang.control.Option;
import javaslang.jackson.datatype.BaseTest;

public abstract class SetTest extends BaseTest {

    protected abstract Class<?> clz();

    protected abstract TypeReference<? extends Set<Integer>> typeReference();

    protected abstract TypeReference<? extends Set<Option<String>>> typeReferenceWithOption();

    protected abstract Set<?> of(Object... objects);

    @Test
    public void test1() throws IOException {
        ObjectWriter writer = mapper().writer();
        Set<?> src = of(1, 2, 5);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonList(1, 2, 5), json);
        Set<?> dst = mapper().readValue(json, typeReference());
        Assert.assertEquals(src, dst);
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        Assert.assertEquals(src, restored);
    }

    @Test
    public void test4() throws IOException {
        JavaslangModule.Settings settings = new JavaslangModule.Settings();
        settings.deserializeNullAsEmptyCollection(true);
        ObjectMapper mapper = mapper(settings);
        Set<?> restored = mapper.readValue("null", typeReference());
        Assert.assertTrue(restored.isEmpty());
    }

    @Test
    public void test5() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("null", typeReference());
        Assert.assertNull(restored);
    }

    @Test
    public void test6() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("[]", typeReference());
        Assert.assertTrue(restored.isEmpty());
        Assert.assertTrue(clz().isAssignableFrom(restored.getClass()));
    }

    @Test
    public void testWithOption() throws Exception {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(of(Option.some("value")), genJsonList("value")),
                Tuple.of(of(Option.none()), genJsonList((Object) null))
        ));
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaslang {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public Set<Integer> transitTypes = HashSet.of(1, 2, 3);
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaUtil {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public java.util.Set<Integer> transitTypes = new java.util.HashSet<>();

        public JaxbXmlSerializeJavaUtil() {
            transitTypes.add(1);
            transitTypes.add(2);
            transitTypes.add(3);
        }
    }

    @Test
    public void testJaxbXmlSerialization() throws IOException {
        String javaUtilValue = xmlMapperJaxb().writeValueAsString(new JaxbXmlSerializeJavaslang());
        Assert.assertEquals(xmlMapperJaxb().writeValueAsString(new JaxbXmlSerializeJavaUtil()), javaUtilValue);
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeJavaslang {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public Set<Integer> transitTypes = HashSet.of(1, 2, 3);
    }

    @JacksonXmlRootElement(localName = "xmlSerialize")
    private static class XmlSerializeJavaUtil {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public java.util.Set<Integer> transitTypes = new java.util.HashSet<>();

        public XmlSerializeJavaUtil() {
            transitTypes.add(1);
            transitTypes.add(2);
            transitTypes.add(3);
        }
    }

    @Test
    public void testXmlSerialization() throws IOException {
        String javaUtilValue = xmlMapper().writeValueAsString(new XmlSerializeJavaslang());
        Assert.assertEquals(xmlMapper().writeValueAsString(new XmlSerializeJavaUtil()), javaUtilValue);
    }
}
