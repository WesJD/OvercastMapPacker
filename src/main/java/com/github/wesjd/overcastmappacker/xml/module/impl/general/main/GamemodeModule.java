package com.github.wesjd.overcastmappacker.xml.module.impl.general.main;

import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.attributes.XMLAttribute;

import java.util.List;

/**
 * Created by gilar on 5/1/2016.
 */
public class GamemodeModule extends XMLModule {

    @Override
    public String getTag() {
        return "gamemode";
    }

    @Override
    protected List<XMLAttribute> getAttributes() {
        return null;
    }
}
