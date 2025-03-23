package io.vavr.jackson.datatype.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vavr.collection.CharSeq;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    void nonEmpty() throws Exception {
        BeanObjectOptional bean = new BeanObjectOptional(false);
        String json = mapper().writer().writeValueAsString(bean);
        assertThat(json.contains(CHARSEQ_VALUE)).isTrue();
        assertThat(json.contains(EITHER_VALUE)).isTrue();
        assertThat(json.contains(OPTION_VALUE)).isTrue();
        assertThat(json.contains(MAP_VALUE)).isTrue();
        assertThat(json.contains(MULTIMAP_VALUE)).isTrue();
        assertThat(json.contains(SEQ_VALUE)).isTrue();
        assertThat(json.contains(SET_VALUE)).isTrue();
    }

    @Test
    void empty() throws Exception {
        BeanObjectOptional bean = new BeanObjectOptional();
        String json = mapper().writer().writeValueAsString(bean);
        assertThat(json).isEqualTo(EMPTY_JSON);
    }
}
