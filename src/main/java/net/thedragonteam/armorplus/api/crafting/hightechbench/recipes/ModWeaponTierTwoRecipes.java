/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;


import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.*;
import static net.thedragonteam.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModWeaponTierTwoRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (enableSwordsRecipes) {
                    if (enableEmeraldWeapons[0]) {
                        createSwordRecipes(manager, emeraldSword, "gemEmerald");
                    }
                    if (enableObsidianWeapons[0]) {
                        createSwordRecipes(manager, obsidianSword, "obsidian");
                    }
                    if (enableLavaWeapons[0]) {
                        createSwordRecipes(manager, lavaSword, "gemLavaCrystal");
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableEmeraldWeapons[1]) {
                        createBattleAxeRecipe(manager, emeraldBattleAxe, "gemEmerald");
                    }
                    if (enableObsidianWeapons[1]) {
                        createBattleAxeRecipe(manager, obsidianBattleAxe, "obsidian");
                    }
                    if (enableLavaWeapons[1]) {
                        createBattleAxeRecipe(manager, lavaBattleAxe, "gemLavaCrystal");
                    }
                }
                if (enableBowsRecipes) {
                    if (enableEmeraldWeapons[2]) {
                        createBowRecipe(manager, emeraldBow, "gemEmerald");
                    }
                    if (enableObsidianWeapons[2]) {
                        createBowRecipe(manager, obsidianBow, "obsidian");
                    }
                    if (enableLavaWeapons[2]) {
                        createBowRecipe(manager, lavaBow, "gemLavaCrystal");
                    }
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (enableSwordsRecipes) {
                    if (enableEmeraldWeapons[0]) {
                        createSwordRecipes(manager, emeraldSword, "blockEmerald");
                    }
                    if (enableObsidianWeapons[0]) {
                        createSwordRecipes(manager, obsidianSword, "blockCompressedObsidian");
                    }
                    if (enableLavaWeapons[0]) {
                        createSwordRecipes(manager, lavaSword, "gemChargedLavaCrystal");
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableEmeraldWeapons[1]) {
                        createBattleAxeRecipe(manager, emeraldBattleAxe, "blockEmerald");
                    }
                    if (enableObsidianWeapons[1]) {
                        createBattleAxeRecipe(manager, obsidianBattleAxe, "blockCompressedObsidian");
                    }
                    if (enableLavaWeapons[1]) {
                        createBattleAxeRecipe(manager, lavaBattleAxe, "gemChargedLavaCrystal");
                    }
                }
                if (enableBowsRecipes) {
                    if (enableEmeraldWeapons[2]) {
                        createBowRecipe(manager, emeraldBow, "blockEmerald");
                    }
                    if (enableObsidianWeapons[2]) {
                        createBowRecipe(manager, obsidianBow, "blockCompressedObsidian");
                    }
                    if (enableLavaWeapons[2]) {
                        createBowRecipe(manager, lavaBow, "gemChargedLavaCrystal");
                    }
                }
                break;
            }
        }
    }
}
