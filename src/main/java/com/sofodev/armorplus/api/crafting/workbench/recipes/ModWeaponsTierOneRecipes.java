/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;

import static com.sofodev.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.*;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.ModItems.pickaxe;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;

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
                    createBowRecipe(manager, "itemCoal", coalBow);
                    createBowRecipe(manager, "gemLapis", lapisBow);
                    createBowRecipe(manager, "dustRedstone", redstoneBow);
                }
                if (recipes.enablePickaxesRecipes) {
                    createPickaxeRecipe(manager, "itemCoal", pickaxe[0]);
                    createPickaxeRecipe(manager, "gemLapis", pickaxe[1]);
                    createPickaxeRecipe(manager, "dustRedstone", pickaxe[2]);
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
                    createBowRecipe(manager, "blockCoal", coalBow);
                    createBowRecipe(manager, "blockLapis", lapisBow);
                    createBowRecipe(manager, "blockRedstone", redstoneBow);
                }
                if (recipes.enablePickaxesRecipes) {
                    createPickaxeRecipe(manager, "blockCoal", pickaxe[0]);
                    createPickaxeRecipe(manager, "blockLapis", pickaxe[1]);
                    createPickaxeRecipe(manager, "blockRedstone", pickaxe[2]);
                }
                break;
            }
        }
    }
}
