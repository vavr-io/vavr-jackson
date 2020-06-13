package io.vavr.jackson.datatype.multimap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Assertions.assertEquals(genJsonMap(javaObject), json);

        Multimap<?, ?> restored = (Multimap<?, ?>) mapper().readValue(json, clz());
        Assertions.assertEquals(restored, vavrObject);
    }

    @Test
    void testSerializable() throws IOException {
        ObjectMapper mapper = mapper();
        Multimap<Object, Object> src = emptyMap().put("1", 2).put("2", 3).put("2", 4);
        Multimap<?, ?> restored = (Multimap<?, ?>) mapper.readValue(mapper.writeValueAsString(src), clz());
        checkSerialization(restored);
    }

    @Test
    public void testDeserializeValueNull() {
        Assertions.assertThrows(JsonMappingException.class, () -> mapper().readValue("{\"k\":null}", clz()));
    }

    @Test
    void testWithOption() throws Exception {
        Multimap<String, Option<Integer>> multimap = this.<String, Option<Integer>>emptyMap().put("1", Option.some(1)).put("1", Option.none());
        String json = genJsonMap(HashMap.of("1", asList(1, null)).toJavaMap());

        verifySerialization(typeReferenceWithOption(), io.vavr.collection.List.of(Tuple.of(multimap, json)));
    }

    static class CustomKey {
        private final int id;

        CustomKey(int id) {
            this.id = id;
        }
    }

    static class Model {
        private final Multimap<CustomKey, String> map;

        @JsonCreator
        Model(@JsonProperty("map") @JsonDeserialize(keyUsing = CustomKeyDeserializer.class) Multimap<CustomKey, String> map) {
            this.map = map;
        }

        @JsonProperty
        @JsonSerialize(keyUsing = CustomKeySerializer.class)
        public Multimap<CustomKey, String> getMap() {
            return map;
        }
    }

    static class CustomKeySerializer extends JsonSerializer<CustomKey> {
        @Override
        public void serialize(CustomKey value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeFieldName(String.valueOf(value.id));
        }
    }

    static class CustomKeyDeserializer extends KeyDeserializer {
        @Override
        public CustomKey deserializeKey(String key, DeserializationContext ctxt) {
            return new CustomKey(Integer.parseInt(key));
        }
    }

    @Test
    void testContextualSerialization() throws IOException {
        Multimap<CustomKey, String> empty = emptyMap();
        Multimap<CustomKey, String> map = empty.put(new CustomKey(123), "test");

        Model source = new Model(map);
        String json = mapper().writeValueAsString(source);
        assertEquals("{\"map\":{\"123\":[\"test\"]}}", json);

        Model restored = mapper().readValue(json, Model.class);
        assertEquals(1, restored.map.size());
        assertEquals(123, restored.map.get()._1.id);
        assertEquals("test", restored.map.get()._2);
    }
}
