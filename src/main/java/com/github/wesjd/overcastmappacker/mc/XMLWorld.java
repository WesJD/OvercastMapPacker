package com.github.wesjd.overcastmappacker.mc;

import com.github.wesjd.overcastmappacker.mc.inventory.MainInventory;
import com.github.wesjd.overcastmappacker.xml.DocumentHandler;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

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
public class XMLWorld {

    private final World world;
    private final DocumentHandler handler;
    private Player editor;

    public XMLWorld(World world, DocumentHandler handler) {
        this.world = world;
        this.handler = handler;
    }

    public void nullEditor(){
        this.editor = null;
    }

    public boolean setEditor(Player newEditor) {
        if(this.editor == null) {
            this.editor = newEditor;
            return true;
        } else newEditor.sendMessage(ChatColor.RED + editor.getName() + " is currently editing the XML for this map!");
        return false;
    }

    public World getWorld() {
        return world;
    }

    public DocumentHandler getHandler() {
        return handler;
    }

}
