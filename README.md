[![Build Status](https://travis-ci.org/javaslang/javaslang-jackson.svg?branch=master)](https://travis-ci.org/javaslang/javaslang-jackson)
[![Coverage Status](https://coveralls.io/repos/javaslang/javaslang-jackson/badge.svg)](https://coveralls.io/github/javaslang/javaslang-jackson)

# javaslang-jackson

Jackson datatype module for [Javaslang](http://javaslang.com/) library

## Usage

### Registering module
Just register a new instance of <code>JavaslangModule</code>
```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaslangModule());
```
### Standard serialization/deserialization
```java
String json = mapper.writer().writeValueAsString(List.of(List.of(1)));
// = [[1]]
Object restored1 = mapper.readValue(json, List.class);
// = List(java.util.ArrayList(1))
Object restored2 = mapper.readValue(json, new TypeReference<List<List<?>>>() {});
// = List(List(1))
```
### Extended serialization/deserialization
```java
JavaslangModule.Config cfg = new JavaslangModule.Config();
cfg.setCompactMode(false);
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaslangModule(cfg));

String json = mapper.writer().writeValueAsString(List.of(List.of(1)));
// = {"@class":"javaslang.collection.List",
//    "@data":[
//              {"@class":"javaslang.collection.List","@data":[1]}
//            ]
//   }
Object restored = mapper.readValue(json, Value.class);
// = List(List(1))
```
## Using Developer Versions

Developer versions can be found [here](https://oss.sonatype.org/content/repositories/snapshots/com/javaslang/javaslang-jackson).

### Maven

```xml
<dependency>
  <groupId>com.javaslang</groupId>
  <artifactId>javaslang-jackson</artifactId>
  <version>2.0.0-SNAPSHOT</version>
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
compile("com.javaslang:javaslang-jackson:2.0.0-SNAPSHOT")
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
