package net.wesjd.overcastmappacker.xml.module.impl.filters;

import net.wesjd.overcastmappacker.xml.module.XMLModule;
import net.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.Arrays;
import java.util.List;

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
public class PointModule extends XMLModule {
    @Override
    public String getTag() {
        return null;
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return Arrays.asList(new XMLAttribute("yaw", "90", null, true, true),new XMLAttribute("pitch", "50", null, true, true),new XMLAttribute("angle", "0", null, true, true));
    }
}
