package net.wesjd.overcastmappacker.mc.inventory.team;

import net.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import net.wesjd.overcastmappacker.util.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.w3c.dom.Element;

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

//SNT = show-name-tags
public class SNTSelectionInventory extends AbstractEditorInventory {

    private final Element element;

    public SNTSelectionInventory(Player player, AbstractEditorInventory returnInv, Element element) {
        super(player, returnInv, 9, "Show name tags to...", true);
        this.element = element;
        open();
    }

    @Override
    public void build() {
        final String value = element.getAttribute("show-name-tags");

        set(3, Items.build(ChatColor.GREEN + (value.equals("true") ? ChatColor.BOLD.toString() : "") + "Everyone", Material.STAINED_CLAY, (short) 5),
                (clicker, type) -> {
                    element.setAttribute("show-name-tags", "true");
                    super.returnToPrevious();
                });
        set(4, Items.build(ChatColor.RED + (value.equals("false") ? ChatColor.BOLD.toString() : "") + "No one", Material.STAINED_CLAY, (short) 6),
                (clicker, type) -> {
                    element.setAttribute("show-name-tags", "false");
                    super.returnToPrevious();
                });
        set(5, Items.build(ChatColor.GREEN + (value.equals("allies") ? ChatColor.BOLD.toString() : "") + "Allies", Material.SHIELD),
                (clicker, type) -> {
                    element.setAttribute("show-name-tags", "allies");
                    super.returnToPrevious();
                });
        set(6, Items.build(ChatColor.GREEN + (value.equals("enemies") ? ChatColor.BOLD.toString() : "") + "Enemies", Material.IRON_SWORD),
                (clicker, type) -> {
                    element.setAttribute("show-name-tags", "enemies");
                    super.returnToPrevious();
                });
    }

}
