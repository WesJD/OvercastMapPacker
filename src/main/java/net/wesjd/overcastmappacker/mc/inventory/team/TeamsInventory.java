package net.wesjd.overcastmappacker.mc.inventory.team;

import net.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import net.wesjd.overcastmappacker.util.ContinuingMap;
import net.wesjd.overcastmappacker.util.InputAnvil;
import net.wesjd.overcastmappacker.util.Items;
import net.wesjd.overcastmappacker.util.YesNoInventory;
import net.wesjd.overcastmappacker.xml.module.impl.gamesettings.TeamModule;
import net.wesjd.overcastmappacker.xml.module.impl.gamesettings.parents.TeamsParentModule;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

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
public class TeamsInventory extends AbstractEditorInventory {

    public TeamsInventory(Player player, AbstractEditorInventory returnInv) {
        super(player, returnInv, 27, "Teams");
    }

    @Override
    public void build() {
        set(4, Items.build(ChatColor.GREEN + "Add Team", Material.BANNER),  (clicker, type) -> {
            new InputAnvil(clicker, "Type a team name...", (player, input) -> {
                new EditTeamInventory(player, TeamsInventory.this,
                        TeamsInventory.super.documentHandler.add(TeamsParentModule.class, TeamModule.class, input,
                                ContinuingMap.from("id", input.toLowerCase() + "-team").add("plural", "" + (input.charAt(input.length()-1) == 's'))));
                return input; //no flicker
            });
        });

        final List<Element> elements = super.documentHandler.get(TeamsParentModule.class, TeamModule.class);
        if(elements.size() > 0) {
            final int[] cur = new int[]{9};
            elements.forEach(element -> {
                final ChatColor teamColor = ChatColor.valueOf(element.getAttribute("color"));
                set(cur[0]++, Items.build(teamColor + element.getTextContent(), Material.BANNER, Arrays.asList(
                        ChatColor.YELLOW + "Left click to edit",
                        ChatColor.RED + "Right click to " + ChatColor.BOLD + "DELETE"
                )), (clicker, type) -> {
                    if(type.isLeftClick()) new EditTeamInventory(clicker, this, element);
                    else new YesNoInventory(clicker, Items.build("Remove team \"" + element.getTextContent() + "\"?", Material.BANNER)) {
                            @Override
                            public void onYes(Player decider) {
                                TeamsInventory.super.documentHandler.get(null, TeamsParentModule.class).get(0).removeChild(element);
                            }

                            @Override
                            public void onNo(Player decider) {
                                TeamsInventory.super.open();
                            }
                        };
                });
            });
        } else set(13, Items.build(ChatColor.RED + "No teams", Material.BARRIER, Arrays.asList("Click above to add some!")));
    }

}
