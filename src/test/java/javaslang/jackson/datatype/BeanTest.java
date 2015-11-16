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
        String json = mapper().writer().writeValueAsString(src);
        Assert.assertEquals(mapper().readValue(json, BeanObject.class), src);
    }

    static class ComplexInnerClass {
        private Integer scalar;
        private List<String> values;

        public ComplexInnerClass() {
        }

        public ComplexInnerClass(Integer scalar, List<String> values) {
            this.scalar = scalar;
            this.values = values;
        }

        public Integer getScalar() {
            return scalar;
        }

        public void setScalar(Integer scalar) {
            this.scalar = scalar;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ComplexInnerClass that = (ComplexInnerClass) o;

            if (scalar != null ? !scalar.equals(that.scalar) : that.scalar != null) return false;
            return !(values != null ? !values.equals(that.values) : that.values != null);

        }

        @Override
        public int hashCode() {
            int result = scalar != null ? scalar.hashCode() : 0;
            result = 31 * result + (values != null ? values.hashCode() : 0);
            return result;
        }
    }

    static class ComplexBeanObject {
        private String scalar;
        private List<ComplexInnerClass> values;

        public String getScalar() {
            return scalar;
        }

        public void setScalar(String scalar) {
            this.scalar = scalar;
        }

        public List<ComplexInnerClass> getValues() {
            return values;
        }

        public void setValues(List<ComplexInnerClass> values) {
            this.values = values;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ComplexBeanObject that = (ComplexBeanObject) o;

            if (scalar != null ? !scalar.equals(that.scalar) : that.scalar != null) return false;
            return !(values != null ? !values.equals(that.values) : that.values != null);

        }

        @Override
        public int hashCode() {
            int result = scalar != null ? scalar.hashCode() : 0;
            result = 31 * result + (values != null ? values.hashCode() : 0);
            return result;
        }
    }

    @Test
    public void test2() throws IOException {
        ComplexInnerClass innerSrc = new ComplexInnerClass();
        innerSrc.setScalar(10);
        innerSrc.setValues(List.ofAll("Data1", "Data2", "Data3"));

        ComplexBeanObject src = new ComplexBeanObject();
        src.setScalar("Data Scalar");
        src.setValues(List.ofAll(
                new ComplexInnerClass(10, List.ofAll("Data1", "Data2", "Data3")),
                new ComplexInnerClass(12, List.ofAll("Data3", "Data4", "Data5"))
        ));

        String json = mapper().writeValueAsString(src);

        ComplexBeanObject restored = mapper().readValue(json, ComplexBeanObject.class);
        restored.getValues().forEach(innerClass -> {
            Assert.assertTrue("Instance of ComplexInnerClass", innerClass instanceof ComplexInnerClass);
        });

        Assert.assertEquals(restored, src);
    }

}
