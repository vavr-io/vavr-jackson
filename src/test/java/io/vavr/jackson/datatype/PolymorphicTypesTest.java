package io.vavr.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PolymorphicTypesTest {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = A.class, name = "a"),
            @JsonSubTypes.Type(value = B.class, name = "b"),
    })
    public interface I {}

    public static class A implements I {}

    public static class B implements I {}

    @Test
    public void deserializeOptionA() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());

        Option<I> i = mapper.readerFor(new TypeReference<Option<I>>(){}).readValue("{\"type\":\"a\"}");
        Assert.assertTrue(i.isDefined());
        Assert.assertTrue(i.get() instanceof A);
    }

    @Test
    public void deserializeList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());

        List<I> i = mapper.readerFor(new TypeReference<List<I>>(){}).readValue("[{\"type\":\"a\"},{\"type\":\"b\"}]");
        Assert.assertTrue(i.nonEmpty());
        Assert.assertTrue(i.get(0) instanceof A);
        Assert.assertTrue(i.get(1) instanceof B);
    }

    @Test
    public void deserializeMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());

        Map<String, I> i = mapper.readerFor(new TypeReference<Map<String, I>>(){}).readValue("{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}");
        Assert.assertTrue(i.nonEmpty());
        Assert.assertTrue(i.get("a").get() instanceof A);
        Assert.assertTrue(i.get("b").get() instanceof B);
    }

    @Test
    public void deserializeEither() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());

        Either<I, I> i = mapper.readerFor(new TypeReference<Either<I, I>>(){}).readValue("[\"left\",{\"type\":\"b\"}]");
        Assert.assertTrue(i.isLeft());
        Assert.assertTrue(i.getLeft() instanceof B);
    }

    @Test
    public void deserializeTuple2() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());

        Tuple2<I, I> i = mapper.readerFor(new TypeReference<Tuple2<I, I>>(){}).readValue("[{\"type\":\"a\"},{\"type\":\"b\"}]");
        Assert.assertTrue(i._1 instanceof A);
        Assert.assertTrue(i._2 instanceof B);
    }


}
