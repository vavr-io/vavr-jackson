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
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(json).isEqualTo(genJsonList(1, 2, 5));
        Set<?> dst = mapper().readValue(json, typeReference());
        assertThat(dst).isEqualTo(src);
    }

    @Test
    void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(clz().getName(), plainJson)).isEqualTo(wrappedJson);
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        Set<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToArray(clz().getName(), plainJson)).isEqualTo(wrappedJson);
        Set<?> restored = mapper.readValue(wrappedJson, typeReference());
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void test4() throws IOException {
        VavrModule.Settings settings = new VavrModule.Settings();
        settings.deserializeNullAsEmptyCollection(true);
        ObjectMapper mapper = mapper(settings);
        Set<?> restored = mapper.readValue("null", typeReference());
        assertThat(restored.isEmpty()).isTrue();
    }

    @Test
    void test5() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("null", typeReference());
        assertThat(restored).isNull();
    }

    @Test
    void test6() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> restored = mapper.readValue("[]", typeReference());
        assertThat(restored.isEmpty()).isTrue();
        assertThat(clz().isAssignableFrom(restored.getClass())).isTrue();
    }

    @Test
    void serializable() throws IOException {
        ObjectMapper mapper = mapper();
        Set<?> src = of(1);
        Set<?> restored = mapper.readValue(mapper.writeValueAsString(src), typeReference());
        checkSerialization(restored);
    }

    @Test
    void withOption() throws Exception {
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
    void jaxbXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapperJaxb();
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init((Set<Integer>) of(1, 2, 3)));
        assertThat(javaUtilValue).isEqualTo(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(new java.util.HashSet<>(Arrays.asList(1, 2, 3)))));
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        assertThat(restored.transitTypes.size()).isEqualTo(3);
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
    void xmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapper();
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init((Set<Integer>) of(1, 2, 3)));
        assertThat(javaUtilValue).isEqualTo(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(new java.util.HashSet<>(Arrays.asList(1, 2, 3)))));
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        assertThat(restored.transitTypes.size()).isEqualTo(3);
    }
}
