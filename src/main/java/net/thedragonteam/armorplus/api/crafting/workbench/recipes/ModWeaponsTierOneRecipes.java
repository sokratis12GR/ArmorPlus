/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.*;
import static net.thedragonteam.armorplus.registry.APItems.*;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (enableSwordsRecipes) {
                    if (enableCoalWeapons[0]) {
                        createSwordRecipe(manager, "itemCoal", coalSword);
                    }
                    if (enableLapisWeapons[0]) {
                        createSwordRecipe(manager, "gemLapis", lapisSword);
                    }
                    if (enableRedstoneWeapons[0]) {
                        createSwordRecipe(manager, "dustRedstone", redstoneSword);
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableCoalWeapons[1]) {
                        createBattleAxeRecipe(manager, "itemCoal", coalBattleAxe);
                    }
                    if (enableLapisWeapons[1]) {
                        createBattleAxeRecipe(manager, "gemLapis", lapisBattleAxe);
                    }
                    if (enableRedstoneWeapons[1]) {
                        createBattleAxeRecipe(manager, "dustRedstone", redstoneBattleAxe);
                    }
                }
                if (enableBowsRecipes) {
                    if (enableCoalWeapons[2]) {
                        createBowRecipes(manager, "itemCoal", coalBow);
                    }
                    if (enableLapisWeapons[2]) {
                        createBowRecipes(manager, "gemLapis", lapisBow);
                    }
                    if (enableRedstoneWeapons[2]) {
                        createBowRecipes(manager, "dustRedstone", redstoneBow);
                    }
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (enableSwordsRecipes) {
                    if (enableCoalWeapons[0]) {
                        createSwordRecipe(manager, "blockCoal", coalSword);
                    }
                    if (enableLapisWeapons[0]) {
                        createSwordRecipe(manager, "blockLapis", lapisSword);
                    }
                    if (enableRedstoneWeapons[0]) {
                        createSwordRecipe(manager, "blockRedstone", redstoneSword);
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableCoalWeapons[1]) {
                        createBattleAxeRecipe(manager, "blockCoal", coalBattleAxe);
                    }
                    if (enableLapisWeapons[1]) {
                        createBattleAxeRecipe(manager, "blockLapis", lapisBattleAxe);
                    }
                    if (enableRedstoneWeapons[1]) {
                        createBattleAxeRecipe(manager, "blockRedstone", redstoneBattleAxe);
                    }
                }
                if (enableBowsRecipes) {
                    if (enableCoalWeapons[2]) {
                        createBowRecipes(manager, "blockCoal", coalBow);
                    }
                    if (enableLapisWeapons[2]) {
                        createBowRecipes(manager, "blockLapis", lapisBow);
                    }
                    if (enableRedstoneWeapons[2]) {
                        createBowRecipes(manager, "blockRedstone", redstoneBow);
                    }
                }
                break;
            }
        }
    }
}
