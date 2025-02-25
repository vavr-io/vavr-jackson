package io.vavr.jackson.issues.issue185;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.Objects;

//TODO replace with record in the future
public class ParentVavr {

  @JsonMerge(OptBoolean.FALSE)
  List<String> list;

  @JsonMerge
  @JsonDeserialize(using = ParentMapDeserializer.class)
  Map<String, String> map;

  @JsonMerge
  @JsonDeserialize(using = ParentDeepMapDeserializer.class)
  Map<String, Map<String, String>> deepMap;

  @JsonMerge
  Child child;

  // Define specialized deserializers as static inner classes
  private static class ParentMapDeserializer extends VavrJsonMergeMapDeserializer<ParentVavr, String, String> {
    public ParentMapDeserializer() {
      super(ParentVavr.class, String.class, String.class, parent -> parent.map);
    }
  }

  private static class ParentDeepMapDeserializer extends VavrJsonMergeMapDeserializer<ParentVavr, String, Map<String, String>> {
    public ParentDeepMapDeserializer() {
      super(ParentVavr.class, String.class, (Class<Map<String, String>>)(Class<?>)Map.class, parent -> parent.deepMap);
    }
  }

  private ParentVavr() {
  }

  private ParentVavr(List<String> list, Map<String, String> map, 
      Map<String, Map<String, String>> deepMap, Child child) {
    this.list = list;
    this.map = map;
    this.deepMap = deepMap;
    this.child = child;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParentVavr that = (ParentVavr) o;
    return Objects.equals(list, that.list) &&
            Objects.equals(map, that.map) &&
            Objects.equals(deepMap, that.deepMap) &&
            Objects.equals(child, that.child);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list, map, deepMap, child);
  }

  @Override
  public String toString() {
    return "ParentVavr{" +
           "list=" + list +
           ", map=" + map +
           ", deepMap=" + deepMap +
           ", child=" + child +
           '}';
  }
}
