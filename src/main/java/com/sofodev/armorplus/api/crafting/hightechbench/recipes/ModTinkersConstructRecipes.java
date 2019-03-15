/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.util.LoaderUtils;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModTinkersConstructRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (LoaderUtils.isTiCLoaded()) {
            if (getRD() == ModConfig.RecipesDifficulty.EASY) {
                createEasyArmorSetRecipes(manager, "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                createEasyArmorSetRecipes(manager, "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                createEasyArmorSetRecipes(manager, "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                createEasyArmorSetRecipes(manager, "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                createEasyArmorSetRecipes(manager, "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
            } else if (getRD() == ModConfig.RecipesDifficulty.EXPERT || getRD() == ModConfig.RecipesDifficulty.HELLISH) {
                createExpertArmorSetRecipes(manager, "blockArdite", "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                createExpertArmorSetRecipes(manager, "blockCobalt", "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                createExpertArmorSetRecipes(manager, "blockKnightslime", "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                createExpertArmorSetRecipes(manager, "blockManyullyn", "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                createExpertArmorSetRecipes(manager, "blockPigiron", "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
            }
        }
    }
}