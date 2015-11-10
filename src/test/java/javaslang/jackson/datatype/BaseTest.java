package javaslang.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.collection.Seq;

import java.util.Map;

public class BaseTest {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public interface WrapperObject {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_ARRAY)
    public interface WrapperArray {
    }

    protected ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());
        return mapper;
    }

    protected String wrapToArray(String as, String json) {
        return "[\"" + as + "\"," + json + "]";
    }

    protected String wrapToObject(String as, String json) {
        return "{\"" + as + "\":" + json + "}";
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
