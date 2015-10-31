package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Map;

import java.io.IOException;
import java.util.HashMap;

class MapSerializer<T extends Map<?, ?>> extends ValueSerializer<T> {

    private static final long serialVersionUID = 1L;

    MapSerializer(JavaType type, Class<?> clz, boolean compact) {
        super(type, clz, compact);
    }

    @Override
    Object toJavaObj(T value) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        value.forEach(e -> result.put(e.key().toString(), e.value()));
        return result;
    }
}
