package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import javaslang.Tuple;
import javaslang.collection.*;

public class JavaslangDeserializers extends Deserializers.Base {

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type,
                                                    DeserializationConfig config,
                                                    BeanDescription beanDesc) throws JsonMappingException {
        if (HashMap.class.isAssignableFrom(type.getRawClass())) {
            return new MapDeserializer.AsHashMap(type);
        }
        if (TreeMap.class.isAssignableFrom(type.getRawClass())) {
            return new MapDeserializer.AsTreeMap(type);
        }
        if (Tuple.class.isAssignableFrom(type.getRawClass())) {
            return new TupleDeserializer(type);
        }

        if (Array.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer.AsArray(type);
        }
        if (CharSeq.class.isAssignableFrom(type.getRawClass())) {
            return new CharSeqDeserializer(type);
        }
        if (List.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer.AsList(type);
        }
        if (Queue.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer.AsQueue(type);
        }
        if (Stack.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer.AsStack(type);
        }
        if (Stream.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer.AsStream(type);
        }
        if (Vector.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer.AsVector(type);
        }
        return null;
    }
}
