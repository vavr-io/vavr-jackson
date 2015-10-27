package javaslang.jackson.datatype.tuples;

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

    String genJsonTuple(Class<?> clz, Object head, Object tail) {
        java.util.Map<Object, Object> map = new HashMap<>();
        for (int i = 1; i <= arity(); i++) {
            map.put("_" + i, i == 1 ? head : tail);
        }
        return genJsonMap(clz, map);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test1() throws IOException {
        T src = ofObjects(1, 17);
        String json = mapper(false).writeValueAsString(src);
        Assert.assertEquals(genJsonTuple(clz(), 1, 17), json);
        T dst = (T) mapper(false).readValue(json, clz());
        Assert.assertEquals(src, dst);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test2() throws IOException {
        ObjectWriter writer = mapper(true).writer();
        T src = ofObjects(1, 2);
        String json = writer.writeValueAsString(src);
        Assert.assertEquals(genJsonTuple(null, 1, 2), json);
        T dst1 = (T) mapper(false).readValue(json, clz());
        Assert.assertEquals(src, dst1);
        java.util.Map<?, ?> dst2 = (java.util.Map<?, ?>) mapper(false).readValue(json, Object.class);
        Assert.assertEquals(dst2.get("_1"), 1);
        if(arity() > 1) {
            Assert.assertEquals(dst2.get("_2"), 2);
        }
    }
}
