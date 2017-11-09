/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;


import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HTBShapedOreRecipe;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class ModTierTwoRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (enableEmeraldArmor) {
                    if (enableEmeraldArmorRecipes) {
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldHelmet),
                                "    ",
                                "EEEE",
                                "E  E",
                                "    ",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldHelmet),
                                "    ",
                                "    ",
                                "EEEE",
                                "E  E",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldHelmet),
                                "EEEE",
                                "E  E",
                                "    ",
                                "    ",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldChestplate),
                                "E  E",
                                "E  E",
                                "EEEE",
                                "EEEE",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldLeggings),
                                "EEEE",
                                "E  E",
                                "E  E",
                                "E  E",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldBoots),
                                "    ",
                                "    ",
                                "E  E",
                                "E  E",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldBoots),
                                "    ",
                                "E  E",
                                "E  E",
                                "    ",
                                'E', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldBoots),
                                "E  E",
                                "E  E",
                                "    ",
                                "    ",
                                'E', "gemEmerald"));
                    }
                }
                if (enableObsidianArmor) {
                    if (enableObsidianArmorRecipes) {
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianHelmet),
                                "    ",
                                "    ",
                                "OOOO",
                                "O  O",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianHelmet),
                                "OOOO",
                                "O  O",
                                "    ",
                                "    ",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianHelmet),
                                "    ",
                                "OOOO",
                                "O  O",
                                "    ",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianChestplate),
                                "O  O",
                                "O  O",
                                "OOOO",
                                "OOOO",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianLeggings),
                                "OOOO",
                                "O  O",
                                "O  O",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianBoots),
                                "    ",
                                "    ",
                                "O  O",
                                "O  O",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianBoots),
                                "    ",
                                "O  O",
                                "O  O",
                                "    ",
                                'O', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianBoots),
                                "O  O",
                                "O  O",
                                "    ",
                                "    ",
                                'O', "obsidian"));
                    }
                }
                if (enableLavaArmor) {
                    if (enableLavaArmorRecipes) {
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaHelmet),
                                "    ",
                                "    ",
                                "CCCC",
                                "C  C",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaHelmet),
                                "    ",
                                "CCCC",
                                "C  C",
                                "    ",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaHelmet),
                                "CCCC",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaChestplate),
                                "C  C",
                                "C  C",
                                "CCCC",
                                "CCCC",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaLeggings),
                                "CCCC",
                                "C  C",
                                "C  C",
                                "C  C",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaBoots),
                                "    ",
                                "C  C",
                                "C  C",
                                "    ",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaBoots),
                                "C  C",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "gemLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaBoots),
                                "    ",
                                "    ",
                                "C  C",
                                "C  C",
                                'C', "gemLavaCrystal"));
                    }
                }
                break;
            case EXPERT:
            case HELLISH:
                if (enableEmeraldArmor) {
                    if (enableEmeraldArmorRecipes) {
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldHelmet),
                                "    ",
                                "    ",
                                "EEEE",
                                "e  e",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldHelmet),
                                "    ",
                                "EEEE",
                                "e  e",
                                "    ",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldHelmet),
                                "EEEE",
                                "e  e",
                                "    ",
                                "    ",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldChestplate),
                                "e  e",
                                "e  e",
                                "EEEE",
                                "EEEE",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldLeggings),
                                "EEEE",
                                "EeeE",
                                "e  e",
                                "e  e",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldBoots),
                                "    ",
                                "E  E",
                                "E  E",
                                "e  e",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(emeraldBoots),
                                "E  E",
                                "E  E",
                                "e  e",
                                "    ",
                                'E', "blockEmerald",
                                'e', "gemEmerald"));
                    }
                }
                if (enableObsidianArmor) {
                    if (enableObsidianArmorRecipes) {
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianHelmet),
                                "    ",
                                "    ",
                                "OOOO",
                                "o  o",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianHelmet),
                                "OOOO",
                                "o  o",
                                "    ",
                                "    ",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianHelmet),
                                "    ",
                                "OOOO",
                                "o  o",
                                "    ",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianChestplate),
                                "o  o",
                                "o  o",
                                "OOOO",
                                "OOOO",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianLeggings),
                                "OOOO",
                                "OooO",
                                "o  o",
                                "o  o",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianBoots),
                                "    ",
                                "O  O",
                                "O  O",
                                "o  o",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(obsidianBoots),
                                "O  O",
                                "O  O",
                                "o  o",
                                "    ",
                                'O', "blockCompressedObsidian",
                                'o', "obsidian"));
                    }
                }
                if (enableLavaArmor) {
                    if (enableLavaArmorRecipes) {
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaHelmet),
                                "    ",
                                "    ",
                                "FCCF",
                                "C  C",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaHelmet),
                                "    ",
                                "FCCF",
                                "C  C",
                                "    ",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaHelmet),
                                "FCCF",
                                "C  C",
                                "    ",
                                "    ",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaChestplate),
                                "C  C",
                                "C  C",
                                "FCCF",
                                "FFFF",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaLeggings),
                                "FCCF",
                                "C  C",
                                "C  C",
                                "C  C",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaBoots),
                                "    ",
                                "F  F",
                                "C  C",
                                "C  C",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(lavaBoots),
                                "F  F",
                                "C  C",
                                "C  C",
                                "    ",
                                'F', "blockInfusedObsidian",
                                'C', "gemChargedLavaCrystal"));
                    }
                }
                break;
        }
    }
}
