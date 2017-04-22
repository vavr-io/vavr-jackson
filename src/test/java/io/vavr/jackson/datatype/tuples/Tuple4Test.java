package io.vavr.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import io.vavr.Tuple;
import io.vavr.Tuple4;
import io.vavr.control.Option;

public class Tuple4Test extends TupleTest<Tuple4<?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple4.class;
    }

    @Override
    protected int arity() {
        return 4;
    }

    @Override
    protected Tuple4<?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple4<Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple4<Option<String>, Option<String>, Option<String>, Option<String>>>() {};
    }
}
