package net.wesjd.overcastmappacker.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class Items {

    public static final ItemStack GO_BACK = Items.build(ChatColor.GRAY + "Go Back", Material.ARROW);

    public static ItemStack build(Material material) {
        return new ItemStack(material);
    }

    public static ItemStack build(String name, Material material) {
        return build(name, material, null);
    }

    public static ItemStack build(String name, Material material, short value) {
        return build(name, material, null, value);
    }

    public static ItemStack build(String name, Material material, int amount) {
        return build(name, material, null, amount);
    }

    public static ItemStack build(String name, Material material, int amount, short value) {
        return build(name, material, null, amount, value);
    }

    public static ItemStack build(String name, Material material, List<String> lore) {
        return build(name, material, lore, 1);
    }

    public static ItemStack build(String name, Material material, List<String> lore, short value) {
        return build(name, material, lore, 1, value);
    }

    public static ItemStack build(String name, Material material, List<String> lore, int amount) {
        return build(name, material, lore, amount, (short) 0);
    }

    public static ItemStack build(String name, Material material, List<String> lore, int amount, short value) {
        ItemStack stack = new ItemStack(material, amount, value);
        ItemMeta meta = stack.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(lore
                .stream()
                .map(item -> ChatColor.GRAY + item)
                .collect(Collectors.toList()));
        stack.setItemMeta(meta);
        return stack;
    }

    public static void addEnchantments(ItemStack stack, Enchantment... enchantments) {
        Arrays.stream(enchantments).forEach(enchantment -> stack.addUnsafeEnchantment(enchantment, 1));
    }

    public static ItemStack buildHead(String owner) {
        return buildHead(null, owner);
    }

    public static ItemStack buildHead(String name, String owner) {
        return buildHead(name, owner, null);
    }

    public static ItemStack buildHead(String name, String owner, List<String> lore) {
        return buildHead(name, owner, lore, 1);
    }

    public static ItemStack buildHead(String name, String owner, List<String> lore, int amount) {
        ItemStack stack = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(owner != null) meta.setOwner(owner);
        if(lore != null) meta.setLore(lore
                .stream()
                .map(item -> ChatColor.GRAY + item)
                .collect(Collectors.toList()));
        stack.setItemMeta(meta);
        return stack;
    }

}
