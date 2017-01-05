/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.enableArrowRecipes;
import static net.thedragonteam.armorplus.APConfig.gameMode;

public class ModItemsRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaCrystal, 1, 1),
                "RGR",
                "GAG",
                "RGR",
                'R', "dustRedstone",
                'G', "dustGlowstone",
                'A', "gemLavaCrystal"));
        if (APConfig.gameMode == 0)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
        if (APConfig.gameMode == 1)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "glowstone"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.theGiftOfTheGods, 1),
                "LOL",
                "OSO",
                "LOL",
                'S', "netherStar",
                'O', "obsidian",
                'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steelIngot, 9), "blockSteel"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steelIngot, 1), "itemCharcoal", "ingotIron", "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 9), "blockElectrical"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', "workbench",
                'O', "blockCoal",
                'L', "gemLapis",
                'C', "gemLavaCrystal"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.benches[1]),
                "LLL",
                "CAC",
                "CCC",
                'C', "gemChargedLavaCrystal",
                'L', "blockRedstone",
                'A', "arpWorkbench"));
        if (Loader.isModLoaded("tesla")) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaRod, 1),
                    " T ",
                    "TST",
                    " T ",
                    'T', "gemLapis",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaShovel, 1),
                    " T ",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaHoe, 1),
                    "TT ",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaHoe, 1),
                    " TT",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaSword, 1),
                    " T ",
                    " T ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaAxe, 1),
                    "TT ",
                    "TS ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaAxe, 1),
                    "TT ",
                    " ST",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaPickaxe, 1),
                    "TTT",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
        }
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.steelBlock, 1),
                "OOO",
                "OOO",
                "OOO",
                'O', "ingotSteel"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.electricalBlock, 1),
                "OOO",
                "OOO",
                "OOO",
                'O', "ingotElectrical"));
        if (gameMode == 0 && enableArrowRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemCoalArrow, 2),
                    "CCC",
                    "CAC",
                    "CCC",
                    'C', "itemCoal",
                    'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemLapisArrow, 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "gemLapis",
                    'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRedstoneArrow, 2),
                    "RRR",
                    "RAR",
                    "RRR",
                    'R', "dustRedstone",
                    'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemLavaArrow, 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "gemLavaCrystal",
                    'A', "itemArrow"));
        }
        if (gameMode == 1 && enableArrowRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemCoalArrow, 2),
                    "CCC",
                    "CAC",
                    "CCC",
                    'C', "blockCoal",
                    'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemLapisArrow, 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "blockLapis",
                    'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRedstoneArrow, 2),
                    "RRR",
                    "RAR",
                    "RRR",
                    'R', "blockRedstone",
                    'A', "itemArrow"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemLavaArrow, 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "gemChargedLavaCrystal",
                    'A', "itemArrow"));
        }
    }
}