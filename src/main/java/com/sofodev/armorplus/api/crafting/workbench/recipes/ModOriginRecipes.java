/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.createArmorRecipes;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;

public class ModOriginRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case DISABLED:
                break;
            case EASY:
                if (recipes.enableCoalArmorRecipes) {
                    createArmorRecipes(manager, "itemCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                } else if (recipes.enableCharcoalCoalArmorRecipe) {
                    createArmorRecipes(manager, "itemCharcoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                }
                if (recipes.enableLapisArmorRecipes) {
                    createArmorRecipes(manager, "gemLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
                }
                if (recipes.enableRedstoneArmorRecipes) {
                    createArmorRecipes(manager, "dustRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (recipes.enableLapisArmorRecipes) {
                    createArmorRecipes(manager, "blockLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
                }
                if (recipes.enableRedstoneArmorRecipes) {
                    createArmorRecipes(manager, "blockRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
                }
                if (recipes.enableCoalArmorRecipes) {
                    createArmorRecipes(manager, "blockCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                }
                break;
        }
    }
}