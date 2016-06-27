package com.github.wesjd.overcastmappacker.mc.inventory.world;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import com.github.wesjd.overcastmappacker.mc.inventory.MainInventory;
import com.github.wesjd.overcastmappacker.util.InputAnvil;
import com.github.wesjd.overcastmappacker.util.Items;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.NameModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ObjectiveModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.VersionModule;
import net.wesjd.anvilgui.AnvilGUI;
import net.wesjd.anvilgui.version.VersionWrapper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

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
public class WorldInventory extends AbstractEditorInventory {

    public WorldInventory(Player player, AbstractEditorInventory returnInv) {
        super(player, returnInv, 54, "Map Settings");
    }

    @Override
    public void build() {
        set(10, Items.build(ChatColor.GREEN + "Name", Material.NAME_TAG, Arrays.asList("The name of this map")),
                (clicker, type) -> handleSimple(player, "Type a name...", NameModule.class));

        set(12, Items.build(ChatColor.GREEN + "Version", Material.BREWING_STAND_ITEM, Arrays.asList("The map's semantic version")),
                (clicker, type) -> handleSimple(player, "1.0.0", VersionModule.class));

        set(14, Items.build(ChatColor.GREEN + "Objective", Material.PAPER, Arrays.asList("Objective of this map")),
                (clicker, type) -> handleSimple(player, "Type an objective...", ObjectiveModule.class));

        set(16, Items.build(ChatColor.GREEN + "Edition", Material.BOOKSHELF, Arrays.asList("Pick an edition for this map")),
                (clicker, type) -> new EditionInventory(clicker, this));

        set(28, Items.build(ChatColor.GREEN + "Rules", Material.BEDROCK, Arrays.asList("Add custom rules to this map")),
                (clicker, type) -> new RulesInventory(clicker, this));

        set(30, Items.build(ChatColor.GREEN + "Broadcasts", Material.BOOK_AND_QUILL));

        set(32, Items.build(ChatColor.GREEN + "Dimension", Material.NETHERRACK, Arrays.asList("Pick the dimension for this map")),
                (clicker, type) -> new DimensionInventory(clicker, this));
    }

    public void handleSimple(Player player, String insert, Class<? extends XMLModule> module) {
        final List<Element> elements = super.documentHandler.get(null, module);
        new InputAnvil(player, elements.size() > 0 ? elements.get(0).getTextContent() : insert, new AnvilGUI.ClickHandler() {
            @Override
            public String onClick(Player player, String reply) {
                WorldInventory.super.documentHandler.set(null, module, reply);
                WorldInventory.super.open();
                return reply; //for no flicker
            }
        });
    }

}
