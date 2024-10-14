package io.vavr.jackson.datatype.seq;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class JavaTypeObjectMapperTest {

    @Test
    void shouldDeserializeList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        Type type = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{XY.class};
            }

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        JavaType a = TypeFactory.defaultInstance().constructType(type);
        List<?> list = mapper.readValue("[{\"x\":1,\"y\":2},{\"x\":3,\"y\":4}]", a);
    }

    static class XY {
        private int x, y;

        public XY() {
        }

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
