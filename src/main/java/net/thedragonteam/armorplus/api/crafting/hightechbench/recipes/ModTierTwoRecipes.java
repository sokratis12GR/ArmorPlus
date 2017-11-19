/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;


import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static net.thedragonteam.armorplus.registry.APItems.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModTierTwoRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (enableEmeraldArmor && enableEmeraldArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                }
                if (enableObsidianArmor && enableObsidianArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                }
                if (enableLavaArmor && enableLavaArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "gemLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (enableEmeraldArmor && enableEmeraldArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockEmerald", "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                }
                if (enableObsidianArmor && enableObsidianArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockCompressedObsidian", "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                }
                if (enableLavaArmor && enableLavaArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockInfusedObsidian", "gemChargedLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                }
                break;
            }
        }
    }
}
