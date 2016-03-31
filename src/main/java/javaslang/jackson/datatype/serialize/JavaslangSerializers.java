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
package javaslang.jackson.datatype.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import javaslang.Lazy;
import javaslang.Tuple;
import javaslang.collection.*;
import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.λ;

public class JavaslangSerializers extends Serializers.Base {

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
                                            JavaType type, BeanDescription beanDesc) {

        Class<?> raw = type.getRawClass();
        if (Lazy.class.isAssignableFrom(raw)) {
            return new LazySerializer(type);
        }
        if (Option.class.isAssignableFrom(raw)) {
            return new OptionSerializer(type);
        }
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherSerializer(type);
        }
        if (CharSeq.class.isAssignableFrom(raw)) {
            return new CharSeqSerializer(type);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new SeqSerializer(type);
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new SetSerializer(type);
        }
        if (Map.class.isAssignableFrom(raw)) {
            return new MapSerializer(type);
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return new MultimapSerializer(type);
        }
        if (Tuple.class.isAssignableFrom(raw)) {
            return new TupleSerializer(type);
        }

        if (λ.class.isAssignableFrom(raw)) {
            return new SerializableSerializer<>(type);
        }

        return super.findSerializer(config, type, beanDesc);
    }
}
