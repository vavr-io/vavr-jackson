package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.collection.Stream;
import javaslang.collection.Traversable;

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

    protected ObjectMapper mapper(boolean compact) {
        ObjectMapper mapper = new ObjectMapper();
        JavaslangModule.Config cfg = new JavaslangModule.Config();
        cfg.setCompactMode(compact);
        mapper.registerModule(new JavaslangModule(cfg));
        return mapper;
    }

    protected String crashJson(String json) {
        int p = json.indexOf("\"@class\"");
        if(p < 0) {
            return json;
        }
        p = json.indexOf("\"", p + 8) + 1;
        return json.substring(0, p) + "X" + json.substring(p);
    }

    protected String genJsonObject(Class<?> clz, String content) {
        StringBuilder sb = new StringBuilder();
        if (clz != null) {
            sb.append("{\"@class\":\"").append(clz.getCanonicalName()).append("\",\"@data\":");
        }
        sb.append(content);
        if (clz != null) {
            sb.append("}");
        }
        return sb.toString();
    }

    protected String genJsonList(Class<?> clz, Object... list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object o = list[i];
            if (o instanceof java.lang.String) {
                sb.append("\"").append(o).append("\"");
            } else if (o instanceof javaslang.collection.Traversable) {
                sb.append(genJsonList(clz == null ? null : javaslangClass(o), ((Traversable) o).toJavaList().toArray()));
            } else {
                sb.append(o);
            }
        }
        sb.append("]");
        return genJsonObject(clz, sb.toString());
    }

    protected String genJsonMap(Class<?> clz, java.util.Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = 0;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("\"").append(entry.getKey().toString()).append("\":");
            Object o = entry.getValue();
            if (o instanceof java.lang.String) {
                sb.append("\"").append(o).append("\"");
            } else if (o instanceof javaslang.collection.Seq) {
                sb.append(genJsonList(clz == null ? null : javaslangClass(o), ((Seq) o).toJavaList().toArray()));
            } else {
                sb.append(o);
            }
            i++;
        }
        sb.append("}");
        return genJsonObject(clz, sb.toString());
    }
}
