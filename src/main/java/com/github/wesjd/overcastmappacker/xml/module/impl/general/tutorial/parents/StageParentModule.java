package com.github.wesjd.overcastmappacker.xml.module.impl.general.tutorial.parents;

import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class StageParentModule extends ParentXMLModule {
    @Override
    public String getTag() {
        return "stage";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return Arrays.asList(
                new XMLAttribute("title", "Capture The Wool", null, true, true)
        );
    }

    @Override
    protected List<Class<? extends XMLModule>> getChildModules() {
        return Arrays.asList(MessageParentModule.class);
    }
}
