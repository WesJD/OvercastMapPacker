package com.github.wesjd.overcastmappacker.xml.module.impl.general.main;

import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class IncludeModule extends XMLModule {
    @Override
    public String getTag() {
        return "include";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return Arrays.asList(
                new XMLAttribute("src", "tutorial.xml", null, true, true)
        );
    }
}
