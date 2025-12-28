package io.vavr.jackson.datatype.bean;

import io.vavr.Lazy;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractContentTest {

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

            return Objects.equals(xs, l.xs);
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

            return Objects.equals(xs, l.xs);
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

        public V setLazy(Lazy<AX> lazy) {
            this.lazy = lazy;
            return this;
        }

        public V setOption(Option<AX> option) {
            this.option = option;
            return this;
        }

        public Lazy<AX> getLazy() {
            return lazy;
        }

        public Option<AX> getOption() {
            return option;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            V v = (V) o;

            if (!Objects.equals(lazy, v.lazy)) return false;
            return Objects.equals(option, v.option);
        }

        @Override
        public int hashCode() {
            int result = lazy != null ? lazy.hashCode() : 0;
            result = 31 * result + (option != null ? option.hashCode() : 0);
            return result;
        }
    }

    public interface AX {
        String getaString();

        int getAnInt();
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
            return Objects.equals(aString, x.aString);
        }

        @Override
        public int hashCode() {
            int result = aString != null ? aString.hashCode() : 0;
            result = 31 * result + anInt;
            return result;
        }
    }

    @Test
    void list() {
        L l = new L(List.of(new X("a", 1), new X("bbb", 42)));
        json_roundtrip_test(l, L.class);
    }

    @Test
    void map() {
        M m = new M(HashMap.of(1, new X("a", 1), 42, new X("bbb", 42)));
        json_roundtrip_test(m, M.class);
    }

    @Test
    void value() {
        V v = new V()
            .setLazy(Lazy.of(() -> new X("b", 2)))
            .setOption(Option.of(new X("c", 3)));
        json_roundtrip_test(v, V.class);
    }

    public static <T> void json_roundtrip_test(T value, Class<T> valueType) {
        ObjectMapper mapper = JsonMapper.builder()
            .addModule(new VavrModule())
            .accessorNaming(new DefaultAccessorNamingStrategy.Provider()
                .withFirstCharAcceptance(true, false)). build();
        String asString = mapper.writeValueAsString(value);
        assertThat(asString).isNotNull();
        final T value_decoded = mapper.readValue(asString, valueType);
        assertThat(value_decoded).isEqualTo(value);
    }
}
