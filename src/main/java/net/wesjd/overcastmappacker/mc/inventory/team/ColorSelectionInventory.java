package net.wesjd.overcastmappacker.mc.inventory.team;

import net.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import net.wesjd.overcastmappacker.util.Items;
import net.wesjd.overcastmappacker.util.XMLColors;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
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
public class ColorSelectionInventory extends AbstractEditorInventory {

    private final Element element;
    private final boolean teamColor;

    public ColorSelectionInventory(Player player, AbstractEditorInventory returnInv, Element element, boolean teamColor) {
        super(player, returnInv, 54, "Select a Color", true);
        this.element = element;
        this.teamColor = teamColor;
        open();
    }

    @Override
    public void build() {
        final int[] cur = new int[1];
        final int[] row = new int[1];
        Arrays.stream(XMLColors.values()).forEach(color -> {
            final String bold = (element.getAttribute(teamColor ? "color" : "overhead-color").equals(color.toString()) ? ChatColor.BOLD.toString() : "");
            final MaterialData materialData = color.getMaterial();
            set((9 * row[0]) + 11 + cur[0]++, Items.build(color.getChatColor() + bold + color.toString(), materialData.getItemType(), materialData.getData()), (clicker, type) -> {
                element.setAttribute(teamColor ? "color" : "overhead-color", color.toString());
                returnToPrevious();
            });

            if(cur[0] == 5) {
                cur[0] = 0;
                row[0]++;
            }
        });
    }

}
