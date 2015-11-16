/**
 * Copyright 2015 The Javaslang Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javaslang.collection.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

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
        if(expectedType == null) {
            expectedType = ctx.constructType(java.util.HashMap.class);
        }
        if(!isAssignableFrom(expectedType, Map.class)) {
            JsonDeserializer<?> des = ctx.findRootValueDeserializer(expectedType);
            return des.deserialize(jp, ctx);
        }
        java.util.Map<Object, Object> result = new java.util.HashMap<>();
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
        if (TreeMap.class.isAssignableFrom(expectedType.getRawClass())) {
            return fill(TreeMap.empty((o1, o2) -> o1.toString().compareTo(o2.toString())), result);
        } else {
            // default deserialization [...] -> Map
            return fill(HashMap.empty(), result);
        }
    }

    @SuppressWarnings("unchecked")
    private Object _deserializeArray(JsonParser jp, JavaType expectedType, DeserializationContext ctx)
            throws IOException {
        if(expectedType == null) {
            expectedType = ctx.constructType(java.util.ArrayList.class);
        }
        if(!isAssignableFrom(expectedType, Seq.class, Set.class)) {
            JsonDeserializer<?> des = ctx.findRootValueDeserializer(expectedType);
            return des.deserialize(jp, ctx);
        }
        java.util.List<Object> result = new java.util.ArrayList<>();
        while (jp.nextToken() != JsonToken.END_ARRAY) {
            JsonToken t = jp.getCurrentToken();
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
        if(javaslang.collection.Seq.class.isAssignableFrom(expectedType.getRawClass())) {
            if (javaslang.collection.Array.class.isAssignableFrom(expectedType.getRawClass())) {
                return javaslang.collection.Array.ofAll(result);
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
        } else {
            if(javaslang.collection.TreeSet.class.isAssignableFrom(expectedType.getRawClass())) {
                if(expectedType.containedTypeCount() == 0) {
                    throw ctx.mappingException(expectedType.getRawClass());
                }
                JavaType generic = expectedType.containedType(0);
                if(generic.getRawClass() == Object.class || !Comparable.class.isAssignableFrom(generic.getRawClass())) {
                    throw ctx.mappingException(expectedType.getRawClass());
                }
                return javaslang.collection.TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), result);
            } else {
                // default deserialization [...] -> Set
                return javaslang.collection.HashSet.ofAll(result);
            }
        }
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
                throw ctx.mappingException("Embedded objects are not supported");
        }
    }

    private static JavaType containedType(JavaType holder, int index) {
        if(holder.containedTypeCount() < index) {
            return null;
        } else {
            return holder.containedType(index);
        }
    }

    private static javaslang.collection.Map<Object, Object> fill(javaslang.collection.Map<Object, Object> result, java.util.Map<Object, Object> content) {
        for (java.util.Map.Entry<Object, Object> e : content.entrySet()) {
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

    private static boolean isAssignableFrom(JavaType expectedType, Class<?>... actualClasses) {
        Class<?> clz = expectedType.getRawClass();
        for (Class<?> actualClass : actualClasses) {
            if (actualClass.isAssignableFrom(clz)) {
                return true;
            }
        }
        return false;
    }
}
