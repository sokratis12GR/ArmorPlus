/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.*;
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
                    createSwordRecipes(manager, emeraldSword, "gemEmerald");
                    createSwordRecipes(manager, obsidianSword, "obsidian");
                    createSwordRecipes(manager, lavaSword, "gemLavaCrystal");
                }
                if (recipes.enableBattleAxesRecipes) {
                    createBattleAxeRecipe(manager, emeraldBattleAxe, "gemEmerald");
                    createBattleAxeRecipe(manager, obsidianBattleAxe, "obsidian");
                    createBattleAxeRecipe(manager, lavaBattleAxe, "gemLavaCrystal");
                }
                if (recipes.enableBowsRecipes) {
                    createBowRecipe(manager, emeraldBow, "gemEmerald");
                    createBowRecipe(manager, obsidianBow, "obsidian");
                    createBowRecipe(manager, lavaBow, "gemLavaCrystal");
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (recipes.enableSwordsRecipes) {
                    createSwordRecipes(manager, emeraldSword, "blockEmerald");
                    createSwordRecipes(manager, obsidianSword, "blockCompressedObsidian");
                    createSwordRecipes(manager, lavaSword, "gemChargedLavaCrystal");
                }
                if (recipes.enableBattleAxesRecipes) {
                    createBattleAxeRecipe(manager, emeraldBattleAxe, "blockEmerald");
                    createBattleAxeRecipe(manager, obsidianBattleAxe, "blockCompressedObsidian");
                    createBattleAxeRecipe(manager, lavaBattleAxe, "gemChargedLavaCrystal");
                }
                if (recipes.enableBowsRecipes) {
                    createBowRecipe(manager, emeraldBow, "blockEmerald");
                    createBowRecipe(manager, obsidianBow, "blockCompressedObsidian");
                    createBowRecipe(manager, lavaBow, "gemChargedLavaCrystal");
                }
                break;
            }
        }
    }
}
