package net.wesjd.overcastmappacker.util;

import java.util.HashMap;
import java.util.Map;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Wesley Smith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
