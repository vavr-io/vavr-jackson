package javaslang.jackson.datatype;

import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BeanTest extends BaseTest {

    static class BeanObject {
        public String scalar;
        public List<?> value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BeanObject that = (BeanObject) o;

            if (scalar != null ? !scalar.equals(that.scalar) : that.scalar != null) return false;
            return !(value != null ? !value.equals(that.value) : that.value != null);

        }

        @Override
        public int hashCode() {
            int result = scalar != null ? scalar.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }

    @Test
    public void test1() throws IOException {
        BeanObject src = new BeanObject();
        src.scalar = "s";
        src.value = List.of(1);
        String extended = mapper(false).writer().writeValueAsString(src);
        String compact = mapper(true).writer().writeValueAsString(src);
        Assert.assertEquals(mapper(false).readValue(extended, BeanObject.class), src);
        Assert.assertEquals(mapper(false).readValue(compact, BeanObject.class), src);
    }
}
