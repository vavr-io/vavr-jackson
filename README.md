# vavr-jackson

[![Build](https://github.com/vavr-io/vavr-jackson/actions/workflows/ci.yml/badge.svg)](https://github.com/vavr-io/vavr-jackson/actions/workflows/ci.yml)
![Maven Central Version](https://img.shields.io/maven-central/v/io.vavr/vavr-jackson?versionPrefix=0)

Jackson datatype module for [Vavr](https://vavr.io/) library.

This module enables Jackson to seamlessly serialize and deserialize Vavr's functional data types, including immutable collections, tuples, and control types like `Option` and `Either`.

[![Stargazers over time](https://starchart.cc/vavr-io/vavr-jackson.svg?variant=adaptive)](https://starchart.cc/vavr-io/vavr-jackson)

## Usage


> - **Jackson 2.x** with **Vavr 0.x** → use **vavr-jackson 0.x**
> - **Jackson 3.x** with **Vavr 0.x** → use **vavr-jackson 1.x (SNAPSHOT)**


### Maven

```xml
<dependency>
  <groupId>io.vavr</groupId>
  <artifactId>vavr-jackson</artifactId>
  <version>0.11.0</version>
</dependency>
```

### Gradle

```groovy
compile("io.vavr:vavr-jackson:0.11.0")
```

### Registering the Module

Register the `VavrModule` with your Jackson `ObjectMapper`:

```java
ObjectMapper mapper = JsonMapper.builder()
    .addModule(new VavrModule())
    .build();
```

### Basic Usage

```java
import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

ObjectMapper mapper = JsonMapper.builder()
  .addModule(new VavrModule())
  .build();

String json = mapper.writeValueAsString(List.of(1, 2, 3)); // Result: [1,2,3]
List<Integer> restored = mapper.readValue(json, new TypeReference<>() {}); // Result: List(1, 2, 3)
```

## Supported Types

The module provides serialization and deserialization support for:

### Collections
- **Sequences**: `List`, `Array`, `Vector`, `Stream`, `Queue`, `IndexedSeq`
- **Sets**: `HashSet`, `LinkedHashSet`, `TreeSet`, `Set`
- **Maps**: `HashMap`, `LinkedHashMap`, `TreeMap`, `Map`
- **Multimaps**: All Vavr Multimap implementations
- **Other**: `PriorityQueue`, `CharSeq`

### Control Types
- `Option` - Optional values that can be `Some` or `None`
- `Either` - Values that can be `Left` or `Right`
- `Lazy` - Lazily evaluated values

### Tuples
- `Tuple0` through `Tuple8` - Immutable tuples with 0 to 8 elements

### Functions
- `Function0` through `Function8`
- `CheckedFunction0` through `CheckedFunction8`

## Configuration

You can customize the module behavior using `VavrModule.Settings`:

### Plain Option Format

By default, `Option` values are serialized in a plain format (just the value or null). You can change this behavior:

```java
VavrModule.Settings settings = new VavrModule.Settings();
ObjectMapper mapper = JsonMapper.builder()
    .addModule(new VavrModule(settings))
    .build();

mapper.writeValueAsString(Option.of(42));  // Result: 42
mapper.writeValueAsString(Option.none());  // Result: null
```

```java
VavrModule.Settings settings = new VavrModule.Settings().useOptionInPlainFormat(false);
ObjectMapper mapper = JsonMapper.builder()
    .addModule(new VavrModule(settings))
    .build();

mapper.writeValueAsString(Option.of(42));  // Result: ["defined",42]
mapper.writeValueAsString(Option.none());  // Result: ["undefined"]
```

### Deserialize Null as Empty Collection

Configure whether `null` values should be deserialized as empty collections:

```java
VavrModule.Settings settings = new VavrModule.Settings()
    .deserializeNullAsEmptyCollection(true);
ObjectMapper mapper = JsonMapper.builder()
    .addModule(new VavrModule(settings))
    .build();

List<Integer> result = mapper.readValue("null", new TypeReference<>() {});
// Result: List() (empty list instead of null)
```

## Using Developer Versions

Developer versions can be found [here](https://oss.sonatype.org/content/repositories/snapshots/io/vavr/vavr-jackson).

### Maven

```xml
<dependency>
  <groupId>io.vavr</groupId>
  <artifactId>vavr-jackson</artifactId>
  <version>0.11.1-SNAPSHOT</version>
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
compile("io.vavr:vavr-jackson:0.11.1-SNAPSHOT")
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
