package io.vavr.jackson.datatype.tuples;

import io.vavr.Tuple;
import io.vavr.Tuple8;
import io.vavr.control.Option;
import tools.jackson.core.type.TypeReference;

public class Tuple8Test extends TupleTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple8.class;
    }

    @Override
    protected int arity() {
        return 8;
    }

    @Override
    protected Tuple8<?, ?, ?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple8<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple8<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>>() {
        };
    }
}
