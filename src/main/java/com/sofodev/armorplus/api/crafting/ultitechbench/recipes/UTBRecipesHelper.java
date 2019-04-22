/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static net.minecraft.init.Items.STICK;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class UTBRecipesHelper {

    public static void registerBowRecipes(BaseCraftingManager manager, String materialA, String materialB, Item bow) {
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(bow),
            "    GGS",
            "  GG  S",
            " G    S",
            "G     S",
            " G    S",
            "  GG  S",
            "    GGS",
            'G', materialA,
            'S', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(bow),
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
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(sword),
            "   E   ",
            "  ESE  ",
            " E S E ",
            "E  S  E",
            "   S   ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', STICK));
    }

    public static void registerBattleAxeRecipe(BaseCraftingManager manager, String material, Item battleAxe) {
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(battleAxe),
            " E   E ",
            "E  S  E",
            " EESEE ",
            "E  S  E",
            " E S E ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', STICK));
    }

    public static void registerPickaxeRecipe(BaseCraftingManager manager, String material, Item pickaxe) {
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(pickaxe),
            " EEEEE ",
            "EE S EE",
            "E  S  E",
            "E  S  E",
            "   S   ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', STICK));
    }

    public static void registerEasyArmorSetRecipes(BaseCraftingManager manager, int materialMeta, Object... outputs) {
        manager.addRecipe(getItemStack(outputs[0]),
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "       ",
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[1]),
            "E     E",
            "E     E",
            "E     E",
            "EEEEEEE",
            "EEEEEEE",
            "EEEEEEE",
            "EEEEEEE",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[2]),
            "EEEEEEE",
            "EEEEEEE",
            "E     E",
            "E     E",
            "E     E",
            "E     E",
            "E     E",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "       ",
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            'E', getItemStack(materials, materialMeta));
    }
}
