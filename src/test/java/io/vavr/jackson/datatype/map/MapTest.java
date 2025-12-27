package io.vavr.jackson.datatype.map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import java.io.IOException;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.KeyDeserializer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.deser.ContextualKeyDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;
import tools.jackson.databind.deser.std.StdScalarDeserializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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
        assertThat(json).isEqualTo(genJsonMap(javaObject));

        Map<?, ?> restored = (Map<?, ?>) mapper().readValue(json, clz());
        assertThat(vavrObject).isEqualTo(restored);
    }

    @Test
    void test2() throws IOException {
        ObjectMapper mapper = ((JsonMapper) mapper()).rebuild().addMixIn(clz(), WrapperObject.class).build();
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(clz().getName(), plainJson)).isEqualTo(wrappedJson);
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void test3()  throws IOException {
        ObjectMapper mapper = ((JsonMapper) mapper()).rebuild().addMixIn(clz(), WrapperArray.class).build();
        Map<?, ?> src = emptyMap().put("1", 2);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToArray(clz().getName(), plainJson)).isEqualTo(wrappedJson);
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(wrappedJson, clz());
        assertThat(restored).isEqualTo(src);
        assertThat(restored).isEqualTo(src);
    }

    // Issue 138: Cannot deserialize to Map<String, String>
    // https://github.com/vavr-io/vavr-jackson/issues/138
    @Test
    public void deserializeNullValue() throws IOException {
        Map<String, String> stringStringMap = mapper().readValue("{\"1\":null}", new TypeReference<Map<String, String>>() {
        });
        Map<String, Object> stringObjectMap = mapper().readValue("{\"1\":null}", new TypeReference<Map<String, Object>>() {
        });

        assertThat(stringStringMap).isEqualTo(emptyMap().put("1", null));
        assertThat(stringObjectMap).isEqualTo(emptyMap().put("1", null));
    }

    @Test
    void test4() {
        assertThatExceptionOfType(JacksonException.class).isThrownBy(() -> mapper().readValue("{1: 1}", clz()));
    }

    @Test
    void serializable() throws IOException {
        ObjectMapper mapper = mapper();
        Map<?, ?> src = emptyMap().put("1", 2);
        Map<?, ?> restored = (Map<?, ?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    void withOption() throws Exception {
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
        assertThat(slangJson).isEqualTo(javaJson);
        JaxbXmlSerializeVavr restored = mapper.readValue(slangJson, JaxbXmlSerializeVavr.class);
        assertThat(restored.transitTypes.get("key1").get()).isEqualTo("1");
        assertThat(restored.transitTypes.get("key2").get()).isEqualTo("2");
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
        assertThat(slangJson).isEqualTo(javaJson);
        XmlSerializeVavr restored = mapper.readValue(slangJson, XmlSerializeVavr.class);
        assertThat(restored.transitTypes.get("key1").get()).isEqualTo("1");
        assertThat(restored.transitTypes.get("key2").get()).isEqualTo("2");
    }

    static class CustomKey {
        private final int id;

        CustomKey(int id) {
            this.id = id;
        }
    }

    static class CustomValue {
        private final String value;

        CustomValue(String value) {
            this.value = value;
        }
    }

    static class Model {
        private final Map<CustomKey, CustomValue> map;

        @JsonCreator
        Model(@JsonProperty("map")
              @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentUsing = CustomValueDeserializer.class)
              Map<CustomKey, CustomValue> map) {
            this.map = map;
        }

        @JsonProperty
        @JsonSerialize(keyUsing = CustomKeySerializer.class, contentUsing = CustomValueSerializer.class)
        public Map<CustomKey, CustomValue> getMap() {
            return map;
        }
    }

    static class CustomKeySerializer extends ValueSerializer<CustomKey> {
        @Override
        public void serialize(CustomKey value, JsonGenerator jgen, SerializationContext context) {
            jgen.writeName(String.valueOf(value.id));
        }
    }

    static class CustomKeyDeserializer extends KeyDeserializer {
        @Override
        public CustomKey deserializeKey(String key, DeserializationContext ctxt) {
            return new CustomKey(Integer.parseInt(key));
        }
    }

    static class CustomValueSerializer extends ValueSerializer<CustomValue> {
        @Override
        public void serialize(CustomValue value, JsonGenerator jgen, SerializationContext context) {
            jgen.writeString(value.value);
        }
    }

    static class CustomValueDeserializer extends StdDeserializer<CustomValue> {

        CustomValueDeserializer() {
            super(CustomValue.class);
        }

        @Override
        public CustomValue deserialize(JsonParser p, DeserializationContext context) {
            return new CustomValue(p.getValueAsString());
        }
    }

    @Test
    void contextualSerialization() throws IOException {
        Map<CustomKey, CustomValue> empty = emptyMap();
        Map<CustomKey, CustomValue> map = empty.put(new CustomKey(123), new CustomValue("test"));

        Model source = new Model(map);
        String json = mapper().writeValueAsString(source);
        assertThat(json).isEqualTo("{\"map\":{\"123\":\"test\"}}");

        Model restored = mapper().readValue(json, Model.class);
        assertThat(restored.map.size()).isEqualTo(1);
        assertThat(restored.map.get()._1.id).isEqualTo(123);
        assertThat(restored.map.get()._2.value).isEqualTo("test");
    }

    static class BeanWithModifiedKey {
        @JsonDeserialize(keyUsing = ClassNameKeyDeserializer.class)
        Multimap<String, Integer> map;
    }

    static class ClassNameKeyDeserializer extends KeyDeserializer implements ContextualKeyDeserializer {

        final String className;

        ClassNameKeyDeserializer() {
            this("N/A");
        }

        ClassNameKeyDeserializer(String className) {
            this.className = className;
        }

        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) {
            return className;
        }

        @Override
        public ClassNameKeyDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) {
            return new ClassNameKeyDeserializer(ctxt.getContextualType().getKeyType().getRawClass().getSimpleName());
        }
    }

    static class BeanWithModifiedContent {
        @JsonDeserialize(contentUsing = ClassNameContentDeserializer.class)
        Map<Integer, String> map;
    }

    static class ClassNameContentDeserializer extends StdScalarDeserializer<Object> {

        final String className;

        ClassNameContentDeserializer() {
            this("N/A");
        }

        ClassNameContentDeserializer(String className) {
            super(String.class);
            this.className = className;
        }

        @Override
        public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
            return new ClassNameContentDeserializer(ctxt.getContextualType().getRawClass().getSimpleName());
        }

        @Override
        public String deserialize(JsonParser p, DeserializationContext context) {
            return className;
        }
    }

    @Test
    void secondaryKeyContextualization() throws IOException {
        BeanWithModifiedKey bean = mapper().readValue("{\"map\":{\"Will be replaced\":[1,2,3]}}", BeanWithModifiedKey.class);
        assertIterableEquals(Arrays.asList(1, 2, 3), bean.map.get("String").get());
    }

    @Test
    void secondaryContentContextualization() throws IOException {
        BeanWithModifiedContent bean = mapper().readValue("{\"map\":{\"1\":\"Will be replaced\"}}", BeanWithModifiedContent.class);
        assertThat(bean.map.get(1).get()).isEqualTo("String");
    }
}
