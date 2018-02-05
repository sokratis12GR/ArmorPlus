/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;

import static net.thedragonteam.armorplus.registry.ModItems.itemEnderDragonArrow;
import static net.thedragonteam.armorplus.registry.ModItems.materials;

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
