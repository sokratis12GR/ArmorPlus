/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;


import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.ModConfig.getRD;
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
                if (global_registry.enableEmeraldArmor && recipes.enableEmeraldArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                }
                if (global_registry.enableObsidianArmor && recipes.enableObsidianArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                }
                if (global_registry.enableLavaArmor && recipes.enableLavaArmorRecipes) {
                    createEasyArmorSetRecipes(manager, "gemLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (global_registry.enableEmeraldArmor && recipes.enableEmeraldArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockEmerald", "gemEmerald", emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
                }
                if (global_registry.enableObsidianArmor && recipes.enableObsidianArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockCompressedObsidian", "obsidian", obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
                }
                if (global_registry.enableLavaArmor && recipes.enableLavaArmorRecipes) {
                    createExpertArmorSetRecipes(manager, "blockInfusedObsidian", "gemChargedLavaCrystal", lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
                }
                break;
            }
        }
    }
}
