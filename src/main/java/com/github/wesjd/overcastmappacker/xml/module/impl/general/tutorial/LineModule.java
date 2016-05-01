package com.github.wesjd.overcastmappacker.xml.module.impl.general.tutorial;

import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class LineModule extends XMLModule {

    @Override
    public String getTag() {
        return "line";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return null;
    }
}
