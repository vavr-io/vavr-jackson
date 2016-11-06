package javaslang.jackson.datatype;

import java.lang.reflect.Type;

import javaslang.collection.*;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.*;

public class JavaslangTypeModifier extends TypeModifier {

    @Override
    public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory)
    {
        if (type.isReferenceType() || type.isContainerType()) {
            return type;
        }
        final Class<?> raw = type.getRawClass();
        if (Seq.class.isAssignableFrom(raw) && CharSeq.class != raw) {
            return CollectionLikeType.construct(raw, type.containedTypeOrUnknown(0));
        }
        if (Set.class.isAssignableFrom(raw)) {
            return CollectionLikeType.construct(raw, type.containedTypeOrUnknown(0));
        }
        if (PriorityQueue.class.isAssignableFrom(raw)) {
            return CollectionLikeType.construct(raw, type.containedTypeOrUnknown(0));
        }
        return type;
    }
}
