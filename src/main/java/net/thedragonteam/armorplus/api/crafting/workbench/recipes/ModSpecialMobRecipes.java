/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModSpecialMobRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Chicken Armor */
        if (enableChickenArmor) {
            if (getRD() == EASY && enableChickenArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenHelmet), "   ", "FFF", "F F", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenHelmet), "FFF", "F F", "   ", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenChestplate), "F F", "FFF", "FFF", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenLeggings), "FFF", "F F", "F F", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenBoots), "   ", "F F", "F F", 'F', "feather"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenBoots), "F F", "F F", "   ", 'F', "feather"));
            }
            if (getRD() == EXPERT && enableChickenArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenHelmet), "   ", "FFF", "E E", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenHelmet), "FFF", "E E", "   ", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenChestplate), "E E", "FEF", "FFF", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenLeggings), "EFE", "F F", "F F", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenBoots), "   ", "F F", "E E", 'F', "feather", 'E', "egg"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(chickenBoots), "F F", "E E", "   ", 'F', "feather", 'E', "egg"));
            }
        }
        /* Slime Armor */
        if (enableSlimeArmor) {
            if (getRD() == EASY && enableSlimeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeHelmet), "   ", "SSS", "S S", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeHelmet), "SSS", "S S", "   ", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeChestplate), "S S", "SSS", "SSS", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeLeggings), "SSS", "S S", "S S", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeBoots), "   ", "S S", "S S", 'S', "slimeball"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeBoots), "S S", "S S", "   ", 'S', "slimeball"));
            }
            if (getRD() == EXPERT && enableSlimeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeHelmet), "   ", "SSS", "S S", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeHelmet), "SSS", "S S", "   ", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeChestplate), "S S", "SSS", "SSS", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeLeggings), "SSS", "S S", "S S", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeBoots), "   ", "S S", "S S", 'S', "blockSlime"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(slimeBoots), "S S", "S S", "   ", 'S', "blockSlime"));
            }
        }
    }
}