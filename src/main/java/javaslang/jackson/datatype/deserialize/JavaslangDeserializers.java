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

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import javaslang.Lazy;
import javaslang.Tuple;
import javaslang.collection.*;
import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.λ;

public class JavaslangDeserializers extends Deserializers.Base {

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type,
                                                    DeserializationConfig config,
                                                    BeanDescription beanDesc) throws JsonMappingException {
        Class<?> raw = type.getRawClass();
        if (CharSeq.class.isAssignableFrom(raw)) {
            return new CharSeqDeserializer(type);
        }
        if (Lazy.class.isAssignableFrom(raw)) {
            return new LazyDeserializer(type);
        }
        if (Option.class.isAssignableFrom(raw)) {
            return new OptionDeserializer(type);
        }
        if (Either.class.isAssignableFrom(raw)) {
            return new EitherDeserializer(type);
        }
        if (Map.class.isAssignableFrom(raw)) {
            return new MapDeserializer(type);
        }
        if (Tuple.class.isAssignableFrom(raw)) {
            return new TupleDeserializer(type);
        }
        if (Seq.class.isAssignableFrom(raw)) {
            return new SeqDeserializer(type);
        }
        if (Stack.class.isAssignableFrom(raw)) { // TODO remove when Javaslang will be fixed
            return new SeqDeserializer(type);
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new SetDeserializer(type);
        }

        if (λ.class.isAssignableFrom(raw)) {
            return new SerializableDeserializer<>(type);
        }

        return null;
    }
}
