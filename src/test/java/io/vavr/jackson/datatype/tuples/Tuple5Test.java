package io.vavr.jackson.datatype.tuples;

import tools.jackson.core.type.TypeReference;
import io.vavr.Tuple;
import io.vavr.Tuple5;
import io.vavr.control.Option;

public class Tuple5Test extends TupleTest<Tuple5<?, ?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple5.class;
    }

    @Override
    protected int arity() {
        return 5;
    }

    @Override
    protected Tuple5<?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple5<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple5<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>>() {
        };
    }
}
