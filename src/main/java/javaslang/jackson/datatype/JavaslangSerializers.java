package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.collection.List;

public class JavaslangSerializers extends Serializers.Base {

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        if (List.class.isAssignableFrom(type.getRawClass())) {
            return new ListSerializer(type);
        }

        return super.findSerializer(config, type, beanDesc);
    }
}
