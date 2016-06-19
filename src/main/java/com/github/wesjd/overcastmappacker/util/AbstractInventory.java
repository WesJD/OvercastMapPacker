package com.github.wesjd.overcastmappacker.util;

import com.github.wesjd.overcastmappacker.OvercastMapPacker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class AbstractInventory {

    private static final String SERVER_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    private static Method handleInventoryCloseEvent;
    private static Method getHandle;
    private static Field defaultContainer;
    private static Field activeContainer;

    static {
        try {
            final Class<?> entityHuman = Class.forName("net.minecraft.server." + SERVER_VERSION + ".EntityHuman");
            handleInventoryCloseEvent = Class.forName("org.bukkit.craftbukkit." + SERVER_VERSION + ".event.CraftEventFactory")
                    .getDeclaredMethod("handleInventoryCloseEvent", entityHuman);
            getHandle = Class.forName("org.bukkit.craftbukkit." + SERVER_VERSION + ".entity.CraftPlayer").getDeclaredMethod("getHandle");
            defaultContainer = entityHuman.getDeclaredField("defaultContainer");
            activeContainer = entityHuman.getDeclaredField("activeContainer");
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    private final Map<Integer, BiConsumer<Player, ClickType>> buttons = new HashMap<>();
    private final Listeners listeners = new Listeners();

    protected final Inventory inventory;
    protected final Player player;

    public AbstractInventory(Player player, int size, String name) {
        this(player, size, name, false);
    }

    public AbstractInventory(Player player, int size, String name, boolean manualOpen) {
        this.inventory = Bukkit.createInventory(player, size, name);
        this.player = player;
        if (!manualOpen) open();
    }

    protected void open() {
        build();
        handleOpenInventoryClosing();
        player.openInventory(inventory);
        Bukkit.getPluginManager().registerEvents(listeners, OvercastMapPacker.get());
        onOpen();
    }

    protected void close() {
        onClose();
        HandlerList.unregisterAll(listeners);
    }

    protected void handleOpenInventoryClosing() {
        try {
            final Object entityHuman = getHandle.invoke(player);
            handleInventoryCloseEvent.invoke(null, entityHuman);
            activeContainer.set(entityHuman, defaultContainer.get(entityHuman));
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    public void onOpen() {
    }

    public void onClose() {
    }

    public abstract void build();

    public void set(int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
    }

    public void set(int slot, ItemStack stack, BiConsumer<Player, ClickType> consumer) {
        set(slot, stack);
        buttons.put(slot, consumer);
    }

    private class Listeners implements Listener {

        @EventHandler
        public void onInventoryItemDrag(InventoryDragEvent e) {
            e.setCancelled(e.getInventory().getName().equals(inventory.getName()) && e.getInventory().getHolder().equals(player));
        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            if (e.getInventory().getName().equals(inventory.getName()) && e.getInventory().getHolder().equals(player)) {
                e.setCancelled(true);
                if (buttons.containsKey(e.getRawSlot())) buttons.get(e.getRawSlot()).accept((Player) e.getWhoClicked(), e.getClick());
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            if (e.getInventory().getName().equals(inventory.getName()) && e.getInventory().getHolder().equals(player)) close();
        }

    }

}
