/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.util.LoaderUtils;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
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
                    if (global_registry.enableArditeArmor && recipes.enableArditeArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                    }
                    if (global_registry.enableCobaltArmor && recipes.enableCobaltArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                    }
                    if (global_registry.enableKnightSlimeArmor && recipes.enableKnightSlimeArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                    }
                    if (global_registry.enableManyullynArmor && recipes.enableManyullynArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                    }
                    if (global_registry.enablePigIronArmor && recipes.enablePigIronArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
                    }
                    break;
                }
                case EXPERT:
                case HELLISH: {
                    if (global_registry.enableArditeArmor && recipes.enableArditeArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockArdite", "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                    }
                    if (global_registry.enableCobaltArmor && recipes.enableCobaltArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockCobalt", "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                    }
                    if (global_registry.enableKnightSlimeArmor && recipes.enableKnightSlimeArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockKnightslime", "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                    }
                    if (global_registry.enableManyullynArmor && recipes.enableManyullynArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockManyullyn", "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                    }
                    if (global_registry.enablePigIronArmor && recipes.enablePigIronArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockPigiron", "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
                    }
                    break;
                }
            }
        }
    }
}