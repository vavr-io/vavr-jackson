package javaslang.jackson.datatype.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javaslang.collection.List;
import javaslang.jackson.datatype.JavaslangModule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

            return xs != null ? xs.equals(l.xs) : l.xs == null;
        }

        @Override
        public int hashCode() {
            return xs != null ? xs.hashCode() : 0;
        }
    }

    public interface  AX {
        String getaString();
        int getAnInt();
    }

    public static class X implements AX {
        String aString ;
        int anInt ;

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

    @Test
    public void javaslang_jackson() throws IOException {
        L l = new L(List.of(new X("a", 1), new X("bbb", 42)));
        json_roundtrip_test(l, L.class);
    }

    public static <T> void json_roundtrip_test(T value, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());
        String asString = mapper.writeValueAsString(value);
        assertNotNull(asString);
        System.out.println(asString);
        final T value_decoded = mapper.readValue(asString, valueType);
        assertEquals(value, value_decoded);
    }
}