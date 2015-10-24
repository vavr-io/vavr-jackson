package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.collection.*;

class JavaslangSerializers extends Serializers.Base {

    private final boolean compact;

    JavaslangSerializers(boolean compact) {
        this.compact = compact;
    }

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        if (Array.class.isAssignableFrom(type.getRawClass())) {
            return new SeqSerializer<Array<?>>(type, Array.class, compact);
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

        return super.findSerializer(config, type, beanDesc);
    }
}
