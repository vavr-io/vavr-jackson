/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2017 Vavr, http://vavr.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vavr.jackson.datatype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import io.vavr.Lazy;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.control.Option;

import java.lang.reflect.Type;

public class VavrTypeModifier extends TypeModifier {

    @Override
    public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory) {
        final Class<?> raw = type.getRawClass();
        if (Seq.class.isAssignableFrom(raw) && CharSeq.class != raw) {
            return CollectionLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (Set.class.isAssignableFrom(raw)) {
            return CollectionLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return CollectionLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (Map.class.isAssignableFrom(raw)) {
            return MapLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0), type.containedTypeOrUnknown(1));
        }
        if (Multimap.class.isAssignableFrom(raw)) {
            return MapLikeType.upgradeFrom(type, type.containedTypeOrUnknown(0), type.containedTypeOrUnknown(1));
        }
        if (Lazy.class.isAssignableFrom(raw)) {
            return ReferenceType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        if (Option.class.isAssignableFrom(raw)) {
            return ReferenceType.upgradeFrom(type, type.containedTypeOrUnknown(0));
        }
        return type;
    }
}
