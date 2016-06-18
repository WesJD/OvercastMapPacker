package com.github.wesjd.overcastmappacker.mc.inventory.world;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import com.github.wesjd.overcastmappacker.util.Items;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.EditionModule;
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
class EditionInventory extends AbstractEditorInventory {

    public EditionInventory(Player player, XMLWorld xmlWorld) {
        super(player, xmlWorld, 9, "Pick an Edition");
    }

    @Override
    public void build() {
        set(0, Items.GO_BACK, new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                new WorldInventory(clicker, EditionInventory.super.xmlWorld);
            }
        });

        String value = "wot";
        final List<Element> elements = super.documentHandler.get(null, EditionModule.class);
        if (elements.size() > 0) value = elements.get(0).getTextContent();

        final ItemStack standardItem = Items.build(ChatColor.GREEN + "Standard", Material.IRON_AXE);
        final ItemStack rankedItem = Items.build(ChatColor.GREEN + "Ranked", Material.IRON_SWORD);
        final ItemStack tournamentItem = Items.build(ChatColor.GREEN + "Tournament", Material.BANNER);

        switch (value) {
            case "standard":
                Items.addEnchantments(standardItem, Enchantment.DURABILITY);
                break;
            case "ranked":
                Items.addEnchantments(rankedItem, Enchantment.DURABILITY);
                break;
            case "tournament":
                Items.addEnchantments(tournamentItem, Enchantment.DURABILITY);
                break;
        }

        set(3, standardItem, new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                EditionInventory.super.documentHandler.set(null, EditionModule.class, "standard");
                new WorldInventory(clicker, EditionInventory.super.xmlWorld);
            }
        });
        set(4, rankedItem, new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                EditionInventory.super.documentHandler.set(null, EditionModule.class, "ranked");
                new WorldInventory(clicker, EditionInventory.super.xmlWorld);
            }
        });
        set(5, tournamentItem, new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                EditionInventory.super.documentHandler.set(null, EditionModule.class, "tournament");
                new WorldInventory(clicker, EditionInventory.super.xmlWorld);
            }
        });
    }

}