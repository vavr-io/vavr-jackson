package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.collection.List;

public class JavaslangSerializers extends Serializers.Base {

    private final boolean compact;

    JavaslangSerializers(boolean compact) {
        this.compact = compact;
    }

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        if (List.class.isAssignableFrom(type.getRawClass())) {
            return new ListSerializer(type, compact);
        }

        return super.findSerializer(config, type, beanDesc);
    }
}
