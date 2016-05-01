package com.github.wesjd.overcastmappacker.xml;

import com.github.wesjd.overcastmappacker.util.ContinuingMap;
import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

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
public class DocumentHandler {

    private final XPath xPath = XMLConstants.X_PATH_FACTORY.newXPath();
    private final Expressions expressions = new Expressions(this);

    private File documentFile;
    private DocumentBuilder documentBuilder;
    private Document document;
    private Transformer transformer;

    public DocumentHandler(File documentFile) {
        try {
            Validate.isTrue(documentFile.exists(), "Document must exist for DocumentHandler to handle it.");
            Validate.isTrue(FilenameUtils.getExtension(documentFile.getPath()).equals("xml"), "Document supplied is not an XML file.");

            this.documentFile = documentFile;

            documentBuilder = XMLConstants.DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
            document = documentBuilder.parse(new FileInputStream(documentFile));

            transformer = XMLConstants.TRANSFORMER_FACTORY.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (ParserConfigurationException | IOException | SAXException | TransformerConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public void set(Class<? extends ParentXMLModule> parent, Class<? extends XMLModule> moduleClass) {
        set(parent, moduleClass, ContinuingMap.empty());
    }

    public void set(Class<? extends ParentXMLModule> parent, Class<? extends XMLModule> moduleClass, ContinuingMap<String, String> attributeMapping) {
        set(parent, moduleClass, null, attributeMapping);
    }

    public void set(Class<? extends ParentXMLModule> parent, Class<? extends XMLModule> moduleClass, String moduleValue) {
        set(parent, moduleClass, moduleValue, ContinuingMap.empty());
    }

    public void set(Class<? extends ParentXMLModule> parent, Class<? extends XMLModule> moduleClass, String moduleValue, ContinuingMap<String, String> attributeMapping) {
        System.out.println("SET par " + parent + " AND mod " + moduleClass);
        try {
            final Map<String, String> rawAttributeMapping = attributeMapping.getRaw();
            final XMLModule module = XMLModule.of(moduleClass);

            System.out.println("NO PRE MOD " + module.getTag() + "?? " + (document.getElementsByTagName(module.getTag()).getLength() == 0));

            ParentXMLModule parentModule = XMLConstants.MAIN_BODY_MODULE;
            if (parent != null) parentModule = (ParentXMLModule) XMLModule.of(parent);
            Validate.isTrue(parentModule.getChildXMLModules() == null || parentModule.getChildXMLModules().contains(module.getClass()), "Parent module must accept child.");

            Element moduleElement = (Element) expressions.getExpression(module.getTag()).evaluate(document, XPathConstants.NODE);
            if (moduleElement != null) {
                moduleElement.setTextContent(moduleValue);
            } else {
                moduleElement = document.createElement(module.getTag());
                moduleElement.setTextContent(moduleValue);

                final XPathExpression expression = expressions.getExpression("(" + parentModule.getTag() + ")[1]");
                Node parentNode = (Node) expression.evaluate(document, XPathConstants.NODE);
                if (parentNode == null) {
                    System.out.println((parentNode == null) + "VAL");
                    set(null, parentModule.getClass());
                    System.out.println("FROM " + parentModule.getTag() + ": " + document.getElementsByTagName(parentModule.getTag()).getLength());
                    parentNode = (Node) expression.evaluate(document, XPathConstants.NODE);
                }
                System.out.println((parentNode == null) + "VAL2");
                parentNode.appendChild(moduleElement);
            }

            if (module.getXMLAttributes() != null) {
                final Element finalModuleElement = moduleElement;
                module.getXMLAttributes().forEach(xmlAttribute -> {
                    final String attributeName = xmlAttribute.getName();

                    String value = rawAttributeMapping.containsKey(attributeName) ? rawAttributeMapping.get(attributeName) : xmlAttribute.getDefaultValue();
                    if ((xmlAttribute.getValidValues() == null || !xmlAttribute.getValidValues().contains(value)) && xmlAttribute.isRequired())
                        value = xmlAttribute.getDefaultValue();

                    finalModuleElement.setAttribute(attributeName, value);
                });
            }

            System.out.println("POST MOD " + module.getTag() + "?? " + (document.getElementsByTagName(module.getTag()).getLength() > 0));
            System.out.println("--------------------------- for " + module.getTag());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public void saveDocument() {
        try {
            final Writer writer = new StringWriter();
            new XMLSerializer(writer, XMLConstants.INDENTED_OUTPUT_FORMAT).serialize(document);
            document = documentBuilder.parse(new InputSource(new StringReader(writer.toString())));
            transformer.transform(new DOMSource(document), new StreamResult(documentFile));
        } catch (TransformerException | IOException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    public XPath getLocalXPath() {
        return xPath;
    }

    public File getDocumentFile() {
        return documentFile;
    }

    public Document getLoadedDocument() {
        return document;
    }

    public static DocumentHandler createNewXMLFile(File file) {
        try {
            if (!file.exists() && !file.createNewFile()) throw new RuntimeException("Unable to create new XML file.");
            Files.write(Paths.get(file.getAbsolutePath()), XMLConstants.DEFAULT_LINES);
            return new DocumentHandler(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
