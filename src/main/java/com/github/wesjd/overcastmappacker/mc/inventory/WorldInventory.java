package com.github.wesjd.overcastmappacker.mc.inventory;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.util.InputAnvil;
import com.github.wesjd.overcastmappacker.util.Items;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.EditionModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.NameModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ObjectiveModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.VersionModule;
import net.buildstatic.util.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.w3c.dom.Element;

import javax.swing.text.EditorKit;
import java.beans.PropertyEditorManager;
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

    public WorldInventory(Player player, XMLWorld xmlWorld) {
        super(player, xmlWorld, 54, "Map Settings");
    }

    @Override
    public void build() {
        set(0, Items.GO_BACK, new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                new MainInventory(clicker, WorldInventory.super.xmlWorld);
            }
        });

        set(10, Items.build(ChatColor.GREEN + "Name", Material.NAME_TAG, Arrays.asList("The name of this map")), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                handleSimple(clicker, "Type a name...", NameModule.class);
            }
        });

        set(12, Items.build(ChatColor.GREEN + "Version", Material.BREWING_STAND_ITEM, Arrays.asList("The map's semantic version")), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                handleSimple(clicker, "1.0.0", VersionModule.class);
            }
        });

        set(14, Items.build(ChatColor.GREEN + "Objective", Material.PAPER, Arrays.asList("Objective of this map")), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                handleSimple(clicker, "Type an objective...", ObjectiveModule.class);
            }
        });

        set(16, Items.build(ChatColor.GREEN + "Edition", Material.BOOKSHELF, Arrays.asList("Pick an edition for this map")), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                new PickAnEdition(clicker, WorldInventory.super.xmlWorld);
            }
        });
    }

    public void handleSimple(Player player, String insert, Class<? extends XMLModule> module) {
        final List<Element> elements = super.documentHandler.get(null, module);
        new InputAnvil(player, elements.size() > 0 ? elements.get(0).getTextContent() : insert, new AnvilGUI.ClickHandler() {
            @Override
            public String onClick(Player player, String reply) {
                WorldInventory.super.documentHandler.set(null, module, reply);
                new WorldInventory(player, WorldInventory.super.xmlWorld);
                return reply; //for no flicker
            }
        });
    }

    private class PickAnEdition extends AbstractEditorInventory {

        public PickAnEdition(Player player, XMLWorld xmlWorld) {
            super(player, xmlWorld, 9, "Pick an Edition");
        }

        @Override
        public void build() {
            set(0, Items.GO_BACK, new Button() {
                @Override
                public void onClick(Player clicker, ClickType type) {
                    new WorldInventory(clicker, PickAnEdition.super.xmlWorld);
                }
            });

            String value = "wot";
            final List<Element> elements = super.documentHandler.get(null, EditionModule.class);
            if(elements.size() > 0) value = elements.get(0).getTextContent();

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
                    PickAnEdition.super.documentHandler.set(null, EditionModule.class, "standard");
                    new WorldInventory(clicker, PickAnEdition.super.xmlWorld);
                }
            });
            set(4, rankedItem, new Button() {
                @Override
                public void onClick(Player clicker, ClickType type) {
                    PickAnEdition.super.documentHandler.set(null, EditionModule.class, "ranked");
                    new WorldInventory(clicker, PickAnEdition.super.xmlWorld);
                }
            });
            set(5, tournamentItem, new Button() {
                @Override
                public void onClick(Player clicker, ClickType type) {
                    PickAnEdition.super.documentHandler.set(null, EditionModule.class, "tournament");
                    new WorldInventory(clicker, PickAnEdition.super.xmlWorld);
                }
            });
        }

    }

}
