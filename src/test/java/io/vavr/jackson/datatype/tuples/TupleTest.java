package io.vavr.jackson.datatype.tuples;

import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TupleTest<T> extends BaseTest {

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
    void test1() throws IOException {
        T src = ofObjects(1, 17);
        String json = mapper().writeValueAsString(src);
        assertThat(json).isEqualTo(genJsonTuple(1, 17));
        assertThat(mapper().readValue(json, clz())).isEqualTo(src);
    }

    @SuppressWarnings("unchecked")
    @Test
    void test2() throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(clz(), WrapperObject.class).build();
        T src = ofObjects(1, 17);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToObject(clz().getName(), plainJson)).isEqualTo(wrappedJson);
        T restored = (T) mapper.readValue(wrappedJson, clz());
        assertThat(restored).isEqualTo(src);
    }

    @SuppressWarnings("unchecked")
    @Test
    void test3() throws IOException {
        ObjectMapper mapper = mapper().rebuild().addMixIn(clz(), WrapperArray.class).build();
        T src = ofObjects(1, 17);
        String plainJson = mapper().writeValueAsString(src);
        String wrappedJson = mapper.writeValueAsString(src);
        assertThat(wrapToArray(clz().getName(), plainJson)).isEqualTo(wrappedJson);
        T restored = (T) mapper.readValue(wrappedJson, clz());
        assertThat(restored).isEqualTo(src);
    }

    @Test
    void withOption() throws IOException {
        verifySerialization(typeReferenceWithOption(), List.of(
            Tuple.of(ofObjects(Option.some("1"), Option.none()), genJsonTuple("1", null)),
            Tuple.of(ofObjects(Option.some("1"), Option.some("17")), genJsonTuple("1", "17")),
            Tuple.of(ofObjects(Option.none(), Option.some("17")), genJsonTuple(null, "17")),
            Tuple.of(ofObjects(Option.none(), Option.none()), genJsonTuple(null, null))
        ));
    }
}
