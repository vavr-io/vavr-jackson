package javaslang.jackson.datatype.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import javaslang.control.Option;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class BeanAnnotationTest extends BaseTest {

    public static final String SOME = "Some";

    public static final String EMPTY_JSON = "{}";

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class BeanObjectOptional {

        private Option<String> field;

        public BeanObjectOptional() {
            this.field = Option.none();
        }

        public BeanObjectOptional(Option<String> field) {
            this.field = field;
        }

        public Option<String> getField() {
            return field;
        }
    }

    @Test
    public void test1() throws Exception {
        BeanObjectOptional bean = new BeanObjectOptional(Option.of(SOME));
        String json = mapper().writer().writeValueAsString(bean);
        Assert.assertTrue(json.contains(SOME));

    }

    @Test
    public void test2() throws Exception {
        BeanObjectOptional bean = new BeanObjectOptional();
        String json = mapper().writer().writeValueAsString(bean);
        Assert.assertEquals(EMPTY_JSON, json);
        BeanObjectOptional restored = mapper().readValue(EMPTY_JSON, BeanObjectOptional.class);
        Assert.assertEquals(bean.field, restored.field);
    }

}
