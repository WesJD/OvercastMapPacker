package net.wesjd.overcastmappacker.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
public abstract class YesNoInventory extends AbstractInventory {

    private final ItemStack icon;

    public YesNoInventory(Player player, ItemStack icon) {
        super(player, 27, "Yes or No?", true);
        this.icon = icon;
        open();
    }

    @Override
    public void build() {
        set(13, icon);
        set(11, Items.build(ChatColor.GREEN + "Yes", Material.STAINED_CLAY, (short) 5), (clicker, type) -> onYes(clicker));
        set(15, Items.build(ChatColor.RED + "No", Material.STAINED_CLAY, (short) 14), (clicker, type) -> onNo(clicker));
    }

    public abstract void onYes(Player decider);
    public abstract void onNo(Player decider);

}
