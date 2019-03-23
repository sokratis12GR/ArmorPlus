/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.util.LoaderUtils;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModTinkersConstructRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (LoaderUtils.isTiCLoaded()) {
            switch (getRD()) {
                case EASY: {
                    createEasyArmorSetRecipes(recipes.enableArditeArmorRecipes, manager, "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                    createEasyArmorSetRecipes(recipes.enableCobaltArmorRecipes, manager, "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                    createEasyArmorSetRecipes(recipes.enableKnightSlimeArmorRecipes, manager, "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                    createEasyArmorSetRecipes(recipes.enableManyullynArmorRecipes, manager, "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                    createEasyArmorSetRecipes(recipes.enablePigIronArmorRecipes, manager, "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
                    break;
                }
                case EXPERT:
                case HELLISH: {
                    createExpertArmorSetRecipes(recipes.enableArditeArmorRecipes, manager, "blockArdite", "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                    createExpertArmorSetRecipes(recipes.enableCobaltArmorRecipes, manager, "blockCobalt", "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                    createExpertArmorSetRecipes(recipes.enableKnightSlimeArmorRecipes, manager, "blockKnightslime", "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                    createExpertArmorSetRecipes(recipes.enableManyullynArmorRecipes, manager, "blockManyullyn", "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                    createExpertArmorSetRecipes(recipes.enablePigIronArmorRecipes, manager, "blockPigiron", "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
                    break;
                }
            }
        }
    }
}