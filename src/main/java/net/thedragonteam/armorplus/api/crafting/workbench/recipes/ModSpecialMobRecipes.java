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
                    WBRecipesHelper.createArmorRecipes(manager, "feather", chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
                }
                if (enableSlimeArmor && enableSlimeArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "slimeball", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
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
                    WBRecipesHelper.createArmorRecipes(manager, "blockSlime", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
                }
                break;
        }
    }
}