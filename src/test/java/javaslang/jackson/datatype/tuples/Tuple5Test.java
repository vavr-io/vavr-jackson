package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple5;

public class Tuple5Test extends TupleTest<Tuple5<?, ?, ?, ?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple5.class;
    }

    @Override
    int arity() {
        return 5;
    }

    @Override
    Tuple5<?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail);
    }
}
