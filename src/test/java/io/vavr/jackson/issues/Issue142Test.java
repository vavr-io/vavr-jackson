package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Issue142Test extends BaseTest {

    public static class MyComparable implements Comparable<MyComparable> {

        private int v;

        public MyComparable(int v) {
            this.v = v;
        }

        @Override
        public int compareTo(MyComparable o) {
            return Integer.compare(o.v, v);  // reversed
        }

        @JsonValue
        public int getV() {
            return v;
        }

        @Override
        public String toString() {
            return "ke";  // the correct comparator should be used
        }
    }

    private ObjectMapper mapper;

    @BeforeEach
    public void before() {
        mapper = new ObjectMapper();
        SimpleModule module = new VavrModule()
            .addKeyDeserializer(MyComparable.class, new KeyDeserializer() {
                @Override
                public Object deserializeKey(String key, DeserializationContext ctxt) {
                    return new MyComparable(Integer.parseInt(key));
                }
            });
        mapper.registerModule(module);
    }

    @Test
    public void testMap() throws IOException {

        TreeMap<MyComparable, Integer> mp = TreeMap.<MyComparable, Integer>empty()
            .put(new MyComparable(1), 1)
            .put(new MyComparable(2), 2);

        String json = mapper.writeValueAsString(mp);
        Assertions.assertEquals("{\"2\":2,\"1\":1}", json);
        TreeMap<MyComparable, Integer> restored = mapper.readValue(json, new TypeReference<TreeMap<MyComparable, Integer>>() {
        });
        Assertions.assertEquals(restored, mp);
    }

    @Test
    public void testMultimap() throws IOException {

        TreeMultimap<MyComparable, Integer> mp = TreeMultimap.<Integer>withSeq().<MyComparable, Integer>empty()
            .put(new MyComparable(1), 1)
            .put(new MyComparable(2), 2);

        String json = mapper.writeValueAsString(mp);
        Assertions.assertEquals("{\"2\":[2],\"1\":[1]}", json);
        TreeMultimap<MyComparable, Integer> restored = mapper.readValue(json, new TypeReference<TreeMultimap<MyComparable, Integer>>() {
        });
        Assertions.assertEquals(restored, mp);
    }
}
