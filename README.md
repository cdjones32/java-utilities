# Java Utilities

With many of my Java Projects, I continually find a common set of functions that significantly reduce boilerplate code. Some of these are seemingly simple (e.g. an un-opinionated Map builder...) that just do not exist in the standard JDK libraries. Some do exist in Third Party libraries (e.g. Guava), but these are either too heavy to use in a controlled project, or have their own idiosyncrasies (i.e. Guavas ImmutableMap builder not allowing null values...).

## Setup

### Gradle
1. Add JitPack as an available maven repository.
2. Add the dependency for the project.

```

allprojects {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile "com.github.cdjones32:java-utilities:1.0.0"
}
```

# Utilities
## MapBuilder
Since: 1.0.0

Simple MapBuilder class. Mostly lifted from [AndersDJohnson/map-builder-java](https://github.com/AndersDJohnson/map-builder-java).

```java
Map<String, Integer> defaultHashMap = new MapBuilder<String, Integer>()
    .put("a", 1)
    .put("b", 2)
    .build();

Map<String, Integer> treeMap = new MapBuilder<String, Integer>(TreeMap.class)
    .put("a", 1)
    .put("b", 2)
    .build();

Map<String, Animal> existingMapInstance = new HashMap<String, Animal>();

Map<String, Animal> existingMap = new MapBuilder<>(existingMapInstance)
    .put("a", new Animal())
    .build();
```

Or, using something like Project Lombok and the static methods...
```java
val defaultHashMap = MapBuilder.startWith("a", 1).put("b", 2).build();

```