package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import javaslang.Tuple;
import javaslang.Value;
import javaslang.collection.Map;
import javaslang.collection.Seq;
import javaslang.collection.Set;

public class JavaslangDeserializers extends Deserializers.Base {

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type,
                                                    DeserializationConfig config,
                                                    BeanDescription beanDesc) throws JsonMappingException {
        if (Map.class.isAssignableFrom(type.getRawClass())) {
            return new MapDeserializer(type);
        }
        if (Tuple.class.isAssignableFrom(type.getRawClass())) {
            return new TupleDeserializer(type);
        }
        if (Seq.class.isAssignableFrom(type.getRawClass())) {
            return new SeqDeserializer(type);
        }
        if (Set.class.isAssignableFrom(type.getRawClass())) {
            return new SetDeserializer(type);
        }
        return null;
    }
}
