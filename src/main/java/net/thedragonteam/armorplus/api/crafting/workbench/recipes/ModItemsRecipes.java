/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.enableArrowRecipes;
import static net.thedragonteam.armorplus.APConfig.getRD;
import static net.thedragonteam.armorplus.ArmorPlus.isTeslaLoaded;
import static net.thedragonteam.armorplus.registry.APBlocks.highTechBench;
import static net.thedragonteam.armorplus.registry.APBlocks.workbench;
import static net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuser;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModItemsRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        manager.addRecipe(new ShapedOreRecipe(getItemStack(lavaInfuser),
                "LLL",
                "G G",
                "OOO",
                'L', "gemLavaCrystal",
                'G', "blockGlass",
                'O', "blockObsidian"
        ));
        if (getRD() == EASY)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
        if (getRD() == EXPERT)
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
        if (getRD() == EASY && enableArrowRecipes) {
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
        if (getRD() == EXPERT && enableArrowRecipes) {
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