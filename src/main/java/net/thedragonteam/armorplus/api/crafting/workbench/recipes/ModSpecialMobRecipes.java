/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModSpecialMobRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Chicken Armor */
        if (APConfig.enableSlimeArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableChickenArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[0], 1), "   ", "FFF", "F F", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[0], 1), "FFF", "F F", "   ", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[1], 1), "F F", "FFF", "FFF", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[2], 1), "FFF", "F F", "F F", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[3], 1), "   ", "F F", "F F", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[3], 1), "F F", "F F", "   ", 'F', "feather"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableChickenArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[0], 1), "   ", "FFF", "E E", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[0], 1), "FFF", "E E", "   ", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[1], 1), "E E", "FEF", "FFF", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[2], 1), "EFE", "F F", "F F", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[3], 1), "   ", "F F", "E E", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken[3], 1), "F F", "E E", "   ", 'F', "feather", 'E', "egg"));
            }
        }
        /* Slime Armor */
        if (APConfig.enableSlimeArmor) {
            if (APConfig.gameMode == 0 && APConfig.enableSlimeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[0], 1), "   ", "SSS", "S S", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[0], 1), "SSS", "S S", "   ", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[1], 1), "S S", "SSS", "SSS", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[2], 1), "SSS", "S S", "S S", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[3], 1), "   ", "S S", "S S", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[3], 1), "S S", "S S", "   ", 'S', "slimeball"));
            }
            if (APConfig.gameMode == 1 && APConfig.enableSlimeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[0], 1), "   ", "SSS", "S S", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[0], 1), "SSS", "S S", "   ", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[1], 1), "S S", "SSS", "SSS", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[2], 1), "SSS", "S S", "S S", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[3], 1), "   ", "S S", "S S", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime[3], 1), "S S", "S S", "   ", 'S', "blockSlime"));
            }
        }
    }
}