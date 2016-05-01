package com.github.wesjd.overcastmappacker.xml;

import com.github.wesjd.overcastmappacker.OvercastMapPacker;
import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPathFactory;
import java.util.Arrays;
import java.util.List;

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
class XMLConstants {

    public static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

    public static final XPathFactory X_PATH_FACTORY = XPathFactory.newInstance();

    public static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    public static final OutputFormat INDENTED_OUTPUT_FORMAT = new OutputFormat();
    static {
        INDENTED_OUTPUT_FORMAT.setIndenting(true);
        INDENTED_OUTPUT_FORMAT.setIndent(2);
    }

    public static final ParentXMLModule MAIN_BODY_MODULE = new ParentXMLModule() {
        @Override
        public String getTag() {
            return "map";
        }

        @Override
        public List<XMLAttribute> getAttributes() {
            return Arrays.asList(
                    new XMLAttribute("proto", OvercastMapPacker.MAP_PROTOCOL_NUMBER, OvercastMapPacker.MAP_PROTOCOL_NUMBER, true, true)
            );
        }

        @Override
        protected List<Class<? extends XMLModule>> getChildModules() {
            return null;
        }
    };

    public static final List<String> DEFAULT_LINES = Arrays.asList("<map proto=\"" + OvercastMapPacker.MAP_PROTOCOL_NUMBER + "\">", "</map>");

}
