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

    public static void registerOreDictEnties() {
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
        //CastleBlocks
        registerOre("stonebrickWhite", ModBlocks.whiteStoneBrick);
        registerOre("stonebrickRed", ModBlocks.redStoneBrick);
        registerOre("stonebrickBlack", ModBlocks.blackStoneBrick);
        registerOre("stonebrickBlue", ModBlocks.blueStoneBrick);
        registerOre("stonebrickGreen", ModBlocks.greenStoneBrick);
        registerOre("stonebrickYellow", ModBlocks.yellowStoneBrick);
        registerOre("stonebrickPurple", ModBlocks.purpleStoneBrick);
        registerOre("stonebrickWhiteCorner", ModBlocks.whiteStoneBrickCorner);
        registerOre("stonebrickRedCorner", ModBlocks.redStoneBrickCorner);
        registerOre("stonebrickBlackCorner", ModBlocks.blackStoneBrickCorner);
        registerOre("stonebrickBlueCorner", ModBlocks.blueStoneBrickCorner);
        registerOre("stonebrickGreenCorner", ModBlocks.greenStoneBrickCorner);
        registerOre("stonebrickYellowCorner", ModBlocks.yellowStoneBrickCorner);
        registerOre("stonebrickPurpleCorner", ModBlocks.purpleStoneBrickCorner);
        registerOre("stonebrickWhiteTower", ModBlocks.whiteStoneBrickTower);
        registerOre("stonebrickRedTower", ModBlocks.redStoneBrickTower);
        registerOre("stonebrickBlackTower", ModBlocks.blackStoneBrickTower);
        registerOre("stonebrickBlueTower", ModBlocks.blueStoneBrickTower);
        registerOre("stonebrickGreenTower", ModBlocks.greenStoneBrickTower);
        registerOre("stonebrickYellowTower", ModBlocks.yellowStoneBrickTower);
        registerOre("stonebrickPurpleTower", ModBlocks.purpleStoneBrickTower);
        //Materials
        registerOre("chainmail", ModItems.chainmail);
        registerOre("witherBone", ModItems.witherBone);
        registerOre("materialTheUltimate", ModItems.theUltimateMaterial);
        registerOre("materialUltimate", ModItems.theUltimateMaterial);
        registerOre("scaleGuardian", ModItems.guardianScale);
        registerOre("scaleEnderDragon", ModItems.enderDragonScale);
        registerOre("rodTesla", ModItems.itemTeslaRod);
        //Vanilla
        registerOre("itemCoal", Items.COAL);
        registerOre("coal", Items.COAL);
        registerWildOre("itemCharcoal", Items.COAL, 1);
        registerOre("itemArrow", Items.ARROW);
        registerOre("arrow", Items.ARROW);
        registerWildOre("stonebrick", Blocks.STONEBRICK, WILDCARD_VALUE);
    }

    private static void registerWildOre(String name, Item item, int wildCardValiue) {
        registerOre(name, new ItemStack(item, 1, wildCardValiue));
    }

    private static void registerWildOre(String name, Block block, int wildCardValiue) {
        registerOre(name, new ItemStack(block, 1, wildCardValiue));
    }
}
