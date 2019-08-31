package io.vavr.jackson.datatype.bean;

import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ComplexBeanTest extends BaseTest {

    @Test
    void test1() throws IOException {

        final ComplexClass src = ComplexClass.build();
        final String json = mapper().writer().writeValueAsString(src);
        final ComplexClass fromJson = mapper().readValue(json, ComplexClass.class);

        Assertions.assertEquals(fromJson.getComplexInnerClasses(), src.getComplexInnerClasses());
        Assertions.assertEquals(fromJson.getComplexInnerClassHashSet(), src.getComplexInnerClassHashSet());
        Assertions.assertEquals(fromJson.getComplexInnerClassList(), src.getComplexInnerClassList());
        Assertions.assertEquals(fromJson.getComplexInnerClassQueue(), src.getComplexInnerClassQueue());
        Assertions.assertEquals(fromJson.getComplexInnerClassStream(), src.getComplexInnerClassStream());
        Assertions.assertEquals(fromJson.getComplexInnerClassVector(), src.getComplexInnerClassVector());
        Assertions.assertEquals(fromJson.getComplexInnerClassTuple2(), src.getComplexInnerClassTuple2());
        Assertions.assertEquals(fromJson.getComplexInnerClassTreeMap(), src.getComplexInnerClassTreeMap());
        Assertions.assertEquals(fromJson.getComplexInnerClassHashMap(), src.getComplexInnerClassHashMap());
        Assertions.assertEquals(fromJson.getComplexInnerClassTreeMultimap(), src.getComplexInnerClassTreeMultimap());
        Assertions.assertEquals(fromJson.getComplexInnerClassHashMultimap(), src.getComplexInnerClassHashMultimap());
        Assertions.assertEquals(fromJson.getOpt1(), src.getOpt1());
        Assertions.assertEquals(fromJson.getOpt2(), src.getOpt2());
        Assertions.assertEquals(fromJson.getComplexInnerClassTreeSet(), src.getComplexInnerClassTreeSet());

        final ComplexClass.ComplexInnerClass srcInnerClassFromTuple2 = src.getComplexInnerClassTuple2()._2;
        final ComplexClass.ComplexInnerClass fromJsonInnerClassFromTuple2 = fromJson.getComplexInnerClassTuple2()._2;
        Assertions.assertEquals(srcInnerClassFromTuple2, fromJsonInnerClassFromTuple2);

    }

}
