package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javaslang.collection.Seq;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Class<?> expectedClass = null;
        Map<Object, Object> result = new HashMap<>();
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String name = jp.getCurrentName();
            JsonToken t = jp.nextToken();
            if (CLASS_KEY.equals(name)) {
                if (t == JsonToken.VALUE_STRING) {
                    expectedClass = Class.forName(jp.getText());
                    if (expectedType != null && !expectedType.getRawClass().isAssignableFrom(expectedClass)) {
                        throw JsonMappingException.from(jp, "bad " + CLASS_KEY + " value"); // TODO
                    }
                    continue;
                } else {
                    throw JsonMappingException.from(jp, "bad " + CLASS_KEY + " value"); // TODO
                }
            }
            if (DATA_KEY.equals(name)) {
                if(expectedClass != null) {
                    if(expectedType != null  && expectedClass.isAssignableFrom(expectedType.getRawClass())) {
                        return deserialize(jp, expectedType);  // TODO
                    } else {
                        return deserialize(jp, TypeFactory.defaultInstance().constructFromCanonical(expectedClass.getCanonicalName()));  // TODO
                    }
                } else {
                    return deserialize(jp, expectedType);  // TODO
                }
            }

            switch (t) {
                case START_ARRAY:
                    checkType(expectedType, Seq.class);
                    result.put(name, _deserializeArray(jp, expectedType.containedType(1)));
                    break;
                case START_OBJECT:
                    checkType(expectedType, javaslang.collection.HashMap.class, javaslang.Tuple.class);
                    result.put(name, _deserializeObject(jp, expectedType.containedType(1)));
                    break;
                default:
                    result.put(name, _deserializeScalar(jp, expectedType.containedType(1)));
            }
        }
        return result;
    }

    protected Iterable<?> _deserializeArray(JsonParser jp, JavaType expectedType)
            throws IOException, ClassNotFoundException {
        checkType(expectedType, Seq.class);
        JsonToken t;
        List<Object> result = new ArrayList<>();
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            if(t == null) {
                return null;
            }
            switch (t) {
                case START_ARRAY:
                    result.add(_deserializeArray(jp, expectedType.containedType(0)));
                    //jp.nextToken();
                    break;
                case START_OBJECT:
                    result.add(_deserializeObject(jp, expectedType.containedType(0)));
                    jp.nextToken();
                    break;
                default:
                    result.add(_deserializeScalar(jp, expectedType == null || expectedType.containedTypeCount() == 0 ? null : expectedType.containedType(0)));
            }
        }
        if(expectedType != null) {
            if(javaslang.collection.Array.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.Array.ofAll(result);
            }
            if(javaslang.collection.List.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.List.ofAll(result);
            }
            if(javaslang.collection.Queue.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.Queue.ofAll(result);
            }
            if(javaslang.collection.Stack.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.Stack.ofAll(result);
            }
            if(javaslang.collection.Stream.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.Stream.ofAll(result);
            }
            if(javaslang.collection.Vector.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.Vector.ofAll(result);
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
