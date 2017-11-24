package co.cdjones.javautils.collections;

import lombok.val;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class MapBuilderTest {

    private static class Animal {}
    private static class Dog extends Animal {}

    @Test
    public void testMapBuilder() {
        Map<String,Integer> m = new MapBuilder<String,Integer>()
            .put("a", 1)
            .put("b", 2)
            .putAll(new HashMap<>())
            .put(new AbstractMap.SimpleEntry<>("c", 3))
            // ...
            .build();

        assertThat(m).containsKeys("a", "b", "c");
        assertThat(m).containsValues(1);

        // System.out.println(m);
    }

    @Test
    public void testMapBuilderExtends() {
        Animal d = new Dog();
        Map<String,Animal> m = new MapBuilder<>(new HashMap<String, Animal>())
            .put("a", new Animal())
            .put("b", d)
            .putAll(new HashMap<String, Dog>())
            .put(new AbstractMap.SimpleEntry<>("c", new Animal()))
            .put(new AbstractMap.SimpleEntry<>("d", new Dog()))
            // ...
            .build();

        assertThat(m).containsKeys("a", "b", "c", "d");
        assertThat(m).containsValues(d);

//        System.out.println(m);
    }

    @Test
    public void testMapBuilderClass() throws InstantiationException, IllegalAccessException {
        Animal d = new Dog();
        val m = new MapBuilder<String, Animal>()
            .put("a", new Animal())
            .put("b", d)
            .putAll(new HashMap<String, Dog>())
            .put(new AbstractMap.SimpleEntry<>("c", new Animal()))
            .put(new AbstractMap.SimpleEntry<String,Animal>("d", new Dog()))
            // ...
            .build();

        assertThat(m).containsKeys("a", "b", "c", "d");
        assertThat(m).containsValues(d);

//        System.out.println(m);
    }

    @Test
    public void testMapBuilderRemove() throws InstantiationException, IllegalAccessException {

        val bEntry = new AbstractMap.SimpleEntry<String,Integer>("b", 2);

        val em = new MapBuilder<String, Integer>(HashMap.class)
            .put("a", 1)
            .put(bEntry)
            .build();

        val m = new MapBuilder<>(em)
            .remove("a")
            .remove(bEntry)
            .build();

        assertThat(m.keySet()).isEmpty();
        assertThat(m.values()).isEmpty();

//        System.out.println(m);
    }

    @Test
    public void testStartsWithStatic() throws Exception {
        val defaultHashMap = MapBuilder.startWith("a", 1).put("b", 2).build();

        assertThat(defaultHashMap).containsKeys("a", "b");
        assertThat(defaultHashMap).containsValues(1, 2);
    }

    @Test
    public void testStartsWithStaticExistingMap() throws Exception {

        val m1 = MapBuilder.startWith("a", 1).put("b", 2).build();
        val m2 = MapBuilder.startWith(m1).put("c", 3).build();

        assertThat(m2).containsKeys("a", "b", "c");
        assertThat(m2).containsValues(1, 2, 3);
    }
}