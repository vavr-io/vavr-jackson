package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.collection.Stream;

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

    protected String genJson(Class<?> clz, Object... list) {
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
                sb.append(genJson(clz == null ? null : javaslangClass(o), ((Seq) o).toJavaList().toArray()));
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
}
