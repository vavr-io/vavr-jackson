package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javaslang.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class TupleSerializer<T extends Tuple> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    private final boolean compact;
    private final Class<?> clz;

    protected TupleSerializer(JavaType type, Class<?> clz, boolean compact) {
        super(type);
        this.compact = compact;
        this.clz = clz;
    }

    private static HashMap<String, Object> conv(Tuple tuple) throws IOException {
        List<?> list;
        switch (tuple.arity()) {
            case 0: {
                list = Collections.emptyList();
                break;
            }
            case 1: {
                final Tuple1<?> t = (Tuple1<?>) tuple;
                list = Collections.singletonList(t._1);
                break;
            }
            case 2: {
                final Tuple2<?, ?> t = (Tuple2<?, ?>) tuple;
                list = Arrays.asList(t._1, t._2);
                break;
            }
            case 3: {
                final Tuple3<?, ?, ?> t = (Tuple3<?, ?, ?>) tuple;
                list = Arrays.asList(t._1, t._2, t._3);
                break;
            }
            case 4: {
                final Tuple4<?, ?, ?, ?> t = (Tuple4<?, ?, ?, ?>) tuple;
                list = Arrays.asList(t._1, t._2, t._3, t._4);
                break;
            }
            case 5: {
                final Tuple5<?, ?, ?, ?, ?> t = (Tuple5<?, ?, ?, ?, ?>) tuple;
                list = Arrays.asList(t._1, t._2, t._3, t._4, t._5);
                break;
            }
            case 6: {
                final Tuple6<?, ?, ?, ?, ?, ?> t = (Tuple6<?, ?, ?, ?, ?, ?>) tuple;
                list = Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6);
                break;
            }
            case 7: {
                final Tuple7<?, ?, ?, ?, ?, ?, ?> t = (Tuple7<?, ?, ?, ?, ?, ?, ?>) tuple;
                list = Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6, t._7);
                break;
            }
            case 8: {
                final Tuple8<?, ?, ?, ?, ?, ?, ?, ?> t = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) tuple;
                list = Arrays.asList(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
                break;
            }
            default: {
                throw new IOException();
            }
        }
        HashMap<String, Object> result = new HashMap<>();
        for (int i = 1; i <= list.size(); i++) {
            result.put("_" + i, list.get(i - 1));
        }
        return result;
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (compact) {
            gen.writeObject(conv(value));
        } else {
            gen.writeStartObject();
            gen.writeFieldName("@class");
            gen.writeString(clz.getCanonicalName());
            gen.writeFieldName("@data");
            gen.writeObject(conv(value));
            gen.writeEndObject();
        }
    }
}
