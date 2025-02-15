package io.vavr.jackson.issues;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;

import java.util.List;
import java.util.Map;

//TODO replace with record in the future, currently not supported by jackson
public class ParentJava {

  @JsonMerge(OptBoolean.FALSE)
  List<String> list;

  @JsonMerge
  Map<String, String> map;

  @JsonMerge
  Map<String, Map<String, String>> deepMap;

  @JsonMerge
  Child child;

  private ParentJava() {
  }

  private ParentJava(List<String> list, Map<String, String> map, 
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
    
    ParentJava that = (ParentJava) o;
    
    if (list != null ? !list.equals(that.list) : that.list != null) return false;
    if (map != null ? !map.equals(that.map) : that.map != null) return false;
    if (deepMap != null ? !deepMap.equals(that.deepMap) : that.deepMap != null) return false;
    return child != null ? child.equals(that.child) : that.child == null;
  }

  @Override
  public int hashCode() {
    int result = list != null ? list.hashCode() : 0;
    result = 31 * result + (map != null ? map.hashCode() : 0);
    result = 31 * result + (deepMap != null ? deepMap.hashCode() : 0);
    result = 31 * result + (child != null ? child.hashCode() : 0);
    return result;
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
