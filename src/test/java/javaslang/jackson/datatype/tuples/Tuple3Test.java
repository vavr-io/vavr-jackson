package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.Tuple;
import javaslang.Tuple3;
import javaslang.control.Option;

public class Tuple3Test extends TupleTest<Tuple3<?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple3.class;
    }

    @Override
    protected int arity() {
        return 3;
    }

    @Override
    protected Tuple3<?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail);
    }

    @Override
    protected TypeReference<Tuple3<Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple3<Option<String>, Option<String>, Option<String>>>() {};
    }
}
