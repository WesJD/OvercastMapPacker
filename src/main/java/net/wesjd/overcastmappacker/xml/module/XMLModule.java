package net.wesjd.overcastmappacker.xml.module;

import net.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
public abstract class XMLModule {

    private static final LoadingCache<Class<? extends XMLModule>, XMLModule> moduleCache =
            CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<Class<? extends XMLModule>, XMLModule>() {
                @Override
                public XMLModule load(Class<? extends XMLModule> clazz) throws Exception {
                    final Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (XMLModule) constructor.newInstance();
                }
            });

    public static XMLModule of(Class<? extends XMLModule> module) {
        try {
            return moduleCache.get(module);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private final List<XMLAttribute> cachedAttributes;

    public XMLModule() {
        cachedAttributes = getAttributes();
    }

    public abstract String getTag();

    //returning null means there are no attributes
    protected abstract List<XMLAttribute> getAttributes();

    public List<XMLAttribute> getXMLAttributes() {
        return cachedAttributes;
    }

}
