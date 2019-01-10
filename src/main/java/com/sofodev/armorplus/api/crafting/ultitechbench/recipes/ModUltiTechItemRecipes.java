/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.registry.ModItems.itemEnderDragonArrow;
import static com.sofodev.armorplus.registry.ModItems.materials;

public class ModUltiTechItemRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        manager.addRecipe(new ItemStack(itemEnderDragonArrow, 8),
            "   EEEE",
            "     SE",
            "    S E",
            "   S  E",
            "  S    ",
            " S     ",
            "S      ",
            'E', Items.DRAGON_BREATH,
            'S', new ItemStack(materials, 1, 3));
        manager.addRecipe(new ItemStack(itemEnderDragonArrow, 8),
            "EEEE   ",
            "ES     ",
            "E S    ",
            "E  S   ",
            "    S  ",
            "     S ",
            "      S",
            'E', Items.DRAGON_BREATH,
            'S', new ItemStack(materials, 1, 3));
    }
}
