package co.cdjones.javautils.collections;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * http://docs.guava-libraries.googlecode.com/git/javadoc/src-html/com/google/common/collect/ImmutableMap.Builder.html#line.217
 */
public class MapBuilder<K,V> {

    private Map<K, V> map;

    public MapBuilder() {
        this.map = new HashMap<>();
    }

    public static MapBuilder<String, Object> stringToObject() { return new MapBuilder<String, Object>(); }
    public static MapBuilder<String, Object> SO() { return new MapBuilder<String, Object>(); }
    public static MapBuilder<String, Object> SO(String key, Object value) { return new MapBuilder<String, Object>().put(key, value); }


    public static <K, V> MapBuilder<K, V> ofType(Class<K> keyClass, Class<V> valueClass) { return new MapBuilder<>(); }

    public static <K, V> MapBuilder<K, V> startWith(K key, V value) {
        return new MapBuilder<K, V>().put(key, value);
    }

    public static <K, V> MapBuilder<K, V> startWith(Map<K, V> map) {
        return new MapBuilder<K, V>().putAll(map);
    }

    public static <K, V> Map<K, V> of(K key, V value) {
        return new MapBuilder<K, V>().put(key, value).build();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public MapBuilder(Class<? extends Map> c) {
        this.map = c.newInstance();
    }

    public MapBuilder(Map<K,V> m) {
        this.map = m;
    }

    public MapBuilder<K,V> put(K k, V v) {
        map.put(k, v);
        return this;
    }

    public MapBuilder<K,V> put(Map.Entry<? extends K, ? extends V>e) {
        return put(e.getKey(), e.getValue());
    }

    public MapBuilder<K,V> putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
        return this;
    }

    public MapBuilder<K,V> remove(K k) {
        map.remove(k);
        return this;
    }

    public MapBuilder<K,V> remove(Map.Entry<? extends K, ? extends V>e) {
        return remove(e.getKey());
    }

    public Map<K,V> build() {
        return map;
    }
}