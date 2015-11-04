package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.collection.Stream;

import java.util.Map;

public class BaseTest {

    private static Class<?> javaslangClass(Object o) {
        if (o instanceof List.Cons) {
            return List.class;
        }
        if (o instanceof Stream.Cons) {
            return Stream.class;
        }
        return o.getClass();
    }

    protected ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());
        return mapper;
    }

    protected String genJsonList(Object... list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            appendObj(sb, list[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    protected String genJsonMap(java.util.Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = 0;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("\"").append(entry.getKey().toString()).append("\":");
            appendObj(sb, entry.getValue());
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    private void appendObj(StringBuilder sb, Object o) {
        if (o instanceof java.lang.String) {
            sb.append("\"").append(o).append("\"");
        } else if (o instanceof javaslang.collection.Seq) {
            sb.append(genJsonList(((Seq) o).toJavaList().toArray()));
        } else {
            sb.append(o);
        }
    }
}
