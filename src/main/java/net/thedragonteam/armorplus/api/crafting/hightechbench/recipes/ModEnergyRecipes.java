/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.enableElectricalArmor;
import static net.thedragonteam.armorplus.APConfig.getRD;

public class ModEnergyRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {
        /* Steel Armor */
        if (APConfig.enableSteelArmor) {
            switch (getRD()) {
                case EASY:
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                            "MMMM",
                            "M  M",
                            "    ",
                            "    ",
                            'M', "ingotSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                            "    ",
                            "    ",
                            "MMMM",
                            "M  M",
                            'M', "ingotSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelChestplate, 1),
                            "M  M",
                            "M  M",
                            "MMMM",
                            "MMMM",
                            'M', "ingotSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelLeggings, 1),
                            "MMMM",
                            "M  M",
                            "M  M",
                            "M  M",
                            'M', "ingotSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                            "    ",
                            "    ",
                            "M  M",
                            "M  M",
                            'M', "ingotSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                            "    ",
                            "M  M",
                            "M  M",
                            "    ",
                            'M', "ingotSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                            "M  M",
                            "M  M",
                            "    ",
                            "    ",
                            'M', "ingotSteel"));
                    break;
                case EXPERT:
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                            "MMMM",
                            "M  M",
                            "    ",
                            "    ",
                            'M', "blockSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelHelmet, 1),
                            "    ",
                            "    ",
                            "MMMM",
                            "M  M",
                            'M', "blockSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelChestplate, 1),
                            "M  M",
                            "M  M",
                            "MMMM",
                            "MMMM",
                            'M', "blockSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelLeggings, 1),
                            "MMMM",
                            "M  M",
                            "M  M",
                            "M  M",
                            'M', "blockSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                            "    ",
                            "    ",
                            "M  M",
                            "M  M",
                            'M', "blockSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                            "M  M",
                            "M  M",
                            "    ",
                            "    ",
                            'M', "blockSteel"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steelBoots, 1),
                            "    ",
                            "M  M",
                            "M  M",
                            "    ",
                            'M', "blockSteel"));
                    break;
            }
        }
        /* Electrical Armor */
        if (enableElectricalArmor) {
            switch (getRD()) {
                case EASY:
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                            "    ",
                            "    ",
                            "EEEE",
                            "E  E",
                            'E', "ingotElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                            "EEEE",
                            "EXXE",
                            "    ",
                            "    ",
                            'E', "ingotElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalChestplate, 1),
                            "E  E",
                            "E  E",
                            "EEEE",
                            "EEEE",
                            'E', "ingotElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalLeggings, 1),
                            "EEEE",
                            "E  E",
                            "E  E",
                            "E  E",
                            'E', "ingotElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                            "    ",
                            "   ",
                            "E  E",
                            "E  E",
                            'E', "ingotElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                            "E  E",
                            "E  E",
                            "    ",
                            "    ",
                            'E', "ingotElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                            "    ",
                            "E  E",
                            "E  E",
                            "    ",
                            'E', "ingotElectrical"));
                    break;
                case EXPERT:
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                            "    ",
                            "    ",
                            "EEEE",
                            "E  E",
                            'E', "blockElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalHelmet, 1),
                            "EEEE",
                            "E  E",
                            "    ",
                            "    ",
                            'E', "blockElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalChestplate, 1),
                            "E  E",
                            "E  E",
                            "EEEE",
                            "EEEE",
                            'E', "blockElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalLeggings, 1),
                            "EEEE",
                            "E  E",
                            "E  E",
                            "E  E",
                            'E', "blockElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                            "    ",
                            "    ",
                            "E  E",
                            "E  E",
                            'E', "blockElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                            "E  E",
                            "E  E",
                            "    ",
                            "    ",
                            'E', "blockElectrical"));
                    manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricalBoots, 1),
                            "    ",
                            "E  E",
                            "E  E",
                            "    ",
                            'E', "blockElectrical"));
                    break;
            }
        }
    }
}
