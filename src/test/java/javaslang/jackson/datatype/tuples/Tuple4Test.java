package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple4;

public class Tuple4Test extends TupleTest<Tuple4<?, ?, ?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple4.class;
    }

    @Override
    int arity() {
        return 4;
    }

    @Override
    Tuple4<?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail);
    }
}
