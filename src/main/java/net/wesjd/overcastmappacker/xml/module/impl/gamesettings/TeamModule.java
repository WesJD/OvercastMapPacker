package net.wesjd.overcastmappacker.xml.module.impl.gamesettings;

import net.wesjd.overcastmappacker.xml.module.XMLModule;
import net.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
public class TeamModule extends XMLModule {

    @Override
    public String getTag() {
        return "team";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return Arrays.asList(
                new XMLAttribute("id", UUID.randomUUID().toString(), null, false, true),
                new XMLAttribute("color", "WHITE", null, true, false),
                new XMLAttribute("overhead-color", "WHITE", null, true, false),
                new XMLAttribute("plural", "false", null, true, false),
                new XMLAttribute("show-name-tags", "true", null, true, false),
                new XMLAttribute("min", "20", null, true, false),
                new XMLAttribute("max", "30", null, true, false),
                new XMLAttribute("max-overfill", "10", null, true, false)
        );
    }

}
