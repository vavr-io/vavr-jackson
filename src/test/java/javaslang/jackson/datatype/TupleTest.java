package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import javaslang.Tuple;
import javaslang.Tuple8;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TupleTest extends BaseTest {

    private String makeJson(boolean compact, Object... objects) throws JsonProcessingException {
        ObjectWriter writer = mapper(false).writer();
        StringBuilder sb = new StringBuilder();
        if (!compact) {
            sb.append("{\"@class\":\"javaslang.Tuple").append(objects.length).append("\",\"@data\":");
        }
        sb.append("{");
        for (int i = 1; i <= objects.length; i++) {
            if (i > 1) {
                sb.append(",");
            }
            sb.append("\"_").append(i).append("\":").append(writer.writeValueAsString(objects[i - 1]));
        }
        sb.append("}");
        if (!compact) {
            sb.append("}");
        }
        return sb.toString();
    }

    @Test
    public void test8() throws IOException {
        String result = mapper(false).writeValueAsString(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(makeJson(false, 1, 2, 3, 4, 5, 6, 7, 8), result);
        Tuple8<?, ?, ?, ?, ?, ?, ?, ?> tuple = mapper(false).readValue(result, new TypeReference<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>>() {
        });
        Assert.assertEquals(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8), tuple);
    }

}
