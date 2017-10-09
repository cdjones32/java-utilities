package co.cdjones.javautils.collections;

import lombok.val;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;

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

        assertThat(m, Matchers.hasKey("a"));
        assertThat(m, Matchers.hasKey("b"));
        assertThat(m, Matchers.hasKey("c"));

        assertThat(m, Matchers.hasValue(1));

        System.out.println(m);
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

        assertThat(m, Matchers.hasKey("a"));
        assertThat(m, Matchers.hasKey("b"));
        assertThat(m, Matchers.hasKey("c"));
        assertThat(m, Matchers.hasKey("d"));

        assertThat(m, Matchers.hasValue(d));

        System.out.println(m);
    }

    @Test
    public void testMapBuilderClass() throws InstantiationException, IllegalAccessException {
        Animal d = new Dog();
        val m = MapBuilder.<String, Animal>create()
            .put("a", new Animal())
            .put("b", d)
            .putAll(new HashMap<String, Dog>())
            .put(new AbstractMap.SimpleEntry<String,Animal>("c", new Animal()))
            .put(new AbstractMap.SimpleEntry<String,Animal>("d", new Dog()))
            // ...
            .build();

        assertThat(m, Matchers.hasKey("a"));
        assertThat(m, Matchers.hasKey("b"));
        assertThat(m, Matchers.hasKey("c"));
        assertThat(m, Matchers.hasKey("d"));

        assertThat(m, Matchers.hasValue(d));

        System.out.println(m);
    }

    @Test
    public void testMapBuilderRemove() throws InstantiationException, IllegalAccessException {

        val bEntry = new AbstractMap.SimpleEntry<String,Integer>("b", 2);

        val em = MapBuilder.<String, Integer>of(HashMap.class)
            .put("a", 1)
            .put(bEntry)
            .build();

        val m = new MapBuilder<>(em)
            .remove("a")
            .remove(bEntry)
            .build();

        assertThat(m.keySet(), Matchers.empty());
        assertThat(m.values(), Matchers.empty());

        System.out.println(m);
    }

}