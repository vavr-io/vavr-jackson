package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.util.RawValue;
import javaslang.collection.List;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ScalarTest extends BaseTest {

    @Test
    public void test1() throws IOException {
        List<Integer> l1 = mapper().readValue("[1]", new TypeReference<List<Integer>>() {});
        Assert.assertEquals(l1, List.of(1));
        List<Boolean> l2 = mapper().readValue("[true]", new TypeReference<List<Boolean>>() {});
        Assert.assertEquals(l2, List.of(true));
        List<Boolean> l3 = mapper().readValue("[false]", new TypeReference<List<Boolean>>() {});
        Assert.assertEquals(l3, List.of(false));
        List<Float> l4 = mapper().readValue("[2.4]", new TypeReference<List<Float>>() {});
        Assert.assertEquals(l4, List.of(2.4f));
        List<Double> l5 = mapper().readValue("[2.4]", new TypeReference<List<Double>>() {});
        Assert.assertEquals(l5, List.of(2.4));
        List<BigDecimal> l5d = mapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS).readValue("[2.4]", new TypeReference<List<BigDecimal>>() {});
        Assert.assertEquals(l5d, List.of(BigDecimal.valueOf(2.4)));
        List<String> l6 = mapper().readValue("[\"1\"]", new TypeReference<List<String>>() {});
        Assert.assertEquals(l6, List.of("1"));
        List<Long> l7 = mapper().readValue("[24]", new TypeReference<List<Long>>() {});
        Assert.assertEquals(l7, List.of(24L));
        List<Long> l8 = mapper().readValue("[" + Long.MAX_VALUE + "]", new TypeReference<List<Long>>() {});
        Assert.assertEquals(l8, List.of(Long.MAX_VALUE));
        List<BigInteger> l9 = mapper().readValue("[1234567890123456789012]", new TypeReference<List<BigInteger>>() {});
        Assert.assertEquals(l9, List.of(new BigInteger("1234567890123456789012")));
    }

    @Test(expected = JsonMappingException.class)
    public void test2() throws IOException {
        mapper().readValue("[1.3]", new TypeReference<List<Integer>>() {});
    }

    @Test(expected = JsonMappingException.class)
    public void test3() throws IOException {
        mapper().readValue("[1]", new TypeReference<List<String>>() {});
    }

    @Test
    public void test4() throws JsonProcessingException {
        JsonNodeFactory factory = new JsonNodeFactory(true);
        ArrayNode arrNode = factory.arrayNode();
        arrNode.add(factory.numberNode(BigDecimal.TEN));
        Assert.assertEquals(mapper().treeToValue(arrNode, List.class), List.of(BigDecimal.TEN));
    }

    @Test(expected = JsonMappingException.class)
    public void test5() throws JsonProcessingException {
        JsonNodeFactory factory = new JsonNodeFactory(true);
        ArrayNode arrNode = factory.arrayNode();
        arrNode.add(factory.rawValueNode(new RawValue("kekeke")));
        mapper().treeToValue(arrNode, List.class);
    }
}
