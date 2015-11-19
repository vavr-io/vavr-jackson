package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.Tuple;
import javaslang.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public abstract class TupleTest<T extends Tuple> extends BaseTest {

    abstract Class<?> clz();

    abstract int arity();

    abstract T ofObjects(Object head, Object tail);

    String genJsonTuple(Object head, Object tail) {
        java.util.List<Object> map = new ArrayList<>();
        for (int i = 0; i < arity(); i++) {
            map.add(i == 0 ? head : tail);
        }
        return genJsonList(map.toArray());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test1() throws IOException {
        T src = ofObjects(1, 17);
        String json = mapper().writeValueAsString(src);
        Assert.assertEquals(genJsonTuple(1, 17), json);
        Assert.assertEquals(src, mapper().readValue(json, clz()));
        Assert.assertEquals(src, mapper().readValue(json, Tuple.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperObject.class);
        T src = ofObjects(1, 17);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToObject(clz().getName(), plainJson));
        T restored = (T) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = mapper().addMixIn(clz(), WrapperArray.class);
        T src = ofObjects(1, 17);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        Assert.assertEquals(wrappedJson, wrapToArray(clz().getName(), plainJson));
        T restored = (T) mapper.readValue(wrappedJson, clz());
        Assert.assertEquals(src, restored);
    }
}
