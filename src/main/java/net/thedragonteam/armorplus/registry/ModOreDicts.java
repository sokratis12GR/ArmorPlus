/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class ModOreDicts {

    public static void registerOreDictEnties() {
        //Ores
        registerOre("oreLavaCrystal", new ItemStack(ModBlocks.blockLavaCrystal, 1));
        registerOre("blockLavaCrystal", new ItemStack(ModBlocks.blockLavaCrystal, 1));
        //Ingots
        registerOre("ingotSteel", new ItemStack(ModItems.steelIngot, 1));
        registerOre("ingotElectrical", new ItemStack(ModItems.electricalIngot, 1));
        registerOre("blockSteel", new ItemStack(ModBlocks.steelBlock, 1));
        registerOre("blockElectrical", new ItemStack(ModBlocks.electricalBlock, 1));
        registerOre("blockCompressedObsidian", new ItemStack(ModBlocks.compressedObsidian, 1));
        registerOre("arpWorkbench", new ItemStack(ModBlocks.arpWorkbench, 1));
        registerOre("arpWorkbenchTier1", new ItemStack(ModBlocks.arpWorkbench, 1));
        registerOre("arpWorkbenchTierOne", new ItemStack(ModBlocks.arpWorkbench, 1));
        registerOre("arpHighTechBench", new ItemStack(ModBlocks.arpHighTechBench, 1));
        registerOre("arpWorkbenchTier2", new ItemStack(ModBlocks.arpHighTechBench, 1));
        registerOre("arpWorkbenchTierTwo", new ItemStack(ModBlocks.arpHighTechBench, 1));
        registerOre("arpUltiTechBench", new ItemStack(ModBlocks.arpUltiTechBench, 1));
        registerOre("arpWorkbenchTier3", new ItemStack(ModBlocks.arpUltiTechBench, 1));
        registerOre("arpWorkbenchTierThree", new ItemStack(ModBlocks.arpUltiTechBench, 1));

        //Gems
        registerOre("gemLavaCrystal", new ItemStack(ModItems.lavaCrystal, 1));
        registerOre("ingotLavaCrystal", new ItemStack(ModItems.lavaCrystal, 1));
        registerOre("gemChargedLavaCrystal", new ItemStack(ModItems.lavaCrystal, 1, 1));
        registerOre("ingotChargedLavaCrystal", new ItemStack(ModItems.lavaCrystal, 1, 1));
        //Materials
        registerOre("chainmail", new ItemStack(ModItems.chainmail, 1));
        registerOre("witherBone", new ItemStack(ModItems.witherBone, 1));
        registerOre("materialTheUltimate", new ItemStack(ModItems.theUltimateMaterial, 1));
        registerOre("materialUltimate", new ItemStack(ModItems.theUltimateMaterial, 1));
        registerOre("scaleGuardian", new ItemStack(ModItems.guardianScale, 1));
        registerOre("scaleEnderDragon", new ItemStack(ModItems.enderDragonScale, 1));
        registerOre("rodTesla", new ItemStack(ModItems.itemTeslaRod, 1));
        registerOre("rodRF", new ItemStack(ModItems.itemRFRod, 1));
        //Vanilla
        registerOre("itemCoal", new ItemStack(Items.COAL, 1));
        registerOre("itemCharcoal", new ItemStack(Items.COAL, 1, 1));
    }
}
