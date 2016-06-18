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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;
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
            public void onClick(Player clicker, ClickType type) {
                new MainInventory(clicker, ContributorsInventory.super.xmlWorld);
            }
        });

        set(3, Items.build(ChatColor.GRAY + "Add Author", Material.BOOK), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                handle(player, false);
            }
        });
        set(5, Items.build(ChatColor.GREEN + "Add Contributor", Material.BOOK), new Button() {
            @Override
            public void onClick(Player clicker, ClickType type) {
                handle(player, true);
            }
        });

        final List<Element> authors = super.documentHandler.get(AuthorsParentModule.class, AuthorModule.class);
        final List<Element> contributors = super.documentHandler.get(ContributorsParentModule.class, ContributorModule.class);
        if(contributors.size() == 0 && authors.size() == 0)
            set(30, Items.build(ChatColor.RED + "No Contributors or Authors", Material.BARRIER, Arrays.asList("Click above to add some!")));
        else {
            handleShow(authors, 0, false);
            handleShow(contributors, 5, true);
        }
    }

    private void handleShow(List<Element> elements, int offset, boolean contributor) {
        final int[] cur = new int[1];
        final int[] row = new int[1];
        elements.forEach(element -> {
            String from;
            String name;

            if(element.hasAttribute("uuid")) {
                name = Bukkit.getOfflinePlayer(UUID.fromString(element.getAttribute("uuid"))).getName();
                from = "UUID";
            } else {
                name = element.getTextContent();
                from = "Name";
            }

            set(((9 * (row[0] + 1)) + offset) + cur[0], Items.build(ChatColor.GREEN + name, Material.SKULL_ITEM,
                    Arrays.asList(
                            ChatColor.DARK_GRAY + (contributor ? "A contributor" : "An author") + " from " + from,
                            ChatColor.YELLOW + "Left click to change contribution",
                            ChatColor.RED + "Right click to " + ChatColor.BOLD + "DELETE"
                    )), new Button() {
                @Override
                public void onClick(Player clicker, ClickType type) {
                    if(type.isLeftClick()) new InputAnvil(clicker, element.getAttribute("contribution"), new AnvilGUI.ClickHandler() {
                        @Override
                        public String onClick(Player player, String input) {
                            element.setAttribute("contribution", input);
                            ContributorsInventory.super.open();
                            return input;
                        }
                    });
                    else new YesNoInventory(clicker, Items.build(ChatColor.RED + "Remove " + name + " as " + (contributor ? "contributor" : "author") + "?", Material.SKULL_ITEM)) {
                        @Override
                        public void onYes(Player decider) {
                            ContributorsInventory.super.documentHandler.get(null, ContributorsParentModule.class).get(0).removeChild(element);
                            ContributorsInventory.super.open();
                        }

                        @Override
                        public void onNo(Player decider) {
                            ContributorsInventory.super.open();
                        }
                    };
                }
            });

            cur[0]++;
            if(cur[0] == 4) {
                cur[0] = 0;
                row[0]++;
            }
        });
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

                ContributorsInventory.super.open();
                return contribution; //no flicker
            }
        });
    }

}
