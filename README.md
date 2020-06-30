[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.vavr/vavr-jackson/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.vavr/vavr-jackson)
[![Build Status](https://travis-ci.org/vavr-io/vavr-jackson.svg?branch=master)](https://travis-ci.org/vavr-io/vavr-jackson)
[![Coverage Status](https://codecov.io/github/vavr-io/vavr-jackson/coverage.svg?branch=master)](https://codecov.io/github/vavr-io/vavr-jackson?branch=master)
[![Gitter Chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vavr-io/vavr)

# vavr-jackson

Jackson datatype module for [Vavr](http://vavr.io/) library

## Usage

### Maven

```xml
<dependency>
  <groupId>io.vavr</groupId>
  <artifactId>vavr-jackson</artifactId>
  <version>0.10.3</version>
</dependency>
```

### Gradle

```groovy
compile("io.vavr:vavr-jackson:0.10.3")
```

### Registering module
Just register a new instance of <code>VavrModule</code>
```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new VavrModule());
```

### Serialization/deserialization

<!-- see io.vavr.jackson.datatype.docs.ReadmeTest#testDeser -->

```java
String json = mapper.writeValueAsString(List.of(1));
// = [1]
List<Integer> restored = mapper.readValue(json, new TypeReference<List<Integer>>() {});
// = List(1)
```

## Using Developer Versions

Developer versions can be found [here](https://oss.sonatype.org/content/repositories/snapshots/io/vavr/vavr-jackson).

### Maven

```xml
<dependency>
  <groupId>io.vavr</groupId>
  <artifactId>vavr-jackson</artifactId>
  <version>1.0.0-SNAPSHOT</version>
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
compile("io.vavr:vavr-jackson:1.0.0-SNAPSHOT")
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
