# Java Utilities

With many of my Java Projects, I continually find a common set of functions that significantly reduce boilerplate code. Some of these are seemingly simple (e.g. an un-opinionated Map builder...) that just do not exist in the standard JDK libraries. Some do exist in Third Party libraries (e.g. Guava), but these are either too heavy to use in a controlled project, or have their own idiosyncrasies (i.e. Guavas ImmutableMap builder not allowing null values...).

## MapBuilder
Simple MapBuilder class. Mostly lifted from [AndersDJohnson/map-builder-java](https://github.com/AndersDJohnson/map-builder-java).

```
val em = MapBuilder.<String, Integer>of(HashMap.class)
            .put("a", 1)
            .put("b", 2)
            .build();
```