/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class WBRecipesHelper {

    private static BaseShapedOreRecipe createShapedRecipe(Object output, Object... recipe) {
        return new BaseShapedOreRecipe(3, getItemStack(output), recipe);
    }

    public static void createArmorRecipes(BaseCraftingManager manager, String material, Object... outputs) {
        manager.addRecipes(
            createShapedRecipe(outputs[0], "   ", "CCC", "C C", 'C', material),
            createShapedRecipe(outputs[0], "   ", "CCC", "C C", 'C', material),
            createShapedRecipe(outputs[0], "CCC", "C C", "   ", 'C', material),
            createShapedRecipe(outputs[1], "C C", "CCC", "CCC", 'C', material),
            createShapedRecipe(outputs[2], "CCC", "C C", "C C", 'C', material),
            createShapedRecipe(outputs[3], "   ", "C C", "C C", 'C', material),
            createShapedRecipe(outputs[3], "C C", "C C", "   ", 'C', material)
        );
    }

    public static void createSwordRecipe(BaseCraftingManager manager, String material, Object output) {
        manager.addRecipe(createShapedRecipe(output, " C ", " C ", " S ", 'C', material, 'S', "stickWood"));
    }

    public static void createBattleAxeRecipe(BaseCraftingManager manager, String material, Object output) {
        manager.addRecipe(createShapedRecipe(output, "C C", "CSC", " S ", 'C', material, 'S', "stickWood"));
    }

    public static void createBowRecipes(BaseCraftingManager manager, String material, Object output) {
        manager.addRecipes(
            createShapedRecipe(output, " CS", "C S", " CS", 'C', material, 'S', "string"),
            createShapedRecipe(output, "SC ", "S C", "SC ", 'C', material, 'S', "string")
        );
    }
}
