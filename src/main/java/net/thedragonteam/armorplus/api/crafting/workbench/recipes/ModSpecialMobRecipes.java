/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModSpecialMobRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Chicken Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableChickenArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenHelmet, 1), "XXX", "FFF", "FXF", 'F', "feather"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenHelmet, 1), "FFF", "FXF", "XXX", 'F', "feather"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenChestplate, 1), "FXF", "FFF", "FFF", 'F', "feather"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenLeggings, 1), "FFF", "FXF", "FXF", 'F', "feather"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenBoots, 1), "XXX", "FXF", "FXF", 'F', "feather"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenBoots, 1), "FXF", "FXF", "XXX", 'F', "feather"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableChickenArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenHelmet, 1), "XXX", "FFF", "EXE", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenHelmet, 1), "FFF", "EXE", "XXX", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenChestplate, 1), "EXE", "FEF", "FFF", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenLeggings, 1), "EFE", "FXF", "FXF", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenBoots, 1), "XXX", "FXF", "EXE", 'F', "feather", 'E', "egg"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chickenBoots, 1), "FXF", "EXE", "XXX", 'F', "feather", 'E', "egg"));
        }
        /* Slime Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableSlimeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeHelmet, 1), "XXX", "SSS", "SXS", 'S', "slimeball"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeHelmet, 1), "SSS", "SXS", "XXX", 'S', "slimeball"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeChestplate, 1), "SXS", "SSS", "SSS", 'S', "slimeball"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeLeggings, 1), "SSS", "SXS", "SXS", 'S', "slimeball"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeBoots, 1), "XXX", "SXS", "SXS", 'S', "slimeball"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeBoots, 1), "SXS", "SXS", "XXX", 'S', "slimeball"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableSlimeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeHelmet, 1), "XXX", "SSS", "SXS", 'S', "blockSlime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeHelmet, 1), "SSS", "SXS", "XXX", 'S', "blockSlime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeChestplate, 1), "SXS", "SSS", "SSS", 'S', "blockSlime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeLeggings, 1), "SSS", "SXS", "SXS", 'S', "blockSlime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeBoots, 1), "XXX", "SXS", "SXS", 'S', "blockSlime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slimeBoots, 1), "SXS", "SXS", "XXX", 'S', "blockSlime"));
        }
    }
}