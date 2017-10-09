# Java Utilities

With many of my Java Projects, I continually find a common set of functions that significantly reduce boilerplate code. Some of these are seemingly simple (e.g. an un-opinionated Map builder...) that just do not exist in the standard JDK libraries. Some do exist in Third Party libraries (e.g. Guava), but these are either too heavy to use in a controlled project, or have their own idiosyncrasies (i.e. Guavas ImmutableMap builder not allowing null values...).

## MapBuilder

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
