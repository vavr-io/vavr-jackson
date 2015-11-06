package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

class TupleSerializer extends ValueSerializer<Tuple> {

    private static final long serialVersionUID = 1L;

    TupleSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Tuple tuple) throws IOException {
        switch (tuple.arity()) {
            case 1: {
                final Tuple1<?> t = (Tuple1<?>) tuple;
                return Collections.singletonList(t._1);
            }
            case 2: {
                final Tuple2<?, ?> t = (Tuple2<?, ?>) tuple;
                return Arrays.asList(t._1, t._2);
            }
            case 3: {
                final Tuple3<?, ?, ?> t = (Tuple3<?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3);
            }
            case 4: {
                final Tuple4<?, ?, ?, ?> t = (Tuple4<?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4);
            }
            case 5: {
                final Tuple5<?, ?, ?, ?, ?> t = (Tuple5<?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5);
            }
            case 6: {
                final Tuple6<?, ?, ?, ?, ?, ?> t = (Tuple6<?, ?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6);
            }
            case 7: {
                final Tuple7<?, ?, ?, ?, ?, ?, ?> t = (Tuple7<?, ?, ?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6, t._7);
            }
            case 8: {
                final Tuple8<?, ?, ?, ?, ?, ?, ?, ?> t = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) tuple;
                return Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
            }
            default: {
                return Collections.emptyList();
            }
        }
    }

}
