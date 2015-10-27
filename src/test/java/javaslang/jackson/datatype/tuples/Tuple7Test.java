package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple7;

public class Tuple7Test extends TupleTest<Tuple7<?, ?, ?, ?, ?, ?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple7.class;
    }

    @Override
    int arity() {
        return 7;
    }

    @Override
    Tuple7<?, ?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail, tail);
    }
}
