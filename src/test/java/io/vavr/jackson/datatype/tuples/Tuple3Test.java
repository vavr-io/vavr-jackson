package io.vavr.jackson.datatype.tuples;

import tools.jackson.core.type.TypeReference;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.control.Option;

public class Tuple3Test extends TupleTest<Tuple3<?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple3.class;
    }

    @Override
    protected int arity() {
        return 3;
    }

    @Override
    protected Tuple3<?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail);
    }

    @Override
    protected TypeReference<Tuple3<Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple3<Option<String>, Option<String>, Option<String>>>() {
        };
    }
}
