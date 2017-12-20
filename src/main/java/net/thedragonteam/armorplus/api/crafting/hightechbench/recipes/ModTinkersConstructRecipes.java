/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;


import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.util.LoaderUtils;

import static net.thedragonteam.armorplus.APConfig.*;
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
                    if (enableArditeArmor && enableArditeArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                    }
                    if (enableCobaltArmor && enableCobaltArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                    }
                    if (enableKnightSlimeArmor && enableKnightSlimeArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                    }
                    if (enableManyullynArmor && enableManyullynArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                    }
                    if (enablePigIronArmor && enablePigIronArmorRecipes) {
                        createEasyArmorSetRecipes(manager, "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
                    }
                    break;
                }
                case EXPERT:
                case HELLISH: {
                    if (enableArditeArmor && enableArditeArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockArdite", "ingotArdite", arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
                    }
                    if (enableCobaltArmor && enableCobaltArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockCobalt", "ingotCobalt", cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
                    }
                    if (enableKnightSlimeArmor && enableKnightSlimeArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockKnightslime", "ingotKnightslime", knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
                    }
                    if (enableManyullynArmor && enableManyullynArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockManyullyn", "ingotManyullyn", manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
                    }
                    if (enablePigIronArmor && enablePigIronArmorRecipes) {
                        createExpertArmorSetRecipes(manager, "blockPigiron", "ingotPigiron", pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
                    }
                    break;
                }
            }
        }
    }
}