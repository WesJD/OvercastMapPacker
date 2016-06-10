package com.github.wesjd.overcastmappacker.mc.inventory;

import com.github.wesjd.overcastmappacker.mc.XMLWorld;
import com.github.wesjd.overcastmappacker.util.*;
import com.github.wesjd.overcastmappacker.xml.module.ParentXMLModule;
import com.github.wesjd.overcastmappacker.xml.module.XMLModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.AuthorModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.ContributorModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.parents.AuthorsParentModule;
import com.github.wesjd.overcastmappacker.xml.module.impl.general.main.parents.ContributorsParentModule;
import net.buildstatic.util.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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
                handle(player, true);
            }
        });

        set(5, Items.build(ChatColor.GRAY + "Add Author", Material.BOOK), new Button() {
            @Override
            public void onClick(Player clicker) {
                handle(player, false);
            }
        });

        //TODO - Show all contributors and make them able to be removed
    }

    private void handle(Player player, boolean contributor) {
        new YesNoInventory(player, Items.build("Do they have an account?", Material.GRASS, Arrays.asList("Does this person own a", "Minecraft account?"))) {
            @Override
            public void onYes(Player decider) {
                new InputAnvil(decider, "Their ingame name?", new AnvilGUI.ClickHandler() {
                    @Override
                    public String onClick(Player player, String input) {
                        try {
                            final UUID uuid = UUIDFetcher.getUUIDOf(input);
                            if(uuid != null) {
                                final String uuidString = uuid.toString();
                                handleAdd(contributor, player, new String[] { uuidString, null });
                            } else return ChatColor.RED + "Not a valid name.";
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return input; //no flicker
                    }
                });
            }

            @Override
            public void onNo(Player decider) {
                new InputAnvil(decider, "What is thier name?", new AnvilGUI.ClickHandler() {
                    @Override
                    public String onClick(Player player, String input) {
                        handleAdd(contributor, decider, new String[] { null, input });
                        return input;
                    }
                });
            }
        };
    }

    private void handleAdd(boolean contributor, Player player, String[] name) {
        //name[0] = uuid, name[1] = name
        new InputAnvil(player, "Their contribution?", new AnvilGUI.ClickHandler() {
            @Override
            public String onClick(Player player, String contribution) {
                final Class<? extends ParentXMLModule> parentModule = (contributor ? ContributorsParentModule.class : AuthorsParentModule.class);
                final Class<? extends XMLModule> module = (contributor ? ContributorModule.class : AuthorModule.class);
                final ContinuingMap<String, String> attributes = new ContinuingMap<>();
                if(name[0] != null) attributes.add("uuid", name[0]);
                attributes.add("contribution", contribution);

                if(name[0] != null) ContributorsInventory.super.documentHandler.add(parentModule, module, attributes);
                else ContributorsInventory.super.documentHandler.add(parentModule, module, name[1], attributes);

                new ContributorsInventory(player, ContributorsInventory.super.xmlWorld);
                return contribution; //no flicker
            }
        });
    }

}
