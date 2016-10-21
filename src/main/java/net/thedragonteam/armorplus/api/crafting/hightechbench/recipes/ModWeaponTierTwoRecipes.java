/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ModWeaponTierTwoRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {
        if (enableSwordsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "XXXE",
                    "XXXE",
                    "XXXE",
                    "XXXS",
                    'E', "gemEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "XXXO",
                    "XXXO",
                    "XXXO",
                    "XXXS",
                    'O', "obsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "XXXL",
                    "XXXL",
                    "XXXL",
                    "XXXS",
                    'L', "gemLavaCrystal",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "XXEX",
                    "XXEX",
                    "XXEX",
                    "XXSX",
                    'E', "gemEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "XXOX",
                    "XXOX",
                    "XXOX",
                    "XXSX",
                    'O', "obsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "XXLX",
                    "XXLX",
                    "XXLX",
                    "XXSX",
                    'L', "gemLavaCrystal",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "XEXX",
                    "XEXX",
                    "XEXX",
                    "XSXX",
                    'E', "gemEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "XOXX",
                    "XOXX",
                    "XOXX",
                    "XSXX",
                    'O', "obsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "XLXX",
                    "XLXX",
                    "XLXX",
                    "XSXX",
                    'L', "gemLavaCrystal",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "EXXX",
                    "EXXX",
                    "EXXX",
                    "SXXX",
                    'E', "gemEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "OXXX",
                    "OXXX",
                    "OXXX",
                    "SXXX",
                    'O', "obsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "LXXX",
                    "LXXX",
                    "LXXX",
                    "LXXX",
                    'L', "gemLavaCrystal",
                    'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBattleAxe, 1),
                    "EXXE",
                    "ESSE",
                    "ESSE",
                    "XSSX",
                    'E', "gemEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBattleAxe, 1),
                    "OXXO",
                    "OSSO",
                    "OSSO",
                    "XSSX",
                    'O', "obsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBattleAxe, 1),
                    "LXXL",
                    "LSSL",
                    "LSSL",
                    "XSSX",
                    'L', "gemLavaCrystal",
                    'S', "stickWood"));
        }
        if (enableBowsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                    "XEES",
                    "EXXS",
                    "EXXS",
                    "XEES",
                    'E', "gemEmerald",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                    "SEEX",
                    "SXXE",
                    "SXXE",
                    "SEEX",
                    'E', "gemEmerald",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                    "XOOS",
                    "OXXS",
                    "OXXS",
                    "XOOS",
                    'O', "obsidian",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                    "SOOX",
                    "SXXO",
                    "SXXO",
                    "SOOX",
                    'O', "obsidian",
                    'S', "C"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                    "XLLS",
                    "LXXS",
                    "LXXS",
                    "XLLS",
                    'L', "gemLavaCrystal",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                    "SLLX",
                    "SXXL",
                    "SXXL",
                    "SLLX",
                    'L', "gemLavaCrystal",
                    'S', "string"));
        }
        if (enableSwordsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "XXXE",
                    "XXXE",
                    "XXXE",
                    "XXXS",
                    'E', "blockEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "XXXO",
                    "XXXO",
                    "XXXO",
                    "XXXS",
                    'O', "blockCompressedObsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "XXXL",
                    "XXXL",
                    "XXXL",
                    "XXXS",
                    'L', "gemChargedLavaCrystal",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "XXEX",
                    "XXEX",
                    "XXEX",
                    "XXSX",
                    'E', "blockEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "XXOX",
                    "XXOX",
                    "XXOX",
                    "XXSX",
                    'O', "blockCompressedObsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "XXLX",
                    "XXLX",
                    "XXLX",
                    "XXSX",
                    'L', "gemChargedLavaCrystal",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "XEXX",
                    "XEXX",
                    "XEXX",
                    "XSXX",
                    'E', "blockEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "XOXX",
                    "XOXX",
                    "XOXX",
                    "XSXX",
                    'O', "blockCompressedObsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "XLXX",
                    "XLXX",
                    "XLXX",
                    "XSXX",
                    'L', "gemChargedLavaCrystal",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldSword, 1),
                    "EXXX",
                    "EXXX",
                    "EXXX",
                    "SXXX",
                    'E', "blockEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianSword, 1),
                    "OXXX",
                    "OXXX",
                    "OXXX",
                    "SXXX",
                    'O', "blockCompressedObsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaSword, 1),
                    "LXXX",
                    "LXXX",
                    "LXXX",
                    "LXXX",
                    'L', "gemChargedLavaCrystal",
                    'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBattleAxe, 1),
                    "EXXE",
                    "ESSE",
                    "ESSE",
                    "XSSX",
                    'E', "blockEmerald",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBattleAxe, 1),
                    "OXXO",
                    "OSSO",
                    "OSSO",
                    "XSSX",
                    'O', "blockCompressedObsidian",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBattleAxe, 1),
                    "LXXL",
                    "LSSL",
                    "LSSL",
                    "XSSX",
                    'L', "gemChargedLavaCrystal",
                    'S', "stickWood"));
        }
        if (enableBowsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                    "XEES",
                    "EXXS",
                    "EXXS",
                    "XEES",
                    'E', "blockEmerald",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBow, 1),
                    "SEEX",
                    "SXXE",
                    "SXXE",
                    "SEEX",
                    'E', "blockEmerald",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                    "XOOS",
                    "OXXS",
                    "OXXS",
                    "XOOS",
                    'O', "blockCompressedObsidian",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBow, 1),
                    "SOOX",
                    "SXXO",
                    "SXXO",
                    "SOOX",
                    'O', "blockCompressedObsidian",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                    "XLLS",
                    "LXXS",
                    "LXXS",
                    "XLLS",
                    'L', "gemChargedLavaCrystal",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBow, 1),
                    "SLLX",
                    "SXXL",
                    "SXXL",
                    "SLLX",
                    'L', "gemChargedLavaCrystal",
                    'S', "string"));
        }
    }
}
