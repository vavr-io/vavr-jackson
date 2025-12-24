package io.vavr.jackson.datatype.map;

import io.vavr.collection.Map;
import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;
import io.vavr.control.Option;
import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import static org.assertj.core.api.Assertions.assertThat;

class TreeMapTest extends MapTest {
    @Override
    Class<?> clz() {
        return TreeMap.class;
    }

    @Override
    protected TypeReference<TreeMap<String, Option<Integer>>> typeReferenceWithOption() {
        return new TypeReference<TreeMap<String, Option<Integer>>>() {
        };
    }

    @Override
    <K, V> Map<K, V> emptyMap() {
        return TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }

    static class Clazz {
        private SortedMap<Integer, Integer> set;

        public SortedMap<Integer, Integer> getSet() {
            return set;
        }

        public void setSet(SortedMap<Integer, Integer> set) {
            this.set = set;
        }

        public boolean equals(Object o) {
            return Objects.equals(set, ((Clazz) o).set);
        }

        public int hashCode() {
            return set.hashCode();
        }
    }

    @Test
    void deserializeToSortedMap() throws IOException {
        Clazz c = new Clazz();
        c.setSet(TreeMap.of(1, 3, 5, 7));
        Clazz dc = mapper().readValue(mapper().writeValueAsString(c), Clazz.class);
        assertThat(dc).isEqualTo(c);
    }
}
