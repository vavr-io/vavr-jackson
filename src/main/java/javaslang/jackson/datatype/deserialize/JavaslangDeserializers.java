package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import javaslang.Tuple;
import javaslang.collection.List;

public class JavaslangDeserializers extends Deserializers.Base {

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type,
                                                    DeserializationConfig config,
                                                    BeanDescription beanDesc) throws JsonMappingException {
        if (Tuple.class.isAssignableFrom(type.getRawClass())) {
            return new TupleDeserializer(type);
        }
        if (List.class.isAssignableFrom(type.getRawClass())) {
            return new ListDeserializer(type);
        }
        return null;
    }
}
