package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.Tuple;
import javaslang.Tuple4;
import javaslang.control.Option;

public class Tuple4Test extends TupleTest<Tuple4<?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple4.class;
    }

    @Override
    protected int arity() {
        return 4;
    }

    @Override
    protected Tuple4<?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple4<Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple4<Option<String>, Option<String>, Option<String>, Option<String>>>() {};
    }
}
