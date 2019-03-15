/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.registerEasyArmorSetRecipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

public class ModEnderDragonRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        ItemMaterial enderDragonScale = (ItemMaterial) Utils.getItem("ender_dragon_scale");
        switch (getRD()) {
            case EASY: {
                registerEasyArmorSetRecipes(manager, enderDragonScale, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
                break;
            }
            case EXPERT:
            case HELLISH: {
                manager.addRecipe(new ItemStack(enderDragonHelmet),
                    "CEEEEEC",
                    "ES   SE",
                    "E     E",
                    "E     E",
                    "       ",
                    "       ",
                    "       ",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonHelmet),
                    "       ",
                    "CEEEEEC",
                    "ES   SE",
                    "E     E",
                    "E     E",
                    "       ",
                    "       ",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonHelmet),
                    "       ",
                    "       ",
                    "CEEEEEC",
                    "ES   SE",
                    "E     E",
                    "E     E",
                    "       ",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonHelmet),
                    "       ",
                    "       ",
                    "       ",
                    "CEEEEEC",
                    "ES   SE",
                    "E     E",
                    "E     E",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonChestplate),
                    "S     S",
                    "S     S",
                    "E     E",
                    "NEECEEN",
                    "EECSCEE",
                    "EECSCEE",
                    "NEECEEN",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL,
                    'N', Blocks.END_STONE);
                manager.addRecipe(new ItemStack(enderDragonLeggings),
                    "SEEEEES",
                    "ECCSCCE",
                    "EC   CE",
                    "E     E",
                    "E     E",
                    "E     E",
                    "E     E",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonBoots),
                    "S     S",
                    "E     E",
                    "E     E",
                    "C     C",
                    "       ",
                    "       ",
                    "       ",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonBoots),
                    "       ",
                    "S     S",
                    "E     E",
                    "E     E",
                    "C     C",
                    "       ",
                    "       ",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonBoots),
                    "       ",
                    "       ",
                    "S     S",
                    "E     E",
                    "E     E",
                    "C     C",
                    "       ",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(enderDragonBoots),
                    "       ",
                    "       ",
                    "       ",
                    "S     S",
                    "E     E",
                    "E     E",
                    "C     C",
                    'E', new ItemStack(enderDragonScale),
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            }
            break;
        }
    }
}
