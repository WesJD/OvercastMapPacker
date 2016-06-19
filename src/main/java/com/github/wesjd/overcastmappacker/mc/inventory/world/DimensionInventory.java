package com.github.wesjd.overcastmappacker.mc.inventory.world;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import com.github.wesjd.overcastmappacker.util.Items;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.EditionModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.terrain.DimensionModule;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.w3c.dom.Element;

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
class DimensionInventory extends AbstractEditorInventory {

    public DimensionInventory(Player player, XMLWorld xmlWorld) {
        super(player, xmlWorld, 9, "Pick a Dimension");
    }

    @Override
    public void build() {
        set(0, Items.GO_BACK, new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                new WorldInventory(clicker, DimensionInventory.super.xmlWorld);
            }
        });

        String value = "wot";
        final List<Element> elements = super.documentHandler.get(null, DimensionModule.class);
        if (elements.size() > 0) value = elements.get(0).getTextContent();

        set(3, Items.build(ChatColor.GREEN + (value.equals("normal") ? ChatColor.BOLD.toString() : "") + "Normal", Material.GRASS), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                DimensionInventory.super.documentHandler.set(null, DimensionModule.class, "normal");
                new WorldInventory(clicker, DimensionInventory.super.xmlWorld);
            }
        });
        set(4, Items.build(ChatColor.GREEN + (value.equals("nether") ? ChatColor.BOLD.toString() : "") + "Nether", Material.NETHERRACK), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                DimensionInventory.super.documentHandler.set(null, DimensionModule.class, "nether");
                new WorldInventory(clicker, DimensionInventory.super.xmlWorld);
            }
        });
        set(5, Items.build(ChatColor.GREEN + (value.equals("the end") ? ChatColor.BOLD.toString() : "") + "The End", Material.ENDER_STONE), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                DimensionInventory.super.documentHandler.set(null, DimensionModule.class, "the end");
                new WorldInventory(clicker, DimensionInventory.super.xmlWorld);
            }
        });
    }

}