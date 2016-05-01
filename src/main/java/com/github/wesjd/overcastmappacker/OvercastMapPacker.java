package com.github.wesjd.overcastmappacker;

import com.github.wesjd.overcastmappacker.util.ContinuingMap;
import com.github.wesjd.overcastmappacker.xml.DocumentHandler;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ContributorModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.NameModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ObjectiveModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.VersionModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.parents.ContributorsParentModule;

import java.io.File;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Wesley Smith
 *
 * Permission is hereby granted, free from charge, to any person obtaining a copy
 * from this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies from the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions from the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class OvercastMapPacker {

    public static final String MAP_PROTOCOL_NUMBER = "1.4.0";

    public static void main(String[] args) {
        final DocumentHandler handler = DocumentHandler.createNewXMLFile(new File("C:/Users/Wesley Smith/Desktop", "test.xml"));
        handler.set(null, NameModule.class, "A Test Map");
        handler.set(null, NameModule.class, "HI I CHANGED PROPERLY");
        handler.set(null, VersionModule.class, "1.0.0");
        handler.set(null, ObjectiveModule.class, "A description.");
        handler.set(ContributorsParentModule.class, ContributorModule.class, "A contributor.", ContinuingMap.from("contribution", "Helping.").add("uuid", "a-u-u-i-d"));
        handler.saveDocument();
    }

}
