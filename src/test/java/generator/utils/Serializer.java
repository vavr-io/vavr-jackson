package generator.utils;

import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author <a href="mailto:ruslan.sennov@gmail.com">Ruslan Sennov</a>
 */
public class Serializer {

    public static final int EXTENDED_OPTION = 1;

    public static String expectedJson(Object o) {
        return expectedJson(o, 0);
    }

    public static String expectedJson(Object o, int opts) {
        if (o instanceof Either) {
            return expectedEitherJson((Either<?, ?>) o, opts);
        }
        if (o instanceof Lazy) {
            return expectedLazyJson((Lazy<?>) o, opts);
        }
        if (o instanceof Option) {
            return expectedOptionJson((Option<?>) o, opts);
        }
        if (o instanceof Seq) {
            return expectedSeqJson((Seq<?>) o, opts);
        }
        if (o instanceof Set) {
            return expectedSetJson((Set<?>) o, opts);
        }
        if (o instanceof Map) {
            return expectedMapJson((Map<?, ?>) o, opts);
        }
        if (o instanceof Multimap) {
            return expectedMultimapJson((Multimap<?, ?>) o, opts);
        }
        if (o instanceof Tuple2) {
            return expectedTupleJson((Tuple2<?, ?>) o, opts);
        }
        if (o instanceof String) {
            return "\"" + o.toString().replace("\"", "\\\"") + "\"";
        }
        return o.toString();
    }

    private static String expectedEitherJson(Either<?, ?> either, int opts) {
        if (either.isLeft()) {
            return "[\"left\"," + expectedJson(either.left().get(), opts) + "]";
        } else {
            return "[\"right\"," + expectedJson(either.right().get(), opts) + "]";
        }
    }

    private static String expectedTupleJson(Tuple tuple, int opts) {
        return expectedSeqJson(tuple.toSeq(), opts);
    }

    private static String expectedLazyJson(Lazy<?> lazy, int opts) {
        return expectedJson(lazy.get(), opts);
    }

    private static String expectedOptionJson(Option<?> opt, int opts) {
        if ((opts & EXTENDED_OPTION) == 0) {
            return expectedJson(opt.get(), opts);
        } else {
            if (opt.isDefined()) {
                return "[\"defined\"," + expectedJson(opt.get(), opts) + "]";
            } else {
                return "[\"undefined\"]";
            }
        }
    }

    private static String expectedSeqJson(Seq<?> seq, int opts) {
        return seq.toStream().map(o -> expectedJson(o, opts)).mkString("[", ",", "]");
    }

    private static String expectedSetJson(Set<?> seq, int opts) {
        return seq.toStream().map(o -> expectedJson(o, opts)).mkString("[", ",", "]");
    }

    private static String expectedMapJson(Map<?, ?> map, int opts) {
        return map.toStream().map(o -> expectedJson(o._1.toString(), opts) + ":" + expectedJson(o._2, opts)).mkString("{", ",", "}");
    }

    private static String expectedMultimapJson(Multimap<?, ?> multimap, int opts) {
        final LinkedHashMap<Object, List<Object>> map = new LinkedHashMap<>();
        multimap.forEach(e -> {
            List<Object> list = map.computeIfAbsent(e._1, k -> new ArrayList<>());
            list.add(e._2);
        });
        StringBuilder sb = new StringBuilder("{");
        map.forEach((k, l) -> sb.append(expectedJson(k.toString(), opts)).append(":").append(expectedJson(io.vavr.collection.Stream.ofAll(l))));
        sb.append("}");
        return sb.toString();
    }

}
