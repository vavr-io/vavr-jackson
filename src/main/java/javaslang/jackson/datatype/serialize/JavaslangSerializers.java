package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.*;
import javaslang.collection.*;

public class JavaslangSerializers extends Serializers.Base {

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        if (Array.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Array<?>>(type, Array.class);
        }
        if (CharSeq.class.isAssignableFrom(type.getRawClass())) {
            return new CharSeqSerializer(type);
        }
        if (List.class.isAssignableFrom(type.getRawClass()) || Stack.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<List<?>>(type, List.class);
        }
        if (Queue.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Queue<?>>(type, Queue.class);
        }
        if (Stream.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Stream<?>>(type, Stream.class);
        }
        if (Vector.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Vector<?>>(type, Vector.class);
        }

        if (HashSet.class.isAssignableFrom(type.getRawClass())) {
            return new SetSerializer<HashSet<?>>(type, HashSet.class);
        }
        if (TreeSet.class.isAssignableFrom(type.getRawClass())) {
            return new SetSerializer<TreeSet<?>>(type, TreeSet.class);
        }

        if (HashMap.class.isAssignableFrom(type.getRawClass())) {
            return new MapSerializer<HashMap<?, ?>>(type, HashMap.class);
        }
        if (TreeMap.class.isAssignableFrom(type.getRawClass())) {
            return new MapSerializer<TreeMap<?, ?>>(type, TreeMap.class);
        }

        if (Tuple0.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple0>(type, Tuple0.class);
        }
        if (Tuple1.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple1<?>>(type, Tuple1.class);
        }
        if (Tuple2.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple2<?, ?>>(type, Tuple2.class);
        }
        if (Tuple3.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple3<?, ?, ?>>(type, Tuple3.class);
        }
        if (Tuple4.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple4<?, ?, ?, ?>>(type, Tuple4.class);
        }
        if (Tuple5.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple5<?, ?, ?, ?, ?>>(type, Tuple5.class);
        }
        if (Tuple6.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple6<?, ?, ?, ?, ?, ?>>(type, Tuple6.class);
        }
        if (Tuple7.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple7<?, ?, ?, ?, ?, ?, ?>>(type, Tuple7.class);
        }
        if (Tuple8.class.isAssignableFrom(type.getRawClass())) {
            return new TupleSerializer<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>>(type, Tuple8.class);
        }

        return super.findSerializer(config, type, beanDesc);
    }
}
