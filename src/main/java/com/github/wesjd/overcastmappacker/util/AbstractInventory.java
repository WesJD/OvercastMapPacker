package com.github.wesjd.overcastmappacker.util;

import com.github.wesjd.overcastmappacker.OvercastMapPacker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

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
public abstract class AbstractInventory {

    private final Map<Integer, Button> buttons = new HashMap<>();
    private final Listeners listeners = new Listeners();

    private final Inventory inventory;
    private final Player player;

    public AbstractInventory(Player player, int size, String name) {
        this(player, size, name, false);
    }

    public AbstractInventory(Player player, int size, String name, boolean manualOpen) {
        this.inventory = Bukkit.createInventory(player, size, name);
        this.player = player;
        if(!manualOpen) open();
        Bukkit.getPluginManager().registerEvents(listeners, OvercastMapPacker.getInstance());
    }

    public void open() {
        build();
        player.openInventory(inventory);
    }

    public void onClose() {}

    public abstract void build();

    public void set(int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
    }

    public void set(int slot, ItemStack stack, Button button) {
        set(slot, stack);
        buttons.put(slot, button);
    }

    public abstract class Button {

        public abstract void onClick(Player clicker);

    }

    private class Listeners implements Listener {

        @EventHandler
        public void onInventoryItemDrag(InventoryDragEvent e) {
            e.setCancelled(e.getInventory().getName().equals(inventory.getName()) && e.getInventory().getHolder().equals(player));
        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            if(e.getInventory().getName().equals(inventory.getName()) && e.getInventory().getHolder().equals(player)) {
                e.setCancelled(true);
                if(buttons.containsKey(e.getRawSlot())) buttons.get(e.getRawSlot()).onClick((Player) e.getWhoClicked());
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            if(e.getInventory().getName().equals(inventory.getName()) && e.getInventory().getHolder().equals(player)) {
                onClose();
                HandlerList.unregisterAll(listeners);
            }
        }

    }

}
