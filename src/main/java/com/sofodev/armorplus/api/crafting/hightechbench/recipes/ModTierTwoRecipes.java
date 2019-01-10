/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModTierTwoRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (global_registry.enableEmeraldArmor && recipes.enableEmeraldArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                }
                if (global_registry.enableObsidianArmor && recipes.enableObsidianArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                }
                if (global_registry.enableLavaArmor && recipes.enableLavaArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "gemLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (global_registry.enableEmeraldArmor && recipes.enableEmeraldArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockEmerald", "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                }
                if (global_registry.enableObsidianArmor && recipes.enableObsidianArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockCompressedObsidian", "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                }
                if (global_registry.enableLavaArmor && recipes.enableLavaArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockInfusedObsidian", "gemChargedLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                }
                break;
            }
        }
    }
}
