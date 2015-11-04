package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public abstract class TupleTest<T extends Tuple> extends BaseTest {

    abstract Class<?> clz();

    abstract int arity();

    abstract T ofObjects(Object head, Object tail);

    String genJsonTuple(Object head, Object tail) {
        java.util.Map<Object, Object> map = new HashMap<>();
        for (int i = 1; i <= arity(); i++) {
            map.put("_" + i, i == 1 ? head : tail);
        }
        return genJsonMap(map);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test1() throws IOException {
        T src = ofObjects(1, 17);
        String json = mapper().writeValueAsString(src);
        Assert.assertEquals(genJsonTuple(1, 17), json);
        T dst = (T) mapper().readValue(json, clz());
        Assert.assertEquals(src, dst);
    }
}
