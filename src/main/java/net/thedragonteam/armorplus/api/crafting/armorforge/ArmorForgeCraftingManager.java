/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.armorforge;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * net.thedragonteam.armorplus.api.crafting.armorforge
 * ArmorPlus created by sokratis12GR on 6/19/2016 12:29PM.
 * - TheDragonTeam
 */
public class ArmorForgeCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final ArmorForgeCraftingManager INSTANCE = new ArmorForgeCraftingManager();
    private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

    private ArmorForgeCraftingManager() {

        // ===================================== Set Variables =====================================

        ItemStack coalHelmet = new ItemStack(ModItems.coalHelmet, 1);
        ItemStack emeraldHelmet = new ItemStack(ModItems.emeraldHelmet, 1);
        ItemStack lapisHelmet = new ItemStack(ModItems.lapisHelmet, 1);
        ItemStack lavaHelmet = new ItemStack(ModItems.lavaHelmet, 1);
        ItemStack obsidianHelmet = new ItemStack(ModItems.obsidianHelmet);
        ItemStack redstoneHelmet = new ItemStack(ModItems.redstoneHelmet, 1);
        ItemStack chickenHelmet = new ItemStack(ModItems.chickenHelmet, 1);
        ItemStack slimeHelmet = new ItemStack(ModItems.slimeHelmet, 1);
        ItemStack arditeHelmet = new ItemStack(ModItems.arditeHelmet, 1);
        ItemStack cobaltHelmet = new ItemStack(ModItems.cobaltHelmet, 1);
        ItemStack manyullynHelmet = new ItemStack(ModItems.manyullynHelmet, 1);
        ItemStack pigIronHelmet = new ItemStack(ModItems.pigIronHelmet, 1);
        ItemStack knightSlimeHelmet = new ItemStack(ModItems.knightSlimeHelmet, 1);
        ItemStack steelHelmet = new ItemStack(ModItems.steelHelmet, 1);
        ItemStack electricalHelmet = new ItemStack(ModItems.electricalHelmet, 1);
        // ===================================== Origin Armors =====================================
        /* Coal Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableCoalArmorRecipes) {
            this.addRecipe(coalHelmet, "XXX", "CCC", "CXC", 'C', Items.COAL);
            this.addRecipe(coalHelmet, "CCC", "CXC", "XXX", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.coalChestplate, 1), "CXC", "CCC", "CCC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "CXC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.coalBoots, 1), "XXX", "CXC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", "XXX", 'C', Items.COAL);
        }
        if (ARPConfig.recipes == 0 && ARPConfig.enableCharcoalCoalArmorRecipe) {
            this.addRecipe(coalHelmet, "XXX", "CCC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(coalHelmet, "CCC", "CXC", "XXX", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.coalChestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.coalBoots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Items.COAL, 1, 1));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableCoalArmorRecipes) {
            this.addRecipe(coalHelmet, "XXX", "CCC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(coalHelmet, "CCC", "CXC", "XXX", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.coalChestplate, 1), "CXC", "CCC", "CCC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "CXC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", "XXX", 'C', Blocks.COAL_BLOCK);
        }
        /* Emerald Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableEmeraldArmorRecipes) {
            this.addRecipe(emeraldHelmet, "XXX", "EEE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(emeraldHelmet, "EEE", "EXE", "XXX", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.emeraldChestplate, 1), "EXE", "EEE", "EEE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.emeraldLeggings, 1), "EEE", "EXE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.emeraldBoots, 1), "XXX", "EXE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.emeraldBoots, 1), "EXE", "EXE", "XXX", 'E', Items.EMERALD);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableEmeraldArmorRecipes) {
            this.addRecipe(emeraldHelmet, "XXX", "EEE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(emeraldHelmet, "EEE", "EXE", "XXX", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.emeraldChestplate, 1), "EXE", "EEE", "EEE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.emeraldLeggings, 1), "EEE", "EXE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.emeraldBoots, 1), "XXX", "EXE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.emeraldBoots, 1), "EXE", "EXE", "XXX", 'E', Blocks.EMERALD_BLOCK);
        }
        /* Lapis Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableLapisArmorRecipes) {
            ItemStack lapisLazuli = new ItemStack(Items.DYE, 1, 4);
            this.addRecipe(lapisHelmet, "XXX", "LLL", "LXL", 'L', lapisLazuli);
            this.addRecipe(lapisHelmet, "LLL", "LXL", "XXX", 'L', lapisLazuli);
            this.addRecipe(new ItemStack(ModItems.lapisChestplate, 1), "LXL", "LLL", "LLL", 'L', lapisLazuli);
            this.addRecipe(new ItemStack(ModItems.lapisLeggings, 1), "LLL", "LXL", "LXL", 'L', lapisLazuli);
            this.addRecipe(new ItemStack(ModItems.lapisBoots, 1), "XXX", "LXL", "LXL", 'L', lapisLazuli);
            this.addRecipe(new ItemStack(ModItems.lapisBoots, 1), "LXL", "LXL", "XXX", 'L', lapisLazuli);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLapisArmorRecipes) {
            this.addRecipe(lapisHelmet, "XXX", "LLL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(lapisHelmet, "LLL", "LXL", "XXX", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.lapisChestplate, 1), "LXL", "LLL", "LLL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.lapisLeggings, 1), "LLL", "LXL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.lapisBoots, 1), "XXX", "LXL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.lapisBoots, 1), "LXL", "LXL", "XXX", 'L', Blocks.LAPIS_BLOCK);
        }
        /* Lava Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableLavaArmorRecipes && ARPConfig.enableOldLavaArmorRecipes) {
            this.addShapelessRecipe(lavaHelmet, ModItems.obsidianHelmet, Items.LAVA_BUCKET, ModItems.obsidianHelmet);
            this.addShapelessRecipe(new ItemStack(ModItems.lavaChestplate, 1), ModItems.obsidianChestplate, Items.LAVA_BUCKET, ModItems.obsidianChestplate);
            this.addShapelessRecipe(new ItemStack(ModItems.lavaLeggings, 1), ModItems.obsidianLeggings, Items.LAVA_BUCKET, ModItems.obsidianLeggings);
            this.addShapelessRecipe(new ItemStack(ModItems.lavaBoots, 1), ModItems.obsidianBoots, Items.LAVA_BUCKET, ModItems.obsidianBoots);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLavaArmorRecipes && ARPConfig.enableOldLavaArmorRecipes) {
            this.addShapelessRecipe(lavaHelmet, ModItems.obsidianHelmet, Items.LAVA_BUCKET, ModItems.obsidianHelmet,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(ModItems.lavaChestplate, 1), ModItems.obsidianChestplate, Items.LAVA_BUCKET, ModItems.obsidianChestplate,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(ModItems.lavaLeggings, 1), ModItems.obsidianLeggings, Items.LAVA_BUCKET, ModItems.obsidianLeggings,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(ModItems.lavaBoots, 1), ModItems.obsidianBoots, Items.LAVA_BUCKET, ModItems.obsidianBoots,
                    Items.LAVA_BUCKET);
        }
        if (ARPConfig.recipes == 0 && ARPConfig.enableLavaArmorRecipes && !ARPConfig.enableOldLavaArmorRecipes) {
            this.addRecipe(lavaHelmet, "XXX", "CCC", "CXC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(lavaHelmet, "CCC", "CXC", "XXX", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaChestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaLeggings, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaBoots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaBoots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLavaArmorRecipes && !ARPConfig.enableOldLavaArmorRecipes) {
            this.addRecipe(lavaHelmet, "XXX", "CCC", "CXC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(lavaHelmet, "CCC", "CXC", "XXX", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaChestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaLeggings, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaBoots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addRecipe(new ItemStack(ModItems.lavaBoots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
        }
        /* Obsidian Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableObsidianArmorRecipes) {
            this.addRecipe(obsidianHelmet, "XXX", "OOO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(obsidianHelmet, "OOO", "OXO", "XXX", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.obsidianChestplate, 1), "OXO", "OOO", "OOO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.obsidianLeggings, 1), "OOO", "OXO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.obsidianBoots, 1), "XXX", "OXO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.obsidianBoots, 1), "OXO", "OXO", "XXX", 'O', Blocks.OBSIDIAN);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableObsidianArmorRecipes) {
            this.addRecipe(obsidianHelmet, "XXX", "OOO", "OXO", 'O', ModBlocks.compressedObsidian);
            this.addRecipe(obsidianHelmet, "OOO", "OXO", "XXX", 'O', ModBlocks.compressedObsidian);
            this.addRecipe(new ItemStack(ModItems.obsidianChestplate, 1), "OXO", "OOO", "OOO", 'O', ModBlocks.compressedObsidian);
            this.addRecipe(new ItemStack(ModItems.obsidianLeggings, 1), "OOO", "OXO", "OXO", 'O', ModBlocks.compressedObsidian);
            this.addRecipe(new ItemStack(ModItems.obsidianBoots, 1), "XXX", "OXO", "OXO", 'O', ModBlocks.compressedObsidian);
            this.addRecipe(new ItemStack(ModItems.obsidianBoots, 1), "OXO", "OXO", "XXX", 'O', ModBlocks.compressedObsidian);
        }
        /* Redstone Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableRedstoneArmorRecipes) {
            this.addRecipe(redstoneHelmet, "XXX", "RRR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(redstoneHelmet, "RRR", "RXR", "XXX", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.redstoneChestplate, 1), "RXR", "RRR", "RRR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.redstoneLeggings, 1), "RRR", "RXR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.redstoneBoots, 1), "XXX", "RXR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.redstoneBoots, 1), "RXR", "RXR", "XXX", 'R', Items.REDSTONE);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableRedstoneArmorRecipes) {
            this.addRecipe(redstoneHelmet, "XXX", "RRR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(redstoneHelmet, "RRR", "RXR", "XXX", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.redstoneChestplate, 1), "RXR", "RRR", "RRR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.redstoneLeggings, 1), "RRR", "RXR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.redstoneBoots, 1), "XXX", "RXR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.redstoneBoots, 1), "RXR", "RXR", "XXX", 'R', Blocks.REDSTONE_BLOCK);
        }
        // ===================================== Special Mob Armors =====================================
        /* Chicken Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableChickenArmorRecipes) {
            this.addRecipe(chickenHelmet, "XXX", "FFF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(chickenHelmet, "FFF", "FXF", "XXX", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.chickenChestplate, 1), "FXF", "FFF", "FFF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.chickenLeggings, 1), "FFF", "FXF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.chickenBoots, 1), "XXX", "FXF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.chickenBoots, 1), "FXF", "FXF", "XXX", 'F', Items.FEATHER);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableChickenArmorRecipes) {
            this.addRecipe(chickenHelmet, "XXX", "FFF", "EXE", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(chickenHelmet, "FFF", "EXE", "XXX", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.chickenChestplate, 1), "EXE", "FEF", "FFF", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.chickenLeggings, 1), "EFE", "FXF", "FXF", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.chickenBoots, 1), "XXX", "FXF", "EXE", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.chickenBoots, 1), "FXF", "EXE", "XXX", 'F', Items.FEATHER, 'E', Items.EGG);
        }
        /* Slime Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableSlimeArmorRecipes) {
            this.addRecipe(slimeHelmet, "XXX", "SSS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(slimeHelmet, "SSS", "SXS", "XXX", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.slimeChestplate, 1), "SXS", "SSS", "SSS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.slimeLeggings, 1), "SSS", "SXS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.slimeBoots, 1), "XXX", "SXS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.slimeBoots, 1), "SXS", "SXS", "XXX", 'S', Items.SLIME_BALL);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableSlimeArmorRecipes) {
            this.addRecipe(slimeHelmet, "XXX", "SSS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(slimeHelmet, "SSS", "SXS", "XXX", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.slimeChestplate, 1), "SXS", "SSS", "SSS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.slimeLeggings, 1), "SSS", "SXS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.slimeBoots, 1), "XXX", "SXS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.slimeBoots, 1), "SXS", "SXS", "XXX", 'S', Blocks.SLIME_BLOCK);
        }
        // ===================================== Tinkers' Construct Armors =====================================
        /* Ardite Armor */
        ItemStack arditeIngot = new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1);
        if (ARPConfig.recipes == 0 && ARPConfig.enableArditeArmorRecipes) {
            this.addRecipe(arditeHelmet, "XXX", "CCC", "CXC", 'C', arditeIngot);
            this.addRecipe(arditeHelmet, "CCC", "CXC", "XXX", 'C', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeChestplate, 1), "CXC", "CCC", "CCC", 'C', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeLeggings, 1), "CCC", "CXC", "CXC", 'C', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeBoots, 1), "XXX", "CXC", "CXC", 'C', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeBoots, 1), "CXC", "CXC", "XXX", 'C', arditeIngot);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableArditeArmorRecipes) {
            ItemStack arditeBlock = new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1);
            this.addRecipe(arditeHelmet, "XXX", "CCC", "AXA", 'C', arditeBlock, 'A', arditeIngot);
            this.addRecipe(arditeHelmet, "CCC", "AXA", "XXX", 'C', arditeBlock, 'A', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeChestplate, 1), "AXA", "CAC", "CCC", 'C', arditeBlock, 'A', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeLeggings, 1), "CAC", "CXC", "AXA", 'C', arditeBlock, 'A', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeBoots, 1), "XXX", "CXC", "AXA", 'C', arditeBlock, 'A', arditeIngot);
            this.addRecipe(new ItemStack(ModItems.arditeBoots, 1), "CXC", "AXA", "XXX", 'C', arditeBlock, 'A', arditeIngot);
        }
        /* Cobalt Armor */
        ItemStack cobaltIngot = new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0);
        if (ARPConfig.recipes == 0 && ARPConfig.enableCobaltArmorRecipes) {
            this.addRecipe(cobaltHelmet, "XXX", "CCC", "CXC", 'C', cobaltIngot);
            this.addRecipe(cobaltHelmet, "CCC", "CXC", "XXX", 'C', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltChestplate, 1), "CXC", "CCC", "CCC", 'C', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltLeggings, 1), "CCC", "CXC", "CXC", 'C', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltBoots, 1), "XXX", "CXC", "CXC", 'C', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltBoots, 1), "CXC", "CXC", "XXX", 'C', cobaltIngot);
        }
        ItemStack cobaltBlock = new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0);
        if (ARPConfig.recipes == 1 && ARPConfig.enableCobaltArmorRecipes) {
            this.addRecipe(cobaltHelmet, "XXX", "CCC", "AXA", 'C', cobaltBlock, 'A', cobaltIngot);
            this.addRecipe(cobaltHelmet, "CCC", "AXA", "XXX", 'C', cobaltBlock, 'A', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltChestplate, 1), "AXA", "CAC", "CCC", 'C', cobaltBlock, 'A', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltLeggings, 1), "CAC", "CXC", "AXA", 'C', cobaltBlock, 'A', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltBoots, 1), "XXX", "CXC", "AXA", 'C', cobaltBlock, 'A', cobaltIngot);
            this.addRecipe(new ItemStack(ModItems.cobaltBoots, 1), "CXC", "AXA", "XXX", 'C', cobaltBlock, 'A', cobaltIngot);
        }
        /* Knight Slime Armor */
        ItemStack knightSlimeIngot = new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3);
        if (ARPConfig.recipes == 0 && ARPConfig.enableKnightSlimeArmorRecipes) {
            this.addRecipe(knightSlimeHelmet, "XXX", "CCC", "CXC", 'C', knightSlimeIngot);
            this.addRecipe(knightSlimeHelmet, "CCC", "CXC", "XXX", 'C', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1), "CXC", "CCC", "CCC", 'C', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1), "CCC", "CXC", "CXC", 'C', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "XXX", "CXC", "CXC", 'C', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "CXC", "CXC", "XXX", 'C', knightSlimeIngot);
        }
        ItemStack knightSlimeBlock = new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3);
        if (ARPConfig.recipes == 1 && ARPConfig.enableKnightSlimeArmorRecipes) {
            this.addRecipe(knightSlimeHelmet, "XXX", "CCC", "AXA", 'C', knightSlimeBlock, 'A', knightSlimeIngot);
            this.addRecipe(knightSlimeHelmet, "CCC", "AXA", "XXX", 'C', knightSlimeBlock, 'A', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1), "AXA", "CAC", "CCC", 'C', knightSlimeBlock, 'A', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1), "CAC", "CXC", "AXA", 'C', knightSlimeBlock, 'A', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "XXX", "CXC", "AXA", 'C', knightSlimeBlock, 'A', knightSlimeIngot);
            this.addRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "CXC", "AXA", "XXX", 'C', knightSlimeBlock, 'A', knightSlimeIngot);
        }
        /* Manyullyn Armor */
        ItemStack manyullynIngot = new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2);
        if (ARPConfig.recipes == 0 && ARPConfig.enableManyullynArmorRecipes) {
            this.addRecipe(manyullynHelmet, "XXX", "CCC", "CXC", 'C', manyullynIngot);
            this.addRecipe(manyullynHelmet, "CCC", "CXC", "XXX", 'C', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynChestplate, 1), "CXC", "CCC", "CCC", 'C', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynLeggings, 1), "CCC", "CXC", "CXC", 'C', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynBoots, 1), "XXX", "CXC", "CXC", 'C', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynBoots, 1), "CXC", "CXC", "XXX", 'C', manyullynIngot);
        }
        ItemStack manyullynBlock = new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2);
        if (ARPConfig.recipes == 1 && ARPConfig.enableManyullynArmorRecipes) {
            this.addRecipe(manyullynHelmet, "XXX", "CCC", "AXA", 'C', manyullynBlock, 'A', manyullynIngot);
            this.addRecipe(manyullynHelmet, "CCC", "AXA", "XXX", 'C', manyullynBlock, 'A', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynChestplate, 1), "AXA", "CAC", "CCC", 'C', manyullynBlock, 'A', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynLeggings, 1), "CCC", "CXC", "AXA", 'C', manyullynBlock, 'A', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynBoots, 1), "XXX", "CXC", "AXA", 'C', manyullynBlock, 'A', manyullynIngot);
            this.addRecipe(new ItemStack(ModItems.manyullynBoots, 1), "CXC", "AXA", "XXX", 'C', manyullynBlock, 'A', manyullynIngot);
        }
        /* Pig Iron Armor */
        ItemStack pigIronIngot = new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4);
        if (ARPConfig.recipes == 0 && ARPConfig.enablePigIronArmorRecipes) {
            this.addRecipe(pigIronHelmet, "XXX", "CCC", "CXC", 'C', pigIronIngot);
            this.addRecipe(pigIronHelmet, "CCC", "CXC", "XXX", 'C', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronChestplate, 1), "CXC", "CCC", "CCC", 'C', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronLeggings, 1), "CCC", "CXC", "CXC", 'C', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronBoots, 1), "XXX", "CXC", "CXC", 'C', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronBoots, 1), "CXC", "CXC", "XXX", 'C', pigIronIngot);
        }
        ItemStack pigIronBlock = new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4);
        if (ARPConfig.recipes == 1 && ARPConfig.enablePigIronArmorRecipes) {
            this.addRecipe(pigIronHelmet, "XXX", "CCC", "AXA", 'C', pigIronBlock, 'A', pigIronIngot);
            this.addRecipe(pigIronHelmet, "CCC", "AXA", "XXX", 'C', pigIronBlock, 'A', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronChestplate, 1), "AXA", "CAC", "CCC", 'C', pigIronBlock, 'A', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronLeggings, 1), "CCC", "CXC", "AXA", 'C', pigIronBlock, 'A', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronBoots, 1), "XXX", "CXC", "AXA", 'C', pigIronBlock, 'A', pigIronIngot);
            this.addRecipe(new ItemStack(ModItems.pigIronBoots, 1), "CXC", "AXA", "XXX", 'C', pigIronBlock, 'A', pigIronIngot);
        }
        // ===================================== v2 Armors =====================================
        this.addShapelessRecipe(new ItemStack(ModItems.electricalIngot, 1), ModItems.steelIngot, Items.REDSTONE, Items.GLOWSTONE_DUST);
        /* Metal Armor */
        if (ARPConfig.recipes == 0) {
            this.addRecipe(steelHelmet, "XXX", "MMM", "MXM", 'M', ModItems.steelIngot);
            this.addRecipe(steelHelmet, "MMM", "MXM", "XXX", 'M', ModItems.steelIngot);
            this.addRecipe(new ItemStack(ModItems.steelChestplate, 1), "MXM", "MMM", "MMM", 'M', ModItems.steelIngot);
            this.addRecipe(new ItemStack(ModItems.steelLeggings, 1), "MMM", "MXM", "MXM", 'M', ModItems.steelIngot);
            this.addRecipe(new ItemStack(ModItems.steelBoots, 1), "XXX", "MXM", "MXM", 'M', ModItems.steelIngot);
            this.addRecipe(new ItemStack(ModItems.steelBoots, 1), "MXM", "MXM", "XXX", 'M', ModItems.steelIngot);
        }
        if (ARPConfig.recipes == 1) {
            this.addRecipe(steelHelmet, "XXX", "MMM", "MXM", 'M', ModBlocks.steelBlock);
            this.addRecipe(steelHelmet, "MMM", "MXM", "XXX", 'M', ModBlocks.steelBlock);
            this.addRecipe(new ItemStack(ModItems.steelChestplate, 1), "MXM", "MMM", "MMM", 'M', ModBlocks.steelBlock);
            this.addRecipe(new ItemStack(ModItems.steelLeggings, 1), "MMM", "MXM", "MXM", 'M', ModBlocks.steelBlock);
            this.addRecipe(new ItemStack(ModItems.steelBoots, 1), "XXX", "MXM", "MXM", 'M', ModBlocks.steelBlock);
            this.addRecipe(new ItemStack(ModItems.steelBoots, 1), "MXM", "MXM", "XXX", 'M', ModBlocks.steelBlock);
        }
        /* Electrical Armor */
        if (ARPConfig.recipes == 0) {
            this.addRecipe(electricalHelmet, "XXX", "EEE", "EXE", 'E', ModItems.electricalIngot);
            this.addRecipe(electricalHelmet, "EEE", "EXE", "XXX", 'E', ModItems.electricalIngot);
            this.addRecipe(new ItemStack(ModItems.electricalChestplate, 1), "EXE", "EEE", "EEE", 'E', ModItems.electricalIngot);
            this.addRecipe(new ItemStack(ModItems.electricalLeggings, 1), "EEE", "EXE", "EXE", 'E', ModItems.electricalIngot);
            this.addRecipe(new ItemStack(ModItems.electricalBoots, 1), "XXX", "EXE", "EXE", 'E', ModItems.electricalIngot);
            this.addRecipe(new ItemStack(ModItems.electricalBoots, 1), "EXE", "EXE", "XXX", 'E', ModItems.electricalIngot);
        }
        if (ARPConfig.recipes == 1) {
            this.addRecipe(electricalHelmet, "XXX", "EEE", "EXE", 'E', ModBlocks.electricalBlock);
            this.addRecipe(electricalHelmet, "EEE", "EXE", "XXX", 'E', ModBlocks.electricalBlock);
            this.addRecipe(new ItemStack(ModItems.electricalChestplate, 1), "EXE", "EEE", "EEE", 'E', ModBlocks.electricalBlock);
            this.addRecipe(new ItemStack(ModItems.electricalLeggings, 1), "EEE", "EXE", "EXE", 'E', ModBlocks.electricalBlock);
            this.addRecipe(new ItemStack(ModItems.electricalBoots, 1), "XXX", "EXE", "EXE", 'E', ModBlocks.electricalBlock);
            this.addRecipe(new ItemStack(ModItems.electricalBoots, 1), "EXE", "EXE", "XXX", 'E', ModBlocks.electricalBlock);
        }

        // ===================================== Items =====================================
        this.addRecipe(new ItemStack(ModItems.theGiftOfTheGods, 1), "SOS", "OLO", "SOS", 'S', new ItemStack(Items.NETHER_STAR, 1), 'O', new ItemStack(Blocks.OBSIDIAN), 'L', new ItemStack(ModItems.lavaCrystal, 1, 1));
        //this.addRecipe(new ItemStack(Blocks.TNT, 1), new Object[]{"X#X", "#X#", "X#X", 'X', Items.GUNPOWDER, '#', Blocks.SAND});
        Collections.sort(this.recipes, new Comparator<IRecipe>() {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
                return p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes ? 1 : (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes ? -1 : (p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() ? -1 : (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0)));
            }
        });
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static ArmorForgeCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public ShapedRecipes addRecipe(ItemStack stack, Object... recipeComponents) {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[]) {
            String[] astring = (String[]) ((String[]) recipeComponents[i++]);

            for (int l = 0; l < astring.length; ++l) {
                String s2 = astring[l];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        } else {
            while (recipeComponents[i] instanceof String) {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2) {
            Character character = (Character) recipeComponents[i];
            ItemStack itemstack = null;

            if (recipeComponents[i + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[i + 1]);
            } else if (recipeComponents[i + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            } else if (recipeComponents[i + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1) {
            char c0 = s.charAt(i1);

            if (map.containsKey(Character.valueOf(c0))) {
                aitemstack[i1] = ((ItemStack) map.get(Character.valueOf(c0))).copy();
            } else {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */

    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.<ItemStack>newArrayList();

        for (Object object : recipeComponents) {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item) object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block) object));
            }
        }

        this.recipes.add(new ShapelessRecipes(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    @Nullable
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getCraftingResult(craftMatrix);
            }
        }

        return null;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        ItemStack[] aitemstack = new ItemStack[craftMatrix.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            aitemstack[i] = craftMatrix.getStackInSlot(i);
        }

        return aitemstack;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}