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