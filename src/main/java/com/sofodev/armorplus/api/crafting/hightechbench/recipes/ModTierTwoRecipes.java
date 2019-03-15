/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.config.ModConfig;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModTierTwoRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (getRD() == ModConfig.RecipesDifficulty.EASY) {
            createEasyArmorSetRecipes(manager, "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
            createEasyArmorSetRecipes(manager, "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
            createEasyArmorSetRecipes(manager, "gemLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        } else if (getRD() == ModConfig.RecipesDifficulty.EXPERT || getRD() == ModConfig.RecipesDifficulty.HELLISH) {
            createExpertArmorSetRecipes(manager, "blockEmerald", "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
            createExpertArmorSetRecipes(manager, "blockCompressedObsidian", "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
            createExpertArmorSetRecipes(manager, "blockInfusedObsidian", "gemChargedLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }
    }
}
