package com.github.wesjd.overcastmappacker.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.stream.Collectors;

public class Items {

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
