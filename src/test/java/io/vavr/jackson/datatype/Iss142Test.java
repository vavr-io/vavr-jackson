package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Iss142Test extends BaseTest {

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

    @Before
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

        Map<MyComparable, Integer> mp = TreeMap.<MyComparable, Integer>empty()
                .put(new MyComparable(1), 1)
                .put(new MyComparable(2), 2);

        String json = mapper.writeValueAsString(mp);
        Assert.assertEquals("{\"2\":2,\"1\":1}", json);
        Map<MyComparable, Integer> restored = mapper.readValue(json, new TypeReference<TreeMap<MyComparable, Integer>>() {});
        Assert.assertEquals(restored, mp);
    }

    @Test
    public void testMultimap() throws IOException {

        Multimap<MyComparable, Integer> mp = TreeMultimap.<Integer>withSeq().<MyComparable, Integer>empty()
                .put(new MyComparable(1), 1)
                .put(new MyComparable(2), 2);

        String json = mapper.writeValueAsString(mp);
        Assert.assertEquals("{\"2\":[2],\"1\":[1]}", json);
        Multimap<MyComparable, Integer> restored = mapper.readValue(json, new TypeReference<TreeMultimap<MyComparable, Integer>>() {});
        Assert.assertEquals(restored, mp);
    }
}
