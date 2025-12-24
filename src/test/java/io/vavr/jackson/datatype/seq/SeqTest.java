package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

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
        assertThat(json).isEqualTo(genJsonList(1, null, 2.0, "s"));
        Seq<?> dst = (Seq<?>) mapper().readValue(json, clz());
        assertThat(dst).isEqualTo(src);
    }

    @Test
    void test2() throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(clz(), WrapperObject.class).build();
        Seq<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(implClzName(), plainJson)).isEqualTo(wrappedJson);
        Seq<?> restored = (Seq<?>) mapper.readValue(wrappedJson, clz());
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void test3() throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(clz(), WrapperArray.class).build();
        Seq<?> src = of(1);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToArray(implClzName(), plainJson)).isEqualTo(wrappedJson);
        Seq<?> restored = (Seq<?>) mapper.readValue(wrappedJson, clz());
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void test4() throws IOException {
        VavrModule.Settings settings = new VavrModule.Settings();
        settings.deserializeNullAsEmptyCollection(true);
        ObjectMapper mapper = mapper(settings);
        Seq<?> restored = (Seq<?>) mapper.readValue("null", clz());
        assertThat(restored.isEmpty()).isTrue();
    }

    @Test
    void test5() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> restored = (Seq<?>) mapper.readValue("null", clz());
        assertThat(restored).isNull();
    }

    @Test
    void test6() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> restored = (Seq<?>) mapper.readValue("[]", clz());
        assertThat(restored.isEmpty()).isTrue();
        assertThat(clz().isAssignableFrom(restored.getClass())).isTrue();
    }

    @Test
    void serializable() throws IOException {
        ObjectMapper mapper = mapper();
        Seq<?> src = of(1);
        Seq<?> restored = (Seq<?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    void expectedStartArrayToken() {
        ObjectMapper mapper = mapper();
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() ->
            mapper.readValue("42", clz()));
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
    void jaxbXmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapperJaxb();
        String javaUtilValue = mapper.writeValueAsString(new JaxbXmlSerializeVavr().init((Seq<Integer>) of(1, 2, 3)));
        assertThat(javaUtilValue).isEqualTo(mapper.writeValueAsString(new JaxbXmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))));
        JaxbXmlSerializeVavr restored = mapper.readValue(javaUtilValue, JaxbXmlSerializeVavr.class);
        assertThat(restored.transitTypes.size()).isEqualTo(3);
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
    void xmlSerialization() throws IOException {
        ObjectMapper mapper = xmlMapper();
        String javaUtilValue = mapper.writeValueAsString(new XmlSerializeVavr().init((Seq<Integer>) of(1, 2, 3)));
        assertThat(javaUtilValue).isEqualTo(mapper.writeValueAsString(new XmlSerializeJavaUtil().init(Arrays.asList(1, 2, 3))));
        XmlSerializeVavr restored = mapper.readValue(javaUtilValue, XmlSerializeVavr.class);
        assertThat(restored.transitTypes.size()).isEqualTo(3);
    }
}
