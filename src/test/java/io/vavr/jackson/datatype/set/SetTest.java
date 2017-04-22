package io.vavr.jackson.datatype.set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.jackson.datatype.VavrModule;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

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
        VavrModule.Settings settings = new VavrModule.Settings();
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

    @Test
    @SuppressWarnings("unchecked")
    public void testJaxbXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapperJaxb();
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init((Set<Integer>) of(1, 2, 3)));
        Assert.assertEquals(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(new java.util.HashSet<>(Arrays.asList(1, 2, 3)))), javaUtilValue);
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        Assert.assertEquals(restored.transitTypes.size(), 3);
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

    @Test
    @SuppressWarnings("unchecked")
    public void testXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapper();
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init((Set<Integer>) of(1, 2, 3)));
        Assert.assertEquals(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(new java.util.HashSet<>(Arrays.asList(1, 2, 3)))), javaUtilValue);
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        Assert.assertEquals(restored.transitTypes.size(), 3);
    }

    public static class Parameterized<T> {
        public Set<T> value;
        public Parameterized() {}
        public Parameterized(Set<T> value) {
            this.value = value;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWrappedParameterizedSome() throws IOException {
        String expected = "{\"value\":[1]}";
        Parameterized<Integer> object = new Parameterized<>((Set<Integer>) of(1));
        Assert.assertEquals(expected, mapper().writeValueAsString(object));
        Parameterized<Integer> restored = mapper().readValue(expected, new TypeReference<Parameterized<Integer>>() {});
        Assert.assertEquals(restored.value.head(), (Integer) 1);
    }
}
