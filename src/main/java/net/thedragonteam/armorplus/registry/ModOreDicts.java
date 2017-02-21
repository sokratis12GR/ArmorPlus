/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;
import static net.minecraftforge.oredict.OreDictionary.registerOre;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

public class ModOreDicts {

    public static String[] colors = new String[]{"White", "Red", "Black", "Blue", "Green", "Yellow", "Purple"};

    public static void registerOreDictEntries() {
        //Ingots
        registerOre("ingotSteel", ModItems.steelIngot);
        registerOre("ingotElectrical", ModItems.electricalIngot);
        registerOre("gemLavaCrystal", ModItems.lavaCrystal);
        registerOre("ingotLavaCrystal", ModItems.lavaCrystal);
        registerWildOre("gemChargedLavaCrystal", ModItems.lavaCrystal, 1);
        registerWildOre("ingotChargedLavaCrystal", ModItems.lavaCrystal, 1);
        //Blocks
        registerOre("oreLavaCrystal", ModBlocks.blockLavaCrystal);
        registerOre("blockLavaCrystal", ModBlocks.blockLavaCrystal);
        registerOre("blockSteel", ModBlocks.steelBlock);
        registerOre("blockElectrical", ModBlocks.electricalBlock);
        registerOre("blockCompressedObsidian", ModBlocks.compressedObsidian);
        registerOre("apWorkbench", benches[0]);
        registerOre("workbenchTier1", benches[0]);
        registerOre("workbenchTierOne", benches[0]);
        registerOre("apHighTechBench", benches[1]);
        registerOre("workbenchTier2", benches[1]);
        registerOre("workbenchTierTwo", benches[1]);
        registerOre("apUltiTechBench", benches[2]);
        registerOre("workbenchTier3", benches[2]);
        registerOre("workbenchTierThree", benches[2]);
        //CastleBlocks
        //CastleBlocks
        for (int i = 0; i < colors.length; i++) {
            registerWildBrick(colors[i], stoneBricks[i]);
            registerWildBrick(colors[i] + "Corner", stoneBrickCorners[i]);
            registerWildBrick(colors[i] + "Tower", stoneBrickTowers[i]);
        }
        //Materials
        registerOre("chainmail", ModItems.chainmail);
        registerOre("witherBone", ModItems.witherBone);
        registerOre("materialTheUltimate", ModItems.theUltimateMaterial);
        registerOre("materialUltimate", ModItems.theUltimateMaterial);
        registerOre("scaleGuardian", ModItems.guardianScale);
        registerOre("scaleEnderDragon", ModItems.enderDragonScale);
        registerOre("rodTesla", ModItems.itemTeslaRod);
        registerOre("rodRF", ModItems.itemRFRod);
        //Vanilla
        registerOre("itemCoal", Items.COAL);
        registerOre("coal", Items.COAL);
        registerWildOre("itemCharcoal", Items.COAL, 1);
        registerOre("itemArrow", Items.ARROW);
        registerOre("arrow", Items.ARROW);
        registerWildBrick(Blocks.STONEBRICK, WILDCARD_VALUE);
    }


    private static void registerWildOre(String name, Item item, int wildCardValiue) {
        registerOre(name, getItemStack(item, wildCardValiue));
    }

    private static void registerWildOre(String name, Block block, int wildCardValiue) {
        registerOre(name, getItemStack(block, wildCardValiue));
    }

    private static void registerWildBrick(Block block, int wildCardValue) {
        registerWildOre("stonebrick", block, wildCardValue);
    }

    private static void registerWildBrick(String color, Block block) {
        registerOre("stonebrick" + color, block);
    }
}
