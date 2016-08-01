package javaslang.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.collection.List;
import javaslang.control.Option;
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
        mapper.registerModule(new JavaslangModule());

        Option<I> i = mapper.readerFor(new TypeReference<Option<I>>(){}).readValue("{\"type\":\"a\"}");
        Assert.assertTrue(i.isDefined());
        Assert.assertTrue(i.get() instanceof A);
    }

    @Test
    public void deserializeList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());

        List<I> i = mapper.readerFor(new TypeReference<List<I>>(){}).readValue("[{\"type\":\"a\"},{\"type\":\"b\"}]");
        Assert.assertTrue(i.nonEmpty());
        Assert.assertTrue(i.get(0) instanceof A);
        Assert.assertTrue(i.get(1) instanceof B);
    }

}
