package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseDeserializer {

    private final static String CLASS_KEY = "@class";
    private final static String DATA_KEY = "@data";

    public Object deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, ClassNotFoundException {
        switch (jp.getCurrentToken()) {
            case START_OBJECT:
                return _deserializeObject(jp, ctxt);
            case START_ARRAY:
                return _deserializeArray(jp, ctxt);
            default:
                return _deserializeScalar(jp, ctxt);
        }
    }

    protected Object _deserializeObject(JsonParser jp, DeserializationContext ctxt)
            throws IOException, ClassNotFoundException {
        Class<?> expectedClass = null;
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String name = jp.getCurrentName();
            JsonToken t = jp.nextToken();
            if (CLASS_KEY.equals(name)) {
                if (t == JsonToken.VALUE_STRING) {
                    expectedClass = Class.forName(jp.getText());
                    continue;
                } else {
                    throw JsonMappingException.from(jp, "bad " + CLASS_KEY + " value"); // TODO
                }
            }
            if (DATA_KEY.equals(name)) {
                return deserialize(jp, ctxt);  // TODO
            }

//            switch (t) {
//                case START_ARRAY:
//                    return _deserializeArray(jp, ctxt);
//                case START_OBJECT:
//                    return _deserializeObject(jp, ctxt);
//                case VALUE_FALSE:
//                    b.add(name, false);
//                    break;
//                case VALUE_TRUE:
//                    b.add(name, true);
//                    break;
//                case VALUE_NULL:
//                    b.addNull(name);
//                    break;
//                case VALUE_NUMBER_FLOAT:
//                    if (jp.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
//                        b.add(name, jp.getDecimalValue());
//                    } else {
//                        b.add(name, jp.getDoubleValue());
//                    }
//                    break;
//                case VALUE_NUMBER_INT:
//                    // very cumbersome... but has to be done
//                    switch (jp.getNumberType()) {
//                        case LONG:
//                            b.add(name, jp.getLongValue());
//                            break;
//                        case INT:
//                            b.add(name, jp.getIntValue());
//                            break;
//                        default:
//                            b.add(name, jp.getBigIntegerValue());
//                    }
//                    break;
//                case VALUE_STRING:
//                    b.add(name, jp.getText());
//                    break;
//                case VALUE_EMBEDDED_OBJECT: {
//                    // 26-Nov-2014, tatu: As per [issue#5], should be able to support
//                    //   binary data as Base64 embedded text
//                    Object ob = jp.getEmbeddedObject();
//                    if (ob instanceof byte[]) {
//                        String b64 = ctxt.getBase64Variant().encode((byte[]) ob, false);
//                        b.add(name, b64);
//                        break;
//                    }
//                }
//                default:
//                    throw ctxt.mappingException(JsonValueDeserializer.class);
//            }
        }
        return null;
    }

    protected List<?> _deserializeArray(JsonParser jp, DeserializationContext ctxt)
            throws IOException, ClassNotFoundException {
        JsonToken t;
        List<Object> result = new ArrayList<>();
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            switch (t) {
                case START_ARRAY:
                    result.add(_deserializeArray(jp, ctxt));
                    break;
                case START_OBJECT:
                    result.add(_deserializeObject(jp, ctxt));
                    break;
                default:
                    result.add(_deserializeScalar(jp, ctxt));
            }
        }
        return result;
    }

    protected Object _deserializeScalar(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        switch (jp.getCurrentToken()) {
            case VALUE_EMBEDDED_OBJECT:
                throw ctxt.mappingException(BaseDeserializer.class);
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_NULL:
                return null;
            case VALUE_NUMBER_FLOAT:
                if (jp.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
                    return jp.getDecimalValue();
                } else {
                    return jp.getDoubleValue();
                }
            case VALUE_NUMBER_INT:
                switch (jp.getNumberType()) {
                    case LONG:
                        return jp.getLongValue();
                    case INT:
                        return jp.getIntValue();
                    default:
                        return jp.getBigIntegerValue();
                }
            case VALUE_STRING:
                return jp.getText();
            default:
                throw ctxt.mappingException(BaseDeserializer.class);
        }
    }
}
