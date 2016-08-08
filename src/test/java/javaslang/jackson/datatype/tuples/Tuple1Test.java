package javaslang.jackson.datatype.tuples;

import com.fasterxml.jackson.core.type.TypeReference;

import javaslang.Tuple;
import javaslang.Tuple1;
import javaslang.control.Option;

public class Tuple1Test extends TupleTest<Tuple1<?>> {

    @Override
    protected Class<?> clz() {
        return Tuple1.class;
    }

    @Override
    protected int arity() {
        return 1;
    }

    @Override
    protected Tuple1<?> ofObjects(Object head, Object tail) {
        return Tuple.of(head);
    }

    @Override
    protected TypeReference<Tuple1<Option<String>>> typeReferenceWithOption() {
        return new TypeReference<Tuple1<Option<String>>>() {};
    }
}
