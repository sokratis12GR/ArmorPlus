/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModSpecialMobRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (enableChickenArmor && enableChickenArmorRecipes) {
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenHelmet), "   ", "FFF", "F F", 'F', "feather"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenHelmet), "FFF", "F F", "   ", 'F', "feather"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenChestplate), "F F", "FFF", "FFF", 'F', "feather"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenLeggings), "FFF", "F F", "F F", 'F', "feather"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenBoots), "   ", "F F", "F F", 'F', "feather"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenBoots), "F F", "F F", "   ", 'F', "feather"));
                }
                if (enableSlimeArmor && enableSlimeArmorRecipes) {
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeHelmet), "   ", "SSS", "S S", 'S', "slimeball"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeHelmet), "SSS", "S S", "   ", 'S', "slimeball"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeChestplate), "S S", "SSS", "SSS", 'S', "slimeball"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeLeggings), "SSS", "S S", "S S", 'S', "slimeball"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeBoots), "   ", "S S", "S S", 'S', "slimeball"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeBoots), "S S", "S S", "   ", 'S', "slimeball"));
                }
                break;
            case EXPERT:
            case HELLISH:
                if (enableChickenArmor && enableChickenArmorRecipes) {
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenHelmet), "   ", "FFF", "E E", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenHelmet), "FFF", "E E", "   ", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenChestplate), "E E", "FEF", "FFF", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenLeggings), "EFE", "F F", "F F", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenBoots), "   ", "F F", "E E", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenBoots), "F F", "E E", "   ", 'F', "feather", 'E', "egg"));
                }
                if (enableSlimeArmor && enableSlimeArmorRecipes) {
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeHelmet), "   ", "SSS", "S S", 'S', "blockSlime"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeHelmet), "SSS", "S S", "   ", 'S', "blockSlime"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeChestplate), "S S", "SSS", "SSS", 'S', "blockSlime"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeLeggings), "SSS", "S S", "S S", 'S', "blockSlime"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeBoots), "   ", "S S", "S S", 'S', "blockSlime"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(slimeBoots), "S S", "S S", "   ", 'S', "blockSlime"));
                }
                break;
        }
    }
}