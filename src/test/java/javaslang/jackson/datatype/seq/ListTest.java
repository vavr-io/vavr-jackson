package javaslang.jackson.datatype.seq;

import javaslang.collection.List;
import javaslang.collection.Seq;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class ListTest extends SeqTest {

    @Override
    protected Class<?> clz() {
        return List.class;
    }

    @Override
    protected Class<?> implClz() {
        return List.Cons.class;
    }

    @Override
    protected Seq<?> of(Object... objects) {
        return List.ofAll(Arrays.asList(objects));
    }

    @Test
    public void testDefaultDeserialization() throws IOException {
        Assert.assertEquals(mapper().readValue("[1]", Seq.class), List.of(1));
    }

}
