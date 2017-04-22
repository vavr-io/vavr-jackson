package io.vavr.jackson.datatype.bean;

import io.vavr.jackson.datatype.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ComplexBeanTest extends BaseTest {

    @Test
    public void test1() throws IOException {

        final ComplexClass src = ComplexClass.build();
        final String json = mapper().writer().writeValueAsString(src);
        final ComplexClass fromJson = mapper().readValue(json, ComplexClass.class);

        Assert.assertEquals(fromJson.getComplexInnerClasses(), src.getComplexInnerClasses());
        Assert.assertEquals(fromJson.getComplexInnerClassHashSet(), src.getComplexInnerClassHashSet());
        Assert.assertEquals(fromJson.getComplexInnerClassList(), src.getComplexInnerClassList());
        Assert.assertEquals(fromJson.getComplexInnerClassQueue(), src.getComplexInnerClassQueue());
        Assert.assertEquals(fromJson.getComplexInnerClassStream(), src.getComplexInnerClassStream());
        Assert.assertEquals(fromJson.getComplexInnerClassVector(), src.getComplexInnerClassVector());
        Assert.assertEquals(fromJson.getComplexInnerClassTuple2(), src.getComplexInnerClassTuple2());
        Assert.assertEquals(fromJson.getComplexInnerClassTreeMap(), src.getComplexInnerClassTreeMap());
        Assert.assertEquals(fromJson.getComplexInnerClassHashMap(), src.getComplexInnerClassHashMap());
        Assert.assertEquals(fromJson.getComplexInnerClassTreeMultimap(), src.getComplexInnerClassTreeMultimap());
        Assert.assertEquals(fromJson.getComplexInnerClassHashMultimap(), src.getComplexInnerClassHashMultimap());
        Assert.assertEquals(fromJson.getOpt1(), src.getOpt1());
        Assert.assertEquals(fromJson.getOpt2(), src.getOpt2());
        Assert.assertEquals(fromJson.getComplexInnerClassTreeSet(), src.getComplexInnerClassTreeSet());

        final ComplexClass.ComplexInnerClass srcInnerClassFromTuple2 = src.getComplexInnerClassTuple2()._2;
        final ComplexClass.ComplexInnerClass fromJsonInnerClassFromTuple2 = fromJson.getComplexInnerClassTuple2()._2;
        Assert.assertEquals(srcInnerClassFromTuple2, fromJsonInnerClassFromTuple2);

    }

}
