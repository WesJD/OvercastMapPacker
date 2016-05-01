package com.github.wesjd.overcastmappacker.xml.module.impl.general.tutorial;

import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gilar on 5/1/2016.
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
