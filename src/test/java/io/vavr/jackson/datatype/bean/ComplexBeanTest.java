package io.vavr.jackson.datatype.bean;

import io.vavr.jackson.datatype.BaseTest;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComplexBeanTest extends BaseTest {

    @Test
    void test1() throws IOException {

        final ComplexClass src = ComplexClass.build();
        final String json = mapper().writer().writeValueAsString(src);
        final ComplexClass fromJson = mapper().readValue(json, ComplexClass.class);

        assertThat(src.getComplexInnerClasses()).isEqualTo(fromJson.getComplexInnerClasses());
        assertThat(src.getComplexInnerClassHashSet()).isEqualTo(fromJson.getComplexInnerClassHashSet());
        assertThat(src.getComplexInnerClassList()).isEqualTo(fromJson.getComplexInnerClassList());
        assertThat(src.getComplexInnerClassQueue()).isEqualTo(fromJson.getComplexInnerClassQueue());
        assertThat(src.getComplexInnerClassStream()).isEqualTo(fromJson.getComplexInnerClassStream());
        assertThat(src.getComplexInnerClassVector()).isEqualTo(fromJson.getComplexInnerClassVector());
        assertThat(src.getComplexInnerClassTuple2()).isEqualTo(fromJson.getComplexInnerClassTuple2());
        assertThat(src.getComplexInnerClassTreeMap()).isEqualTo(fromJson.getComplexInnerClassTreeMap());
        assertThat(src.getComplexInnerClassHashMap()).isEqualTo(fromJson.getComplexInnerClassHashMap());
        assertThat(src.getComplexInnerClassTreeMultimap()).isEqualTo(fromJson.getComplexInnerClassTreeMultimap());
        assertThat(src.getComplexInnerClassHashMultimap()).isEqualTo(fromJson.getComplexInnerClassHashMultimap());
        assertThat(src.getOpt1()).isEqualTo(fromJson.getOpt1());
        assertThat(src.getOpt2()).isEqualTo(fromJson.getOpt2());
        assertThat(src.getComplexInnerClassTreeSet()).isEqualTo(fromJson.getComplexInnerClassTreeSet());

        final ComplexClass.ComplexInnerClass srcInnerClassFromTuple2 = src.getComplexInnerClassTuple2()._2;
        final ComplexClass.ComplexInnerClass fromJsonInnerClassFromTuple2 = fromJson.getComplexInnerClassTuple2()._2;
        assertThat(fromJsonInnerClassFromTuple2).isEqualTo(srcInnerClassFromTuple2);
    }
}
