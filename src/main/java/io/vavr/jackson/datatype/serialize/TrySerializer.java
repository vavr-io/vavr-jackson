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
package io.vavr.jackson.datatype.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.vavr.control.Try;
import java.io.IOException;

class TrySerializer extends StdSerializer<Try<?>> {

  private static final long serialVersionUID = 1L;

  private final JavaType type;

  TrySerializer(JavaType type) {
      super(type);
      this.type = type;
  }

  void write(Object val, JavaType type, JsonGenerator gen, SerializerProvider provider) throws IOException {
      if (val != null) {
          JsonSerializer<Object> ser;
          if (type != null && type.hasGenericTypes()) {
              JavaType st = provider.constructSpecializedType(type, val.getClass());
              ser = provider.findTypedValueSerializer(st, true, null);
          } else {
              ser = provider.findTypedValueSerializer(val.getClass(), true, null);
          }
          ser.serialize(val, gen, provider);
      } else {
          gen.writeObject(val);
      }
  }

  @Override
  public void serializeWithType(Try<?> value, JsonGenerator gen, SerializerProvider serializers,
                                TypeSerializer typeSer) throws IOException {
      typeSer.writeTypePrefixForScalar(value, gen);
      serialize(value, gen, serializers);
      typeSer.writeTypeSuffixForScalar(value, gen);
  }

  @Override
  public void serialize(Try<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeStartArray();
      if (value.isSuccess()) {
          gen.writeString("success");
          write(value.get(), type.containedType(0), gen, provider);
      } else {
          gen.writeString("failure");
          write(value.getCause(), provider.constructType(Throwable.class), gen, provider);
      }
      gen.writeEndArray();
  }

  @Override
  public boolean isEmpty(SerializerProvider provider, Try<?> value) {
      return value.isEmpty();
  }
}
