package com.github.wesjd.overcastmappacker.xml.module.impl.general.tutorial.parents;

import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.tutorial.PointModule;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class MessageParentModule extends ParentXMLModule {
    @Override
    public String getTag() {
        return "message";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return null;
    }

    @Override
    protected List<Class<? extends XMLModule>> getChildModules() {
        return Arrays.asList(PointModule.class);
    }
}
