package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.*;
import javaslang.collection.*;

public class JavaslangSerializers extends Serializers.Base {

    private final boolean compact;

    public JavaslangSerializers(boolean compact) {
        this.compact = compact;
    }

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        if (Array.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Array<?>>(type, Array.class, compact);
        }
        if (CharSeq.class.isAssignableFrom(type.getRawClass())) {
            return new CharSeqSerializer(type, compact);
        }
        if (List.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<List<?>>(type, List.class, compact);
        }
        if (Queue.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Queue<?>>(type, Queue.class, compact);
        }
        if (Stack.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Stack<?>>(type, Stack.class, compact);
        }
        if (Stream.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Stream<?>>(type, Stream.class, compact);
        }
        if (Vector.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Vector<?>>(type, Vector.class, compact);
        }

        if (HashMap.class.isAssignableFrom(type.getRawClass())) {
            return new MapSerializer<HashMap<?, ?>>(type, HashMap.class, compact);
        }
        if (TreeMap.class.isAssignableFrom(type.getRawClass())) {
            return new MapSerializer<TreeMap<?, ?>>(type, TreeMap.class, compact);
        }

        if (Tuple0.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple0>(type, Tuple0.class, compact);
        }
        if (Tuple1.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple1<?>>(type, Tuple1.class, compact);
        }
        if (Tuple2.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple2<?, ?>>(type, Tuple2.class, compact);
        }
        if (Tuple3.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple3<?, ?, ?>>(type, Tuple3.class, compact);
        }
        if (Tuple4.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple4<?, ?, ?, ?>>(type, Tuple4.class, compact);
        }
        if (Tuple5.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple5<?, ?, ?, ?, ?>>(type, Tuple5.class, compact);
        }
        if (Tuple6.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple6<?, ?, ?, ?, ?, ?>>(type, Tuple6.class, compact);
        }
        if (Tuple7.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple7<?, ?, ?, ?, ?, ?, ?>>(type, Tuple7.class, compact);
        }
        if (Tuple8.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>>(type, Tuple8.class, compact);
        }

        return super.findSerializer(config, type, beanDesc);
    }
}
