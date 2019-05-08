/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModOreDicts {

    public static String[] colors = new String[]{"White", "Red", "Black", "Blue", "Green", "Yellow", "Purple"};

    public static void registerOreDictEntries() {
        //     //Ingots
        //     registerOre("ingotSteel", ModItems.steelIngot);
        //     registerOre("ingotElectrical", ModItems.electricalIngot);
        //     registerOre("gemLavaCrystal", ModItems.itemLavaCrystal);
        //     registerOre("ingotLavaCrystal", ModItems.itemLavaCrystal);
        //     registerWildOre("gemChargedLavaCrystal", ModItems.itemInfusedLavaCrystal);
        //     registerWildOre("ingotChargedLavaCrystal", ModItems.itemInfusedLavaCrystal);
        //     registerWildOre("gemInfusedLavaCrystal", ModItems.itemInfusedLavaCrystal);
        //     registerWildOre("ingotInfusedLavaCrystal", ModItems.itemInfusedLavaCrystal);
        //     //Blocks
        //     registerOre("oreLavaCrystal", blockCrystalOre);
        //     registerOre("blockLavaCrystal", blockLavaCrystal);
        //     registerOre("blockInfusedLavaCrystal", blockInfusedLavaCrystal);
        //     registerOre("blockCompressedLavaCrystal", blockCompressedLavaCrystal);
        //     registerOre("blockCompressedInfusedLavaCrystal", blockCompressedInfusedLavaCrystal);
        //     registerOre("blockSteel", steelBlock);
        //     registerOre("blockElectrical", electricalBlock);
        //     registerOre("blockCompressedObsidian", blockCompressedObsidian);
        //     registerOre("blockLavaInfusedObsidian", blockLavaInfusedObsidian);
        //     registerOre("blockInfusedObsidian", blockLavaInfusedObsidian);
        //     //Benches
        //     OreDictionary.registerOre("apWorkbench", APBlocks.workbench);
        //     OreDictionary.registerOre("workbenchTier1", APBlocks.workbench);
        //     OreDictionary.registerOre("workbenchTierOne", APBlocks.workbench);
        //     OreDictionary.registerOre("apHighTechBench", APBlocks.highTechBench);
        //     OreDictionary.registerOre("workbenchTier2", APBlocks.highTechBench);
        //     OreDictionary.registerOre("workbenchTierTwo", APBlocks.highTechBench);
        //     OreDictionary.registerOre("apUltiTechBench", APBlocks.ultiTechBench);
        //     OreDictionary.registerOre("workbenchTier3", APBlocks.ultiTechBench);
        //     OreDictionary.registerOre("workbenchTierThree", APBlocks.ultiTechBench);
        //     OreDictionary.registerOre("apChampionBench", APBlocks.championBench);
        //     OreDictionary.registerOre("workbenchTier4", APBlocks.championBench);
        //     OreDictionary.registerOre("workbenchTierFour", APBlocks.championBench);

        //CastleBlocks
        //     IntStream.range(0, colors.length).forEachOrdered(index -> {
        //         registerWildBrick(colors[index], stoneBricks[index]);
        //         registerWildBrick(colors[index] + "Corner", stoneBrickCorners[index]);
        //         registerWildBrick(colors[index] + "Tower", stoneBrickTowers[index]);
        //         registerWildBrick(colors[index] + "Wall", stonebrickWalls[index]);
        //     });
        //Materials
        // registerWildOre("chainmail", ModItems.materials, 0);
        // registerWildOre("scaleGuardian", guardianScale);
        // registerWildOre("guardianScale", guardianScale);
        // registerWildOre("witherBone", witherBone);
        // registerWildOre("scaleEnderDragon", enderDragonScale);
        // registerWildOre("enderDragonScale", enderDragonScale);
        // registerWildOre("dragonScale", enderDragonScale);
        // registerWildOre("materialTheUltimate", ModItems.theUltimateMaterial);
        // registerWildOre("materialUltimate", ModItems.theUltimateMaterial);
        //Vanilla
        //      registerOre("itemCoal", COAL);
        //      registerOre("coal", COAL);
        //      registerWildOre("itemCharcoal", COAL, 1);
        //      registerOre("itemArrow", ARROW);
        //      registerOre("arrow", ARROW);
        //      registerWildBrick(STONEBRICK, WILDCARD_VALUE);
    }

    private static void registerWildOre(String name, Object object, int wildCardValiue) {
        if (object instanceof Item || object instanceof Block) {
            //         registerOre(name, new ItemStack(object, wildCardValiue));
        }
    }

    private static void registerWildBrick(Block block, int wildCardValue) {
        registerWildOre("stonebrick", block, wildCardValue);
    }

    //  private static void registerWildBrick(String color, Block block) {
    //      registerOre("stonebrick" + color, block);
    //  }
}
