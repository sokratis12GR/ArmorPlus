/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.armorforge.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.armorforge.ArmorForgeCraftingManager;
import net.thedragonteam.armorplus.api.crafting.armorforge.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.armorforge.ShapelessOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModItemsRecipes {

    public void addRecipes(ArmorForgeCraftingManager manager) {
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaCrystal, 1, 1), "RGR", "GAG", "RGR", 'R', "dustRedstone", 'G', "dustGlowstone", 'A', "gemLavaCrystal"));
        if (ARPConfig.recipes == 0)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "dustGlowstone"));
        if (ARPConfig.recipes == 1)
            manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 1), "ingotSteel", "dustRedstone", "glowstone"));
        manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.theGiftOfTheGods, 1), "LOL", "OSO", "LOL", 'S', "netherStar", 'O', "obsidian", 'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steelIngot, 9), "blockSteel"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steelIngot, 1), "itemCharcoal", "ingotIron", "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.electricalIngot, 9), "blockElectrical"));
    }
}