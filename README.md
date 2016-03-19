[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.javaslang/javaslang-jackson/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.javaslang/javaslang-jackson)
[![Build Status](https://travis-ci.org/javaslang/javaslang-jackson.svg?branch=master)](https://travis-ci.org/javaslang/javaslang-jackson)
[![Coverage Status](https://codecov.io/github/javaslang/javaslang-jackson/coverage.svg?branch=master)](https://codecov.io/github/javaslang/javaslang-jackson?branch=master)
[![Gitter Chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/javaslang/javaslang)

# javaslang-jackson

Jackson datatype module for [Javaslang](http://javaslang.com/) library

## Usage

### Maven

```xml
<dependency>
  <groupId>io.javaslang</groupId>
  <artifactId>javaslang-jackson</artifactId>
  <version>2.0.0</version>
</dependency>
```

### Gradle

```groovy
compile("io.javaslang:javaslang-jackson:2.0.0")
```

### Registering module
Just register a new instance of <code>JavaslangModule</code>
```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaslangModule());
```
### Serialization/deserialization
```java
String json = mapper.writer().writeValueAsString(List.of(List.of(1)));
// = [[1]]
Object restored1 = mapper.readValue(json, List.class);
// = List(java.util.ArrayList(1))
Object restored2 = mapper.readValue(json, new TypeReference<List<List<?>>>() {});
// = List(List(1))
```
## Using Developer Versions

Developer versions can be found [here](https://oss.sonatype.org/content/repositories/snapshots/io/javaslang/javaslang-jackson).

### Maven

```xml
<dependency>
  <groupId>io.javaslang</groupId>
  <artifactId>javaslang-jackson</artifactId>
  <version>2.1.0-SNAPSHOT</version>
</dependency>
```

Ensure that your `~/.m2/settings.xml` contains the following:

```xml
<profiles>
    <profile>
        <id>allow-snapshots</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <repositories>
            <repository>
                <id>snapshots-repo</id>
                <url>https://oss.sonatype.org/content/repositories/snapshots</url>
                <releases>
                    <enabled>false</enabled>
                </releases>
                <snapshots>
                    <enabled>true</enabled>
                </snapshots>
            </repository>
        </repositories>
    </profile>
</profiles>
```

### Gradle

```groovy
compile("io.javaslang:javaslang-jackson:2.1.0-SNAPSHOT")
```

Ensure that your `build.gradle` contains the following:

```groovy
repositories {
    mavenCentral()
    maven {
        url "https://oss.sonatype.org/content/repositories/snapshots"
    }
}
```
