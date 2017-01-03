/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.*;

public class ModWeaponTierTwoRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {
        if (enableSwordsRecipes && recipes == 0) {
            if (enableEmeraldSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        "   E",
                        "   E",
                        "   E",
                        "   S",
                        'E', "gemEmerald",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        "  E ",
                        "  E ",
                        "  E ",
                        "  S ",
                        'E', "gemEmerald",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        " E  ",
                        " E  ",
                        " E  ",
                        " S  ",
                        'E', "gemEmerald",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        "E   ",
                        "E   ",
                        "E   ",
                        "S   ",
                        'E', "gemEmerald",
                        'S', "stickWood"));
            }
            if (enableObsidianSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        "   O",
                        "   O",
                        "   O",
                        "   S",
                        'O', "obsidian",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        "  O ",
                        "  O ",
                        "  O ",
                        "  S ",
                        'O', "obsidian",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        " O  ",
                        " O  ",
                        " O  ",
                        " S  ",
                        'O', "obsidian",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        "O   ",
                        "O   ",
                        "O   ",
                        "S   ",
                        'O', "obsidian",
                        'S', "stickWood"));
            }
            if (enableLavaSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        "   L",
                        "   L",
                        "   L",
                        "   S",
                        'L', "gemLavaCrystal",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        "  L ",
                        "  L ",
                        "  L ",
                        "  S ",
                        'L', "gemLavaCrystal",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        " L  ",
                        " L  ",
                        " L  ",
                        " S  ",
                        'L', "gemLavaCrystal",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        "L   ",
                        "L   ",
                        "L   ",
                        "S   ",
                        'L', "gemLavaCrystal",
                        'S', "stickWood"));
            }
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            if (enableEmeraldBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBattleAxe, 1),
                        "E  E",
                        "ESSE",
                        "ESSE",
                        " SS ",
                        'E', "gemEmerald",
                        'S', "stickWood"));
            }
            if (enableObsidianBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBattleAxe, 1),
                        "O  O",
                        "OSSO",
                        "OSSO",
                        " SS ",
                        'O', "obsidian",
                        'S', "stickWood"));
            }
            if (enableLavaBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBattleAxe, 1),
                        "L  L",
                        "LSSL",
                        "LSSL",
                        " SS ",
                        'L', "gemLavaCrystal",
                        'S', "stickWood"));
            }
        }
        if (enableBowsRecipes && recipes == 0) {
            if (enableEmeraldBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                        " EES",
                        "E  S",
                        "E  S",
                        " EES",
                        'E', "gemEmerald",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                        "SEE ",
                        "S  E",
                        "S  E",
                        "SEE ",
                        'E', "gemEmerald",
                        'S', "string"));
            }
            if (enableObsidianBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                        " OOS",
                        "O  S",
                        "O  S",
                        " OOS",
                        'O', "obsidian",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                        "SOO ",
                        "S  O",
                        "S  O",
                        "SOO ",
                        'O', "obsidian",
                        'S', "C"));
            }
            if (enableLavaBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                        " LLS",
                        "L  S",
                        "L  S",
                        " LLS",
                        'L', "gemLavaCrystal",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                        "SLL ",
                        "S  L",
                        "S  L",
                        "SLL ",
                        'L', "gemLavaCrystal",
                        'S', "string"));
            }
        }
        if (enableSwordsRecipes && recipes == 1) {
            if (enableEmeraldSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        "   E",
                        "   E",
                        "   E",
                        "   S",
                        'E', "blockEmerald",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        "  E ",
                        "  E ",
                        "  E ",
                        "  S ",
                        'E', "blockEmerald",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        " E  ",
                        " E  ",
                        " E  ",
                        " S  ",
                        'E', "blockEmerald",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                        "E   ",
                        "E   ",
                        "E   ",
                        "S   ",
                        'E', "blockEmerald",
                        'S', "stickWood"));
            }
            if (enableObsidianSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        "   O",
                        "   O",
                        "   O",
                        "   S",
                        'O', "blockCompressedObsidian",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        "  O ",
                        "  O ",
                        "  O ",
                        "  S ",
                        'O', "blockCompressedObsidian",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        " O  ",
                        " O  ",
                        " O  ",
                        " S  ",
                        'O', "blockCompressedObsidian",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                        "O   ",
                        "O   ",
                        "O   ",
                        "S   ",
                        'O', "blockCompressedObsidian",
                        'S', "stickWood"));
            }
            if (enableLavaSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        "   L",
                        "   L",
                        "   L",
                        "   S",
                        'L', "gemChargedLavaCrystal",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        "  L ",
                        "  L ",
                        "  L ",
                        "  S ",
                        'L', "gemChargedLavaCrystal",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        " L  ",
                        " L  ",
                        " L  ",
                        " S  ",
                        'L', "gemChargedLavaCrystal",
                        'S', "stickWood"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                        "L   ",
                        "L   ",
                        "L   ",
                        "S   ",
                        'L', "gemChargedLavaCrystal",
                        'S', "stickWood"));
            }
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            if (enableEmeraldBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBattleAxe, 1),
                        "E  E",
                        "ESSE",
                        "ESSE",
                        " SS ",
                        'E', "blockEmerald",
                        'S', "stickWood"));
            }
            if (enableObsidianBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBattleAxe, 1),
                        "O  O",
                        "OSSO",
                        "OSSO",
                        " SS ",
                        'O', "blockCompressedObsidian",
                        'S', "stickWood"));
            }
            if (enableLavaBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBattleAxe, 1),
                        "L  L",
                        "LSSL",
                        "LSSL",
                        " SS ",
                        'L', "gemChargedLavaCrystal",
                        'S', "stickWood"));
            }
        }
        if (enableBowsRecipes && recipes == 1) {
            if (enableEmeraldBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                        " EES",
                        "E  S",
                        "E  S",
                        " EES",
                        'E', "blockEmerald",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                        "SEE ",
                        "S  E",
                        "S  E",
                        "SEE ",
                        'E', "blockEmerald",
                        'S', "string"));
            }
            if (enableObsidianBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                        " OOS",
                        "O  S",
                        "O  S",
                        " OOS",
                        'O', "blockCompressedObsidian",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                        "SOO ",
                        "S  O",
                        "S  O",
                        "SOO ",
                        'O', "blockCompressedObsidian",
                        'S', "string"));
            }
            if (enableLavaBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                        " LLS",
                        "L  S",
                        "L  S",
                        " LLS",
                        'L', "gemChargedLavaCrystal",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                        "SLL ",
                        "S  L",
                        "S  L",
                        "SLL ",
                        'L', "gemChargedLavaCrystal",
                        'S', "string"));
            }
        }
    }
}
