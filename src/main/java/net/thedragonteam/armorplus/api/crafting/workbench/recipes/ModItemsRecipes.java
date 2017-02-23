/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModBlocks;

import static net.thedragonteam.armorplus.APConfig.enableArrowRecipes;
import static net.thedragonteam.armorplus.APConfig.getRD;
import static net.thedragonteam.armorplus.ArmorPlus.isTeslaLoaded;
import static net.thedragonteam.armorplus.registry.APBlocks.highTechBench;
import static net.thedragonteam.armorplus.registry.APBlocks.workbench;
import static net.thedragonteam.armorplus.registry.ModItems.*;

public class ModItemsRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.lavaInfuser, 1),
                "LLL",
                "G G",
                "OOO",
                'L', "gemLavaCrystal",
                'G', "blockGlass",
                'O', "obsidian"
        ));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(theGiftOfTheGods, 1),
                "LOL",
                "OSO",
                "LOL",
                'S', "netherStar",
                'O', "obsidian",
                'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(steelIngot, 9), "blockSteel"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(steelIngot, 1), "itemCharcoal", "ingotIron", "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(electricalIngot, 9), "blockElectrical"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(workbench),
                "LCL",
                "OTO",
                "O O",
                'T', "workbench",
                'O', "blockCoal",
                'L', "gemLapis",
                'C', "gemLavaCrystal"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(highTechBench),
                "LLL",
                "CAC",
                "CCC",
                'C', "gemChargedLavaCrystal",
                'L', "blockRedstone",
                'A', "apWorkbench"));
        if (isTeslaLoaded()) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaRod, 1),
                    " T ",
                    "TST",
                    " T ",
                    'T', "gemLapis",
                    'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaShovel, 1),
                    " T ",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaHoe, 1),
                    "TT ",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaHoe, 1),
                    " TT",
                    " S ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaSword, 1),
                    " T ",
                    " T ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaAxe, 1),
                    "TT ",
                    "TS ",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaAxe, 1),
                    "TT ",
                    " ST",
                    " S ",
                    'T', "ingotSteel",
                    'S', "rodTesla"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaPickaxe, 1),
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
        switch (getRD()) {
            case EASY:
                manager.addRecipe(new ShapelessOreRecipe(new ItemStack(electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
                if (enableArrowRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemCoalArrow, 2),
                            "CCC",
                            "CAC",
                            "CCC",
                            'C', "itemCoal",
                            'A', "itemArrow"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemLapisArrow, 2),
                            "LLL",
                            "LAL",
                            "LLL",
                            'L', "gemLapis",
                            'A', "itemArrow"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemRedstoneArrow, 2),
                            "RRR",
                            "RAR",
                            "RRR",
                            'R', "dustRedstone",
                            'A', "itemArrow"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemLavaArrow, 2),
                            "LLL",
                            "LAL",
                            "LLL",
                            'L', "gemLavaCrystal",
                            'A', "itemArrow"));
                }
                break;
            case EXPERT:
                manager.addRecipe(new ShapelessOreRecipe(new ItemStack(electricalIngot, 1), "ingotSteel", "dustRedstone", "glowstone"));
                if (enableArrowRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemCoalArrow, 2),
                            "CCC",
                            "CAC",
                            "CCC",
                            'C', "blockCoal",
                            'A', "itemArrow"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemLapisArrow, 2),
                            "LLL",
                            "LAL",
                            "LLL",
                            'L', "blockLapis",
                            'A', "itemArrow"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemRedstoneArrow, 2),
                            "RRR",
                            "RAR",
                            "RRR",
                            'R', "blockRedstone",
                            'A', "itemArrow"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemLavaArrow, 2),
                            "LLL",
                            "LAL",
                            "LLL",
                            'L', "gemChargedLavaCrystal",
                            'A', "itemArrow"));
                }
        }
    }
}