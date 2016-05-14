package com.github.wesjd.overcastmappacker.mc.inventory;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.util.InputAnvil;
import com.github.wesjd.overcastmappacker.util.Items;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.NameModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.VersionModule;
import net.buildstatic.util.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

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
            public void onClick(Player clicker) {
                new MainInventory(clicker, WorldInventory.super.xmlWorld);
            }
        });

        set(10, Items.build(ChatColor.GREEN + "Name", Material.NAME_TAG, Arrays.asList("The name of this map")), new Button() {
            @Override
            public void onClick(Player clicker) {
                handleSimple(clicker, "Type a name...", NameModule.class);
            }
        });

        set(12, Items.build(ChatColor.GREEN + "Version", Material.BREWING_STAND_ITEM, Arrays.asList("The map's semantic version")), new Button() {
            @Override
            public void onClick(Player clicker) {
                handleSimple(clicker, "1.0.0", VersionModule.class);
            }
        });


    }

    public void handleSimple(Player player, String insert, Class<? extends XMLModule> module) {
        new InputAnvil(player, insert, new AnvilGUI.ClickHandler() {
            @Override
            public String onClick(Player player, String reply) {
                WorldInventory.super.documentHandler.set(null, module, reply);
                new WorldInventory(player, WorldInventory.super.xmlWorld);
                return reply; //for no flicker
            }
        });
    }

}
