/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case DISABLED:
                break;
            case EASY:
                if (enableCoalArmor) {
                    if (enableCoalArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "itemCoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "itemCoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "itemCoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "itemCoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "   ", "C C", "C C", 'C', "itemCoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "itemCoal"));
                    } else if (enableCharcoalCoalArmorRecipe) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "itemCharcoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "itemCharcoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "itemCharcoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "itemCharcoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "   ", "C C", "C C", 'C', "itemCharcoal"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "itemCharcoal"));
                    }
                }
                if (enableLapisArmor && enableLapisArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "   ", "LLL", "L L", 'L', "gemLapis"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "LLL", "L L", "   ", 'L', "gemLapis"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisChestplate), "L L", "LLL", "LLL", 'L', "gemLapis"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisLeggings), "LLL", "L L", "L L", 'L', "gemLapis"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "   ", "L L", "L L", 'L', "gemLapis"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "L L", "L L", "   ", 'L', "gemLapis"));
                }
                if (enableRedstoneArmor) {
                    if (enableRedstoneArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "   ", "RRR", "R R", 'R', "dustRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "RRR", "R R", "   ", 'R', "dustRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneChestplate), "R R", "RRR", "RRR", 'R', "dustRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneLeggings), "RRR", "R R", "R R", 'R', "dustRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "   ", "R R", "R R", 'R', "dustRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "R R", "R R", "   ", 'R', "dustRedstone"));
                    }
                }
                break;
            case EXPERT:
            case HELLISH:
                if (enableLapisArmor) {
                    if (enableLapisArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "   ", "LLL", "L L", 'L', "blockLapis"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "LLL", "L L", "   ", 'L', "blockLapis"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisChestplate), "L L", "LLL", "LLL", 'L', "blockLapis"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisLeggings), "LLL", "L L", "L L", 'L', "blockLapis"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "   ", "L L", "L L", 'L', "blockLapis"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "L L", "L L", "   ", 'L', "blockLapis"));
                    }
                }
                if (enableRedstoneArmor) {
                    if (enableRedstoneArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "   ", "RRR", "R R", 'R', "blockRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "RRR", "R R", "   ", 'R', "blockRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneChestplate), "R R", "RRR", "RRR", 'R', "blockRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneLeggings), "RRR", "R R", "R R", 'R', "blockRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "   ", "R R", "R R", 'R', "blockRedstone"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "R R", "R R", "   ", 'R', "blockRedstone"));
                    }
                }
                if (enableCoalArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "blockCoal"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "blockCoal"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "blockCoal"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "blockCoal"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", 'C', "blockCoal"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "blockCoal"));
                }
                break;
        }
    }
}