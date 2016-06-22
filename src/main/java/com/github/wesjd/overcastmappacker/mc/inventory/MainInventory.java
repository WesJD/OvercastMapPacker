package com.github.wesjd.overcastmappacker.mc.inventory;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.mc.inventory.team.TeamsInventory;
import com.github.wesjd.overcastmappacker.mc.inventory.world.WorldInventory;
import com.github.wesjd.overcastmappacker.util.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
public class MainInventory extends AbstractEditorInventory {

    public MainInventory(Player player, XMLWorld world) {
        super(player, world, 54, "XML for \"" + world.getWorld().getName() + "\"");
    }

    @Override
    public void build() {
        set(4, Items.build(ChatColor.GREEN + super.xmlWorld.getWorld().getName(), Material.GRASS, Arrays.asList("Click to change main map settings")),
                (clicker, type) -> new WorldInventory(clicker, this));

        set(12, Items.build(ChatColor.YELLOW + "Authors and Contributors", Material.SKULL_ITEM, Arrays.asList("Click to manage the contributors", "of this map")),
                (clicker, type) -> new ContributorsInventory(clicker, this));

        set(14, Items.build(ChatColor.GREEN + "Teams", Material.BANNER, Arrays.asList("Click to manage the teams", "of this map")),
                (clicker, type) -> new TeamsInventory(clicker, this));

        set(45, Items.build(ChatColor.YELLOW + "Save XML", Material.ANVIL, Arrays.asList("Save the map.xml")), (clicker, type) -> {
            super.documentHandler.saveDocument();
            clicker.playSound(clicker.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
        });
    }

}
