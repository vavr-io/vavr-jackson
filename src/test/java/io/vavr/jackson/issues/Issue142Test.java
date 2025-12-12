package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonValue;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.KeyDeserializer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.module.SimpleModule;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Issue142Test extends BaseTest {

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
    void before() {
        mapper = new ObjectMapper();
        SimpleModule module = new VavrModule()
            .addKeyDeserializer(MyComparable.class, new KeyDeserializer() {
                @Override
                public Object deserializeKey(String key, DeserializationContext ctxt) {
                    return new MyComparable(Integer.parseInt(key));
                }
            });
        mapper = mapper().rebuild().addModule(module).build();
    }

    @Test
    void map() throws IOException {

        TreeMap<MyComparable, Integer> mp = TreeMap.<MyComparable, Integer>empty()
            .put(new MyComparable(1), 1)
            .put(new MyComparable(2), 2);

        String json = mapper.writeValueAsString(mp);
        assertThat(json).isEqualTo("{\"2\":2,\"1\":1}");
        TreeMap<MyComparable, Integer> restored = mapper.readValue(json, new TypeReference<TreeMap<MyComparable, Integer>>() {
        });
        assertThat(mp).isEqualTo(restored);
    }

    @Test
    void multimap() throws IOException {

        TreeMultimap<MyComparable, Integer> mp = TreeMultimap.<Integer>withSeq().<MyComparable, Integer>empty()
            .put(new MyComparable(1), 1)
            .put(new MyComparable(2), 2);

        String json = mapper.writeValueAsString(mp);
        assertThat(json).isEqualTo("{\"2\":[2],\"1\":[1]}");
        TreeMultimap<MyComparable, Integer> restored = mapper.readValue(json, new TypeReference<TreeMultimap<MyComparable, Integer>>() {
        });
        assertThat(mp).isEqualTo(restored);
    }
}
