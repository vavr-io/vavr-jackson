# jackson-datatype-javaslang
[![Build Status](https://travis-ci.org/javaslang/javaslang-jackson.svg?branch=master)](https://travis-ci.org/javaslang/javaslang-jackson)
[![Coverage Status](https://coveralls.io/repos/javaslang/javaslang-jackson/badge.svg)](https://coveralls.io/github/javaslang/javaslang-jackson)

Jackson datatype module for [Javaslang](http://javaslang.com) library

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

Ensure you `build.gradle` contains the following:

```groovy
repositories {
    mavenCentral()
    maven {
        url "https://oss.sonatype.org/content/repositories/snapshots"
    }
}
```
