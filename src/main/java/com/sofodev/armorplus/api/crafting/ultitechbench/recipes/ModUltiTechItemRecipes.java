/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.common.registry.constants.APItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.registry.ModItems.*;

public class ModUltiTechItemRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        if (recipes.enableArrowRecipes) {
            this.addArrowRecipes(manager, itemGuardianArrow, Items.PRISMARINE_SHARD, APItems.guardianScale);
            this.addArrowRecipes(manager, itemSuperStarArrow, Items.NETHER_STAR, APItems.witherBone);
            this.addArrowRecipes(manager, itemEnderDragonArrow, Items.DRAGON_BREATH, APItems.enderDragonScale);
        }
    }


    private void addArrowRecipes(BaseCraftingManager manager, Item result, Item top, ItemStack handle) {
        manager.addRecipe(new ItemStack(result, 8),
            "   EEEE",
            "     SE",
            "    S E",
            "   S  E",
            "  S    ",
            " S     ",
            "S      ",
            'E', top,
            'S', handle);
        manager.addRecipe(new ItemStack(result, 8),
            "EEEE   ",
            "ES     ",
            "E S    ",
            "E  S   ",
            "    S  ",
            "     S ",
            "      S",
            'E', top,
            'S', handle);
    }
}
