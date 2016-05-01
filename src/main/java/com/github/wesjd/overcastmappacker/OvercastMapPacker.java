package com.github.wesjd.overcastmappacker;

import com.github.wesjd.overcastmappacker.mc.command.XMLCommand;
import com.github.wesjd.overcastmappacker.util.ContinuingMap;
import com.github.wesjd.overcastmappacker.xml.DocumentHandler;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ContributorModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.NameModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ObjectiveModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.VersionModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.parents.ContributorsParentModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
public class OvercastMapPacker extends JavaPlugin {

    public static final String MAP_PROTOCOL_NUMBER = "1.4.0";

    private static OvercastMapPacker instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("xml").setExecutor(new XMLCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static OvercastMapPacker getInstance() {
        return instance;
    }

}
