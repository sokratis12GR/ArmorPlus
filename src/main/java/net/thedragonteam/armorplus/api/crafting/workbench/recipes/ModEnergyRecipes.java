/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModEnergyRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        //---------------------------------------------- Energy ----------------------------------------------\\
        /* Metal Armor */
        if (ARPConfig.recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1), "XXX", "MMM", "MXM", 'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1), "MMM", "MXM", "XXX", 'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelChestplate, 1), "MXM", "MMM", "MMM", 'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelLeggings, 1), "MMM", "MXM", "MXM", 'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1), "XXX", "MXM", "MXM", 'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1), "MXM", "MXM", "XXX", 'M', "ingotSteel"));
        }
        if (ARPConfig.recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1), "XXX", "MMM", "MXM", 'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1), "MMM", "MXM", "XXX", 'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelChestplate, 1), "MXM", "MMM", "MMM", 'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelLeggings, 1), "MMM", "MXM", "MXM", 'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1), "XXX", "MXM", "MXM", 'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1), "MXM", "MXM", "XXX", 'M', "blockSteel"));
        }
        /* Electrical Armor */
        if (ARPConfig.recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1), "XXX", "EEE", "EXE", 'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1), "EEE", "EXE", "XXX", 'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalChestplate, 1), "EXE", "EEE", "EEE", 'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalLeggings, 1), "EEE", "EXE", "EXE", 'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1), "XXX", "EXE", "EXE", 'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1), "EXE", "EXE", "XXX", 'E', "ingotElectrical"));
        }
        if (ARPConfig.recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1), "XXX", "EEE", "EXE", 'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1), "EEE", "EXE", "XXX", 'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalChestplate, 1), "EXE", "EEE", "EEE", 'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalLeggings, 1), "EEE", "EXE", "EXE", 'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1), "XXX", "EXE", "EXE", 'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1), "EXE", "EXE", "XXX", 'E', "blockElectrical"));
        }
    }
}