/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.*;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.APItems.*;
import static com.sofodev.armorplus.common.registry.ModItems.pickaxe;

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
                if (recipes.enablePickaxesRecipes) {
                    createPickaxeRecipe(manager, pickaxe[3], "gemEmerald");
                    createPickaxeRecipe(manager, pickaxe[4], "obsidian");
                    createPickaxeRecipe(manager, pickaxe[5], "gemLavaCrystal");
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
                if (recipes.enablePickaxesRecipes) {
                    createPickaxeRecipe(manager, pickaxe[3], "blockEmerald");
                    createPickaxeRecipe(manager, pickaxe[4], "blockCompressedObsidian");
                    createPickaxeRecipe(manager, pickaxe[5], "gemChargedLavaCrystal");
                }
                break;
            }
        }
    }
}
