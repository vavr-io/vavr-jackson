package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.JavaType;
import javaslang.collection.Map;

import java.io.IOException;
import java.util.HashMap;

class MapSerializer extends ValueSerializer<Map<?, ?>> {

    private static final long serialVersionUID = 1L;

    MapSerializer(JavaType type) {
        super(type);
    }

    @Override
    Object toJavaObj(Map<?, ?> value) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        value.forEach(e -> result.put(e.key().toString(), e.value()));
        return result;
    }
}
