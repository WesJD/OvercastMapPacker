package com.github.wesjd.overcastmappacker.xml.module.impl.general.terrain;

import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class WorldBorderModule extends XMLModule {
    @Override
    public String getTag() {
        return "world-border";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return null;
    }
}
