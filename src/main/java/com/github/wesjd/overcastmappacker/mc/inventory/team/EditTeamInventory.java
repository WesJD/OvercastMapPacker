package com.github.wesjd.overcastmappacker.mc.inventory.team;

import com.github.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import com.github.wesjd.overcastmappacker.util.InputAnvil;
import com.github.wesjd.overcastmappacker.util.Items;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.w3c.dom.Element;

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
public class EditTeamInventory extends AbstractEditorInventory {

    private final Element element;

    public EditTeamInventory(Player player, AbstractEditorInventory returnInv, Element element) {
        super(player, returnInv, 45, "Edit Team", true);
        this.element = element;
        open();
    }

    @Override
    public void build() {
        set(11, Items.build(ChatColor.LIGHT_PURPLE + "Color", Material.STAINED_CLAY, Arrays.asList("Select this team's color")),
                (clicker, type) -> new ColorSelectionInventory(player, this, element, true));
        set(13, Items.build(ChatColor.LIGHT_PURPLE + "Overhead Color", Material.NAME_TAG, Arrays.asList("Select this team's overhead (nametag) color")),
                (clicker, type) -> new ColorSelectionInventory(player, this, element, false));
        set(15, Items.build(ChatColor.YELLOW + "Show Name Tags", Material.POTION, Arrays.asList("Who can see the name tags of this team?")),
                (clicker, type) -> new SNTSelectionInventory(player, this, element));

        set(29, Items.build(ChatColor.GREEN + "Minimum Players", Material.SKULL_ITEM, Arrays.asList("Required amount of players on this team")),
                (clicker, type) -> new InputAnvil(clicker, element.getAttribute("min"), new AnvilGUI.ClickHandler() {
                    @Override
                    public String onClick(Player player, String input) {
                        int value;
                        try {
                            value = Integer.valueOf(input);
                        } catch (NumberFormatException ex) {
                            return ChatColor.RED + "Not a valid number.";
                        }

                        element.setAttribute("min", value+"");
                        EditTeamInventory.this.open();
                        return input; //no flicker
                    }
                }));
        set(31, Items.build(ChatColor.GREEN + "Maximum Players", Material.SKULL_ITEM, Arrays.asList("Maximum normal players for this team")),
                (clicker, type) -> new InputAnvil(clicker, element.getAttribute("max"), new AnvilGUI.ClickHandler() {
                    @Override
                    public String onClick(Player player, String input) {
                        int value;
                        try {
                            value = Integer.valueOf(input);
                        } catch (NumberFormatException ex) {
                            return ChatColor.RED + "Not a valid number.";
                        }

                        element.setAttribute("max", value+"");
                        EditTeamInventory.this.open();
                        return input; //no flicker
                    }
                }));
        set(33, Items.build(ChatColor.GREEN + "Maximum Overfill Players", Material.SKULL_ITEM, Arrays.asList("Maximum of premium space on this team")),
                (clicker, type) -> new InputAnvil(clicker, element.getAttribute("max-overfill"), new AnvilGUI.ClickHandler() {
                    @Override
                    public String onClick(Player player, String input) {
                        int value;
                        try {
                            value = Integer.valueOf(input);
                        } catch (NumberFormatException ex) {
                            return ChatColor.RED + "Not a valid number.";
                        }

                        if(value > Integer.valueOf(element.getAttribute("max"))) {
                            element.setAttribute("max-overfill", value + "");
                            EditTeamInventory.this.open();
                            return input; //no flicker
                        } else return ChatColor.RED + "Overfill must be greater than max.";
                    }
                }));
    }

}
