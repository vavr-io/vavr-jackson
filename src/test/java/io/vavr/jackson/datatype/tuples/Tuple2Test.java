package io.vavr.jackson.datatype.tuples;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import tools.jackson.core.type.TypeReference;

public class Tuple2Test extends TupleTest<Tuple2<?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple2.class;
    }

    @Override
    protected int arity() {
        return 2;
    }

    @Override
    protected Tuple2<?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail);
    }

    @Override
    protected TypeReference<Tuple2<Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple2<Option<String>, Option<String>>>() {
        };
    }
}
