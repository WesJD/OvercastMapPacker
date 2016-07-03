package net.wesjd.overcastmappacker.mc.inventory.world;

import net.wesjd.overcastmappacker.mc.inventory.AbstractEditorInventory;
import net.wesjd.overcastmappacker.util.InputAnvil;
import net.wesjd.overcastmappacker.util.Items;
import net.wesjd.overcastmappacker.util.YesNoInventory;
import net.wesjd.overcastmappacker.xml.module.impl.general.matchinfo.RuleModule;
import net.wesjd.overcastmappacker.xml.module.impl.general.matchinfo.parents.RulesParentModule;
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
public class RulesInventory extends AbstractEditorInventory {

    public RulesInventory(Player player, AbstractEditorInventory returnInv) {
        super(player, returnInv, 27, "Rules");
    }

    @Override
    public void build() {
        set(4, Items.build(ChatColor.YELLOW + "Add Rule", Material.BEDROCK), (clicker, type) ->
                new InputAnvil(clicker, "Type a rule...", (player, input) -> {
                    RulesInventory.super.documentHandler.add(RulesParentModule.class, RuleModule.class, input);
                    RulesInventory.super.open();
                    return input; //no flicker
                }));

        final List<Element> elements = super.documentHandler.get(RulesParentModule.class, RuleModule.class);
        if (elements.size() > 0) {
            final int[] cur = new int[]{9};
            elements.forEach(element -> {
                set(cur[0], Items.build(ChatColor.YELLOW + element.getTextContent(), Material.BEDROCK, Arrays.asList(
                        ChatColor.YELLOW + "Left click to change",
                        ChatColor.RED + "Right click to " + ChatColor.BOLD + "DELETE"
                )), (clicker, type) -> {
                    if (type.isLeftClick()) {
                        new InputAnvil(clicker, element.getTextContent(), (player, input) -> {
                            element.setTextContent(input);
                            RulesInventory.super.open();
                            return input; //no flicker
                        });
                    } else {
                        new YesNoInventory(clicker, Items.build(ChatColor.YELLOW + "Remove rule \"" + element.getTextContent() + "\"?", Material.BEDROCK)) {
                            @Override
                            public void onYes(Player decider) {
                                RulesInventory.super.documentHandler.get(null, RulesParentModule.class).get(0).removeChild(element);
                                RulesInventory.super.open();
                            }

                            @Override
                            public void onNo(Player decider) {
                                RulesInventory.super.open();
                            }
                        };
                    }
                });
                cur[0]++;
            });
        } else set(13, Items.build(ChatColor.RED + "No rules", Material.BARRIER, Arrays.asList("Click above to add some!")));
    }

}
