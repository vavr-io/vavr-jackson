package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.Tuple;
import javaslang.Value;
import javaslang.collection.CharSeq;
import javaslang.collection.Seq;
import javaslang.collection.Set;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class BaseDeserializer<T> extends StdDeserializer<T> {

    private static final long serialVersionUID = 1L;

    BaseDeserializer(JavaType valueType) {
        super(valueType);
    }

    Object _deserialize(JsonParser jp, JavaType expectedType, DeserializationContext ctx)
            throws IOException {
        switch (jp.getCurrentToken()) {
            case START_OBJECT:
                return _deserializeObject(jp, expectedType, ctx);
            case START_ARRAY:
                return _deserializeArray(jp, expectedType, ctx);
            default:
                return _deserializeScalar(jp, expectedType, ctx);
        }
    }

    private Object _deserializeObject(JsonParser jp, JavaType expectedType, DeserializationContext ctx)
            throws IOException {
        // TODO (hotfix)
        if(expectedType != null && !Value.class.isAssignableFrom(expectedType.getRawClass()) && !Tuple.class.isAssignableFrom(expectedType.getRawClass())) {
            JsonDeserializer<?> des = ctx.findRootValueDeserializer(expectedType);
            return des.deserialize(jp, ctx);
        }
        Map<Object, Object> result = new HashMap<>();
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String name = jp.getCurrentName();
            JsonToken t = jp.nextToken();

            switch (t) {
                case START_ARRAY:
                    result.put(name, _deserializeArray(jp, containedType(expectedType, 1), ctx));
                    break;
                case START_OBJECT:
                    result.put(name, _deserializeObject(jp, containedType(expectedType, 1), ctx));
                    break;
                default:
                    result.put(name, _deserializeScalar(jp, containedType(expectedType, 1), ctx));
            }
        }
        if(expectedType != null) {
            if(javaslang.collection.Map.class.isAssignableFrom(expectedType.getRawClass())) {
                if (javaslang.collection.HashMap.class.isAssignableFrom(expectedType.getRawClass())) {
                    return fill(javaslang.collection.HashMap.empty(), result);
                }
                if (javaslang.collection.TreeMap.class.isAssignableFrom(expectedType.getRawClass())) {
                    return fill(javaslang.collection.TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString())), result);
                }
                // default deserialization {...} -> Map
                return fill(javaslang.collection.HashMap.empty(), result);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private Iterable<?> _deserializeArray(JsonParser jp, JavaType expectedType, DeserializationContext ctx)
            throws IOException {
        checkType(ctx, expectedType, Iterable.class);
        JsonToken t;
        List<Object> result = new ArrayList<>();
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            switch (t) {
                case START_ARRAY:
                    result.add(_deserializeArray(jp, containedType(expectedType, 0), ctx));
                    break;
                case START_OBJECT:
                    result.add(_deserializeObject(jp, containedType(expectedType, 0), ctx));
                    break;
                default:
                    result.add(_deserializeScalar(jp, containedType(expectedType, 0), ctx));
            }
        }
        if(expectedType != null) {
            if(javaslang.collection.Seq.class.isAssignableFrom(expectedType.getRawClass())) {
                if (javaslang.collection.Array.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.Array.ofAll(result);
                }
                if (javaslang.collection.List.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.List.ofAll(result);
                }
                if (javaslang.collection.Queue.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.Queue.ofAll(result);
                }
                if (javaslang.collection.Stack.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.Stack.ofAll(result);
                }
                if (javaslang.collection.Stream.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.Stream.ofAll(result);
                }
                if (javaslang.collection.Vector.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.Vector.ofAll(result);
                }
                // default deserialization [...] -> Seq
                return javaslang.collection.List.ofAll(result);
            }
            if(javaslang.collection.Set.class.isAssignableFrom(expectedType.getRawClass())) {
                if(javaslang.collection.HashSet.class.isAssignableFrom(expectedType.getRawClass())) {
                    return javaslang.collection.HashSet.ofAll(result);
                }
                if(javaslang.collection.TreeSet.class.isAssignableFrom(expectedType.getRawClass())) {
                    if(expectedType.containedTypeCount() == 0) {
                        throw ctx.mappingException(expectedType.getRawClass());
                    }
                    JavaType generic = expectedType.containedType(0);
                    if(generic.getRawClass() == Object.class || !Comparable.class.isAssignableFrom(generic.getRawClass())) {
                        throw ctx.mappingException(expectedType.getRawClass());
                    }
                    return javaslang.collection.TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), result); // TODO
                }
                // default deserialization [...] -> Set
                return javaslang.collection.HashSet.ofAll(result);
            }
        }
        return result;
    }

    private Object _deserializeScalar(JsonParser jp, JavaType expectedType, DeserializationContext ctx)
            throws IOException {
        switch (jp.getCurrentToken()) {
            case VALUE_FALSE:
                checkType(ctx, expectedType, Boolean.class);
                return Boolean.FALSE;
            case VALUE_TRUE:
                checkType(ctx, expectedType, Boolean.class);
                return Boolean.TRUE;
            case VALUE_NULL:
                return null;
            case VALUE_NUMBER_FLOAT:
                boolean useBigDec = ctx.getConfig().isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
                if (useBigDec || jp.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
                    checkType(ctx, expectedType, BigDecimal.class);
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
                        checkType(ctx, expectedType, Long.class);
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
                        checkType(ctx, expectedType, BigInteger.class);
                        return jp.getBigIntegerValue();
                }
            case VALUE_STRING:
                checkType(ctx, expectedType, String.class, CharSeq.class);
                if(expectedType != null && CharSeq.class.isAssignableFrom(expectedType.getRawClass())) {
                    return CharSeq.of(jp.getText());
                } else {
                    return jp.getText();
                }
            default:
                throw ctx.mappingException(BaseDeserializer.class);
        }
    }

    private static JavaType containedType(JavaType holder, int index) {
        if(holder == null || holder.containedTypeCount() < index) {
            return null;
        } else {
            return holder.containedType(index);
        }
    }

    private static javaslang.collection.Map<Object, Object> fill(javaslang.collection.Map<Object, Object> result, Map<Object, Object> content) {
        for (Map.Entry<Object, Object> e : content.entrySet()) {
            result = result.put(e.getKey(), e.getValue());
        }
        return result;
    }

    private static void checkType(DeserializationContext ctx, JavaType expectedType, Class<?>... actualClasses) throws JsonMappingException {
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
