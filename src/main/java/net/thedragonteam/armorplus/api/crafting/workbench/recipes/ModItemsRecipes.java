/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableArrowRecipes;
import static net.thedragonteam.armorplus.ARPConfig.recipes;

public class ModItemsRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaCrystal, 1, 1), "RGR", "GAG", "RGR", 'R', "dustRedstone", 'G', "dustGlowstone", 'A', "gemLavaCrystal"));
        if (ARPConfig.recipes == 0)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
        if (ARPConfig.recipes == 1)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "glowstone"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.theGiftOfTheGods, 1), "LOL", "OSO", "LOL", 'S', "netherStar", 'O', "obsidian", 'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steelIngot, 9), "blockSteel"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steelIngot, 1), "itemCharcoal", "ingotIron", "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 9), "blockElectrical"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.arpWorkbench), "LCL", "OTO", "OXO", 'T', "workbench", 'O', "blockCoal", 'L', "gemLapis", 'C', "gemLavaCrystal"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.arpHighTechBench), "LLL", "CAC", "CCC", 'C', "gemChargedLavaCrystal", 'L', "blockRedstone", 'A', "arpWorkbench"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFRod, 1), "XTX", "TST", "XTX", 'T', "dustRedstone", 'S', "stickWood"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFAxe, 1), "TTX", "TSX", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFAxe, 1), "XTT", "XST", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFShovel, 1), "XTX", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFHoe, 1), "TTX", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFHoe, 1), "XTT", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFSword, 1), "XTX", "XTX", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFPickaxe, 1), "TTT", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodRF"));
        if (Loader.isModLoaded("tesla")) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaRod, 1), "XTX", "TST", "XTX", 'T', "gemLapis", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaShovel, 1), "XTX", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaHoe, 1), "TTX", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaHoe, 1), "XTT", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaSword, 1), "XTX", "XTX", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaAxe, 1), "TTX", "TSX", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaAxe, 1), "XTT", "XST", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaPickaxe, 1), "TTT", "XSX", "XSX", 'T', "ingotSteel", 'S', "rodTesla"));
        }
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.steelBlock, 1), "OOO", "OOO", "OOO", 'O', "ingotSteel"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.electricalBlock, 1), "OOO", "OOO", "OOO", 'O', "ingotElectrical"));
        if (recipes == 0 && enableArrowRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalArrow, 2), "CCC", "CAC", "CCC", 'C', "itemCoal", 'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisArrow, 2), "LLL", "LAL", "LLL", 'L', "gemLapis", 'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneArrow, 2), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaArrow, 2), "LLL", "LAL", "LLL", 'L', "gemLavaCrystal", 'A', "itemArrow"));
        }
        if (recipes == 1 && enableArrowRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalArrow, 2), "CCC", "CAC", "CCC", 'C', "blockCoal", 'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisArrow, 2), "LLL", "LAL", "LLL", 'L', "blockLapis", 'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneArrow, 2), "RRR", "RAR", "RRR", 'R', "blockRedstone", 'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaArrow, 2), "LLL", "LAL", "LLL", 'L', "gemChargedLavaCrystal", 'A', "itemArrow"));
        }
    }
}