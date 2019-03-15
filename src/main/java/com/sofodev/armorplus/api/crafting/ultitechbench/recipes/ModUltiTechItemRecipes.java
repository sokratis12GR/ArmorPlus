/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.items.arrows.ItemSpecialArrow;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModUltiTechItemRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        ItemSpecialArrow itemEnderDragonArrow = (ItemSpecialArrow) Utils.getItem("item_ender_dragon_arrow");
        ItemMaterial enderDragonScale = (ItemMaterial) Utils.getItem("ender_dragon_scale");
        manager.addRecipe(new ItemStack(itemEnderDragonArrow, 8),
            "   EEEE",
            "     SE",
            "    S E",
            "   S  E",
            "  S    ",
            " S     ",
            "S      ",
            'E', Items.DRAGON_BREATH,
            'S', new ItemStack(enderDragonScale));
        manager.addRecipe(new ItemStack(itemEnderDragonArrow, 8),
            "EEEE   ",
            "ES     ",
            "E S    ",
            "E  S   ",
            "    S  ",
            "     S ",
            "      S",
            'E', Items.DRAGON_BREATH,
            'S', new ItemStack(enderDragonScale));
    }
}
