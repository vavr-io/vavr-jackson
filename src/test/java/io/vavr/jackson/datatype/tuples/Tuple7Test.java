package io.vavr.jackson.datatype.tuples;

import io.vavr.Tuple;
import io.vavr.Tuple7;
import io.vavr.control.Option;
import tools.jackson.core.type.TypeReference;

public class Tuple7Test extends TupleTest<Tuple7<?, ?, ?, ?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple7.class;
    }

    @Override
    protected int arity() {
        return 7;
    }

    @Override
    protected Tuple7<?, ?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple7<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple7<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>>() {
        };
    }
}
