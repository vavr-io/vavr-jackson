package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;

import java.io.IOException;

class MapDeserializer extends StdDeserializer<Map<?,?>> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    protected MapDeserializer(JavaType valueType) {
        super(valueType);
        javaType = valueType;
    }

    @Override
    public Map<?,?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            final BaseDeserializer deserializer = new BaseDeserializer(ctxt);
            java.util.Map<?, ?> mp = (java.util.Map<?,?>) deserializer.deserialize(p, javaType);
            Map<Object, Object> result = HashMap.empty();
            for (java.util.Map.Entry<?, ?> e : mp.entrySet()) {
                result = result.put(e.getKey(), e.getValue());
            }
            return result;
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(this.getClass());
        }
    }
}
