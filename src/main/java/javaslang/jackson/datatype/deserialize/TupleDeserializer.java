package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import javaslang.Tuple;

import java.io.IOException;
import java.util.Map;

class TupleDeserializer extends BaseDeserializer<Tuple> {

    private static final long serialVersionUID = 1L;

    private final JavaType javaType;

    TupleDeserializer(JavaType valueType) {
        super(valueType);
        this.javaType = valueType;
    }

    @Override
    public Tuple deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            Map<?, ?> list = (Map<?, ?>) _deserialize(p, javaType, ctxt);
            switch (list.size()) {
                case 0:
                    return Tuple.empty();
                case 1:
                    return Tuple.of(list.get("_1"));
                case 2:
                    return Tuple.of(list.get("_1"), list.get("_2"));
                case 3:
                    return Tuple.of(list.get("_1"), list.get("_2"), list.get("_3"));
                case 4:
                    return Tuple.of(list.get("_1"), list.get("_2"), list.get("_3"), list.get("_4"));
                case 5:
                    return Tuple.of(list.get("_1"), list.get("_2"), list.get("_3"), list.get("_4"), list.get("_5"));
                case 6:
                    return Tuple.of(list.get("_1"), list.get("_2"), list.get("_3"), list.get("_4"), list.get("_5"), list.get("_6"));
                case 7:
                    return Tuple.of(list.get("_1"), list.get("_2"), list.get("_3"), list.get("_4"), list.get("_5"), list.get("_6"), list.get("_7"));
                case 8:
                    return Tuple.of(list.get("_1"), list.get("_2"), list.get("_3"), list.get("_4"), list.get("_5"), list.get("_6"), list.get("_7"), list.get("_8"));
                default:
                    throw ctxt.mappingException(javaType.getRawClass());
            }
        } catch (ClassNotFoundException e) {
            throw ctxt.mappingException(javaType.getRawClass());
        }
    }
}
