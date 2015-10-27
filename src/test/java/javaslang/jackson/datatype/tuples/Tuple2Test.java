package javaslang.jackson.datatype.tuples;

import javaslang.Tuple;
import javaslang.Tuple2;

public class Tuple2Test extends TupleTest<Tuple2<?, ?>> {

    @Override
    Class<?> clz() {
        return Tuple2.class;
    }

    @Override
    int arity() {
        return 2;
    }

    @Override
    Tuple2<?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail);
    }
}
