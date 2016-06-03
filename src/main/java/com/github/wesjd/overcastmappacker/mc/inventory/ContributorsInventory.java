package com.github.wesjd.overcastmappacker.mc.inventory;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.util.*;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ContributorModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.parents.ContributorsParentModule;
import net.buildstatic.util.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.UUID;

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
public class ContributorsInventory extends AbstractEditorInventory {

    public ContributorsInventory(Player player, XMLWorld xmlWorld) {
        super(player, xmlWorld, 54, "Authors and Contributors");
    }

    @Override
    public void build() {
        set(0, Items.GO_BACK, new Button() {
            @Override
            public void onClick(Player clicker) {
                new MainInventory(clicker, ContributorsInventory.super.xmlWorld);
            }
        });

        set(3, Items.build(ChatColor.GREEN + "Add Contributor", Material.BOOK), new Button() {
            @Override
            public void onClick(Player clicker) {
                final String[] name = new String[2]; //0 = uuid, 1 = name
                new YesNoInventory(clicker, Items.build("Do they have an account?", Material.GRASS, Arrays.asList("Does this person own a", "Minecraft account?"))) {
                    @Override
                    public void onYes(Player decider) {
                        new InputAnvil(decider, "Their ingame name?", new AnvilGUI.ClickHandler() {
                            @Override
                            public String onClick(Player player, String input) {
                                try {
                                    final UUID uuid = UUIDFetcher.getUUIDOf(input);
                                    if(uuid != null) name[0] = uuid.toString();
                                    else return ChatColor.RED + "Not a valid name.";
                                } catch (Exception ex) {
                                    throw new RuntimeException(ex);
                                }
                                return input; //no flicker
                            }
                        });
                    }

                    @Override
                    public void onNo(Player decider) {
                        decider.sendMessage("you said no");
                    }
                };


                new InputAnvil(clicker, "Their contribution?", new AnvilGUI.ClickHandler() {
                    @Override
                    public String onClick(Player player, String contribution) {
                        if(name[0] != null) ContributorsInventory.super.documentHandler.set(ContributorsParentModule.class, ContributorModule.class,
                                    ContinuingMap.from("uuid", name[0]).add("contribution", contribution));
                        else ContributorsInventory.super.documentHandler.set(ContributorsParentModule.class, ContributorModule.class, name[1],
                                    ContinuingMap.from("contribution", contribution));

                        new ContributorsInventory(player, ContributorsInventory.super.xmlWorld);
                        return contribution; //no flicker
                    }
                });
            }
        });

        set(5, Items.build(ChatColor.GRAY + "Add Author", Material.BOOK), new Button() {
            @Override
            public void onClick(Player clicker) {

            }
        });

        //TODO - Show all contributors
    }

}
