/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModSpecialMobRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (recipes.enableChickenArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "feather", chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
                }
                if (recipes.enableSlimeArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "slimeball", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (recipes.enableChickenArmorRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(3, getItemStack(chickenHelmet), "   ", "FFF", "E E", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, getItemStack(chickenHelmet), "FFF", "E E", "   ", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, getItemStack(chickenChestplate), "E E", "FEF", "FFF", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, getItemStack(chickenLeggings), "EFE", "F F", "F F", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, getItemStack(chickenBoots), "   ", "F F", "E E", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, getItemStack(chickenBoots), "F F", "E E", "   ", 'F', "feather", 'E', "egg"));
                }
                if (recipes.enableSlimeArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "blockSlime", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
                }
                break;
        }
    }
}