package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.Tuple;
import javaslang.collection.CharSeq;
import javaslang.collection.Map;
import javaslang.collection.Seq;
import javaslang.collection.Set;

public class JavaslangSerializers extends Serializers.Base {

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        Class<?> raw = type.getRawClass();
        if (CharSeq.class.isAssignableFrom(raw)) {
            return new CharSeqSerializer(type);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new SeqSerializer(type);
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new SetSerializer(type);
        }
        if (Map.class.isAssignableFrom(raw)) {
            return new MapSerializer(type);
        }
        if (Tuple.class.isAssignableFrom(raw)) {
            return new TupleSerializer(type);
        }

        return super.findSerializer(config, type, beanDesc);
    }
}
