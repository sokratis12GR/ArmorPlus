/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.stream.IntStream;

import static net.minecraft.init.Blocks.STONEBRICK;
import static net.minecraft.init.Items.ARROW;
import static net.minecraft.init.Items.COAL;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;
import static net.minecraftforge.oredict.OreDictionary.registerOre;
import static net.thedragonteam.armorplus.registry.APBlocks.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModOreDicts {

    public static String[] colors = new String[]{"White", "Red", "Black", "Blue", "Green", "Yellow", "Purple"};

    public static void registerOreDictEntries() {
        //Ingots
        registerOre("ingotSteel", steelIngot);
        registerOre("ingotElectrical", electricalIngot);
        registerOre("gemLavaCrystal", lavaCrystal);
        registerOre("ingotLavaCrystal", lavaCrystal);
        registerWildOre("gemChargedLavaCrystal", lavaCrystal, 1);
        registerWildOre("ingotChargedLavaCrystal", lavaCrystal, 1);
        registerWildOre("gemInfusedLavaCrystal", lavaCrystal, 1);
        registerWildOre("ingotInfusedLavaCrystal", lavaCrystal, 1);
        //Blocks
        registerOre("oreLavaCrystal", oreLavaCrystal);
        registerOre("blockLavaCrystal", blockLavaCrystal);
        registerOre("blockInfusedLavaCrystal", blockInfusedLavaCrystal);
        registerOre("blockCompressedLavaCrystal", blockCompressedLavaCrystal);
        registerOre("blockCompressedInfusedLavaCrystal", blockCompressedInfusedLavaCrystal);
        registerOre("blockSteel", steelBlock);
        registerOre("blockElectrical", electricalBlock);
        registerOre("blockCompressedObsidian", compressedObsidian);
        registerOre("blockLavaInfusedObsidian", blockLavaInfusedObsidian);
        registerOre("blockInfusedObsidian", blockLavaInfusedObsidian);
        //Benches
        registerOre("apWorkbench", workbench);
        registerOre("workbenchTier1", workbench);
        registerOre("workbenchTierOne", workbench);
        registerOre("apHighTechBench", highTechBench);
        registerOre("workbenchTier2", highTechBench);
        registerOre("workbenchTierTwo", highTechBench);
        registerOre("apUltiTechBench", ultiTechBench);
        registerOre("workbenchTier3", ultiTechBench);
        registerOre("workbenchTierThree", ultiTechBench);
        registerOre("apChampionBench", championBench);
        registerOre("workbenchTier4", championBench);
        registerOre("workbenchTierFour", championBench);

        //CastleBlocks
        IntStream.range(0, colors.length).forEachOrdered(index -> {
            registerWildBrick(colors[index], stoneBricks[index]);
            registerWildBrick(colors[index] + "Corner", stoneBrickCorners[index]);
            registerWildBrick(colors[index] + "Tower", stoneBrickTowers[index]);
            registerWildBrick(colors[index] + "Wall", stonebrickWalls[index]);
        });
        //Materials
        registerWildOre("chainmail", materials, 0);
        registerWildOre("witherBone", materials, 2);
        registerWildOre("materialTheUltimate", materials, 4);
        registerWildOre("materialUltimate", materials, 4);
        registerWildOre("scaleGuardian", materials, 1);
        registerWildOre("scaleEnderDragon", materials, 3);
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
