/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.config.ModConfig;

import static com.sofodev.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.createArmorRecipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

public class ModOriginRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (getRD() == ModConfig.RecipesDifficulty.EASY) {
            createArmorRecipes(manager, "itemCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
            createArmorRecipes(manager, "itemCharcoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
            createArmorRecipes(manager, "gemLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
            createArmorRecipes(manager, "dustRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        } else if (getRD() == ModConfig.RecipesDifficulty.EXPERT || getRD() == ModConfig.RecipesDifficulty.HELLISH) {
            createArmorRecipes(manager, "blockLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
            createArmorRecipes(manager, "blockRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
            createArmorRecipes(manager, "blockCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }
    }
}