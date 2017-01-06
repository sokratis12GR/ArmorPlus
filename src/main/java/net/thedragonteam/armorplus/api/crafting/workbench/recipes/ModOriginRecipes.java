/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Coal Armor */
        if (enableCoalArmor) {
            if (getRD() == EASY && enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "   ", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "itemCoal"));
            }
            if (getRD() == EASY && enableCharcoalCoalArmorRecipe) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "   ", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "itemCharcoal"));
            }
            if (getRD() == EXPERT && enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "blockCoal"));
            }
        }
        /* Lapis Armor */
        if (enableLapisArmor) {
            if (getRD() == EASY && enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "   ", "LLL", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "LLL", "L L", "   ", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisChestplate), "L L", "LLL", "LLL", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisLeggings), "LLL", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "   ", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "L L", "L L", "   ", 'L', "gemLapis"));
            }
            if (getRD() == EXPERT && enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "   ", "LLL", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "LLL", "L L", "   ", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisChestplate), "L L", "LLL", "LLL", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisLeggings), "LLL", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "   ", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "L L", "L L", "   ", 'L', "blockLapis"));
            }
        }
        /* Redstone Armor */
        if (enableRedstoneArmor) {
            if (getRD() == EASY && enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "   ", "RRR", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "RRR", "R R", "   ", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneChestplate), "R R", "RRR", "RRR", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneLeggings), "RRR", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "   ", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "R R", "R R", "   ", 'R', "dustRedstone"));
            }
            if (getRD() == EXPERT && enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "   ", "RRR", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "RRR", "R R", "   ", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneChestplate), "R R", "RRR", "RRR", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneLeggings), "RRR", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "   ", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "R R", "R R", "   ", 'R', "blockRedstone"));
            }
        }
        /* Emerald Armor */
        if (enableEmeraldArmor) {
            if (getRD() == EASY && enableEmeraldArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldHelmet), "   ", "EEE", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldHelmet), "EEE", "E E", "   ", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldChestplate), "E E", "EEE", "EEE", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldLeggings), "EEE", "E E", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBoots), "   ", "E E", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBoots), "E E", "E E", "   ", 'E', "gemEmerald"));
            }
            if (getRD() == EXPERT && enableEmeraldArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldHelmet), "   ", "EEE", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldHelmet), "EEE", "E E", "   ", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldChestplate), "E E", "EEE", "EEE", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldLeggings), "EEE", "E E", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBoots), "   ", "E E", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(emeraldBoots), "E E", "E E", "   ", 'E', "blockEmerald"));
            }
        }
        /* Obsidian Armor */
        if (enableObsidianArmor) {
            if (getRD() == EASY && enableObsidianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianHelmet), "   ", "OOO", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianHelmet), "OOO", "O O", "   ", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianChestplate), "O O", "OOO", "OOO", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianLeggings), "OOO", "O O", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBoots), "   ", "O O", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBoots), "O O", "O O", "   ", 'O', "obsidian"));
            }
            if (getRD() == EXPERT && enableObsidianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianHelmet), "   ", "OOO", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianHelmet), "OOO", "O O", "   ", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianChestplate), "O O", "OOO", "OOO", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianLeggings), "OOO", "O O", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBoots), "   ", "O O", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(obsidianBoots), "O O", "O O", "   ", 'O', "blockCompressedObsidian"));
            }
        }
        /* Lava Armor */
        if (enableLavaArmor) {
            if (getRD() == EASY && enableLavaArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaHelmet), "   ", "CCC", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaHelmet), "CCC", "C C", "   ", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaChestplate), "C C", "CCC", "CCC", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaLeggings), "CCC", "C C", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBoots), "   ", "C C", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBoots), "C C", "C C", "   ", 'C', "gemLavaCrystal"));
            }
            if (getRD() == EXPERT && enableLavaArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaHelmet), "   ", "CCC", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaHelmet), "CCC", "C C", "   ", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaChestplate), "C C", "CCC", "CCC", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaLeggings), "CCC", "C C", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBoots), "   ", "C C", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaBoots), "C C", "C C", "   ", 'C', "gemChargedLavaCrystal"));
            }
        }
    }
}