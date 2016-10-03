package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.Tuple;
import javaslang.Tuple8;
import javaslang.control.Option;

public class Tuple8Test extends TupleTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>> {

    @Override
    protected Class<?> clz() {
        return Tuple8.class;
    }

    @Override
    protected int arity() {
        return 8;
    }

    @Override
    protected Tuple8<?, ?, ?, ?, ?, ?, ?, ?> ofObjects(Object head, Object tail) {
        return Tuple.of(head, tail, tail, tail, tail, tail, tail, tail);
    }

    @Override
    protected TypeReference<Tuple8<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple8<Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>, Option<String>>>() {};
    }
}
