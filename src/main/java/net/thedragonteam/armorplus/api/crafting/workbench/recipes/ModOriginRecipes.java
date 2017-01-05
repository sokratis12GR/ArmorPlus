/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Coal Armor */
        if (APConfig.enableCoalArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[0], 1), "   ", "CCC", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[0], 1), "CCC", "C C", "   ", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[1], 1), "C C", "CCC", "CCC", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[2], 1), "CCC", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[3], 1), "   ", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[3], 1), "C C", "C C", "   ", 'C', "itemCoal"));
            }
            if (APConfig.gameMode == 0 && APConfig.enableCharcoalCoalArmorRecipe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[0], 1), "   ", "CCC", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[0], 1), "CCC", "C C", "   ", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[1], 1), "C C", "CCC", "CCC", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[2], 1), "CCC", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[3], 1), "   ", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[3], 1), "C C", "C C", "   ", 'C', "itemCharcoal"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[0], 1), "   ", "CCC", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[0], 1), "CCC", "C C", "   ", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[1], 1), "C C", "CCC", "CCC", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[2], 1), "CCC", "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[3], 1), "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal[3], 1), "C C", "C C", "   ", 'C', "blockCoal"));
            }
        }
        /* Lapis Armor */
        if (APConfig.enableLapisArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[0], 1), "   ", "LLL", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[0], 1), "LLL", "L L", "   ", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[1], 1), "L L", "LLL", "LLL", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[2], 1), "LLL", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[3], 1), "   ", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[3], 1), "L L", "L L", "   ", 'L', "gemLapis"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[0], 1), "   ", "LLL", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[0], 1), "LLL", "L L", "   ", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[1], 1), "L L", "LLL", "LLL", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[2], 1), "LLL", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[3], 1), "   ", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis[3], 1), "L L", "L L", "   ", 'L', "blockLapis"));
            }
        }
        /* Redstone Armor */
        if (APConfig.enableRedstoneArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[0], 1), "   ", "RRR", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[0], 1), "RRR", "R R", "   ", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[1], 1), "R R", "RRR", "RRR", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[2], 1), "RRR", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[3], 1), "   ", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[3], 1), "R R", "R R", "   ", 'R', "dustRedstone"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[0], 1), "   ", "RRR", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[0], 1), "RRR", "R R", "   ", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[1], 1), "R R", "RRR", "RRR", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[2], 1), "RRR", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[3], 1), "   ", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone[3], 1), "R R", "R R", "   ", 'R', "blockRedstone"));
            }
        }
        /* Emerald Armor */
        if (APConfig.enableEmeraldArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableEmeraldArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[0], 1), "   ", "EEE", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[0], 1), "EEE", "E E", "   ", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[1], 1), "E E", "EEE", "EEE", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[2], 1), "EEE", "E E", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[3], 1), "   ", "E E", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[3], 1), "E E", "E E", "   ", 'E', "gemEmerald"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableEmeraldArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[0], 1), "   ", "EEE", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[0], 1), "EEE", "E E", "   ", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[1], 1), "E E", "EEE", "EEE", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[2], 1), "EEE", "E E", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[3], 1), "   ", "E E", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald[3], 1), "E E", "E E", "   ", 'E', "blockEmerald"));
            }
        }
        /* Obsidian Armor */
        if (APConfig.enableObsidianArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableObsidianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[0], 1), "   ", "OOO", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[0], 1), "OOO", "O O", "   ", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[1], 1), "O O", "OOO", "OOO", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[2], 1), "OOO", "O O", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[3], 1), "   ", "O O", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[3], 1), "O O", "O O", "   ", 'O', "obsidian"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableObsidianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[0], 1), "   ", "OOO", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[0], 1), "OOO", "O O", "   ", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[1], 1), "O O", "OOO", "OOO", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[2], 1), "OOO", "O O", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[3], 1), "   ", "O O", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian[3], 1), "O O", "O O", "   ", 'O', "blockCompressedObsidian"));
            }
        }
        /* Lava Armor */
        if (APConfig.enableLavaArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableLavaArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[0], 1), "   ", "CCC", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[0], 1), "CCC", "C C", "   ", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[1], 1), "C C", "CCC", "CCC", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[2], 1), "CCC", "C C", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[3], 1), "   ", "C C", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[3], 1), "C C", "C C", "   ", 'C', "gemLavaCrystal"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableLavaArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[0], 1), "   ", "CCC", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[0], 1), "CCC", "C C", "   ", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[1], 1), "C C", "CCC", "CCC", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[2], 1), "CCC", "C C", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[3], 1), "   ", "C C", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lava[3], 1), "C C", "C C", "   ", 'C', "gemChargedLavaCrystal"));
            }
        }
    }
}