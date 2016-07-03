package net.wesjd.overcastmappacker.mc;

import net.wesjd.overcastmappacker.xml.DocumentHandler;
import org.bukkit.World;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
public class XMLWorldHandler {

    private final Set<XMLWorld> worlds = new HashSet<>();

    public XMLWorld getWorld(World bukkitWorld) {
        return worlds.stream()
                .filter(xmlWorld -> xmlWorld.getWorld().equals(bukkitWorld))
                .findFirst()
                .orElse(createAndAddFor(bukkitWorld));
    }

    public XMLWorld createAndAddFor(World bukkitWorld) {
        final XMLWorld ret = new XMLWorld(bukkitWorld, DocumentHandler.createNewXMLFile(new File(bukkitWorld.getWorldFolder(), "map.xml")));
        worlds.add(ret);
        return ret;
    }

    public Collection<XMLWorld> getAndRemoveAll() {
        final Collection<XMLWorld> ret = Collections.unmodifiableCollection(worlds);
        worlds.clear();
        return ret;
    }

}
