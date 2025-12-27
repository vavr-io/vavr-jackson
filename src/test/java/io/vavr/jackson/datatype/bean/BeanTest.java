package io.vavr.jackson.datatype.bean;

import io.vavr.collection.List;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeanTest extends BaseTest {

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
    void test1() {
        BeanObject src = new BeanObject();
        src.scalar = "s";
        src.value = List.of(1);
        String json = mapper().writer().writeValueAsString(src);
        assertThat(src).isEqualTo(mapper().readValue(json, BeanObject.class));
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
    void test2() {
        ComplexInnerClass innerSrc = new ComplexInnerClass();
        innerSrc.setScalar(10);
        innerSrc.setValues(List.of("Data1", "Data2", "Data3"));

        ComplexBeanObject src = new ComplexBeanObject();
        src.setScalar("Data Scalar");
        src.setValues(List.of(
            new ComplexInnerClass(10, List.of("Data1", "Data2", "Data3")),
            new ComplexInnerClass(12, List.of("Data3", "Data4", "Data5"))
        ));

        String json = mapper().writeValueAsString(src);

        ComplexBeanObject restored = mapper().readValue(json, ComplexBeanObject.class);
        restored.getValues().forEach(innerClass -> {
            assertThat(innerClass instanceof ComplexInnerClass).as("Instance of ComplexInnerClass").isTrue();
        });

        assertThat(src).isEqualTo(restored);
    }

    @Test
    void deserializeScalarNull() {
        // language=JSON
        String json = "{\"scalar\":null,\"value\":[]}";

        BeanObject actual = mapper().readValue(json, BeanObject.class);

        BeanObject expected = new BeanObject();
        expected.value = List.empty();
        assertThat(actual).isEqualTo(expected);
    }
}
