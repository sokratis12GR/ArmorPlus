/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

class HTBRecipesHelper {

    static void createEasyArmorSetRecipes(BaseCraftingManager manager, String material, IItemProvider... outputs) {
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "EEEEE",
            "E   E",
            "     ",
            "     ",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "     ",
            "EEEEE",
            "E   E",
            "     ",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "     ",
            "     ",
            "EEEEE",
            "E   E",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "     ",
            "     ",
            "     ",
            "EEEEE",
            "E   E",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[1]),
            "E   E",
            "E   E",
            "EEEEE",
            "EEEEE",
            "EEEEE",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[2]),
            "EEEEE",
            "E   E",
            "E   E",
            "E   E",
            "E   E",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[3]),
            "     ",
            "     ",
            "E   E",
            "E   E",
            "E   E",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[3]),
            "     ",
            "E   E",
            "E   E",
            "E   E",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[3]),
            "E   E",
            "E   E",
            "E   E",
            "     ",
            "     ",
            'E', material));
    }

    static void createExpertArmorSetRecipes(BaseCraftingManager manager, String materialA, String materialB, IItemProvider... outputs) {
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "EEeEE",
            "e   e",
            "e   e",
            "     ",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "     ",
            "EEeEE",
            "e   e",
            "e   e",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[0]),
            "     ",
            "     ",
            "EEeEE",
            "e   e",
            "e   e",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[1]),
            "e   e",
            "e   e",
            "EEEEE",
            "EeEeE",
            "EEEEE",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[2]),
            "EEEEE",
            "EeeeE",
            "e   e",
            "e   e",
            "e   e",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[3]),
            "E   E",
            "E   E",
            "e   e",
            "     ",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[3]),
            "     ",
            "E   E",
            "E   E",
            "e   e",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(outputs[3]),
            "     ",
            "     ",
            "E   E",
            "E   E",
            "e   e",
            'E', materialA,
            'e', materialB));
    }

    static void createSwordRecipes(BaseCraftingManager manager, IItemProvider output, String material) {
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "    E",
            "    E",
            "    E",
            "    E",
            "    S",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "   E ",
            "   E ",
            "   E ",
            "   E ",
            "   S ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "  E  ",
            "  E  ",
            "  E  ",
            "  E  ",
            "  S  ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            " E   ",
            " E   ",
            " E   ",
            " E   ",
            " S   ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "E    ",
            "E    ",
            "E    ",
            "E    ",
            "S    ",
            'E', material,
            'S', "stickWood"));
    }

    static void createBattleAxeRecipe(BaseCraftingManager manager, IItemProvider output, String material) {
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "E   E",
            "EESEE",
            "E S E",
            "  S  ",
            "  S  ",
            'E', material,
            'S', "stickWood"));
    }

    static void createBowRecipe(BaseCraftingManager manager, IItemProvider output, String material) {
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "  EES",
            " E  S",
            "E   S",
            " E  S",
            "  EES",
            'E', material,
            'S', "string"));
        manager.addRecipe(new BaseShapedOreRecipe(5, new ItemStack(output),
            "SEE  ",
            "S  E ",
            "S   E",
            "S  E ",
            "SEE  ",
            'E', material,
            'S', "string"));
    }
}
