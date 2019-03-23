/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.ModBlocks.*;
import static net.minecraft.init.Blocks.STONEBRICK;
import static net.minecraft.init.Items.ARROW;
import static net.minecraft.init.Items.COAL;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;
import static net.minecraftforge.oredict.OreDictionary.registerOre;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModOreDicts {

    public static String[] colors = new String[]{"White", "Red", "Black", "Blue", "Green", "Yellow", "Purple"};

    public static void registerOreDictEntries() {
        //Ingots
        registerOre("ingotSteel", ModItems.steelIngot);
        registerOre("ingotElectrical", ModItems.electricalIngot);
        registerOre("gemLavaCrystal", ModItems.itemLavaCrystal);
        registerOre("ingotLavaCrystal", ModItems.itemLavaCrystal);
        registerWildOre("gemChargedLavaCrystal", ModItems.itemLavaCrystal, 1);
        registerWildOre("ingotChargedLavaCrystal", ModItems.itemLavaCrystal, 1);
        registerWildOre("gemInfusedLavaCrystal", ModItems.itemLavaCrystal, 1);
        registerWildOre("ingotInfusedLavaCrystal", ModItems.itemLavaCrystal, 1);
        //Blocks
        registerOre("oreLavaCrystal", oreLavaCrystal);
        registerOre("blockLavaCrystal", blockLavaCrystal);
        registerOre("blockInfusedLavaCrystal", blockInfusedLavaCrystal);
        registerOre("blockCompressedLavaCrystal", blockCompressedLavaCrystal);
        registerOre("blockCompressedInfusedLavaCrystal", blockCompressedInfusedLavaCrystal);
        registerOre("blockSteel", steelBlock);
        registerOre("blockElectrical", electricalBlock);
        registerOre("blockCompressedObsidian", blockCompressedObsidian);
        registerOre("blockLavaInfusedObsidian", blockLavaInfusedObsidian);
        registerOre("blockInfusedObsidian", blockLavaInfusedObsidian);
        //Benches
        OreDictionary.registerOre("apWorkbench", APBlocks.workbench);
        OreDictionary.registerOre("workbenchTier1", APBlocks.workbench);
        OreDictionary.registerOre("workbenchTierOne", APBlocks.workbench);
        OreDictionary.registerOre("apHighTechBench", APBlocks.highTechBench);
        OreDictionary.registerOre("workbenchTier2", APBlocks.highTechBench);
        OreDictionary.registerOre("workbenchTierTwo", APBlocks.highTechBench);
        OreDictionary.registerOre("apUltiTechBench", APBlocks.ultiTechBench);
        OreDictionary.registerOre("workbenchTier3", APBlocks.ultiTechBench);
        OreDictionary.registerOre("workbenchTierThree", APBlocks.ultiTechBench);
        OreDictionary.registerOre("apChampionBench", APBlocks.championBench);
        OreDictionary.registerOre("workbenchTier4", APBlocks.championBench);
        OreDictionary.registerOre("workbenchTierFour", APBlocks.championBench);

        //CastleBlocks
        IntStream.range(0, colors.length).forEachOrdered(index -> {
            registerWildBrick(colors[index], stoneBricks[index]);
            registerWildBrick(colors[index] + "Corner", stoneBrickCorners[index]);
            registerWildBrick(colors[index] + "Tower", stoneBrickTowers[index]);
            registerWildBrick(colors[index] + "Wall", stonebrickWalls[index]);
        });
        //Materials
        registerWildOre("chainmail", ModItems.materials, 0);
        registerWildOre("scaleGuardian", ModItems.materials, 1);
        registerWildOre("guardianScale", ModItems.materials, 1);
        registerWildOre("witherBone", ModItems.materials, 2);
        registerWildOre("scaleEnderDragon", ModItems.materials, 3);
        registerWildOre("enderDragonScale", ModItems.materials, 3);
        registerWildOre("dragonScale", ModItems.materials, 3);
        registerWildOre("materialTheUltimate", ModItems.materials, 4);
        registerWildOre("materialUltimate", ModItems.materials, 4);
        //Vanilla
        registerOre("itemCoal", COAL);
        registerOre("coal", COAL);
        registerWildOre("itemCharcoal", COAL, 1);
        registerOre("itemArrow", ARROW);
        registerOre("arrow", ARROW);
        registerWildBrick(STONEBRICK, WILDCARD_VALUE);
    }

    private static void registerWildOre(String name, Object object, int wildCardValiue) {
        if (object instanceof Item || object instanceof Block) {
            registerOre(name, getItemStack(object, wildCardValiue));
        }
    }

    private static void registerWildBrick(Block block, int wildCardValue) {
        registerWildOre("stonebrick", block, wildCardValue);
    }

    private static void registerWildBrick(String color, Block block) {
        registerOre("stonebrick" + color, block);
    }
}
