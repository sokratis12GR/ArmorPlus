/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import com.sofodev.armorplus.items.base.ItemBase;
import com.sofodev.armorplus.items.consumables.ItemTGOTG;
import com.sofodev.armorplus.items.materials.ItemLavaCrystal;
import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.util.Utils.getBlock;
import static com.sofodev.armorplus.util.Utils.getItem;

public class ModItemRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        ItemBase steelIngot = (ItemBase) getItem("steel_ingot");
        ItemBase electricalIngot = (ItemBase) getItem("electrical_ingot");
        ItemTGOTG itemTGOTG = (ItemTGOTG) getItem("the_gift_of_the_gods");
        ItemLavaCrystal lavaCrystal = (ItemLavaCrystal) getItem("lava_crystal");
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(ModBlocks.lavaInfuser, 1),
            "LLL",
            "G G",
            "OOO",
            'L', lavaCrystal,
            'G', "blockGlass",
            'O', "obsidian"
        ));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(itemTGOTG, 1),
            "LOL",
            "OSO",
            "LOL",
            'S', "netherStar",
            'O', "obsidian",
            'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(steelIngot, 9), "blockSteel"));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(steelIngot, 1), "itemCharcoal", "ingotIron", "gemChargedLavaCrystal"));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(electricalIngot, 9), "blockElectrical"));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getBlock("workbench")),
            "LCL",
            "OTO",
            "O O",
            'T', "workbench",
            'O', "blockCoal",
            'L', "gemLapis",
            'C', lavaCrystal));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getBlock("high_tech_bench")),
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
        manager.addRecipe(new ItemStack(ModBlocks.blockLavaCrystal), "CCC", "CCC", "CCC", 'C', new ItemStack(lavaCrystal));
        manager.addRecipe(new ItemStack(ModBlocks.blockInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', new ItemStack(getItem("infused_lava_crystal")));

        manager.addRecipe(new ItemStack(ModBlocks.blockCompressedLavaCrystal), "CCC", "CCC", "CCC", 'C', new ItemStack(ModBlocks.blockLavaCrystal));
        manager.addRecipe(new ItemStack(ModBlocks.blockCompressedInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', new ItemStack(ModBlocks.blockInfusedLavaCrystal));
        switch (getRD()) {
            case EASY:
                manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("coal_arrow"), 2),
                    "CCC",
                    "CAC",
                    "CCC",
                    'C', "itemCoal",
                    'A', "itemArrow"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("lapis_arrow"), 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "gemLapis",
                    'A', "itemArrow"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("redstone_arrow"), 2),
                    "RRR",
                    "RAR",
                    "RRR",
                    'R', "dustRedstone",
                    'A', "itemArrow"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("lava_arrow"), 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', lavaCrystal,
                    'A', "itemArrow"));
                break;
            case EXPERT:
            case HELLISH:
                manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(electricalIngot, 1), "ingotSteel", "dustRedstone", "glowstone"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("coal_arrow"), 2),
                    "CCC",
                    "CAC",
                    "CCC",
                    'C', "blockCoal",
                    'A', "itemArrow"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("lapis_arrow"), 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "blockLapis",
                    'A', "itemArrow"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("redstone_arrow"), 2),
                    "RRR",
                    "RAR",
                    "RRR",
                    'R', "blockRedstone",
                    'A', "itemArrow"));
                manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(getItem("lava_arrow"), 2),
                    "LLL",
                    "LAL",
                    "LLL",
                    'L', "gemChargedLavaCrystal",
                    'A', "itemArrow"));
                break;
        }
    }
}
