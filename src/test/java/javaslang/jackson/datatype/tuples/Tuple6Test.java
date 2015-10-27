package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple6;

public class Tuple6Test extends TupleTest<Tuple6<?, ?, ?, ?, ?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple6.class;
    }

    @Override
    int arity() {
        return 6;
    }

    @Override
    Tuple6<?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail);
    }
}
