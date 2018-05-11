package co.cdjones.javautils;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class DefaultsTest {

    @Test
    public void defaultIfNull() {
        List nullList = null;
        Map<String, String> nullMap = null;
        String nullString = null;

        assertNotNull(Defaults.defaultIfNull(nullList));
        assertNotNull(Defaults.defaultIfNull(nullMap));
        assertNotNull(Defaults.defaultIfNull(nullString, "test"));

        Map nullTreeMap = null;

        Object typeCheck = Defaults.defaultIfNull(nullTreeMap, new TreeMap());

        assertEquals("java.util.TreeMap", typeCheck.getClass().getName());
    }
}