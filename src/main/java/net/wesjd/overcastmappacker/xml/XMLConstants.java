package net.wesjd.overcastmappacker.xml;

import net.wesjd.overcastmappacker.OvercastMapPacker;
import net.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import net.wesjd.overcastmappacker.xml.module.XMLModule;
import net.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;
import org.apache.xml.serialize.OutputFormat;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
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
class XMLConstants {

    public static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

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

    //yes, this is a little hacky
    public static final List<String> DEFAULT_LINES = Arrays.asList(
            "<!-- This XML file was created with OvercastMapPacker -->",
            "<!--    https://github.com/WesJD/OvercastMapPacker    -->",
            "<map proto=\"" + OvercastMapPacker.MAP_PROTOCOL_NUMBER + "\">",
            "  <name>Unknown Name</name>",
            "  <version>1.0.0</version>",
            "  <objective>Unknown Objective</objective>",
            "  <authors/>",
            "  <contributors/>",
            "</map>"
    );

}
