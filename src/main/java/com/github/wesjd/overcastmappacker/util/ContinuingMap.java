package com.github.wesjd.overcastmappacker.util;

import java.util.HashMap;
import java.util.Map;

public class ContinuingMap<K, V> {

    private final Map<K, V> localMap = new HashMap<>();

    public ContinuingMap<K, V> add(K k, V v) {
        localMap.put(k, v);
        return this;
    }

    public Map<K, V> getRaw() {
        return localMap;
    }

    public static <T, T2> ContinuingMap<T, T2> from(T t, T2 t2) {
        final ContinuingMap<T, T2> ret = new ContinuingMap<>();
        ret.add(t, t2);
        return ret;
    }

    public static <T1, T2> ContinuingMap<T1, T2> empty() {
        return new ContinuingMap<>();
    }

}
