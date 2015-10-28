package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javaslang.collection.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
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
                Object o;
                if(expectedClass != null) {
                    if(expectedType != null  && expectedClass.isAssignableFrom(expectedType.getRawClass())) {
                        o = deserialize(jp, expectedType);  // TODO
                    } else {
                        o = deserialize(jp, TypeFactory.defaultInstance().constructFromCanonical(expectedClass.getCanonicalName()));  // TODO
                    }
                } else {
                    o = deserialize(jp, expectedType);  // TODO
                }
                jp.nextToken();
                return o;
            }

            switch (t) {
                case START_ARRAY:
                    result.put(name, _deserializeArray(jp, expectedType.containedType(1)));
                    break;
                case START_OBJECT:
                    result.put(name, _deserializeObject(jp, expectedType.containedType(1)));
                    break;
                default:
                    result.put(name, _deserializeScalar(jp, expectedType.containedType(1)));
            }
        }
        if(expectedType != null) {
            if(javaslang.collection.HashMap.class.isAssignableFrom(expectedType.getRawClass())) {
                return fill(javaslang.collection.HashMap.empty(), result);
            }
            if(javaslang.collection.TreeMap.class.isAssignableFrom(expectedType.getRawClass())) {
                return fill(javaslang.collection.TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString())), result);
            }
        }
        return result;
    }

    private javaslang.collection.Map<Object, Object> fill(javaslang.collection.Map<Object, Object> result, Map<Object, Object> content) {
        for (Map.Entry<Object, Object> e : content.entrySet()) {
            result = result.put(e.getKey(), e.getValue());
        }
        return result;
    }

    protected Iterable<?> _deserializeArray(JsonParser jp, JavaType expectedType)
            throws IOException, ClassNotFoundException {
        checkType(expectedType, Seq.class);
        JsonToken t;
        List<Object> result = new ArrayList<>();
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            switch (t) {
                case START_ARRAY:
                    result.add(_deserializeArray(jp, expectedType.containedType(0)));
                    break;
                case START_OBJECT:
                    result.add(_deserializeObject(jp, expectedType.containedType(0)));
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
                    if(expectedType != null && Double.class.isAssignableFrom(expectedType.getRawClass())) {
                        return jp.getDoubleValue();
                    }
                    if(expectedType == null || Float.class.isAssignableFrom(expectedType.getRawClass())) {
                        return jp.getFloatValue();
                    }
                    throw ctx.mappingException(expectedType.getRawClass());
                }
            case VALUE_NUMBER_INT:
                switch (jp.getNumberType()) {
                    case LONG:
                        checkType(expectedType, Long.class);
                        return jp.getLongValue();
                    case INT:
                        if(expectedType != null && Long.class.isAssignableFrom(expectedType.getRawClass())) {
                            return jp.getLongValue();
                        }
                        if(expectedType == null || expectedType.getRawClass() == Object.class || Integer.class.isAssignableFrom(expectedType.getRawClass())) {
                            return jp.getIntValue();
                        }
                        throw ctx.mappingException(expectedType.getRawClass());
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
        throw ctx.mappingException(expectedType.getRawClass());
    }
}
