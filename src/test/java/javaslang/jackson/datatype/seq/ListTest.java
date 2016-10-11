package javaslang.jackson.datatype.seq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;

public class ListTest extends SeqTest {

    @Override
    protected Class<?> clz() {
        return List.class;
    }

    @Override
    protected TypeReference<List<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<List<Option<String>>>() {};
    }

    @Override
    protected String implClzName() {
        return List.Cons.class.getName();
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return List.ofAll(Arrays.asList(objects));
    }

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("[1]", Seq.class), List.of(1));
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type")
    @JsonTypeName("card")
    private static class TestSerialize {
        public String type = "hello";
    }

    private static class A {
        public List<TestSerialize> f = List.of(new TestSerialize());
    }

    private static class B {
        public java.util.List<TestSerialize> f = List.of(new TestSerialize()).toJavaList();
    }

    @Test
    public void testJsonTypeInfo() throws IOException {
        String javaUtilValue = mapper().writeValueAsString(new A());
        Assert.assertEquals(mapper().writeValueAsString(new B()), javaUtilValue);
        A restored = mapper().readValue(javaUtilValue, A.class);
        Assert.assertEquals("hello", restored.f.head().type);
    }
  @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaslang {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public List<Integer> transitTypes = List.of(1, 2, 3);
    }

    @XmlRootElement(name = "xmlSerialize")
    private static class JaxbXmlSerializeJavaUtil {
        @XmlElementWrapper(name = "transitTypes")
        @XmlElement(name = "transitType")
        public java.util.List<Integer> transitTypes = new java.util.ArrayList<>();

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

    private static class XmlSerializeJavaslang {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public List<Integer> transitTypes = List.of(1, 2, 3);
    }

    private static class XmlSerializeJavaUtil {
        @JacksonXmlElementWrapper(localName = "transitTypes")
        @JsonProperty("transitType")
        public java.util.List<Integer> transitTypes = new java.util.ArrayList<>();

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
