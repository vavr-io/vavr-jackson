package io.vavr.jackson.datatype.multimap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.KeyDeserializer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.deser.ContextualKeyDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public abstract class MultimapTest extends BaseTest {

    abstract Class<?> clz();

    abstract <K, V> Multimap<K, V> emptyMap();

    protected abstract TypeReference<? extends Multimap<String, Option<Integer>>> typeReferenceWithOption();

    @Test
    void test1() throws IOException {
        Multimap<Object, Object> vavrObject = emptyMap().put("1", 2).put("2", 3).put("2", 4);
        java.util.Map<Object, List<Object>> javaObject = new java.util.HashMap<>();
        javaObject.put("1", Collections.singletonList(2));
        List<Object> list = new ArrayList<>();
        Collections.addAll(list, 3, 4);
        javaObject.put("2", list);

        String json = mapper().writer().writeValueAsString(vavrObject);
        assertThat(json).isEqualTo(genJsonMap(javaObject));

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        assertThat(vavrObject).isEqualTo(restored);
    }

    @Test
    void serializable() throws IOException {
        ObjectMapper mapper = mapper();
        Multimap<Object, Object> src = emptyMap().put("1", 2).put("2", 3).put("2", 4);
        Multimap<?, ?> restored = (Multimap<?, ?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    public void deserializeValueNull() {
        assertThatExceptionOfType(DatabindException.class).isThrownBy(() -> mapper().readValue("{\"k\":null}", clz()));
    }

    @Test
    void withOption() throws Exception {
        Multimap<String, Option<Integer>> multimap = this.<String, Option<Integer>>emptyMap().put("1", Option.some(1))
            .put("1", Option.none());
        String json = genJsonMap(HashMap.of("1", asList(1, null)).toJavaMap());

        verifySerialization(typeReferenceWithOption(), io.vavr.collection.List.of(Tuple.of(multimap, json)));
    }

    static class CustomKey {
        private final int id;

        CustomKey(int id) {
            this.id = id;
        }
    }

    @JsonSerialize(using = CustomElementSerializer.class)
    @JsonDeserialize(using = CustomElementDeserializer.class)
    static class CustomElement {
        private final String value;

        CustomElement(String value) {
            this.value = value;
        }
    }

    static class ModelWithCustomKey {
        private final Multimap<CustomKey, String> map;

        @JsonCreator
        ModelWithCustomKey(@JsonProperty("map")
                           @JsonDeserialize(keyUsing = CustomKeyDeserializer.class) Multimap<CustomKey, String> map) {
            this.map = map;
        }

        @JsonProperty
        @JsonSerialize(keyUsing = CustomKeySerializer.class)
        public Multimap<CustomKey, String> getMap() {
            return map;
        }
    }

    static class ModelWithCustomKeyAndElement {
        private final Multimap<CustomKey, CustomElement> map;

        @JsonCreator
        ModelWithCustomKeyAndElement(@JsonProperty("map")
                                     @JsonDeserialize(keyUsing = CustomKeyDeserializer.class) Multimap<CustomKey, CustomElement> map) {
            this.map = map;
        }

        @JsonProperty
        @JsonSerialize(keyUsing = CustomKeySerializer.class)
        public Multimap<CustomKey, CustomElement> getMap() {
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

    static class CustomElementSerializer extends ValueSerializer<CustomElement> {
        @Override
        public void serialize(CustomElement value, JsonGenerator jgen, SerializationContext context) {
            jgen.writeString(value.value);
        }
    }

    static class CustomElementDeserializer extends StdDeserializer<CustomElement> {

        CustomElementDeserializer() {
            super(CustomElement.class);
        }

        @Override
        public CustomElement deserialize(JsonParser p, DeserializationContext context) {
            return new CustomElement(p.getValueAsString());
        }
    }

    @Test
    void contextualizationOfKey() throws IOException {
        Multimap<CustomKey, String> empty = emptyMap();
        Multimap<CustomKey, String> map = empty.put(new CustomKey(123), "test");

        ModelWithCustomKey source = new ModelWithCustomKey(map);
        String json = mapper().writeValueAsString(source);
        assertThat(json).isEqualTo("{\"map\":{\"123\":[\"test\"]}}");

        ModelWithCustomKey restored = mapper().readValue(json, ModelWithCustomKey.class);
        assertThat(restored.map.size()).isEqualTo(1);
        assertThat(restored.map.get()._1.id).isEqualTo(123);
        assertThat(restored.map.get()._2).isEqualTo("test");
    }

    @Test
    void contextualizationOfKeyAndElement() throws IOException {
        Multimap<CustomKey, CustomElement> empty = emptyMap();
        Multimap<CustomKey, CustomElement> map = empty.put(new CustomKey(123), new CustomElement("test"));

        ModelWithCustomKeyAndElement source = new ModelWithCustomKeyAndElement(map);
        String json = mapper().writeValueAsString(source);
        assertThat(json).isEqualTo("{\"map\":{\"123\":[\"test\"]}}");

        ModelWithCustomKeyAndElement restored = mapper().readValue(json, ModelWithCustomKeyAndElement.class);
        assertThat(restored.map.size()).isEqualTo(1);
        assertThat(restored.map.get()._1.id).isEqualTo(123);
        assertThat(restored.map.get()._2.value).isEqualTo("test");
    }

    static class MyBean {
        @JsonDeserialize(keyUsing = ClassNameDeserializer.class)
        Multimap<String, Integer> map;
    }

    static class ClassNameDeserializer extends KeyDeserializer implements ContextualKeyDeserializer {

        final String className;

        ClassNameDeserializer() {
            this("N/A");
        }

        ClassNameDeserializer(String className) {
            this.className = className;
        }

        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) {
            return className;
        }

        @Override
        public ClassNameDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) {
            return new ClassNameDeserializer(ctxt.getContextualType().getKeyType().getRawClass().getSimpleName());
        }
    }

    @Test
    void secondaryContextualization() throws IOException {
        MyBean bean = mapper().readValue("{\"map\":{\"Will be replaced\":[1,2,3]}}", MyBean.class);
        assertIterableEquals(Arrays.asList(1, 2, 3), bean.map.get("String").get());
    }
}
