package com.github.wesjd.overcastmappacker.mc.inventory;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.util.AbstractInventory;
import com.github.wesjd.overcastmappacker.util.Items;
import com.github.wesjd.overcastmappacker.xml.DocumentHandler;
import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

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
public abstract class AbstractEditorInventory extends AbstractInventory {

    protected final XMLWorld xmlWorld;
    protected final DocumentHandler documentHandler;
    private final AbstractEditorInventory returnInv;
    private final boolean goBack;

    public AbstractEditorInventory(Player player, AbstractEditorInventory returnInv, int size, String name) {
        this(player, returnInv, size, name, false);
    }

    public AbstractEditorInventory(Player player, XMLWorld xmlWorld, int size, String name) {
        this(player, xmlWorld, null, size, name, false, false);
    }

    public AbstractEditorInventory(Player player, AbstractEditorInventory returnInv, int size, String name, boolean manualOpen) {
        this(player, returnInv.xmlWorld, returnInv, size, name, true, manualOpen);
    }

    public AbstractEditorInventory(Player player, XMLWorld xmlWorld, AbstractEditorInventory returnInv, int size, String name, boolean goBack, boolean manualOpen) {
        super(player, size, name, true);
        this.xmlWorld = xmlWorld;
        this.documentHandler = xmlWorld.getHandler();
        this.returnInv = returnInv;
        this.goBack = goBack;
        if(!manualOpen) open();
    }

    public void returnToPrevious() {
        returnInv.open();
    }

    @Override
    public void open() {
        handleOpenInventoryClosing();
        if(xmlWorld.setEditor(super.player)) {
            super.inventory.clear();
            if(goBack) set(0, Items.GO_BACK, (clicker, type) -> returnToPrevious());
            super.open();
        }
    }

    @Override
    public void onClose() {
        xmlWorld.nullEditor();
    }

}
