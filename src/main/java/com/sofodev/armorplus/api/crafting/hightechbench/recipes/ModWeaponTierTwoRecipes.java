/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.config.ModConfig;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.*;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModWeaponTierTwoRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (getRD() == ModConfig.RecipesDifficulty.EASY) {
            createSwordRecipes(manager, emeraldSword, "gemEmerald");
            createSwordRecipes(manager, obsidianSword, "obsidian");
            createSwordRecipes(manager, lavaSword, "gemLavaCrystal");
            createBattleAxeRecipe(manager, emeraldBattleAxe, "gemEmerald");
            createBattleAxeRecipe(manager, obsidianBattleAxe, "obsidian");
            createBattleAxeRecipe(manager, lavaBattleAxe, "gemLavaCrystal");
            createBowRecipe(manager, emeraldBow, "gemEmerald");
            createBowRecipe(manager, obsidianBow, "obsidian");
            createBowRecipe(manager, lavaBow, "gemLavaCrystal");
        } else if (getRD() == ModConfig.RecipesDifficulty.EXPERT || getRD() == ModConfig.RecipesDifficulty.HELLISH) {
            createSwordRecipes(manager, emeraldSword, "blockEmerald");
            createSwordRecipes(manager, obsidianSword, "blockCompressedObsidian");
            createSwordRecipes(manager, lavaSword, "gemChargedLavaCrystal");
            createBattleAxeRecipe(manager, emeraldBattleAxe, "blockEmerald");
            createBattleAxeRecipe(manager, obsidianBattleAxe, "blockCompressedObsidian");
            createBattleAxeRecipe(manager, lavaBattleAxe, "gemChargedLavaCrystal");
            createBowRecipe(manager, emeraldBow, "blockEmerald");
            createBowRecipe(manager, obsidianBow, "blockCompressedObsidian");
            createBowRecipe(manager, lavaBow, "gemChargedLavaCrystal");
        }
    }
}
