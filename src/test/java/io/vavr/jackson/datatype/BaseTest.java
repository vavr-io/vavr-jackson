package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;
import tools.jackson.module.jaxb.JaxbAnnotationModule;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    protected interface WrapperObject {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_ARRAY)
    protected interface WrapperArray {
    }

    protected void verifySerialization(TypeReference<?> typeReference, List<Tuple2<?, String>> testValues) {
        ObjectWriter writer = mapper().writerFor(typeReference);
        for (Tuple2<?, String> testValue : testValues) {
            Object src = testValue._1();
            String expectedJson = testValue._2();

            String json = writer.writeValueAsString(src);
            assertThat(json).isEqualTo(expectedJson);

            Object dst = mapper().readValue(json, typeReference);
            assertThat(dst).isEqualTo(src);
        }
    }

    protected ObjectMapper mapper() {
        return JsonMapper.builder().addModule(new VavrModule()).build();
    }

    protected ObjectMapper mapper(VavrModule.Settings settings) {
        return JsonMapper.builder().addModule(new VavrModule(settings)).build();
    }

    public XmlMapper xmlMapper() {
        return XmlMapper.builder().addModule(new VavrModule()).build();
    }

    public XmlMapper xmlMapperJaxb() {
        return XmlMapper.builder().addModules(new JaxbAnnotationModule(), new VavrModule()).build();
    }

    protected String wrapToArray(String as, String json) {
        return "[\"%s\",%s]".formatted(as, json);
    }

    protected String wrapToObject(String as, String json) {
        return "{\"%s\":%s}".formatted(as, json);
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
            Object value = entry.getValue();
            if (value instanceof Collection<?> collection ) {
                sb.append("[");
                int j = 0;
                for (Object v : (collection)) {
                    if (j > 0) {
                        sb.append(",");
                    }
                    appendObj(sb, v);
                    j++;
                }
                sb.append("]");
            } else {
                appendObj(sb, value);
            }
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    protected void checkSerialization(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
    }

    private void appendObj(StringBuilder sb, Object o) {
        if (o instanceof java.lang.String str) {
            sb.append("\"").append(str).append("\"");
        } else if (o instanceof io.vavr.collection.Seq<?> seq) {
            sb.append(genJsonList(seq.toJavaList().toArray()));
        } else {
            sb.append(o);
        }
    }

    protected String asJson(String json) {
        return json.replace("'", "\"");
    }
}
