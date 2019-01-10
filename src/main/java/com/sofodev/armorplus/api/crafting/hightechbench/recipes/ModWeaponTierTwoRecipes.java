/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.*;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModWeaponTierTwoRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (recipes.enableSwordsRecipes) {
                    if (global_registry.enableEmeraldWeapons[0]) {
                        createSwordRecipes(manager, emeraldSword, "gemEmerald");
                    }
                    if (global_registry.enableObsidianWeapons[0]) {
                        createSwordRecipes(manager, obsidianSword, "obsidian");
                    }
                    if (global_registry.enableLavaWeapons[0]) {
                        createSwordRecipes(manager, lavaSword, "gemLavaCrystal");
                    }
                }
                if (recipes.enableBattleAxesRecipes) {
                    if (global_registry.enableEmeraldWeapons[1]) {
                        createBattleAxeRecipe(manager, emeraldBattleAxe, "gemEmerald");
                    }
                    if (global_registry.enableObsidianWeapons[1]) {
                        createBattleAxeRecipe(manager, obsidianBattleAxe, "obsidian");
                    }
                    if (global_registry.enableLavaWeapons[1]) {
                        createBattleAxeRecipe(manager, lavaBattleAxe, "gemLavaCrystal");
                    }
                }
                if (recipes.enableBowsRecipes) {
                    if (global_registry.enableEmeraldWeapons[2]) {
                        createBowRecipe(manager, emeraldBow, "gemEmerald");
                    }
                    if (global_registry.enableObsidianWeapons[2]) {
                        createBowRecipe(manager, obsidianBow, "obsidian");
                    }
                    if (global_registry.enableLavaWeapons[2]) {
                        createBowRecipe(manager, lavaBow, "gemLavaCrystal");
                    }
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (recipes.enableSwordsRecipes) {
                    if (global_registry.enableEmeraldWeapons[0]) {
                        createSwordRecipes(manager, emeraldSword, "blockEmerald");
                    }
                    if (global_registry.enableObsidianWeapons[0]) {
                        createSwordRecipes(manager, obsidianSword, "blockCompressedObsidian");
                    }
                    if (global_registry.enableLavaWeapons[0]) {
                        createSwordRecipes(manager, lavaSword, "gemChargedLavaCrystal");
                    }
                }
                if (recipes.enableBattleAxesRecipes) {
                    if (global_registry.enableEmeraldWeapons[1]) {
                        createBattleAxeRecipe(manager, emeraldBattleAxe, "blockEmerald");
                    }
                    if (global_registry.enableObsidianWeapons[1]) {
                        createBattleAxeRecipe(manager, obsidianBattleAxe, "blockCompressedObsidian");
                    }
                    if (global_registry.enableLavaWeapons[1]) {
                        createBattleAxeRecipe(manager, lavaBattleAxe, "gemChargedLavaCrystal");
                    }
                }
                if (recipes.enableBowsRecipes) {
                    if (global_registry.enableEmeraldWeapons[2]) {
                        createBowRecipe(manager, emeraldBow, "blockEmerald");
                    }
                    if (global_registry.enableObsidianWeapons[2]) {
                        createBowRecipe(manager, obsidianBow, "blockCompressedObsidian");
                    }
                    if (global_registry.enableLavaWeapons[2]) {
                        createBowRecipe(manager, lavaBow, "gemChargedLavaCrystal");
                    }
                }
                break;
            }
        }
    }
}
