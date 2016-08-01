/**
 * Copyright 2015 The Javaslang Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javaslang.jackson.datatype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.control.Either;
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

    @Test
    public void deserializeMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());

        Map<String, I> i = mapper.readerFor(new TypeReference<Map<String, I>>(){}).readValue("{\"a\":{\"type\":\"a\"},\"b\":{\"type\":\"b\"}}");
        Assert.assertTrue(i.nonEmpty());
        Assert.assertTrue(i.get("a").get() instanceof A);
        Assert.assertTrue(i.get("b").get() instanceof B);
    }

    @Test
    public void deserializeEither() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());

        Either<I, I> i = mapper.readerFor(new TypeReference<Either<I, I>>(){}).readValue("[\"left\",{\"type\":\"b\"}]");
        Assert.assertTrue(i.isLeft());
        Assert.assertTrue(i.getLeft() instanceof B);
    }

    @Test
    public void deserializeTuple2() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaslangModule());

        Tuple2<I, I> i = mapper.readerFor(new TypeReference<Tuple2<I, I>>(){}).readValue("[{\"type\":\"a\"},{\"type\":\"b\"}]");
        Assert.assertTrue(i._1 instanceof A);
        Assert.assertTrue(i._2 instanceof B);
    }


}
