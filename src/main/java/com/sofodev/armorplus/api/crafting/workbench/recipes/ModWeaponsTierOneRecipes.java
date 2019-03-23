/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.*;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (recipes.enableSwordsRecipes) {
                    createSwordRecipe(manager, "itemCoal", coalSword);
                    createSwordRecipe(manager, "gemLapis", lapisSword);
                    createSwordRecipe(manager, "dustRedstone", redstoneSword);
                }
                if (recipes.enableBattleAxesRecipes) {
                    createBattleAxeRecipe(manager, "itemCoal", coalBattleAxe);
                    createBattleAxeRecipe(manager, "gemLapis", lapisBattleAxe);
                    createBattleAxeRecipe(manager, "dustRedstone", redstoneBattleAxe);
                }
                if (recipes.enableBowsRecipes) {
                    createBowRecipes(manager, "itemCoal", coalBow);
                    createBowRecipes(manager, "gemLapis", lapisBow);
                    createBowRecipes(manager, "dustRedstone", redstoneBow);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (recipes.enableSwordsRecipes) {
                    createSwordRecipe(manager, "blockCoal", coalSword);
                    createSwordRecipe(manager, "blockLapis", lapisSword);
                    createSwordRecipe(manager, "blockRedstone", redstoneSword);
                }
                if (recipes.enableBattleAxesRecipes) {
                    createBattleAxeRecipe(manager, "blockCoal", coalBattleAxe);
                    createBattleAxeRecipe(manager, "blockLapis", lapisBattleAxe);
                    createBattleAxeRecipe(manager, "blockRedstone", redstoneBattleAxe);
                }
                if (recipes.enableBowsRecipes) {
                    createBowRecipes(manager, "blockCoal", coalBow);
                    createBowRecipes(manager, "blockLapis", lapisBow);
                    createBowRecipes(manager, "blockRedstone", redstoneBow);
                }
                break;
            }
        }
    }
}
