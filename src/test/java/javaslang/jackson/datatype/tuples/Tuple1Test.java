package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple1;

public class Tuple1Test extends TupleTest<Tuple1<?>> {

    @Override
    Class<?> clz() {
        return Tuple1.class;
    }

    @Override
    int arity() {
        return 1;
    }

    @Override
    Tuple1<?> ofObjects(Object head, Object tail) {
        return Tuple.of(head);
    }
}
