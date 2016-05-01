package com.github.wesjd.overcastmappacker.xml.module.impl;

import com.github.wesjd.overcastmappacker.util.ContinuingMap;
import com.github.wesjd.overcastmappacker.xml.DocumentHandler;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ContributorModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.NameModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ObjectiveModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.VersionModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.parents.ContributorsParentModule;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.File;

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
public class XMLTest {

    @org.junit.Test
    public void testXML() {
        final File tempDir = new File(System.getProperty("java.io.tmpdir"));
        tempDir.mkdirs();
        final File xmlTest = new File(tempDir, "mappackerxmltest.xml");

        final DocumentHandler handler = DocumentHandler.createNewXMLFile(xmlTest);
        handler.set(null, NameModule.class, "A Test Map");
        handler.set(null, NameModule.class, "HI I CHANGED PROPERLY");
        handler.set(null, VersionModule.class, "1.0.0");
        handler.set(null, ObjectiveModule.class, "A description.");
        handler.set(ContributorsParentModule.class, ContributorModule.class, "A contributor.", ContinuingMap.from("contribution", "Helping.").add("uuid", "a-u-u-i-d"));
        handler.saveDocument();

        Assert.assertTrue("Check name module", handler.getLoadedDocument().getElementsByTagName("name").item(0).getTextContent().equals("HI I CHANGED PROPERLY"));
        Assert.assertTrue("Check contributors parent", handler.getLoadedDocument().getElementsByTagName("contributors").item(0) != null);
        Assert.assertTrue("Check contributor", handler.getLoadedDocument().getElementsByTagName("contributor").getLength() > 0);

        FileUtils.deleteQuietly(xmlTest);
    }

}
