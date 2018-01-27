/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;


import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.util.LoaderUtils;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.ModConfig.getRD;
import static net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static net.thedragonteam.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModTinkersConstructRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
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