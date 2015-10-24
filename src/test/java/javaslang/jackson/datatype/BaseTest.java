package javaslang.jackson.datatype;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

    protected ObjectMapper mapper(boolean compact) {
        ObjectMapper mapper = new ObjectMapper();
        JavaslangModule.JavaslangModuleConfig cfg = new JavaslangModule.JavaslangModuleConfig();
        cfg.setCompact(compact);
        mapper.registerModule(new JavaslangModule(cfg));
        return mapper;
    }

    protected String genPlainJsonList(Object... list) {
        return genPlainJsonList(null, list);
    }

    protected String genPlainJsonList(Class<?> clz, Object... list) {
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
