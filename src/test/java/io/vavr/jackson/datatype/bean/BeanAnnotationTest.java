package io.vavr.jackson.datatype.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vavr.jackson.datatype.BaseTest;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeanAnnotationTest extends BaseTest {

    public static final String CHARSEQ_VALUE = "CHARSEQ_VALUE";
    public static final String EITHER_VALUE = "EITHER_VALUE";
    public static final String MAP_VALUE = "MAP_VALUE";
    public static final String MULTIMAP_VALUE = "MULTIMAP_VALUE";
    public static final String OPTION_VALUE = "OPTION_VALUE";
    public static final String SEQ_VALUE = "SEQ_VALUE";
    public static final String SET_VALUE = "SET_VALUE";
    public static final String EMPTY_JSON = "{}";

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class BeanObjectOptional {

        public CharSeq charSeq;
        public Either<String, String> either;
        public Map<String, String> map;
        public Multimap<String, String> multimap;
        public Option<String> option;
        public Seq<String> seq;
        public Set<String> set;

        public BeanObjectOptional() {
            this(true);
        }

        public BeanObjectOptional(boolean empty) {
            if (empty) {
                charSeq = CharSeq.empty();
                either = Either.left(EITHER_VALUE);
                option = Option.none();
                map = HashMap.empty();
                multimap = HashMultimap.withSeq().empty();
                seq = List.empty();
                set = HashSet.empty();
            } else {
                charSeq = CharSeq.of(CHARSEQ_VALUE);
                either = Either.right(EITHER_VALUE);
                option = Option.of(OPTION_VALUE);
                map = HashMap.of("key", MAP_VALUE);
                multimap = HashMultimap.withSeq().of("key", MULTIMAP_VALUE);
                seq = List.of(SEQ_VALUE);
                set = HashSet.of(SET_VALUE);
            }
        }
    }

    @Test
    void testNonEmpty() throws Exception {
        BeanObjectOptional bean = new BeanObjectOptional(false);
        String json = mapper().writer().writeValueAsString(bean);
        Assertions.assertTrue(json.contains(CHARSEQ_VALUE));
        Assertions.assertTrue(json.contains(EITHER_VALUE));
        Assertions.assertTrue(json.contains(OPTION_VALUE));
        Assertions.assertTrue(json.contains(MAP_VALUE));
        Assertions.assertTrue(json.contains(MULTIMAP_VALUE));
        Assertions.assertTrue(json.contains(SEQ_VALUE));
        Assertions.assertTrue(json.contains(SET_VALUE));
    }

    @Test
    void testEmpty() throws Exception {
        BeanObjectOptional bean = new BeanObjectOptional();
        String json = mapper().writer().writeValueAsString(bean);
        Assertions.assertEquals(EMPTY_JSON, json);
    }

}
