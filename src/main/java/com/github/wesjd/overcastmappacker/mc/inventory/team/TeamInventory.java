package com.github.wesjd.overcastmappacker.mc.inventory.team;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import com.github.wesjd.overcastmappacker.mc.inventory.MainInventory;
import com.github.wesjd.overcastmappacker.util.InputAnvil;
import com.github.wesjd.overcastmappacker.util.Items;
import net.buildstatic.util.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
public class TeamInventory extends AbstractEditorInventory {

    public TeamInventory(Player player, XMLWorld xmlWorld, AbstractEditorInventory returnInv) {
        super(player, returnInv, 18, "Teams");
    }

    @Override
    public void build() {
        set(4, Items.build(ChatColor.GREEN + "Add Team", Material.BANNER),  (clicker, type) -> {
            new InputAnvil(clicker, "Type a team name...", new AnvilGUI.ClickHandler() {
                @Override
                public String onClick(Player player, String input) {
                    final String name = input;
                    final String id = input.toLowerCase() + "-team";

                    return input; //no flicker
                }
            });
        });
    }

}
