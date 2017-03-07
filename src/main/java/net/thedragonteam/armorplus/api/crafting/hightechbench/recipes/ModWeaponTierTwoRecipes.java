/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModWeaponTierTwoRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (enableSwordsRecipes) {
                    if (enableEmeraldWeapons[0]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                "   E",
                                "   E",
                                "   E",
                                "   S",
                                'E', "gemEmerald",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                "  E ",
                                "  E ",
                                "  E ",
                                "  S ",
                                'E', "gemEmerald",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                " E  ",
                                " E  ",
                                " E  ",
                                " S  ",
                                'E', "gemEmerald",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                "E   ",
                                "E   ",
                                "E   ",
                                "S   ",
                                'E', "gemEmerald",
                                'S', "stickWood"));
                    }
                    if (enableObsidianWeapons[0]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                "   O",
                                "   O",
                                "   O",
                                "   S",
                                'O', "obsidian",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                "  O ",
                                "  O ",
                                "  O ",
                                "  S ",
                                'O', "obsidian",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                " O  ",
                                " O  ",
                                " O  ",
                                " S  ",
                                'O', "obsidian",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                "O   ",
                                "O   ",
                                "O   ",
                                "S   ",
                                'O', "obsidian",
                                'S', "stickWood"));
                    }
                    if (enableLavaWeapons[0]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                "   L",
                                "   L",
                                "   L",
                                "   S",
                                'L', "gemLavaCrystal",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                "  L ",
                                "  L ",
                                "  L ",
                                "  S ",
                                'L', "gemLavaCrystal",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                " L  ",
                                " L  ",
                                " L  ",
                                " S  ",
                                'L', "gemLavaCrystal",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                "L   ",
                                "L   ",
                                "L   ",
                                "S   ",
                                'L', "gemLavaCrystal",
                                'S', "stickWood"));
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableEmeraldWeapons[1]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBattleAxe),
                                "E  E",
                                "ESSE",
                                "ESSE",
                                " SS ",
                                'E', "gemEmerald",
                                'S', "stickWood"));
                    }
                    if (enableObsidianWeapons[1]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBattleAxe),
                                "O  O",
                                "OSSO",
                                "OSSO",
                                " SS ",
                                'O', "obsidian",
                                'S', "stickWood"));
                    }
                    if (enableLavaWeapons[1]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBattleAxe),
                                "L  L",
                                "LSSL",
                                "LSSL",
                                " SS ",
                                'L', "gemLavaCrystal",
                                'S', "stickWood"));
                    }
                }
                if (enableBowsRecipes) {
                    if (enableEmeraldWeapons[2]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBow),
                                " EES",
                                "E  S",
                                "E  S",
                                " EES",
                                'E', "gemEmerald",
                                'S', "string"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBow),
                                "SEE ",
                                "S  E",
                                "S  E",
                                "SEE ",
                                'E', "gemEmerald",
                                'S', "string"));
                    }
                    if (enableObsidianWeapons[2]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBow),
                                " OOS",
                                "O  S",
                                "O  S",
                                " OOS",
                                'O', "obsidian",
                                'S', "string"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBow),
                                "SOO ",
                                "S  O",
                                "S  O",
                                "SOO ",
                                'O', "obsidian",
                                'S', "C"));
                    }
                    if (enableLavaWeapons[2]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBow),
                                " LLS",
                                "L  S",
                                "L  S",
                                " LLS",
                                'L', "gemLavaCrystal",
                                'S', "string"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBow),
                                "SLL ",
                                "S  L",
                                "S  L",
                                "SLL ",
                                'L', "gemLavaCrystal",
                                'S', "string"));
                    }
                }

                break;
            case EXPERT:
            case HELLISH:
                if (enableSwordsRecipes) {
                    if (enableEmeraldWeapons[0]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                "   E",
                                "   E",
                                "   E",
                                "   S",
                                'E', "blockEmerald",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                "  E ",
                                "  E ",
                                "  E ",
                                "  S ",
                                'E', "blockEmerald",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                " E  ",
                                " E  ",
                                " E  ",
                                " S  ",
                                'E', "blockEmerald",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldSword),
                                "E   ",
                                "E   ",
                                "E   ",
                                "S   ",
                                'E', "blockEmerald",
                                'S', "stickWood"));
                    }
                    if (enableObsidianWeapons[0]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                "   O",
                                "   O",
                                "   O",
                                "   S",
                                'O', "blockCompressedObsidian",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                "  O ",
                                "  O ",
                                "  O ",
                                "  S ",
                                'O', "blockCompressedObsidian",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                " O  ",
                                " O  ",
                                " O  ",
                                " S  ",
                                'O', "blockCompressedObsidian",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianSword),
                                "O   ",
                                "O   ",
                                "O   ",
                                "S   ",
                                'O', "blockCompressedObsidian",
                                'S', "stickWood"));
                    }
                    if (enableLavaWeapons[0]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                "   L",
                                "   L",
                                "   L",
                                "   S",
                                'L', "gemChargedLavaCrystal",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                "  L ",
                                "  L ",
                                "  L ",
                                "  S ",
                                'L', "gemChargedLavaCrystal",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                " L  ",
                                " L  ",
                                " L  ",
                                " S  ",
                                'L', "gemChargedLavaCrystal",
                                'S', "stickWood"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaSword),
                                "L   ",
                                "L   ",
                                "L   ",
                                "S   ",
                                'L', "gemChargedLavaCrystal",
                                'S', "stickWood"));
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableEmeraldWeapons[1]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBattleAxe),
                                "E  E",
                                "ESSE",
                                "ESSE",
                                " SS ",
                                'E', "blockEmerald",
                                'S', "stickWood"));
                    }
                    if (enableObsidianWeapons[1]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBattleAxe),
                                "O  O",
                                "OSSO",
                                "OSSO",
                                " SS ",
                                'O', "blockCompressedObsidian",
                                'S', "stickWood"));
                    }
                    if (enableLavaWeapons[1]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBattleAxe),
                                "L  L",
                                "LSSL",
                                "LSSL",
                                " SS ",
                                'L', "gemChargedLavaCrystal",
                                'S', "stickWood"));
                    }
                }
                if (enableBowsRecipes) {
                    if (enableEmeraldWeapons[2]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBow),
                                " EES",
                                "E  S",
                                "E  S",
                                " EES",
                                'E', "blockEmerald",
                                'S', "string"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBow),
                                "SEE ",
                                "S  E",
                                "S  E",
                                "SEE ",
                                'E', "blockEmerald",
                                'S', "string"));
                    }
                    if (enableObsidianWeapons[2]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBow),
                                " OOS",
                                "O  S",
                                "O  S",
                                " OOS",
                                'O', "blockCompressedObsidian",
                                'S', "string"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBow),
                                "SOO ",
                                "S  O",
                                "S  O",
                                "SOO ",
                                'O', "blockCompressedObsidian",
                                'S', "string"));
                    }
                    if (enableLavaWeapons[2]) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBow),
                                " LLS",
                                "L  S",
                                "L  S",
                                " LLS",
                                'L', "gemChargedLavaCrystal",
                                'S', "string"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBow),
                                "SLL ",
                                "S  L",
                                "S  L",
                                "SLL ",
                                'L', "gemChargedLavaCrystal",
                                'S', "string"));
                    }
                }

                break;
        }
    }
}
