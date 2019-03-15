/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class UTBRecipesHelper {

    public static void registerBowRecipes(BaseCraftingManager manager, String materialA, String materialB, Item bow) {
        manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(bow),
            "    GGS",
            "  GG  S",
            " G    S",
            "G     S",
            " G    S",
            "  GG  S",
            "    GGS",
            'G', materialA,
            'S', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(bow),
            "SGG    ",
            "S  GG  ",
            "S    G ",
            "S     G",
            "S    G ",
            "S  GG  ",
            "SGG    ",
            'G', materialA,
            'S', materialB));
    }

    public static void registerSwordRecipe(BaseCraftingManager manager, String material, Item sword) {
        manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(sword),
            "   E   ",
            "  ESE  ",
            " E S E ",
            "E  S  E",
            "   S   ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', Items.STICK));
    }

    public static void registerBattleAxeRecipe(BaseCraftingManager manager, String material, Item sword) {
        manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(sword),
            " E   E ",
            "E  S  E",
            " EESEE ",
            "E  S  E",
            " E S E ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', Items.STICK));
    }

    public static void registerEasyArmorSetRecipes(BaseCraftingManager manager, ItemMaterial material, IItemProvider... outputs) {
        manager.addRecipe(new ItemStack(outputs[0]),
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[0]),
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[0]),
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[0]),
            "       ",
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[0]),
            "       ",
            "       ",
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[1]),
            "E     E",
            "E     E",
            "E     E",
            "EEEEEEE",
            "EEEEEEE",
            "EEEEEEE",
            "EEEEEEE",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[2]),
            "EEEEEEE",
            "EEEEEEE",
            "E     E",
            "E     E",
            "E     E",
            "E     E",
            "E     E",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[3]),
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[3]),
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[3]),
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[3]),
            "       ",
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            'E', new ItemStack(material));
        manager.addRecipe(new ItemStack(outputs[3]),
            "       ",
            "       ",
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            'E', new ItemStack(material));
    }
}
