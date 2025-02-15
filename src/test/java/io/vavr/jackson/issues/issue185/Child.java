package io.vavr.jackson.issues;

import java.util.Objects;

public class Child {
  String name;
  String description;

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