package co.cdjones.javautils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Defaults {
    public static <T> List<T> defaultIfNull( List<T> list ) {
        return list == null ? new ArrayList<>() : list;
    }
    public static <K, V> Map<K, V> defaultIfNull( Map<K, V> map ) {
        return map == null ? new HashMap<>() : map;
    }
}
