package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.Tuple;
import javaslang.Tuple7;
import javaslang.control.Option;

public class Tuple7Test extends TupleTest<Tuple7<?, ?, ?, ?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple7.class;
    }

    @Override
    protected int arity() {
        return 7;
    }

    @Override
    protected Tuple7<?, ?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple7<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple7<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>>() {};
    }
}
