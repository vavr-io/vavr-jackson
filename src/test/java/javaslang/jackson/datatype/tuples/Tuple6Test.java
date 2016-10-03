package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.Tuple;
import javaslang.Tuple6;
import javaslang.control.Option;

public class Tuple6Test extends TupleTest<Tuple6<?, ?, ?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple6.class;
    }

    @Override
    protected int arity() {
        return 6;
    }

    @Override
    protected Tuple6<?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple6<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple6<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>>() {};
    }
}
