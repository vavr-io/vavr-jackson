package io.vavr.jackson.datatype.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.vavr.Lazy;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AbstractContentTest {

    public static <T> void json_roundtrip_test(T value, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        String asString = mapper.writeValueAsString(value);
        assertNotNull(asString);
        final T value_decoded = mapper.readValue(asString, valueType);
        assertEquals(value, value_decoded);
    }

    @Test
    void testList() throws IOException {
        L l = new L(List.of(new X("a", 1), new X("bbb", 42)));
        json_roundtrip_test(l, L.class);
    }

    @Test
    void testMap() throws IOException {
        M m = new M(HashMap.of(1, new X("a", 1), 42, new X("bbb", 42)));
        json_roundtrip_test(m, M.class);
    }

    @Test
    void testValue() throws IOException {
        V v = new V()
            .setLazy(Lazy.of(() -> new X("b", 2)))
            .setOption(Option.of(new X("c", 3)));
        json_roundtrip_test(v, V.class);
    }

    public interface AX {
        String getaString();

        int getAnInt();
    }

    public static class L {
        @JsonDeserialize(contentAs = X.class)
        private List<AX> xs;

        public L() {
        }

        public L(List<AX> xs) {
            this.xs = xs;
        }

        public List<AX> getXs() {
            return xs;
        }

        public void setXs(List<AX> xs) {
            this.xs = xs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            L l = (L) o;

            return xs != null ? xs.equals(l.xs) : l.xs == null;
        }

        @Override
        public int hashCode() {
            return xs != null ? xs.hashCode() : 0;
        }
    }

    public static class M {
        @JsonDeserialize(contentAs = X.class)
        private Map<Integer, AX> xs;

        public M() {
        }

        public M(Map<Integer, AX> xs) {
            this.xs = xs;
        }

        public Map<Integer, AX> getXs() {
            return xs;
        }

        public void setXs(Map<Integer, AX> xs) {
            this.xs = xs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            M l = (M) o;

            return xs != null ? xs.equals(l.xs) : l.xs == null;
        }

        @Override
        public int hashCode() {
            return xs != null ? xs.hashCode() : 0;
        }
    }

    public static class V {
        @JsonDeserialize(contentAs = X.class)
        private Lazy<AX> lazy;
        @JsonDeserialize(contentAs = X.class)
        private Option<AX> option;

        public V() {
        }

        public Lazy<AX> getLazy() {
            return lazy;
        }

        public V setLazy(Lazy<AX> lazy) {
            this.lazy = lazy;
            return this;
        }

        public Option<AX> getOption() {
            return option;
        }

        public V setOption(Option<AX> option) {
            this.option = option;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            V v = (V) o;

            if (lazy != null ? !lazy.equals(v.lazy) : v.lazy != null) return false;
            return option != null ? option.equals(v.option) : v.option == null;
        }

        @Override
        public int hashCode() {
            int result = lazy != null ? lazy.hashCode() : 0;
            result = 31 * result + (option != null ? option.hashCode() : 0);
            return result;
        }
    }

    public static class X implements AX {
        String aString;
        int anInt;

        public X() {
        }

        public X(String aString, int anInt) {
            this.aString = aString;
            this.anInt = anInt;
        }

        public String getaString() {
            return aString;
        }

        public void setaString(String aString) {
            this.aString = aString;
        }

        public int getAnInt() {
            return anInt;
        }

        public void setAnInt(int anInt) {
            this.anInt = anInt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            X x = (X) o;

            if (anInt != x.anInt) return false;
            return aString != null ? aString.equals(x.aString) : x.aString == null;
        }

        @Override
        public int hashCode() {
            int result = aString != null ? aString.hashCode() : 0;
            result = 31 * result + anInt;
            return result;
        }
    }
}
