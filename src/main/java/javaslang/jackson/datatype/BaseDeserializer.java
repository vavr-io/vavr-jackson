package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class BaseDeserializer {

    private final static String CLASS_KEY = "@class";
    private final static String DATA_KEY = "@data";

    private final DeserializationContext ctx;

    BaseDeserializer(DeserializationContext ctx) {
        this.ctx = ctx;
    }

    public Object deserialize(JsonParser jp, JavaType expectedType)
            throws IOException, ClassNotFoundException {
        switch (jp.getCurrentToken()) {
            case START_OBJECT:
                return _deserializeObject(jp, expectedType);
            case START_ARRAY:
                return _deserializeArray(jp, expectedType);
            default:
                return _deserializeScalar(jp, expectedType);
        }
    }

    protected Object _deserializeObject(JsonParser jp, JavaType expectedType)
            throws IOException, ClassNotFoundException {
        Object result = null;
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String name = jp.getCurrentName();
            JsonToken t = jp.nextToken();
            if (CLASS_KEY.equals(name)) {
                if (t == JsonToken.VALUE_STRING) {
                    Class<?> expectedClass = Class.forName(jp.getText());
                    if (expectedType != null && expectedType.getRawClass() != expectedClass) {
                        throw JsonMappingException.from(jp, "bad " + CLASS_KEY + " value"); // TODO
                    }
                    continue;
                } else {
                    throw JsonMappingException.from(jp, "bad " + CLASS_KEY + " value"); // TODO
                }
            }
            if (DATA_KEY.equals(name)) {
                result = deserialize(jp, expectedType);  // TODO
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
        return result;
    }

    protected List<?> _deserializeArray(JsonParser jp, JavaType expectedType)
            throws IOException, ClassNotFoundException {
        JsonToken t;
        List<Object> result = new ArrayList<>();
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            switch (t) {
                case START_ARRAY:
                    result.add(_deserializeArray(jp, expectedType));
                    break;
                case START_OBJECT:
                    result.add(_deserializeObject(jp, expectedType));
                    break;
                default:
                    result.add(_deserializeScalar(jp, expectedType.containedType(0)));
            }
        }
        return result;
    }

    protected Object _deserializeScalar(JsonParser jp, JavaType expectedType)
            throws IOException {
        switch (jp.getCurrentToken()) {
            case VALUE_EMBEDDED_OBJECT:
                throw ctx.mappingException(this.getClass());
            case VALUE_FALSE:
                checkType(expectedType, Boolean.class);
                return Boolean.FALSE;
            case VALUE_TRUE:
                checkType(expectedType, Boolean.class);
                return Boolean.TRUE;
            case VALUE_NULL:
                return null;
            case VALUE_NUMBER_FLOAT:
                if (jp.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
                    checkType(expectedType, BigDecimal.class);
                    return jp.getDecimalValue();
                } else {
                    checkType(expectedType, Float.class, Double.class);
                    return jp.getDoubleValue();
                }
            case VALUE_NUMBER_INT:
                switch (jp.getNumberType()) {
                    case LONG:
                        checkType(expectedType, Long.class);
                        return jp.getLongValue();
                    case INT:
                        checkType(expectedType, Integer.class);
                        return jp.getIntValue();
                    default:
                        checkType(expectedType, BigInteger.class);
                        return jp.getBigIntegerValue();
                }
            case VALUE_STRING:
                checkType(expectedType, String.class);
                return jp.getText();
            default:
                throw ctx.mappingException(BaseDeserializer.class);
        }
    }

    private void checkType(JavaType expectedType, Class<?>... actualClasses) throws JsonMappingException {
        if (expectedType == null || expectedType.getRawClass() == Object.class) {
            return;
        }
        for (Class<?> actualClass : actualClasses) {
            if (actualClass.isAssignableFrom(expectedType.getRawClass())) {
                return;
            }
        }
        throw ctx.mappingException(this.getClass());
    }
}
