package io.vavr.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;

public abstract class TupleTest<T extends Tuple> extends BaseTest {

    protected abstract Class<?> clz();

    protected abstract int arity();

    protected abstract T ofObjects(Object head, Object tail);

    protected abstract TypeReference<? extends T> typeReferenceWithOption();

    private String genJsonTuple(Object head, Object tail) {
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

    @Test
    public void testWithOption() throws IOException {
        verifySerialization(typeReferenceWithOption(), List.of(
                Tuple.of(ofObjects(Option.some("1"), Option.none()), genJsonTuple("1", null)),
                Tuple.of(ofObjects(Option.some("1"), Option.some("17")), genJsonTuple("1", "17")),
                Tuple.of(ofObjects(Option.none(), Option.some("17")), genJsonTuple(null, "17")),
                Tuple.of(ofObjects(Option.none(), Option.none()), genJsonTuple(null, null))
        ));
    }
}
