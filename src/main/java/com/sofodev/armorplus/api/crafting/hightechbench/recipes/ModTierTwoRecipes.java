/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModTierTwoRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                createEasyArmorSetRecipes(recipes.enableEmeraldArmorRecipes, manager, "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                createEasyArmorSetRecipes(recipes.enableObsidianArmorRecipes, manager, "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                createEasyArmorSetRecipes(recipes.enableLavaArmorRecipes, manager, "gemLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                break;
            }
            case EXPERT:
            case HELLISH: {
                createExpertArmorSetRecipes(recipes.enableEmeraldArmorRecipes, manager, "blockEmerald", "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                createExpertArmorSetRecipes(recipes.enableObsidianArmorRecipes, manager, "blockCompressedObsidian", "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                createExpertArmorSetRecipes(recipes.enableLavaArmorRecipes, manager, "blockInfusedObsidian", "gemChargedLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                break;
            }
        }
    }
}
