/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class ModOreDicts {

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
        registerOre("arpWorkbench", ModBlocks.arpWorkbench);
        registerOre("arpWorkbenchTier1", ModBlocks.arpWorkbench);
        registerOre("arpWorkbenchTierOne", ModBlocks.arpWorkbench);
        registerOre("arpHighTechBench", ModBlocks.arpHighTechBench);
        registerOre("arpWorkbenchTier2", ModBlocks.arpHighTechBench);
        registerOre("arpWorkbenchTierTwo", ModBlocks.arpHighTechBench);
        registerOre("arpUltiTechBench", ModBlocks.arpUltiTechBench);
        registerOre("arpWorkbenchTier3", ModBlocks.arpUltiTechBench);
        registerOre("arpWorkbenchTierThree", ModBlocks.arpUltiTechBench);
        //CastleBlocks
        registerWildBrick("White", ModBlocks.whiteStoneBrick);
        registerWildBrick("Red", ModBlocks.redStoneBrick);
        registerWildBrick("Black", ModBlocks.blackStoneBrick);
        registerWildBrick("Blue", ModBlocks.blueStoneBrick);
        registerWildBrick("Green", ModBlocks.greenStoneBrick);
        registerWildBrick("Yellow", ModBlocks.yellowStoneBrick);
        registerWildBrick("Purple", ModBlocks.purpleStoneBrick);
        registerWildBrick("WhiteCorner", ModBlocks.whiteStoneBrickCorner);
        registerWildBrick("RedCorner", ModBlocks.redStoneBrickCorner);
        registerWildBrick("BlackCorner", ModBlocks.blackStoneBrickCorner);
        registerWildBrick("BlueCorner", ModBlocks.blueStoneBrickCorner);
        registerWildBrick("GreenCorner", ModBlocks.greenStoneBrickCorner);
        registerWildBrick("YellowCorner", ModBlocks.yellowStoneBrickCorner);
        registerWildBrick("PurpleCorner", ModBlocks.purpleStoneBrickCorner);
        registerWildBrick("WhiteTower", ModBlocks.whiteStoneBrickTower);
        registerWildBrick("RedTower", ModBlocks.redStoneBrickTower);
        registerWildBrick("BlackTower", ModBlocks.blackStoneBrickTower);
        registerWildBrick("BlueTower", ModBlocks.blueStoneBrickTower);
        registerWildBrick("GreenTower", ModBlocks.greenStoneBrickTower);
        registerWildBrick("YellowTower", ModBlocks.yellowStoneBrickTower);
        registerWildBrick("PurpleTower", ModBlocks.purpleStoneBrickTower);
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

    private static void registerWildOre(String name, Item item, int wildCardValue) {
        registerOre(name, new ItemStack(item, 1, wildCardValue));
    }

    private static void registerWildOre(String name, Block block, int wildCardValue) {
        registerOre(name, new ItemStack(block, 1, wildCardValue));
    }

    private static void registerWildBrick(Block block, int wildCardValue) {
        registerWildOre("stonebrick", block, wildCardValue);
    }

    private static void registerWildBrick(String color, Block block) {
        registerWildOre("stonebrick" + color, block, 1);
    }
}
