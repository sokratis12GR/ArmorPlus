/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.config.ModConfig;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

public class ModSpecialMobRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (getRD() == ModConfig.RecipesDifficulty.EASY) {
            WBRecipesHelper.createArmorRecipes(manager, "feather", chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
            WBRecipesHelper.createArmorRecipes(manager, "slimeball", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        } else if (getRD() == ModConfig.RecipesDifficulty.EXPERT || getRD() == ModConfig.RecipesDifficulty.HELLISH) {
            manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(chickenHelmet), "   ", "FFF", "E E", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(chickenHelmet), "FFF", "E E", "   ", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(chickenChestplate), "E E", "FEF", "FFF", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(chickenLeggings), "EFE", "F F", "F F", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(chickenBoots), "   ", "F F", "E E", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(chickenBoots), "F F", "E E", "   ", 'F', "feather", 'E', "egg"));
            WBRecipesHelper.createArmorRecipes(manager, "blockSlime", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }
    }
}