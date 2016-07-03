package net.wesjd.overcastmappacker.util;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

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
public enum XMLColors {

    BLACK(ChatColor.BLACK, new MaterialData(Material.COAL_BLOCK)),
    DARK_BLUE(ChatColor.DARK_BLUE, new MaterialData(Material.LAPIS_BLOCK)),
    DARK_GREEN(ChatColor.DARK_GREEN, new MaterialData(Material.CACTUS)),
    DARK_AQUA(ChatColor.DARK_AQUA, MaterialUtils.woolOf(DyeColor.CYAN)),
    DARK_RED(ChatColor.DARK_RED, new MaterialData(Material.NETHERRACK)),
    DARK_PURPLE(ChatColor.DARK_PURPLE, new MaterialData(Material.PURPUR_BLOCK)),
    GOLD(ChatColor.GOLD, new MaterialData(Material.GOLD_BLOCK)),
    GRAY(ChatColor.GRAY, MaterialUtils.woolOf(DyeColor.SILVER)),
    DARK_GRAY(ChatColor.DARK_GRAY, MaterialUtils.woolOf(DyeColor.GRAY)),
    BLUE(ChatColor.BLUE, new MaterialData(Material.WATER_BUCKET)),
    GREEN(ChatColor.GREEN, new MaterialData(Material.LEAVES)),
    AQUA(ChatColor.AQUA, MaterialUtils.woolOf(DyeColor.LIGHT_BLUE)),
    RED(ChatColor.RED, new MaterialData(Material.LAVA_BUCKET)),
    LIGHT_PURPLE(ChatColor.LIGHT_PURPLE, MaterialUtils.woolOf(DyeColor.PINK)),
    YELLOW(ChatColor.YELLOW, new MaterialData(Material.SPONGE)),
    WHITE(ChatColor.WHITE, new MaterialData(Material.STAINED_CLAY));

    private final ChatColor chatColor;
    private final MaterialData material;

    XMLColors(ChatColor chatColor, MaterialData material) {
        this.chatColor = chatColor;
        this.material = material;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public MaterialData getMaterial() {
        return material;
    }

}
