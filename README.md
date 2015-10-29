# jackson-datatype-javaslang
[![Build Status](https://travis-ci.org/ruslansennov/jackson-datatype-javaslang.svg?branch=master)](https://travis-ci.org/ruslansennov/jackson-datatype-javaslang)
[![Coverage Status](https://coveralls.io/repos/ruslansennov/jackson-datatype-javaslang/badge.svg)](https://coveralls.io/github/ruslansennov/jackson-datatype-javaslang)

Jackson datatype module for [Javaslang](http://javaslang.com/) library

## Using Developer Versions

Developer versions can be found [here](https://oss.sonatype.org/content/repositories/snapshots/com/javaslang/jackson-datatype-javaslang).

### Maven

```xml
<dependency>
  <groupId>com.javaslang</groupId>
  <artifactId>jackson-datatype-javaslang</artifactId>
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
