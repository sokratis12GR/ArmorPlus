package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.ModConfig.getRD;
import static net.thedragonteam.armorplus.registry.APBlocks.highTechBench;
import static net.thedragonteam.armorplus.registry.APBlocks.workbench;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModItemRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(ModBlocks.lavaInfuser, 1),
            "LLL",
            "G G",
            "OOO",
            'L', "gemLavaCrystal",
            'G', "blockGlass",
            'O', "obsidian"
        ));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(theGiftOfTheGods, 1),
            "LOL",
            "OSO",
            "LOL",
            'S', "netherStar",
            'O', "obsidian",
            'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(steelIngot, 9), "blockSteel"));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(steelIngot, 1), "itemCharcoal", "ingotIron", "gemChargedLavaCrystal"));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(electricalIngot, 9), "blockElectrical"));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(workbench),
            "LCL",
            "OTO",
            "O O",
            'T', "workbench",
            'O', "blockCoal",
            'L', "gemLapis",
            'C', "gemLavaCrystal"));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(highTechBench),
            "LLL",
            "CAC",
            "CCC",
            'C', "gemChargedLavaCrystal",
            'L', "blockRedstone",
            'A', "apWorkbench"));
        //   if (isTeslaLoaded()) {
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaRod, 1),
        //              " T ",
        //              "TST",
        //              " T ",
        //              'T', "gemLapis",
        //              'S', "stickWood"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaShovel, 1),
        //              " T ",
        //              " S ",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaHoe, 1),
        //              "TT ",
        //              " S ",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaHoe, 1),
        //              " TT",
        //              " S ",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaSword, 1),
        //              " T ",
        //              " T ",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaAxe, 1),
        //              "TT ",
        //              "TS ",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaAxe, 1),
        //              " TT",
        //              " ST",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //      manager.addRecipe(new ShapedOreRecipe(new ItemStack(itemTeslaPickaxe, 1),
        //              "TTT",
        //              " S ",
        //              " S ",
        //              'T', "ingotSteel",
        //              'S', "rodTesla"));
        //    }
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(ModBlocks.steelBlock, 1),
            "OOO",
            "OOO",
            "OOO",
            'O', "ingotSteel"));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(ModBlocks.electricalBlock, 1),
            "OOO",
            "OOO",
            "OOO",
            'O', "ingotElectrical"));
        manager.addRecipe(getItemStack(ModBlocks.blockLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.lavaCrystal, 0));
        manager.addRecipe(getItemStack(ModBlocks.blockInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.lavaCrystal, 1));

        manager.addRecipe(getItemStack(ModBlocks.blockCompressedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModBlocks.blockLavaCrystal));
        manager.addRecipe(getItemStack(ModBlocks.blockCompressedInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModBlocks.blockInfusedLavaCrystal));
        switch (getRD()) {
            case EASY:
                manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
                if (recipes.enableArrowRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemCoalArrow, 2),
                        "CCC",
                        "CAC",
                        "CCC",
                        'C', "itemCoal",
                        'A', "itemArrow"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemLapisArrow, 2),
                        "LLL",
                        "LAL",
                        "LLL",
                        'L', "gemLapis",
                        'A', "itemArrow"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemRedstoneArrow, 2),
                        "RRR",
                        "RAR",
                        "RRR",
                        'R', "dustRedstone",
                        'A', "itemArrow"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemLavaArrow, 2),
                        "LLL",
                        "LAL",
                        "LLL",
                        'L', "gemLavaCrystal",
                        'A', "itemArrow"));
                }
                break;
            case EXPERT:
            case HELLISH:
                manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(electricalIngot, 1), "ingotSteel", "dustRedstone", "glowstone"));
                if (recipes.enableArrowRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemCoalArrow, 2),
                        "CCC",
                        "CAC",
                        "CCC",
                        'C', "blockCoal",
                        'A', "itemArrow"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemLapisArrow, 2),
                        "LLL",
                        "LAL",
                        "LLL",
                        'L', "blockLapis",
                        'A', "itemArrow"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemRedstoneArrow, 2),
                        "RRR",
                        "RAR",
                        "RRR",
                        'R', "blockRedstone",
                        'A', "itemArrow"));
                    manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemLavaArrow, 2),
                        "LLL",
                        "LAL",
                        "LLL",
                        'L', "gemChargedLavaCrystal",
                        'A', "itemArrow"));
                }
                break;
        }
    }
}
