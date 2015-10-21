package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.List;

import java.io.IOException;

public class ListDeserializer extends StdDeserializer<List<?>> {

    private static final long serialVersionUID = 1L;

    protected ListDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Object> list = List.empty();
        p.nextToken();
        while (p.getCurrentToken() != JsonToken.END_ARRAY) {
            list = list.append(p.getIntValue());
            p.nextToken();
        }
        p.nextToken();
        return list;
    }
}
