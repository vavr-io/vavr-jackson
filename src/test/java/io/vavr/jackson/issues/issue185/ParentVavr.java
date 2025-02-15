package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import java.util.Objects;

//TODO replace with record in the future, currently not supported by jackson
public class ParentVavr {

  @JsonMerge(OptBoolean.FALSE)
  List<String> list;

  @JsonMerge
  Map<String, String> map;

  @JsonMerge
  Map<String, Map<String, String>> deepMap;

  @JsonMerge
  Child child;

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
