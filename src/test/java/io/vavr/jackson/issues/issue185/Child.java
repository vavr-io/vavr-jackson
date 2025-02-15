package io.vavr.jackson.issues.issue185;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Child {
  @JsonProperty("name")
  private String name;
  
  @JsonProperty("description")
  private String description;

  private Child() {
    // Private no-args constructor
  }

  private Child(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Child child = (Child) o;
    return Objects.equals(name, child.name) &&
           Objects.equals(description, child.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    return "Child{" +
           "name='" + name + '\'' +
           ", description='" + description + '\'' +
           '}';
  }
}