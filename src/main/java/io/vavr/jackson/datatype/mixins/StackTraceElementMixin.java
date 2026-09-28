package io.vavr.jackson.datatype.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class StackTraceElementMixin {

  @JsonIgnore
  private String classLoaderName;

  @JsonIgnore
  private String moduleName;

  @JsonIgnore
  private String moduleVersion;
}

