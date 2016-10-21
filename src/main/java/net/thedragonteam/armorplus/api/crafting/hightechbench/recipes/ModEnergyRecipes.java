/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModEnergyRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {
        /* Metal Armor */
        if (ARPConfig.recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "MMMM",
                    "MXXM",
                    'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                    "MMMM",
                    "MXXM",
                    "XXXX",
                    "XXXX",
                    'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelChestplate, 1),
                    "MXXM",
                    "MXXM",
                    "MMMM",
                    "MMMM",
                    'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelLeggings, 1),
                    "MMMM",
                    "MXXM",
                    "MXXM",
                    "MXXM",
                    'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                    "XXXX",
                    "XXXX",
                    "MXXM",
                    "MXXM",
                    'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                    "MXXM",
                    "MXXM",
                    "XXXX",
                    "XXXX",
                    'M', "ingotSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                    "XXXX",
                    "MXXM",
                    "MXXM",
                    "XXXX",
                    'M', "ingotSteel"));
        }
        if (ARPConfig.recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "MMMM",
                    "MXXM",
                    'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                    "MMMM",
                    "MXXM",
                    "XXXX",
                    "XXXX",
                    'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelChestplate, 1),
                    "MXXM",
                    "MXXM",
                    "MMMM",
                    "MMMM",
                    'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelLeggings, 1),
                    "MMMM",
                    "MXXM",
                    "MXXM",
                    "MXXM",
                    'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                    "XXXX",
                    "XXXX",
                    "MXXM",
                    "MXXM",
                    'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                    "MXXM",
                    "MXXM",
                    "XXXX",
                    "XXXX",
                    'M', "blockSteel"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                    "XXXX",
                    "MXXM",
                    "MXXM",
                    "XXXX",
                    'M', "blockSteel"));
        }
        /* Electrical Armor */
        if (ARPConfig.recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "EEEE",
                    "EXXE",
                    'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                    "EEEE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalChestplate, 1),
                    "EXXE",
                    "EXXE",
                    "EEEE",
                    "EEEE",
                    'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalLeggings, 1),
                    "EEEE",
                    "EXXE",
                    "EXXE",
                    "EXXE",
                    'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                    "XXXX",
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', "ingotElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    'E', "ingotElectrical"));
        }
        if (ARPConfig.recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "EEEE",
                    "EXXE",
                    'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                    "EEEE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalChestplate, 1),
                    "EXXE",
                    "EXXE",
                    "EEEE",
                    "EEEE",
                    'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalLeggings, 1),
                    "EEEE",
                    "EXXE",
                    "EXXE",
                    "EXXE",
                    'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                    "XXXX",
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', "blockElectrical"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    'E', "blockElectrical"));
        }
    }
}
