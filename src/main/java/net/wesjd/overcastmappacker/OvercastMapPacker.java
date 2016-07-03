package net.wesjd.overcastmappacker;

import net.wesjd.overcastmappacker.mc.XMLWorldHandler;
import net.wesjd.overcastmappacker.mc.command.XMLCommand;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Wesley Smith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
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

    public static final String MAP_PROTOCOL_NUMBER = "1.4.1";

    private static OvercastMapPacker instance;
    private final XMLWorldHandler worldHandler = new XMLWorldHandler();

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
        worldHandler.getAndRemoveAll().forEach(xmlWorld -> xmlWorld.getHandler().saveDocument());
        instance = null;
    }

    public XMLWorldHandler getWorldHandler() {
        return worldHandler;
    }

    public static OvercastMapPacker get() {
        return instance;
    }

}
