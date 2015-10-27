package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple3;

public class Tuple3Test extends TupleTest<Tuple3<?, ?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple3.class;
    }

    @Override
    int arity() {
        return 3;
    }

    @Override
    Tuple3<?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail);
    }
}
