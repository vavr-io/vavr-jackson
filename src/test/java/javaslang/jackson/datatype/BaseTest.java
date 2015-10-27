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

    protected ObjectMapper mapper(boolean compact) {
        ObjectMapper mapper = new ObjectMapper();
        JavaslangModule.JavaslangModuleConfig cfg = new JavaslangModule.JavaslangModuleConfig();
        cfg.setCompact(compact);
        mapper.registerModule(new JavaslangModule(cfg));
        return mapper;
    }

    protected String genJsonList(Class<?> clz, Object... list) {
        StringBuilder sb = new StringBuilder();
        if (clz != null) {
            sb.append("{\"@class\":\"").append(clz.getCanonicalName()).append("\",\"@data\":");
        }
        sb.append("[");
        for (int i = 0; i < list.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object o = list[i];
            if (o instanceof java.lang.String) {
                sb.append("\"").append(o).append("\"");
            } else if (o instanceof javaslang.collection.Seq) {
                sb.append(genJsonList(clz == null ? null : javaslangClass(o), ((Seq) o).toJavaList().toArray()));
            } else {
                sb.append(o);
            }
        }
        sb.append("]");
        if (clz != null) {
            sb.append("}");
        }
        return sb.toString();
    }

    protected String genJsonMap(Class<?> clz, java.util.Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        if (clz != null) {
            sb.append("{\"@class\":\"").append(clz.getCanonicalName()).append("\",\"@data\":");
        }
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
        if (clz != null) {
            sb.append("}");
        }
        return sb.toString();
    }
}
