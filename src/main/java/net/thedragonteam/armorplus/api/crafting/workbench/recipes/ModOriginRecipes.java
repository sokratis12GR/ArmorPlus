/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.init.Items;
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
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "   ", "CCC", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "CCC", "C C", "   ", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalChestplate, 1), "C C", "CCC", "CCC", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "   ", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "C C", "C C", "   ", 'C', "itemCoal"));
            }
            if (APConfig.gameMode == 0 && APConfig.enableCharcoalCoalArmorRecipe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "   ", "CCC", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "CCC", "C C", "   ", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalChestplate, 1), "C C", "CCC", "CCC", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "   ", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "C C", "C C", "   ", 'C', "itemCharcoal"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "   ", "CCC", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "CCC", "C C", "   ", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalChestplate, 1), "C C", "CCC", "CCC", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "C C", "C C", "   ", 'C', "blockCoal"));
            }
        }
        /* Lapis Armor */
        if (APConfig.enableLapisArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "   ", "LLL", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "LLL", "L L", "   ", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisChestplate, 1), "L L", "LLL", "LLL", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisLeggings, 1), "LLL", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "   ", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "L L", "L L", "   ", 'L', "gemLapis"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "   ", "LLL", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "LLL", "L L", "   ", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisChestplate, 1), "L L", "LLL", "LLL", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisLeggings, 1), "LLL", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "   ", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "L L", "L L", "   ", 'L', "blockLapis"));
            }
        }
        /* Redstone Armor */
        if (APConfig.enableRedstoneArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "   ", "RRR", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "RRR", "R R", "   ", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneChestplate, 1), "R R", "RRR", "RRR", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneLeggings, 1), "RRR", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "   ", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "R R", "R R", "   ", 'R', "dustRedstone"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "   ", "RRR", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "RRR", "R R", "   ", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneChestplate, 1), "R R", "RRR", "RRR", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneLeggings, 1), "RRR", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "   ", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "R R", "R R", "   ", 'R', "blockRedstone"));
            }
        }
        /* Emerald Armor */
        if (APConfig.enableEmeraldArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableEmeraldArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "   ", "EEE", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "EEE", "E E", "   ", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldChestplate, 1), "E E", "EEE", "EEE", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldLeggings, 1), "EEE", "E E", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "   ", "E E", "E E", 'E', "gemEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "E E", "E E", "   ", 'E', "gemEmerald"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableEmeraldArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "   ", "EEE", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "EEE", "E E", "   ", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldChestplate, 1), "E E", "EEE", "EEE", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldLeggings, 1), "EEE", "E E", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "   ", "E E", "E E", 'E', "blockEmerald"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "E E", "E E", "   ", 'E', "blockEmerald"));
            }
        }
        /* Obsidian Armor */
        if (APConfig.enableObsidianArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableObsidianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "   ", "OOO", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "OOO", "O O", "   ", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianChestplate, 1), "O O", "OOO", "OOO", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianLeggings, 1), "OOO", "O O", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "   ", "O O", "O O", 'O', "obsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "O O", "O O", "   ", 'O', "obsidian"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableObsidianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "   ", "OOO", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "OOO", "O O", "   ", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianChestplate, 1), "O O", "OOO", "OOO", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianLeggings, 1), "OOO", "O O", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "   ", "O O", "O O", 'O', "blockCompressedObsidian"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "O O", "O O", "   ", 'O', "blockCompressedObsidian"));
            }
        }
        /* Lava Armor */
        if (APConfig.enableLavaArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableLavaArmorRecipes && APConfig.enableOldLavaArmorRecipes) {
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaHelmet, 1), ModItems.obsidianHelmet, 1, Items.LAVA_BUCKET, ModItems.obsidianHelmet, 1);
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaChestplate, 1), ModItems.obsidianChestplate, Items.LAVA_BUCKET, ModItems.obsidianChestplate);
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaLeggings, 1), ModItems.obsidianLeggings, Items.LAVA_BUCKET, ModItems.obsidianLeggings);
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaBoots, 1), ModItems.obsidianBoots, Items.LAVA_BUCKET, ModItems.obsidianBoots);
            }
            if (APConfig.gameMode == 1 && APConfig.enableLavaArmorRecipes && APConfig.enableOldLavaArmorRecipes) {
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaHelmet, 1), ModItems.obsidianHelmet, 1, Items.LAVA_BUCKET, ModItems.obsidianHelmet, 1,
                        Items.LAVA_BUCKET);
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaChestplate, 1), ModItems.obsidianChestplate, Items.LAVA_BUCKET, ModItems.obsidianChestplate,
                        Items.LAVA_BUCKET);
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaLeggings, 1), ModItems.obsidianLeggings, Items.LAVA_BUCKET, ModItems.obsidianLeggings,
                        Items.LAVA_BUCKET);
                manager.addShapelessRecipe(new ItemStack(ModItems.lavaBoots, 1), ModItems.obsidianBoots, Items.LAVA_BUCKET, ModItems.obsidianBoots,
                        Items.LAVA_BUCKET);
            }
            if (APConfig.gameMode == 0 && APConfig.enableLavaArmorRecipes && !APConfig.enableOldLavaArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "   ", "CCC", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "CCC", "C C", "   ", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaChestplate, 1), "C C", "CCC", "CCC", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaLeggings, 1), "CCC", "C C", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "   ", "C C", "C C", 'C', "gemLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "C C", "C C", "   ", 'C', "gemLavaCrystal"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableLavaArmorRecipes && !APConfig.enableOldLavaArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "   ", "CCC", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "CCC", "C C", "   ", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaChestplate, 1), "C C", "CCC", "CCC", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaLeggings, 1), "CCC", "C C", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "   ", "C C", "C C", 'C', "gemChargedLavaCrystal"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "C C", "C C", "   ", 'C', "gemChargedLavaCrystal"));
            }
        }
    }
}