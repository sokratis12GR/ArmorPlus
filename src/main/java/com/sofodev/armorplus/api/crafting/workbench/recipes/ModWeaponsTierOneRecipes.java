/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.config.ModConfig;

import static com.sofodev.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.*;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        if (getRD() == ModConfig.RecipesDifficulty.EASY) {
            createSwordRecipe(manager, "itemCoal", coalSword);
            createSwordRecipe(manager, "gemLapis", lapisSword);
            createSwordRecipe(manager, "dustRedstone", redstoneSword);
            createBattleAxeRecipe(manager, "itemCoal", coalBattleAxe);
            createBattleAxeRecipe(manager, "gemLapis", lapisBattleAxe);
            createBattleAxeRecipe(manager, "dustRedstone", redstoneBattleAxe);
            createBowRecipes(manager, "itemCoal", coalBow);
            createBowRecipes(manager, "gemLapis", lapisBow);
            createBowRecipes(manager, "dustRedstone", redstoneBow);
        } else if (getRD() == ModConfig.RecipesDifficulty.EXPERT || getRD() == ModConfig.RecipesDifficulty.HELLISH) {
            createSwordRecipe(manager, "blockCoal", coalSword);
            createSwordRecipe(manager, "blockLapis", lapisSword);
            createSwordRecipe(manager, "blockRedstone", redstoneSword);
            createBattleAxeRecipe(manager, "blockCoal", coalBattleAxe);
            createBattleAxeRecipe(manager, "blockLapis", lapisBattleAxe);
            createBattleAxeRecipe(manager, "blockRedstone", redstoneBattleAxe);
            createBowRecipes(manager, "blockCoal", coalBow);
            createBowRecipes(manager, "blockLapis", lapisBow);
            createBowRecipes(manager, "blockRedstone", redstoneBow);
        }
    }
}
