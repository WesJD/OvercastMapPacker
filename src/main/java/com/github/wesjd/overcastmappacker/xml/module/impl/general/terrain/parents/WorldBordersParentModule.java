package com.github.wesjd.overcastmappacker.xml.module.impl.general.terrain.parents;

import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class WorldBordersParentModule extends ParentXMLModule {
    @Override
    protected List<Class<? extends XMLModule>> getChildModules() {
        return null;
    }

    @Override
    public String getTag() {
        return "world-borders";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return null;
    }
}
