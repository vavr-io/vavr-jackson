package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple8;

public class Tuple8Test extends TupleTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple8.class;
    }

    @Override
    int arity() {
        return 8;
    }

    @Override
    Tuple8<?, ?, ?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail, tail, tail);
    }
}
